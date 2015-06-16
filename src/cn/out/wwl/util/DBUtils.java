package cn.out.wwl.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cn.out.wwl.annotations.MyColumn;
import cn.out.wwl.annotations.MyTable;
import cn.out.wwl.annotations.TableId;
import cn.out.wwl.model.Mapping;



/**
 * 
 * @author Downey
 * 
 * utils class
 */
public class DBUtils {
	
	//return insert sql
	public static String insertObj(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		String tableName = getTableName(obj);
		List<Mapping> mappings = getMapping(obj);
		String sql = "insert into "+tableName+" ";
		String values = " values ";
		String front = "(";
		String tail = "(";
		for(int i=0;i<mappings.size();i++)
		{
			Mapping mapping = mappings.get(i);
			if(i == (mappings.size() -1))
			{
				front += mapping.getColumnName()+")";
				tail += "'"+mapping.getColumnValue()+"')";
				break;
			}
			front += mapping.getColumnName()+",";
			tail += "'"+mapping.getColumnValue()+"',";
		}
		sql = sql +front + values + tail;
		System.out.println("SQL:"+sql);
		return sql;
		
	}
	
	//pick up tableName
	public static String getTableName(Object obj)
	{
		
		Class clz = obj.getClass();
		boolean isMyTableAnnotation = clz.isAnnotationPresent(MyTable.class);
		if(isMyTableAnnotation)
		{
			MyTable myTable = (MyTable)clz.getAnnotation(MyTable.class);
			if(!myTable.tableName().equals("undefine"))
			{
				return myTable.tableName().toLowerCase();
			}
		}
		//There is no annotation or annotation @MyTable has no value,chose default,
		//then class name was chosen to be tableName
		return clz.getSimpleName().toLowerCase();
	}
	
	//pick up Mapping
	public static List<Mapping> getMapping(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		List<Mapping> mappings = new ArrayList<Mapping>();
		
		Class clz = obj.getClass();
		Field[] fields = clz.getDeclaredFields();
		for(int i=0;i<fields.length;i++)
		{
			Mapping mapping = new Mapping(); 
			Field field = fields[i];
			boolean isMyColAnnotation = field.isAnnotationPresent(MyColumn.class);
			if(isMyColAnnotation)
			{
				MyColumn myColumn = (MyColumn)field.getAnnotation(MyColumn.class);
				if(!myColumn.columnName().equals("undefine"))
				{
					mapping.setColumnName(myColumn.columnName().toLowerCase());
				}else
				{
					mapping.setColumnName(field.getName().toLowerCase());
				}
			}else
			{
				mapping.setColumnName(field.getName().toLowerCase());
			}
		
			String methodName = "get"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1);
			
			Method[] methods = clz.getDeclaredMethods();
			for(int j=0;j<methods.length;j++)
			{
				for(Method m:methods){  
                    if(methodName.equals(m.getName())){
                        mapping.setColumnValue((String)(m.invoke(obj, null)+""));  
                    }  
                }  
			}
			
			mappings.add(mapping);
		}
		
		return mappings;
	}
	
	
	//pick up TableId field
	public static Field getTableId(Object obj)
	{
		Class clz = obj.getClass();
		Field[] fields = clz.getDeclaredFields();
		Field idField = null;
		int amount = 0;//if there are more than one idField, return null
		for(int i=0;i<fields.length;i++)
		{
			Field field = fields[i];
			boolean isTableIdAnnotation = field.isAnnotationPresent(TableId.class);
			if(isTableIdAnnotation)
			{
				amount ++;
				idField = field;
			}
		}
		
		if(amount == 1)
		{
			return idField;
		}else
		{
			return null;
		}
	}
	
	//return delete sql
	public static String deleteObj(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		String tableName = getTableName(obj);
		List<Mapping> mappings = getMapping(obj);
		String sql = "delete from "+tableName+" where ";
		String temp = "";
		for(int i=0;i<mappings.size();i++)
		{
			Mapping mapping = mappings.get(i);
			if(i == (mappings.size() -1))
			{
				temp += mapping.getColumnName()+" = '"+mapping.getColumnValue()+"'";
				break;
			}
			temp += mapping.getColumnName()+" = '"+mapping.getColumnValue()+"' and ";
		}
		
		sql = sql +temp;
		System.out.println("delete sql:"+sql);
		return sql;
	}
	
	//return update sql
	public static String updateObj(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Class clz = obj.getClass();
		Field idField = getTableId(obj);
		if(idField == null)
		{
			return "";
		}
		String tableName = getTableName(obj);
		List<Mapping> mappings = getMapping(obj);
		String sql = "update "+tableName+" set ";
		
		String whereClause;
		String id;
		String idValue = "";
		TableId tableId = (TableId)idField.getAnnotation(TableId.class);
		if(!tableId.value().equals("undefine"))
		{
			id = tableId.value();
		}else
		{
			id = idField.getName().toLowerCase();
		}
		
		String methodName = "get"+idField.getName().substring(0, 1).toUpperCase()+idField.getName().substring(1);
		
		Method[] methods = clz.getDeclaredMethods();
		for(int j=0;j<methods.length;j++)
		{
			for(Method m:methods){  
                if(methodName.equals(m.getName())){
                   idValue = ((String)(m.invoke(obj, null)+""));  
                }  
            }  
		}
		whereClause = "where "+id+" = '"+idValue+"'";
		
		String middleClause = "";
		for(int i=0;i<mappings.size();i++)
		{
			Mapping mapping = mappings.get(i);
			if(i == (mappings.size() -1))
			{
				middleClause += mapping.getColumnName()+" = '"+mapping.getColumnValue()+"' ";
				break;
			}
			if(!mapping.getColumnName().equals(idField.getName().toLowerCase()))
			{
				middleClause += mapping.getColumnName()+" = '"+mapping.getColumnValue()+"',";
			}
			
		}
		
		sql = sql +middleClause + whereClause;
		System.out.println("sql:"+sql);
		return sql;
	}
	
}
