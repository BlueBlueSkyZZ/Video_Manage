package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.File_list;
import entity.Lesson_list;
import entity.Student;
import entity.Teacher;
import entity.Video;

import util.DBHelper;

public class DBDAO {
	public ArrayList<Student> getAllStu() {
		Connection conn = null;
		PreparedStatement stmt = null;// ���Ӷ���
		ResultSet rs = null;// ���ݼ�
		ArrayList<Student> list = new ArrayList<Student>();// �û�����
		try {
			conn = DBHelper.getConnection();// ������Ӷ���
			String sql = "select * from student";// sql����������û�
			stmt = conn.prepareStatement(sql);// �������Ӷ���
			rs = stmt.executeQuery();// ���һ�����ݼ�
			
			while (rs.next()) {
				// ��ʼ������
				Student student = new Student();
				student.setId(rs.getInt("id"));// �����û�id
				student.setStu_name(rs.getString("stu_name"));// �����û�����
				student.setStu_password(rs.getString("stu_password"));// �����û�����
				list.add(student);// ��һ����Ʒ���뼯��
			}
			return list;// ���ؼ���

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null)// �ͷ��ڴ�
			{
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	
	public void addStuToDB(String username, String password)// �����û����ݵ����ݿ�
	{
		Connection conn = null;
		PreparedStatement stmt = null;// ���Ӷ���
		try {
			conn = DBHelper.getConnection();// ������Ӷ���

			String sql = "INSERT INTO student VALUES(NULL, ?, ?)";// sql���ע���û�
			stmt = conn.prepareStatement(sql);// �������Ӷ���,ʹ��PreparedStatement�������ӿ�ά����
			stmt.setString(1, username);
			stmt.setString(2, password);
			int i = stmt.executeUpdate();
			System.out.println("�ɹ������" + i + "��");

		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		} finally {
			// �ͷ����ݼ�����
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null)// �ͷ��ڴ�
			{
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList<Teacher> getAllTea() {
		Connection conn = null;
		PreparedStatement stmt = null;// ���Ӷ���
		ResultSet rs = null;// ���ݼ�
		ArrayList<Teacher> list = new ArrayList<Teacher>();// �û�����
		try {
			conn = DBHelper.getConnection();// ������Ӷ���
			String sql = "select * from teacher";// sql����������û�
			stmt = conn.prepareStatement(sql);// �������Ӷ���
			rs = stmt.executeQuery();// ���һ�����ݼ�
			
			while (rs.next()) {
				// ��ʼ������
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt("id"));// �����û�id
				teacher.setTea_name(rs.getString("tea_name"));// �����û�����
				teacher.setTea_password(rs.getString("tea_password"));// �����û�����
				list.add(teacher);//��һ����Ʒ���뼯��
			}
			return list;// ���ؼ���

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null)// �ͷ��ڴ�
			{
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList<File_list> getAllFile() {
		Connection conn = null;
		PreparedStatement stmt = null;// ���Ӷ���
		ResultSet rs = null;// ���ݼ�
		ArrayList<File_list> list = new ArrayList<File_list>();// �û�����
		try {
			conn = DBHelper.getConnection();// ������Ӷ���
			String sql = "select * from file_list";// sql����������ļ�
			stmt = conn.prepareStatement(sql);// �������Ӷ���
			rs = stmt.executeQuery();// ���һ�����ݼ�
			
			while (rs.next()) {
				// ��ʼ������
				
				File_list file = new File_list();
				file.setId(rs.getInt("id"));// �����ļ�id
				file.setTeacher_id(rs.getInt("teacher_id"));// ������ʦid
				file.setLesson_id(rs.getString("lesson_id"));// ���ÿγ�����
				file.setFile_data(rs.getString("file_data"));//�����ļ��ο�����
				file.setFile_name(rs.getString("file_name"));//�����ļ�����
				file.setFile_id(rs.getString("file_id"));//�����ļ�id
				file.setFile_info(rs.getString("file_info"));//�����ļ����
				file.setFile_url(rs.getString("file_url"));//�����ļ�url
				
				
				list.add(file);// ��һ���ļ����뼯��
			}
			return list;// ���ؼ���

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null)// �ͷ��ڴ�
			{
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	//��ʦ�γ��б���ʾ
		public ArrayList<Lesson_list> SearchLesson(int teacher_id){
			Connection conn = null;
			PreparedStatement stmt = null;// ���Ӷ���
			ResultSet rs = null;
			ArrayList<Lesson_list> list = new ArrayList<Lesson_list>();// �û�����
			try {
				conn = DBHelper.getConnection();// ������Ӷ���

				String sql = "select lesson_id,lesson_name,lesson_info from lesson_list where teacher_id=?";// sql���ע���û�
				stmt = conn.prepareStatement(sql);// �������Ӷ���,ʹ��PreparedStatement�������ӿ�ά����
				stmt.setInt(1, teacher_id);
				rs = stmt.executeQuery();//���ؽ����
				
				while (rs.next()) {
					// ��ʼ������
					Lesson_list less = new Lesson_list();
					less.setLesson_id(rs.getString("lesson_id"));// ���ÿγ̱���
					less.setLesson_name(rs.getString("lesson_name"));// ���ÿγ�����
					less.setLesson_info(rs.getString("lesson_info"));// ���ÿγ���Ϣ
					list.add(less);//����ʦ�Ŀγ̼��뼯��
				}
				
				return list;
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			} finally {
				// �ͷ����ݼ�����
				if (stmt != null) {
					try {
						stmt.close();
						stmt = null;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				// �ͷ�������
				if (stmt != null)// �ͷ��ڴ�
				{
					try {
						stmt.close();
						stmt = null;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	
	public ArrayList<Video> getAllVideo() {
		Connection conn = null;
		PreparedStatement stmt = null;// ���Ӷ���
		ResultSet rs = null;// ���ݼ�
		ArrayList<Video> list = new ArrayList<Video>();// �û�����
		try {
			conn = DBHelper.getConnection();// ������Ӷ���
			String sql = "select * from video_list";// sql�����������Ƶ
			stmt = conn.prepareStatement(sql);// �������Ӷ���
			rs = stmt.executeQuery();// ���һ�����ݼ�
			
			while (rs.next()) {
				// ��ʼ������
				Video video = new Video();
				video.setId(rs.getInt("id"));// ������Ƶid
				video.setTeacher_id(rs.getInt("teacher_id"));// ������ʦid
				video.setLesson_id(rs.getString("lesson_id"));// ���ÿγ�����
				video.setVideo_data(rs.getString("video_data"));//������Ƶ�ο�����
				video.setVideo_name(rs.getString("video_name"));//������Ƶ����
				video.setVideo_id(rs.getString("video_id"));//������Ƶid
				video.setVideo_info(rs.getString("video_info"));//������Ƶ���
				video.setVideo_url(rs.getString("video_url"));//������Ƶurl
				
				
				list.add(video);// ��һ����Ƶ���뼯��
			}
			return list;// ���ؼ���

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null)// �ͷ��ڴ�
			{
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	public void deleteVideo(String video_id)
	{
		Connection conn = null;
		PreparedStatement stmt = null;//���Ӷ���
		try
		{
			conn = DBHelper.getConnection();//������Ӷ���
			String sql = "DELETE FROM video_list WHERE video_id = '" + video_id + "'";//sql����������û�,idΪString��ʽ������ֱ�Ӽ���sql���
			stmt = conn.prepareStatement(sql);//�������Ӷ���
			/*String sql2 = "DELETE FROM comment WHERE articleid = '" + id + "'";//ɾ�����µ�ͬʱɾ������
			stmt = conn.prepareStatement(sql2);*/
			int i = stmt.executeUpdate(sql);
			//int j = stmt.executeUpdate(sql2);
			System.out.println(sql);
			//System.out.println(sql2);
			System.out.println("�ɹ�ɾ��" + i + "����Ƶ");
			//System.out.println("�ɹ�ɾ��" + j + "ƪ����");
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		finally
		{
			//�ͷ����ݼ�����
			if(stmt  != null)
			{
				try
				{
					stmt.close();
					stmt = null;
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			//�ͷ�������
			if(stmt  != null)//�ͷ��ڴ�
			{
				try
				{
					stmt.close();
					stmt = null;
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}
	
	public void deleteFile(String file_id)
	{
		Connection conn = null;
		PreparedStatement stmt = null;//���Ӷ���
		try
		{
			conn = DBHelper.getConnection();//������Ӷ���
			String sql = "DELETE FROM file_list WHERE file_id = '" + file_id + "'";//sql����������û�,idΪString��ʽ������ֱ�Ӽ���sql���
			stmt = conn.prepareStatement(sql);//�������Ӷ���
			/*String sql2 = "DELETE FROM comment WHERE articleid = '" + id + "'";//ɾ�����µ�ͬʱɾ������
			stmt = conn.prepareStatement(sql2);*/
			int i = stmt.executeUpdate(sql);
			//int j = stmt.executeUpdate(sql2);
			System.out.println(sql);
			//System.out.println(sql2);
			System.out.println("�ɹ�ɾ��" + i + "���ļ�");
			//System.out.println("�ɹ�ɾ��" + j + "ƪ����");
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		finally
		{
			//�ͷ����ݼ�����
			if(stmt  != null)
			{
				try
				{
					stmt.close();
					stmt = null;
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			//�ͷ�������
			if(stmt  != null)//�ͷ��ڴ�
			{
				try
				{
					stmt.close();
					stmt = null;
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}
	
	public void addVideoToDB(int teacher_id, String lesson_id, String video_id, String video_name, String video_data, String video_info, String video_url)// ������Ƶ���ݵ����ݿ�
	{
		Connection conn = null;
		PreparedStatement stmt = null;// ���Ӷ���
		try {
			conn = DBHelper.getConnection();// ������Ӷ���

			String sql = "INSERT INTO video_list VALUES(?, ?, NULL, ?, ?, ?, ?, ?)";// sql��䴴��video
			stmt = conn.prepareStatement(sql);// �������Ӷ���,ʹ��PreparedStatement�������ӿ�ά����
			stmt.setInt(1, teacher_id);
			stmt.setString(2, lesson_id);
			stmt.setString(3, video_id);
			stmt.setString(4, video_name);
			stmt.setString(5, video_data);
			stmt.setString(6, video_info);
			stmt.setString(7, video_url);
			int i = stmt.executeUpdate();
			System.out.println("video�ɹ������" + i + "��");

		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		} finally {
			// �ͷ����ݼ�����
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null)// �ͷ��ڴ�
			{
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	public void addFileToDB(int teacher_id, String lesson_id, String file_id, String file_name, String file_data, String file_info, String file_url)// ������Ƶ���ݵ����ݿ�
	{
		Connection conn = null;
		PreparedStatement stmt = null;// ���Ӷ���
		try {
			conn = DBHelper.getConnection();// ������Ӷ���

			String sql = "INSERT INTO file_list VALUES(?, ?, NULL, ?, ?, ?, ?, ?)";// sql��䴴��video
			stmt = conn.prepareStatement(sql);// �������Ӷ���,ʹ��PreparedStatement�������ӿ�ά����
			stmt.setInt(1, teacher_id);
			stmt.setString(2, lesson_id);
			stmt.setString(3, file_id);
			stmt.setString(4, file_name);
			stmt.setString(5, file_data);
			stmt.setString(6, file_info);
			stmt.setString(7, file_url);
			int i = stmt.executeUpdate();
			System.out.println("file�ɹ������" + i + "��");

		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		} finally {
			// �ͷ����ݼ�����
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null)// �ͷ��ڴ�
			{
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	//��ӿγ�
	public String AddLesson(int teacher_id,String lesson_id,String lesson_name,String lesson_info,String lesson_type,String lesson_data){
		Connection conn = null;
		PreparedStatement stmt = null;// ���Ӷ���
		try {
			conn = DBHelper.getConnection();// ������Ӷ���
			String sql = "INSERT INTO lesson_list (teacher_id,lesson_id,lesson_name,lesson_info,lesson_type,lesson_data) VALUES( ?,?,?,?,?,?) ";// sql���ע���û�
			stmt = conn.prepareStatement(sql);// �������Ӷ���,ʹ��PreparedStatement�������ӿ�ά����
			stmt.setInt(1, teacher_id);
			stmt.setString(2, lesson_id);
			stmt.setString(3, lesson_name);
			stmt.setString(4, lesson_info);
			stmt.setString(5, lesson_type);
			stmt.setString(6, lesson_data);
			stmt.executeUpdate();
			
			return "�ɹ�";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "ʧ��";
		} finally {
			// �ͷ����ݼ�����
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null)// �ͷ��ڴ�
			{
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

//����Ƿ��пγ��ظ�
	//��ʦ�γ��б���ʾ
	public int Check(int teacher_id,String lesson_id){
		Connection conn = null;
		PreparedStatement stmt = null;// ���Ӷ���
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();// ������Ӷ���

			String sql = "select * from lesson_list where teacher_id=? and lesson_id=?";// sql���ע���û�
			stmt = conn.prepareStatement(sql);// �������Ӷ���,ʹ��PreparedStatement�������ӿ�ά����
			stmt.setInt(1, teacher_id);
			stmt.setString(2, lesson_id);
			rs = stmt.executeQuery();//���ؽ����
			rs.last();
			int  rowcount = rs.getRow(); 
			return rowcount;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		} finally {
			// �ͷ����ݼ�����
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null)// �ͷ��ڴ�
			{
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}	
	
	//�޸Ŀγ�
	public String ChangeLesson(int teacher_id,String lesson_id,String lesson_name,String lesson_info,String lesson_type,String lesson_data){
		Connection conn = null;
		PreparedStatement stmt = null;// ���Ӷ���
		try {
			conn = DBHelper.getConnection();// ������Ӷ���
			String sql = "update lesson_list set lesson_id=?,lesson_name=?,lesson_info=?,lesson_type=?,lesson_data=? where lesson_id=? and teacher_id=?";// sql���ע���û�
			stmt = conn.prepareStatement(sql);// �������Ӷ���,ʹ��PreparedStatement�������ӿ�ά����
			stmt.setInt(7, teacher_id);
			stmt.setString(1, lesson_id);
			stmt.setString(2, lesson_name);
			stmt.setString(3, lesson_info);
			stmt.setString(4, lesson_type);
			stmt.setString(5, lesson_data);
			stmt.setString(6, lesson_id);
			stmt.executeUpdate();
			System.out.println("�ɹ�");
			return "�ɹ�";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "ʧ��";
		} finally {
			// �ͷ����ݼ�����
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null)// �ͷ��ڴ�
			{
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	//ɾ���γ�
	public String DeleteLesson(int teacher_id,String lesson_id){
		Connection conn = null;
		PreparedStatement stmt = null;// ���Ӷ���
		try {
			conn = DBHelper.getConnection();// ������Ӷ���
			String sql = "delete from lesson_list where lesson_id=? and teacher_id=?";// sql���ע���û�
			stmt = conn.prepareStatement(sql);// �������Ӷ���,ʹ��PreparedStatement�������ӿ�ά����
			stmt.setString(1, lesson_id);
			stmt.setInt(2, teacher_id);
			stmt.executeUpdate();
			System.out.println("�ɹ�");
			
			return "�ɹ�";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "ʧ��";
		} finally {
			// �ͷ����ݼ�����
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null)// �ͷ��ڴ�
			{
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	//�γ���ҳ��ѯ�γ���Ϣ + �޸Ŀγ���Ϣ֮ǰ����ʾ����ʾԭ�γ���Ϣ
		public ArrayList<Lesson_list> SearchClass(int teacher_id,String lesson_id){
			Connection conn = null;
			PreparedStatement stmt = null;// ���Ӷ���
			ResultSet rs = null;
			ArrayList<Lesson_list> list = new ArrayList<Lesson_list>();// �û�����
			try {
				conn = DBHelper.getConnection();// ������Ӷ���

				String sql = "select lesson_id,lesson_name,lesson_info,lesson_data,lesson_type from lesson_list where lesson_id=? and teacher_id=?";// sql���ע���û�
				stmt = conn.prepareStatement(sql);// �������Ӷ���,ʹ��PreparedStatement�������ӿ�ά����
				stmt.setString(1, lesson_id);
				stmt.setInt(2, teacher_id);
				rs = stmt.executeQuery();//���ؽ����
				
				while (rs.next()) {
					// ��ʼ������
					Lesson_list less = new Lesson_list();
					less.setLesson_id(rs.getString("lesson_id"));// ���ÿγ̱���
					less.setLesson_name(rs.getString("lesson_name"));// ���ÿγ�����
					less.setLesson_info(rs.getString("lesson_info"));// ���ÿγ���Ϣ
					less.setLesson_data(rs.getString("lesson_data"));// ���ÿγ̼��
					less.setLesson_type(rs.getString("lesson_type"));// ���ÿγ����
					list.add(less);//����ʦ�Ŀγ̼��뼯��
				}
				
				return list;
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			} finally {
				// �ͷ����ݼ�����
				if (stmt != null) {
					try {
						stmt.close();
						stmt = null;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				// �ͷ�������
				if (stmt != null)// �ͷ��ڴ�
				{
					try {
						stmt.close();
						stmt = null;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
			
			
		}

		

}
