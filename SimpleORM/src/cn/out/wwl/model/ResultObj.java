package cn.out.wwl.model;

public class ResultObj {
	
	private Object[] obj;
	
	public ResultObj(int size)
	{
		obj = new Object[size];
	}
	
	public void set(int i,Object o)
	{
		obj[i] = o;
	}
	
	public Object get(int i)
	{
		return obj[i];
	}
	
	public int getSize()
	{
		return obj.length;
	}
}
