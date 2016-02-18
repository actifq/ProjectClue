package Clue;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.*;
import javax.swing.table.*;
public class WaitRoom extends JPanel implements ActionListener{
	Image back;
	JTable table1,table2;
	DefaultTableModel model1,model2;

	JTextPane ta;
	JTextField tf;
	JComboBox box;
	JPanel movie;
	JButton b1,b2,b3,b4,b5,b6;

	AudioClip clip;
	
	Login login=new Login();

	JScrollBar bar;

	
	
	public WaitRoom() {
		back=Toolkit.getDefaultToolkit().getImage("image/back/gwrback.jpg");
		String[] col1={"방이름","공개/비공개","인원"};
		String[][] row1=new String[0][3];
		model1=new DefaultTableModel(row1, col1){
		     public boolean isCellEditable(int r,int c)
		     {
		    	 return false;
		     }
		     };
		table1=new JTable(model1);
		table1.getTableHeader().setReorderingAllowed(false);
		table1.setRowHeight(30);
		table1.setShowVerticalLines(false);
		table1.setIntercellSpacing(new Dimension(0,0));
		
		JScrollPane js1=new JScrollPane(table1);
		
		
		String[] col2={"ID","대화명","성별","위치"};
		String[][] row2=new String[0][4];
		model2=new DefaultTableModel(row2, col2);
		table2=new JTable(model2);
		JScrollPane js2=new JScrollPane(table2);
		
		//채팅
		ta=new JTextPane();
		ta.setEditable(false);
		JScrollPane js3=new JScrollPane(ta);
		bar=js3.getVerticalScrollBar();
		tf=new JTextField();
		box=new JComboBox();
		box.addItem("white");
		box.addItem("blue");
		box.addItem("pink");
		box.addItem("green");
		box.addItem("cyan");
		
		movie=new JPanel();
		movie.setBackground(Color.black);
		
		b1=new JButton("방만들기");
		b2=new JButton("방들어가기");
		b3=new JButton("게임신청");
		b4=new JButton("쪽지보내기");
		b5=new JButton("정보보기");
		b6=new JButton("나가기");
		
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(3, 2, 5, 5));
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.add(b4);
		p.add(b5);
		p.add(b6);
		p.setOpaque(false);
		
		setLayout(null);
		js1.setBounds(30, 30, 700, 450);
		js2.setBounds(30, 490,700, 350);
		js3.setBounds(740, 30, 420, 300);
		tf.setBounds(740, 335, 310, 40);
		box.setBounds(1055, 335, 105, 40);
		movie.setBounds(740, 385, 420, 250);
		p.setBounds(740, 645, 420, 195);

		add(js1);
		add(js2);
		add(js3);
		add(tf);
		add(box);
		add(movie);
		add(p);
		

		try {
            File file = new File("wav/WaitingRoom_bgm_low.wav");
            clip = Applet.newAudioClip(file.toURL());
            clip.stop();
            
           
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

/*<<<<<<< HEAD
		tf.setEnabled(false);
		js1.setEnabled(false);
		js2.setEnabled(false);
		js3.setEnabled(false);
		
=======*/

		
//>>>>>>> branch 'master' of https://github.com/actifq/ProjectClue.git

	}
	@Override
	//paint, paintComponent => 자동호출
	protected void paintComponent(Graphics g) {
		g.drawImage(back, 0, 0, getWidth(),getHeight(),this);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==login.b1)
		{
			clip.play();
		}
	}
}

