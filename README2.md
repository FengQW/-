JAVA实验报告










班级：计G191
姓名 : 封启为
   学号：2019322047






实验目的:
分析学生选课系统
使用GUI窗体及其组件设计窗体界面
完成学生选课过程业务逻辑编程
基于文件保存并读取数据
处理异常

实验要求：
一、系统角色分析及类设计
   	 例如：学校有”人员”，分为”教师”和”学生”，教师教授”课程”，学生选择课程。                                     
定义每种角色人员的属性，及其操作方法。
属性示例： 人员（编号、姓名、性别......）
教师（编号、姓名、性别、所授课程、......）
学生（编号、姓名、性别、所选课程、......）
课程（编号、课程名称、上课地点、时间、授课教师、......）
二、要求
1、 设计GUI窗体，支持学生注册、课程新加、学生选课、学生退课、打印学生选课列表等操作。
2、 基于事件模型对业务逻辑编程，实现在界面上支持上述操作。
3、 针对操作过程中可能会出现的各种异常，做异常处理
4、 基于输入/输出编程，支持学生、课程、教师等数据的读写操作。5、 基于Github.com提交实验，包括实验SRC源文件夹程序、README.MD实验报告文档。

核心代码：
public class LoginTest extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public LoginTest(){
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("登录窗体");
		setBounds(300, 200, 300, 150);
		Container cp=getContentPane();
		cp.setLayout(new FlowLayout());
		JLabel jl=new JLabel("用户名：");
		jl.setBounds(10, 10, 200, 18);
		final JTextField name=new JTextField(10);
		name.setBounds(80, 10, 150, 18);
		JLabel jl2=new JLabel("密码：");
		jl2.setBounds(10, 50, 200, 18);
		final JPasswordField password=new JPasswordField(10);
		password.setBounds(80, 50, 150, 18);
		cp.add(jl);
		cp.add(name);
		cp.add(jl2);
		cp.add(password);
		JButton  jb=new JButton("确定");
		
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(name.getText().trim().length()==0||new String(password.getPassword()).trim().length()==0){
					JOptionPane.showMessageDialog(null, "用户名密码不允许为空");
					return;
				}
				if(name.getText().trim().equals("feng")&&new String(password.getPassword()).trim().equals("1234")){
					JOptionPane.showMessageDialog(null, "登录成功");
					dispose();
					new GridBagLayoutDemo();
				
				}
				else{
					JOptionPane.showMessageDialog(null, "用户名或密码错误");
				}
			}
		});
		jb.setBounds(80, 80, 60, 18);
		cp.add(jb);

		final JButton button = new JButton();
		button.setText("重置");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成方法存根
				name.setText("");
				password.setText("");
			}
		});
		button.setBounds(150, 80, 60, 18);
		getContentPane().add(button);
	}
	
	public static void main(String[] args) {
		
		LoginTest l = new LoginTest();
		l.setSize(150, 200);
		l.setVisible(true);
	
	}


}


编程感想：
通过这几次实验，教会我们很多实践经验，通过课堂知识，脱离课本走向实践 
。使我们掌握更丰富全面地软件技术和应用技巧，也使我们真正的对所学的课本知识融会贯通，熟练在手。虽然这次实验已经完成，但是它带给我的启发思考至今。首先，实验内容用到的都是课堂知识的总和，所以会遇到很多的问题。这就告诉我们在进行实验之前一定要吃透课本的知识，不能轻视课程。其次，遇到问题困难不要退缩要主动去思考去解决，生活中不可能事事都有引路人，自己要去摸索解决的方法，克服困难。
