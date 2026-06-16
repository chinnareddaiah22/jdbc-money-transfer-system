package com.JDBC1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BankTransactionSystem {
	
	private static final int PIN=123123;
	
	private static String url="jdbc:oracle:thin:@localhost:1521:orcl";
	private static String username="scott";
	private static String password="tiger";
	

	public static void main(String[] args) {
		
		
		 Connection con=null;
		 Scanner scan=null;
		 
		 String checkAccountQuery="SELECT balance from account where acc_no=?";
		 String debitQuery="update account set balance=balance-? where acc_no=?";
		 String creditQuery="update account set balance=balance+? where acc_no=?";
		 
		try {
			//STEP 1: LOAD THE DRIVER CLASS
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loaded ");
			
			//STEP 2 : ESTABLISH THE CONNECTION
			con=DriverManager.getConnection(url,username,password);
			System.out.println("Connection Established");
			
			//STEP 3: START TRANSCATION
			con.setAutoCommit(false);
			
			//STEP 4: TAKE INPUT FROM THE USER 
			scan=new Scanner(System.in);
			
			System.out.println("Enter sender acc_no : ");
			int senderAccNo=scan.nextInt();
			
			System.out.println("Enter reciver acc_no : ");
			int reciverAccNo=scan.nextInt();
			
			System.out.println("Enter amount : ");
			int amount=scan.nextInt();
			
			// STEP 5: VALID AMOUNT OR NOT 
			if(amount<=0) {
				System.out.println("Invalid Amount ! ");
				con.rollback();
				return; // immediately stop the program return 
			}
			
			//STEP 6: CHECK SENDER ACCOUNT AND BALANCE
			PreparedStatement checkSender=con.prepareStatement(checkAccountQuery);
			checkSender.setInt(1, senderAccNo);
			ResultSet senderRs=checkSender.executeQuery();
			
			int senderBalance=0;
			if(senderRs.next()) {
				senderBalance=senderRs.getInt("Balance");
			}else {
				System.out.println("Invalid sender account number ");
				con.rollback();
				return;
			}
			
			//STEP 7: CHECK RECIVER ACCOUNT 
			PreparedStatement checkReciver=con.prepareStatement(checkAccountQuery);
			checkReciver.setInt(1, reciverAccNo);
			ResultSet reciverRs=checkReciver.executeQuery();
			
			if(!reciverRs.next()) {
				System.out.println("Invalid reciver account Number ");
				con.rollback();
				return;
			}
			
			//STEP 8: CHECK SUFFICIENT BALANCE
			if(senderBalance<amount) {
				System.out.println("Insufficient Balance");
				con.rollback();
				return;
			}
			
			//STEP 9: CHECK PIN
			System.out.println("Enter Pin : ");
			int userPin=scan.nextInt();
			
			if(userPin !=PIN) {
				System.out.println("Invalid PIN");
				con.rollback();
				return;
			}
			
			//STEP 10: DEBIT AMOUNT FROM SENDER
			PreparedStatement debitPstmt=con.prepareStatement(debitQuery);
			debitPstmt.setInt(1,amount);
			debitPstmt.setInt(2,senderAccNo);
			int debitRows=debitPstmt.executeUpdate();
			
			//STEP 11: CREDIT AMOUNT TO RECIVER
			PreparedStatement creditPstmt=con.prepareStatement(creditQuery);
			creditPstmt.setInt(1,amount);
			creditPstmt.setInt(2,reciverAccNo);
			int creditRows=creditPstmt.executeUpdate();
			
			//STEP 12: COMMIT OR ROLLBACK
			if(debitRows == 1 && creditRows ==1) {
				con.commit();
				System.out.println("Money Transfered Succesfully");
			}else {
				con.rollback();
				System.out.println("Transaction Failed");
			}
			
			//STEP 13: CLOSE JDBC OBJECTS
			senderRs.close();
			reciverRs.close();
			checkSender.close();
			checkReciver.close();
			debitPstmt.close();
			creditPstmt.close();
			
		} catch (Exception e) {
			try {
				if(con!=null) {
					con.rollback();
				}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				if(scan!=null) {
					scan.close();
				}
				if(con!=null) {
					con.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
}
