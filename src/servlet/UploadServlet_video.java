package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

public class UploadServlet_video extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UploadServlet_video() {
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

		doPost(request, response);
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
				//�����ϴ��ļ�����·��
				String filePath = getServletContext().getRealPath("/")+"videos";
				String url = null;
				String lesson_id = request.getParameter("lesson_id");//ʼ�ճ���lesson_id
				String lesson_name = request.getParameter("lesson_name");
				System.out.println("servlet-----"+"lesson_name" + lesson_name);
				File file = new File(filePath);
				if(!file.exists())//����������򴴽�
				{
					file.mkdir();
				}
				//System.out.println(filePath);
				SmartUpload su = new SmartUpload();
				//��ʼ������
				su.initialize(getServletConfig(), request, response);
				//�����ϴ��ļ���С
				su.setMaxFileSize(1024*1024*1024);
				//���������ļ��Ĵ�С
				//su.setTotalMaxFileSize(1024*1024*100);
				//�����ϴ��ļ�����
				su.setAllowedFilesList("mp4");
				
				String result = "�ϴ��ɹ��������ظ��ϴ���ֹ����";
				try {
					//���ý�ֹ�ϴ��ļ�����
					su.setDeniedFilesList("rar,jsp,js");
					
					//�ϴ��ļ�
					su.upload();
					int count = su.save(filePath);
					System.out.println("�ϴ��ɹ�"+ count + "���ļ�");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					result = "�ϴ�ʧ�ܣ�";
					if(e.getMessage().indexOf("1015") != -1){
						result = "�ϴ�ʧ�ܣ��ϴ���Ƶ���Ͳ���ȷ�����ϴ�mp4!";
					}else if(e.getMessage().indexOf("1010") != -1){
						result = "�ϴ�ʧ�ܣ��ϴ���Ƶ���Ͳ���ȷ�����ϴ�mp4";
					}else if(e.getMessage().indexOf("1110") != -1){
						result = "�ϴ�ʧ�ܣ��ϴ���Ƶ��С���������ϴ������ֵ��";
					}else if(e.getMessage().indexOf("1105") != -1){
						result = "�ϴ�ʧ�ܣ��ϴ���Ƶ���ܴ�С���������ϴ��ܴ�С�����ֵ��";
					}
					
					e.printStackTrace();
				} 
				//����ϴ��ļ���Ϣ
				
				for(int i = 0; i < su.getFiles().getCount(); i++)
				{
					com.jspsmart.upload.File tempFile = su.getFiles().getFile(i);
					System.out.println("------------------------");
					System.out.println("�����е�name��ֵ:" + tempFile.getFieldName());
					System.out.println("�ϴ��ļ���:" + tempFile.getFileName());
					System.out.println("�ϴ��ļ��Ĵ�С:" + tempFile.getSize());
					System.out.println("�ϴ��ļ�����չ����" + tempFile.getFileExt());
					System.out.println("�ϴ��ļ���ȫ����" + tempFile.getFilePathName());
					System.out.println("------------------------");
					url = tempFile.getFilePathName();
				}
				System.out.println(System.getProperty("file.encoding"));
				
				String myurl =  "/JSP/Teacher/adminAddVideo.jsp?lesson_id="+lesson_id+"&lesson_name"+lesson_name;
				//String myurl =  "/JSP/Teacher/adminAddVideo.jsp?lesson_name="+lesson_name;
				request.getSession().setAttribute("video_url", url.toString());//���û����洢���Ự��
				request.setAttribute("result", result + url);
				request.getRequestDispatcher(myurl).forward(request, response);
				//response.sendRedirect(request.getContextPath()+"/login_failure.jsp");
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
