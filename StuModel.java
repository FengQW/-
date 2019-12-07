import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;



public class StuModel extends AbstractTableModel{
	//rowData�������,columnNames�������
	Vector rowData,columnNames;
	//����������ݿ�����Ҫ�����
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	
	public void init(String sql){
		if(sql==""||sql.equals(null)){
			sql ="select * from stu";
		}
		//�м�
		
		columnNames = new Vector<>();
		//��������
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("ϵ��");
		
		rowData =new Vector<>();
		//rowDate���Դ�Ŷ���
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/student?characterEncoding=utf8&useSSL=true";
			ct = (Connection) DriverManager.getConnection(url, "root", "123456");
			ps=(PreparedStatement) ct.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()){
				Vector hang = new Vector<>();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getInt(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				//����rowData
				rowData.add(hang);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(ct!=null){
					ct.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	//���캯�������ڳ�ʼ���ǵ�����ģ��
	public StuModel(String sql){
		this.init(sql);
	}
	
	public StuModel(){
		this.init("");
	}
	
	
	public int getColumnCount() {
		return this.columnNames.size();
	}
	
	public String getColumnName(int column) {
		return (String)this.columnNames.get(column);
	}

	//�õ����ж�����
	public int getRowCount() {
		return this.rowData.size();
	}

	//�õ�ĳ��ĳ�е�����
	public Object getValueAt(int rowIndex, int columnIndex) {
		return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
	}


}