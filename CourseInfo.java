
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class TableDemo extends JFrame{
	public TableDemo(){
		super("�γ���Ϣ");
		String[] columnNames={"����","�Ա�","��Ŀ","�οε�ַ","�ο�ʱ��"};
		Object[][]data={
		{"������","��","�ߵ���ѧ","��102","07:50~09:25"},
		{"κޱ","Ů","��ɢ��ѧ","��105","13:30~15:05"},
		{"������","��","JAVA","��0921","09:40~11:15"},
		{"Babu","Ů","Ӣ��","��1013","13:30~16:50"},
		};
		JTable table=new JTable(data,columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500,70));
		JScrollPane scrollPane=new JScrollPane(table);
		getContentPane().add(scrollPane,BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

}

public class CourseInfo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TableDemo frame=new TableDemo();
	}

}
