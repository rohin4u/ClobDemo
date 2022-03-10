package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
		
		String driver="oracle.jdbc.OracleDriver";
		Class.forName(driver);
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="system";
		String pwd="password";
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		
		String sqlQuery="insert into cities values(?,?)";
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setString(1,"Hyderabad");
		
		File f = new File("data.txt");
		FileReader fr = new FileReader(f);
		ps.setCharacterStream(2,fr);
		System.out.println("file is inserting from :"+f.getAbsolutePath());
		int updateCount=ps.executeUpdate();
		if(updateCount==1)
		{
		System.out.println("Record Inserted");
		}
		else
		{
		System.out.println("Record Not Inserted");
		}

	}

}
