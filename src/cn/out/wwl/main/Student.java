package cn.out.wwl.main;

import cn.out.wwl.annotations.MyColumn;
import cn.out.wwl.annotations.MyTable;
import cn.out.wwl.annotations.TableId;


//if you use @MyTable or don't use annotation,default tableName is your (Class name).toLowerCase()
//
@MyTable(tableName = "tableName")
public class Student {
	
	@TableId(value = "key")//"key" as key in table.default "id" as key¡£ 
	private int id;
	@MyColumn(columnName = "colName")//colName as column 
	private String name;
	@MyColumn     		//default "hobby" as column 
	private String hobby;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
}
