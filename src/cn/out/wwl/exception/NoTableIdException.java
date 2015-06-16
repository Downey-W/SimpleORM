package cn.out.wwl.exception;

public class NoTableIdException extends Exception{
	
	public NoTableIdException()
	{
		super("There is no @TableId in your POJO when you want to update an object");
	}
}
