/**
 * 
 */
package main;

import java.awt.geom.CubicCurve2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author NGAL version 22.03.2012
 */
public class testCreate {
	static String currentURL = "jdbc:mysql://localhost/person_db";
	static String user = "root";
	static String pass = "";

	public static void main(String[] args) throws SQLException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection(currentURL, user, pass);
		Statement statement = conn.createStatement();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				new File("dump.sql")));
		String veryBigQuery = "";
		String str;
		while ((str = bufferedReader.readLine()) != null) {
			System.out.println(str);
			veryBigQuery += str + "\n";
		}
		 System.out.println(veryBigQuery);
		 statement.execute(veryBigQuery);
	}
}
