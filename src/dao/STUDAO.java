package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.*;

import util.DBHelper;

public class STUDAO {
	//��Ƶ�б���ʾ
		public ArrayList<Video> Video_list(){
			Connection conn = null;
			PreparedStatement stmt = null;// ���Ӷ���
			ResultSet rs = null;
			ArrayList<Video> list = new ArrayList<Video>();// �û�����
			try {
				conn = DBHelper.getConnection();// ������Ӷ���

				String sql = "select * from video_list ";// sql���ע���û�
				stmt = conn.prepareStatement(sql);// �������Ӷ���,ʹ��PreparedStatement�������ӿ�ά����
				rs = stmt.executeQuery();//���ؽ����
				
				while (rs.next()) {
					// ��ʼ������
					Video video = new Video();
					video.setTeacher_id(rs.getInt("teacher_id"));//��ȡ��ʦid
					video.setLesson_id(rs.getString("lesson_id"));// ��ȡ�γ�id
					video.setVideo_id(rs.getString("video_id"));// ��ȡ��Ƶid
					video.setVideo_name(rs.getString("video_name"));// ��ȡ��Ƶ����
					video.setVideo_data(rs.getString("video_data"));// ��ȡ��Ƶ����
					video.setVideo_info(rs.getString("video_info"));// ���ÿγ���Ϣ
					list.add(video);//����ʦ�Ŀγ̼��뼯��
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
