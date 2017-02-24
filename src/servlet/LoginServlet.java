package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBDAO;
import entity.Student;


public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);// ��get��������doPost����
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");//��ֹ������������
		response.setCharacterEncoding("UTF-8");//��ֹ�����������
		response.setContentType("text/html;charset=utf-8");//��ֹ�����������
		PrintWriter out = response.getWriter();
		
		/*
		 * ��֤�û����Լ�����ģ��
		 * */
		
		DBDAO loginDao = new DBDAO();//�½��߼���
		ArrayList<Student> list = loginDao.getAllStu();//��ȡUser��ArrayList
		String username = request.getParameter("username");//��ȡ������û���
		String password = request.getParameter("password");//��ȡ���������
		int count = 0;////ͳ�Ʊ������
		boolean flag = false;//����Ƿ���й���ת
		if(list != null && list.size() > 0)
		{
			for(int i = 0; i < list.size(); i++)//��������List
			{
				count++;//ͳ�Ʊ������
				Student student = list.get(i);//���list�е�user��
				if(student.getStu_name().equals(username))//������ϢУ��
				{
					if(student.getStu_password().equals(password))
					{
						request.getSession().setAttribute("username", username.toString());//���û����洢���Ự��
						request.getSession().setAttribute("userid", student.getId());//���û�id�洢���Ự��
						response.sendRedirect(request.getContextPath()+"/JSP/Video_Square.jsp");
						flag = true;
						break;
					}
					else
					{
						response.sendRedirect(request.getContextPath()+"/JSP/login_failure.jsp");
						flag = true;
						break;
					}
				}
			}
			if(count >= list.size() && !flag)
			{
				response.sendRedirect(request.getContextPath()+"/JSP/login_failure.jsp");
			}
		}
		else
		{
			response.sendRedirect(request.getContextPath()+"/JSP/login_failure.jsp");
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
