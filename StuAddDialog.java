import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class StuAddDialog extends JDialog implements ActionListener {

	//定义我需要的swing组件
		JLabel jl1,jl2,jl3,jl4,jl5,jl6;
		JButton jb1,jb2;
		JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
		JPanel jp1,jp2,jp3;
	
	//owner它的父窗口;title窗口名;model指定是模态窗口，还是非模态
	public StuAddDialog(Frame owner ,String tille,boolean modal){
		super(owner, tille, modal);
		jl1=new JLabel("学号");
		jl2=new JLabel("名字");
		jl3=new JLabel("性别");
		jl4=new JLabel("年龄");
		jl5=new JLabel("籍贯");
		jl6=new JLabel("系别");
		
		jtf1=new JTextField();
		jtf2=new JTextField();
		jtf3=new JTextField();
		jtf4=new JTextField();
		jtf5=new JTextField();
		jtf6=new JTextField();
		
		jb1=new JButton("添加");
		jb2=new JButton("取消");
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		//设置布局
		jp1.setLayout(new GridLayout(6,1));
		jp2.setLayout(new GridLayout(6,1));
		
		//添加组件
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		//展现
		this.setSize(350, 250);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		


	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			//链接数据库 
			Connection ct =null;
			PreparedStatement ps =null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url="jdbc:mysql://localhost:3306/student?characterEncoding=utf8&useSSL=true";
				ct=(Connection)DriverManager.getConnection(url,"root","123456");
				
				String sql ="insert into stu values(?,?,?,?,?,?)";
				ps=(PreparedStatement)ct.prepareStatement(sql);
				ps.setString(1, jtf1.getText());
				ps.setString(2, jtf2.getText());
				ps.setString(3, jtf3.getText());
				ps.setInt(4,Integer.parseInt(jtf4.getText()));
				ps.setString(5, jtf5.getText());
				ps.setString(6, jtf6.getText());
//				ps.executeUpdate();
//				this.dispose();
				int i =ps.executeUpdate();
				if(i == 1){
				JOptionPane.showMessageDialog(null, "添加成功!");
				this.dispose();
				}else{
				JOptionPane.showMessageDialog(null, "添加失败！");
				}
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}finally{
				try {
					if(ps!=null){
						ps.close();
					}
					if(ct!=null){
						ps.close();
					}
					
				} catch (SQLException e3) {
					// TODO: handle exception
					e3.printStackTrace();
				}
			}
			
		}else if(e.getSource()==jb2){
			this.dispose();
		}
	}
		
}