package sujin;

import javax.swing.*;
import java.awt.*;
public class Login extends JPanel{
	Image back;
	JLabel la1,la2;
	JTextField tf;
	JPasswordField pf;
	JButton b1,b2;
	// 저장 (초기화)
	
	/* JPanel => 로딩
	 *             1) 생성자 호출
	 *             2) update() => clearRect() ==> 화면을 지움(창 크기 변경시, 배경을 지웠다가 다시 부르기 위해서)
	 *                paint(),paintComponent() ==> 자동 호출
	 *             ==> 화면 변경
	 */
	 
	public Login() 
	{
		back=Toolkit.getDefaultToolkit().getImage("c:\\image\\back.jpg");
		la1=new JLabel("ID");
		la2=new JLabel("PW");
		tf=new JTextField();
		pf=new JPasswordField();
		b1=new JButton("로그인");
		b2=new JButton("취소");
		
		JPanel p=new JPanel(); // FlowLayout
		p.add(b1);
		p.add(b2);
		p.setBounds(10, 85, 185, 35);
		p.setOpaque(false);  //배경 투명하게(배경그림이 보이도록)
		// 배치
		//setLayout(new GridLayout(3, 2 , 5 , 5)); //뒤쪽 5(좌우간격),5(위아래간격)
		setLayout(null); //사용자 지정
		la1.setBounds(10, 15, 30, 30);
		tf.setBounds(45, 15, 150, 30);
		la2.setBounds(10, 50, 30, 30);
		pf.setBounds(45, 50, 150, 30);
		// 추가
		add(la1); add(tf);
		add(la2); add(pf);
		add(b1); add(b2);
		add(p);
	}
	@Override
	//paint, paintComponent => 자동호출
	protected void paintComponent(Graphics g) {
		g.drawImage(back, 0, 0, getWidth(),getHeight(),this);
	}
	
}
