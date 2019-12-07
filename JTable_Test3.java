import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class JTable_Test3 extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//定义组件
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	StuModel sm;
	
	//定义操作数据库所需要的组件
	PreparedStatement ps =null ;
	Connection ct= null;
	ResultSet rs=null;
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// 将当前窗体外观设置为所在操作系统的外观
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new JTable_Test3();


	}
	
	//构造函数
	public JTable_Test3(){
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("查询");
		jb1.addActionListener(this);
		jl1=new JLabel("请输入名字");
		
		//把各个空间加入列
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2=new JPanel();
		jb2=new JButton("添加");
		jb2.addActionListener(this);
		jb3=new JButton("修改");
		jb3.addActionListener(this);
		jb4=new JButton("删除");
		jb4.addActionListener(this);
		
		//把各个按钮加入到jp2中
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		//创建一个数据模型对象
				sm=new StuModel();
				
				//初始化JTable
				jt=new JTable(sm);
				
				//初始化jsp JScrollPane
				jsp=new JScrollPane(jt);
				
				//把jsp放入到jframe
				this.add(jsp);
				this.add(jp1,"North");
				this.add(jp2,"South");
				
				this.setSize(500, 400);
				this.setLocationRelativeTo(null);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setVisible(true);

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			//因为把对表的数据封装到StuModel中，我们就可以比较简单的完成查询
			String name=this.jtf.getText();
			System.out.println("name:"+name);
			if(name==""||name.equals(null)){
				JOptionPane.showMessageDialog(this, "查询条件不能为空","提示",JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
				
				//写一个SQL语句
				String sql="select * from stu where stuName='"+name+"'";
				//构建新的数据模型类，并更新
				sm=new StuModel(sql);
				//更新JTable
				jt.setModel(sm);
			}
		}
		if(e.getSource()==jb2){
			StuAddDialog sa =new StuAddDialog(this, "添加学生", true);
			//重新再获得新的数据模型
			//构建新的数据模型类，并更新
			sm=new StuModel();
			//更新JTable
			jt.setModel(sm);

		}
		if(e.getSource()==jb3){
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1){
				//提示
				JOptionPane.showMessageDialog(this, "请选择一行", "提示", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			StuUpdDialog sa =new StuUpdDialog(this, "修改信息", true, sm, rowNum);
			sm=new StuModel();
			jt.setModel(sm);
		}
		if(e.getSource()==jb4){
			int rowNum = this.jt.getSelectedRow();
			if(rowNum==-1){
				//提示
				JOptionPane.showMessageDialog(this, "请选择一行", "提示", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			String stuID = (String) sm.getValueAt(rowNum, 0);
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url ="jdbc:mysql://localhost:3306/student?characterEncoding=utf8&useSSL=true";
				ct = (Connection) DriverManager.getConnection(url, "root","123456");
				String sql="delete from stu where stuID=? ";
				ps =(PreparedStatement) ct.prepareStatement(sql);
				ps.setString(1, stuID);
				int i =ps.executeUpdate();
				if(i == 1){
				JOptionPane.showMessageDialog(null, "删除成功!");
				this.dispose();
				}else{
				JOptionPane.showMessageDialog(null, "删除失败！");
				}
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}finally{
				try {
					if(ps!=null){
						ps.close();
					}if(ct!=null){
						ct.close();
					}
				} catch (SQLException e3) {
					// TODO: handle exception
					e3.printStackTrace();
				}
			}
			
		}
		
	}

}