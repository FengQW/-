import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;



public class StuModel extends AbstractTableModel{
	//rowData存放数据,columnNames存放列名
	Vector rowData,columnNames;
	//定义操作数据库所需要的组件
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	
	public void init(String sql){
		if(sql==""||sql.equals(null)){
			sql ="select * from stu";
		}
		//中间
		
		columnNames = new Vector<>();
		//设置列名
		columnNames.add("学号");
		columnNames.add("名字");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("籍贯");
		columnNames.add("系别");
		
		rowData =new Vector<>();
		//rowDate可以存放多行
		try {
			//加载驱动
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
				//加入rowData
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
	
	//构造函数，用于初始我们的数据模型
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

	//得到共有多少行
	public int getRowCount() {
		return this.rowData.size();
	}

	//得到某行某列的数据
	public Object getValueAt(int rowIndex, int columnIndex) {
		return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
	}


}