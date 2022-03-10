package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		String driver="oracle.jdbc.OracleDriver";
		Class.forName(driver);
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="system";
		String pwd="password";
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		
		PreparedStatement ps = con.prepareStatement("select * from cities");
		
		ResultSet rs =ps.executeQuery();
		FileWriter fw = new FileWriter("delhi.txt");
		if(rs.next())
		{
		String name=rs.getString(1);
		Reader r = rs.getCharacterStream(2);
		/*char[] buffer = new char[1024];
		while(r.read(buffer)>0)
		{
		fw.write(buffer);
		}*/
		int i= r.read();
		while(i != -1)
		{
		fw.write(i);
		i = r.read();
		}
		fw.flush();
		System.out.println("Retrieved Successfully file :delhi.txt");
		}
		con.close();
	}
}
