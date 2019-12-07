
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Frame extends JFrame implements
	ActionListener{
	private JButton Jbtn;
	public Frame(){
		this.setSize(500,200);
		this.setLocation(300,400);
		Jbtn=new JButton("点击进入教务选课系统");
		this.add(Jbtn);
		Jbtn.addActionListener(this);
		this.setVisible(true);
		
	}
	
	public static void main(String[] args){
		Frame frame=new Frame();
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource()==Jbtn)
		{
			this.dispose();
			new LoginTest();
		}}


	}
			
		
		


