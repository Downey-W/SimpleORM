package cn.out.wwl.main;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import cn.out.wwl.exception.NoTableIdException;
import cn.out.wwl.model.DBHelper;
import cn.out.wwl.model.ResultObj;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Insert
		DBHelper dbhelper = new DBHelper("connections", "dbusername", "dbpassword");
//		Student stu1 = new Student();
//		stu1.setHobby("hobby1");
//		stu1.setId(1);
//		stu1.setName("name1");
//		try {
//			dbhelper.insert(stu1);
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		
		//delete
//		Student stu2 = new Student();
//		stu2.setHobby("hobby2");
//		stu2.setId(2);
//		stu2.setName("name2");
//		try {
//			dbhelper.delete(stu2);
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		//update 
//		Student stu3 = new Student();
//		stu3.setHobby("hooby3");
//		stu3.setId(3);
//		stu3.setName("name3");
//		try {
//			dbhelper.update(stu3);
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoTableIdException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	
		
		//select 
		//List<ResultObj> : All records
		List<ResultObj> resultObj;
		try {
			resultObj = dbhelper.select("select * from table");
			for(int i=0;i<resultObj.size();i++)
			{
				ResultObj ro = resultObj.get(i);
				int amount = ro.getSize();//amount: the amount of properties
				for(int j=0;j<amount;j++)
				{
					Object obj = ro.get(j);// get  every properties..
				}
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
