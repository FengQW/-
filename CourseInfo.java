
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class TableDemo extends JFrame{
	public TableDemo(){
		super("课程信息");
		String[] columnNames={"姓名","性别","科目","任课地址","任课时间"};
		Object[][]data={
		{"任正民","男","高等数学","教102","07:50~09:25"},
		{"魏薇","女","离散数学","教105","13:30~15:05"},
		{"张世博","男","JAVA","综0921","09:40~11:15"},
		{"Babu","女","英语","综1013","13:30~16:50"},
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
