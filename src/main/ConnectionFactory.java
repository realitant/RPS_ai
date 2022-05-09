package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import main.ConnectionFactory;

public class ConnectionFactory {
	private static final ConnectionFactory connectionFactory = new ConnectionFactory();
	private Properties prop = new Properties();
	
	static {
		try
		{
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	//singleton constructor
	private ConnectionFactory()
	{
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			prop.load(loader.getResourceAsStream("main/main.resources/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ConnectionFactory getInstance()
	{
		//if(connectionFactory == null) connectionFactory=new ConnectionFactory();
		return connectionFactory;
	}
	
	public Connection getConnection()
	{
		Connection conn = null;
		
		try
		{
			conn=DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("username"),
					prop.getProperty("password"));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}
