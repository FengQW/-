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
	//�������
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	StuModel sm;
	
	//����������ݿ�����Ҫ�����
	PreparedStatement ps =null ;
	Connection ct= null;
	ResultSet rs=null;
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// ����ǰ�����������Ϊ���ڲ���ϵͳ�����
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
	
	//���캯��
	public JTable_Test3(){
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("��ѯ");
		jb1.addActionListener(this);
		jl1=new JLabel("����������");
		
		//�Ѹ����ռ������
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2=new JPanel();
		jb2=new JButton("���");
		jb2.addActionListener(this);
		jb3=new JButton("�޸�");
		jb3.addActionListener(this);
		jb4=new JButton("ɾ��");
		jb4.addActionListener(this);
		
		//�Ѹ�����ť���뵽jp2��
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		//����һ������ģ�Ͷ���
				sm=new StuModel();
				
				//��ʼ��JTable
				jt=new JTable(sm);
				
				//��ʼ��jsp JScrollPane
				jsp=new JScrollPane(jt);
				
				//��jsp���뵽jframe
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
			//��Ϊ�ѶԱ�����ݷ�װ��StuModel�У����ǾͿ��ԱȽϼ򵥵���ɲ�ѯ
			String name=this.jtf.getText();
			System.out.println("name:"+name);
			if(name==""||name.equals(null)){
				JOptionPane.showMessageDialog(this, "��ѯ��������Ϊ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
				
				//дһ��SQL���
				String sql="select * from stu where stuName='"+name+"'";
				//�����µ�����ģ���࣬������
				sm=new StuModel(sql);
				//����JTable
				jt.setModel(sm);
			}
		}
		if(e.getSource()==jb2){
			StuAddDialog sa =new StuAddDialog(this, "���ѧ��", true);
			//�����ٻ���µ�����ģ��
			//�����µ�����ģ���࣬������
			sm=new StuModel();
			//����JTable
			jt.setModel(sm);

		}
		if(e.getSource()==jb3){
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1){
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			StuUpdDialog sa =new StuUpdDialog(this, "�޸���Ϣ", true, sm, rowNum);
			sm=new StuModel();
			jt.setModel(sm);
		}
		if(e.getSource()==jb4){
			int rowNum = this.jt.getSelectedRow();
			if(rowNum==-1){
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�!");
				this.dispose();
				}else{
				JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
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