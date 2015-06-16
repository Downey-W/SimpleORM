package cn.out.wwl.model;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.out.wwl.exception.NoTableIdException;
import cn.out.wwl.util.DBUtils;

public class DBHelper extends Helper{

	//construtor
	public DBHelper(String connections,String dbusername,String dbpassword)
	{
		super(connections,dbusername,dbpassword);
	}
	
	//insert into database
	public void insert(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, InstantiationException, ClassNotFoundException
	{
		inits();
		String sql = DBUtils.insertObj(obj);
		stmt.executeUpdate(sql);
		free();
	}
	
	//delete from database
	public void delete(Object obj) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IllegalArgumentException, InvocationTargetException
	{
		inits();
		String sql = DBUtils.deleteObj(obj);
		stmt.executeUpdate(sql);
		free();
	}
	
	//update from database
	public void update(Object obj) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IllegalArgumentException, InvocationTargetException, NoTableIdException
	{
		inits();
		String sql = DBUtils.updateObj(obj);
		if(sql.equals(""))
		{
			free();
			throw new NoTableIdException();
		}else
		{
			stmt.executeUpdate(sql);
			free();
		}
		
	}
	
	//select from database
	public List<ResultObj> select(String sql) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		List<ResultObj> resultObj = new ArrayList<ResultObj>();
		inits();
		rs = stmt.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();
		int amount = rsmd.getColumnCount();
		while(rs.next())
		{
			ResultObj ro = new ResultObj(amount);
			for(int i=0;i<amount;i++)
			{
				ro.set(i, rs.getObject(i+1));
			}
			resultObj.add(ro);
		}
		
		free();
		return resultObj;
	}
	
	
}
