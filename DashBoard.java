package com.bridgelab.jdbcWS;

import java.sql.*;
import java.util.Scanner;

public class DashBoard {
	
	public static BankUserData getBankUser() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the name");
		String name = scanner.next();
		System.out.println("Enter the ifsccode");
		String ifsccode = scanner.next();
		System.out.println("Enter the totalamount");
		int totalamount = scanner.nextInt();
		BankUserData bankuserNew = new BankUserData(name, ifsccode, totalamount);
		return bankuserNew;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int choice =-1;
		try {
//			Loading the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver is loaded");
			
//			Giving database details and url
			final String databasename="bank";
			String url = "jdbc:mysql://localhost:3306/"+databasename;
			System.out.println("Database url="+url);
			
//			Providing username and password
			final String username="root";
			final String password="amit123456";
			
//			Connecting to database
			Connection connection = DriverManager.getConnection(url,username,password);
			System.out.println("Database is Connected");
			
			Statement statement = connection.createStatement();
			
			do {
				System.out.println("1.Insert");
				System.out.println("2.Display");
				System.out.println("3.Query For Amit");
				System.out.println("Enter the choice=");
				choice=scan.nextInt();
				
				switch(choice) {
				case 1:
//					Insert data into database
					String query = "insert into bankusers(name,ifsccode,totalamount) values(?,?,?)";
					PreparedStatement preparestatement = connection.prepareStatement(query);
					BankUserData bankuserI = getBankUser();
					preparestatement.setString(1, bankuserI.getName());
					preparestatement.setString(2, bankuserI.getIfsccode());
					preparestatement.setInt(3, bankuserI.getTotalamount());
					
					preparestatement.execute();
					break;
				case 2:
//					Display data 
					ResultSet result = statement.executeQuery("select * from bankusers");
					
					while(result.next()) {
						System.out.println("**********************************");
						System.out.println("Account Number="+result.getString("accountnumber"));
						System.out.println("Name="+result.getString("name"));
						System.out.println("IFSC Code="+result.getString("ifsccode"));
						System.out.println("Total Amount="+result.getInt("totalamount"));
						System.out.println("**********************************");
					}
					break;
				case 3:
//					Updating ifscode
					Statement statementU = connection.createStatement();
					ResultSet resultSetU = statement.executeQuery("select * from bankusers");
					String queryU = "update bankusers set ifsccode=? where accountnumber=?";
					System.out.println("Enter the new ifsccode");
					String ifsccode=scan.next();
					System.out.println("Enter the accountnumber");
					int accnumb = scan.nextInt();
//					BankUserData bankuserU = getBankUser();
					while(resultSetU.next()) {
						if(resultSetU.getInt("accountnumber")==accnumb) {
							PreparedStatement preparedStatementU = connection.prepareStatement(queryU);
							preparedStatementU.setString(1, ifsccode);
							preparedStatementU.setInt(2, accnumb);
							
							preparedStatementU.executeUpdate();
						}
					}
				}
			}while(choice!=0);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
