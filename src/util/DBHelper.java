package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
	//������
	private static final String driver = "com.mysql.jdbc.Driver";
	//�������ݿ��URL��ַ
	private static final String url = "jdbc:mysql://localhost:3306/video_show?useUnicode = true & characterEncoding = UTF-8";
	private static final String username = "root";
	private static final String password = "123";
	//����������
	private static Connection conn = null;
	//��̬����鸺���������
	static 
	{
		try
		{
			Class.forName(driver);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//����ģʽ�������ݿ����Ӷ���
	public static Connection getConnection() throws Exception
	{
		if(conn == null)//���û�д���,�򴴽�����
		{
			conn = DriverManager.getConnection(url, username, password);
		}
		return conn;
	}
	
	public static void main(String[] args) {
	
		try 
		{
			Connection conn = DBHelper.getConnection();
			if(conn != null)
			{
				System.out.println("���ݿ�����������");
			}
			else
			{
				System.out.println("���ݿ������쳣��");
			}
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
