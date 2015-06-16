package cn.out.wwl.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Helper {
	
	protected Connection conn = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;
	
	private String connections = null;
	private String dbusername = null;
	private String dbpassword = null;
	
	public Helper(String connections,String dbusername,String dbpassword)
	{
		this.connections = connections;
		this.dbusername = dbusername;
		this.dbpassword = dbpassword;
	}
	
	public void inits() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = java.sql.DriverManager.getConnection(connections,dbusername,dbpassword);
		stmt = conn.createStatement();
	}
	
	public void free() throws SQLException
	{
		//close connection
		stmt.close();
		conn.close();
	}
}
