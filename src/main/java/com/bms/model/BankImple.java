package com.bms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bms.Connection.ProvideConnection;
import com.bms.DTO.Customer;

public class BankImple {
		public static boolean createAccount(String name,String email,long phone, String pass) throws ClassNotFoundException, SQLException {
			Connection con=ProvideConnection.connectionKodi();
			String iqry="insert into bank_customer(name,email,phone,password) values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(iqry);
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setLong(3, phone);
			ps.setString(4, pass);
			
			int r=ps.executeUpdate();
			if(r>0) {
				return true;
			}
			else {
				return false;
			}
		}
		
		public static Customer login(String email,String pass) throws ClassNotFoundException, SQLException {
			Connection con=ProvideConnection.connectionKodi();
			String sqry="select * from bank_customer where email=? and password=?";
			PreparedStatement ps=con.prepareStatement(sqry);
			ps.setString(1, email);
			ps.setString(2, pass);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				int accNo=rs.getInt(1);
				String name=rs.getString(2);
				String email1=rs.getString(3);
				long phone=rs.getLong(4);
				double  bal=rs.getDouble(5);
				String passw=rs.getString(6);
				
				Customer c=new Customer();
				c.setAccNum(accNo);
				c.setName(name);
				c.setEmail(email1);
				c.setPhone(phone);
				c.setBal(bal);
				c.setPass(passw);
				return c;
			}
			else {
				return null;
			}

		}
		
		public static boolean deposit(int accNum, double amount)
		        throws ClassNotFoundException, SQLException {

		    Connection con = ProvideConnection.connectionKodi();

		    String sql =
		        "UPDATE bank_customer SET bal = bal + ? WHERE accNum = ?";

		    PreparedStatement ps = con.prepareStatement(sql);

		    ps.setDouble(1, amount);
		    ps.setInt(2, accNum);

		    int result = ps.executeUpdate();

		    return result > 0;
		}
		
		public static double getBalance(int accNum)
		        throws ClassNotFoundException, SQLException {

		    Connection con = ProvideConnection.connectionKodi();

		    String sql =
		        "SELECT bal FROM bank_customer WHERE accNum = ?";

		    PreparedStatement ps = con.prepareStatement(sql);

		    ps.setInt(1, accNum);

		    ResultSet rs = ps.executeQuery();

		    if (rs.next()) {
		        return rs.getDouble("bal");
		    }

		    return 0;
		}
		
		public static boolean withdraw(int accNum, double amount)
		        throws ClassNotFoundException, SQLException {

		    Connection con = ProvideConnection.connectionKodi();

		    // First check the current balance
		    String checkSql =
		        "SELECT bal FROM bank_customer WHERE accNum = ?";

		    PreparedStatement checkPs =
		        con.prepareStatement(checkSql);

		    checkPs.setInt(1, accNum);

		    ResultSet rs = checkPs.executeQuery();

		    if (rs.next()) {

		        double currentBalance =
		                rs.getDouble("bal");

		        // Not enough money
		        if (currentBalance < amount) {
		            return false;
		        }

		    } else {

		        return false;
		    }

		    // Withdraw the amount
		    String sql =
		        "UPDATE bank_customer SET bal = bal - ? WHERE accNum = ?";

		    PreparedStatement ps =
		        con.prepareStatement(sql);

		    ps.setDouble(1, amount);
		    ps.setInt(2, accNum);

		    int result = ps.executeUpdate();

		    return result > 0;
		}
		
		
		public static boolean transfer(
		        int senderAccNum,
		        int receiverAccNum,
		        double amount)
		        throws ClassNotFoundException, SQLException {

		    Connection con = ProvideConnection.connectionKodi();

		    try {

		        // Start transaction
		        con.setAutoCommit(false);

		        // Check sender balance
		        String senderSql =
		                "SELECT bal FROM bank_customer WHERE accNum = ?";

		        PreparedStatement senderPs =
		                con.prepareStatement(senderSql);

		        senderPs.setInt(1, senderAccNum);

		        ResultSet senderRs =
		                senderPs.executeQuery();

		        if (!senderRs.next()) {

		            con.rollback();
		            return false;
		        }

		        double senderBalance =
		                senderRs.getDouble("bal");

		        // Check sufficient balance
		        if (senderBalance < amount) {

		            con.rollback();
		            return false;
		        }

		        // Check receiver exists
		        String receiverSql =
		                "SELECT accNum FROM bank_customer WHERE accNum = ?";

		        PreparedStatement receiverPs =
		                con.prepareStatement(receiverSql);

		        receiverPs.setInt(1, receiverAccNum);

		        ResultSet receiverRs =
		                receiverPs.executeQuery();

		        if (!receiverRs.next()) {

		            con.rollback();
		            return false;
		        }

		        // Deduct from sender
		        String deductSql =
		                "UPDATE bank_customer " +
		                "SET bal = bal - ? " +
		                "WHERE accNum = ?";

		        PreparedStatement deductPs =
		                con.prepareStatement(deductSql);

		        deductPs.setDouble(1, amount);
		        deductPs.setInt(2, senderAccNum);

		        int deductResult =
		                deductPs.executeUpdate();

		        if (deductResult == 0) {

		            con.rollback();
		            return false;
		        }

		        // Add to receiver
		        String addSql =
		                "UPDATE bank_customer " +
		                "SET bal = bal + ? " +
		                "WHERE accNum = ?";

		        PreparedStatement addPs =
		                con.prepareStatement(addSql);

		        addPs.setDouble(1, amount);
		        addPs.setInt(2, receiverAccNum);

		        int addResult =
		                addPs.executeUpdate();

		        if (addResult == 0) {

		            con.rollback();
		            return false;
		        }

		        // Everything successful
		        con.commit();

		        return true;

		    } catch (SQLException e) {

		        con.rollback();

		        throw e;

		    } finally {

		        con.setAutoCommit(true);
		        con.close();
		    }
		}
		
}
