package Clue;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.*;
import javax.swing.table.*;
import javax.sound.sampled.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class WaitRoom extends JPanel {
	Image back,movie1;
	JTable table1,table2;
	DefaultTableModel model1,model2;

	JTextArea ta;
	JTextField tf;
	JComboBox box;
	JPanel movie;
	JButton b1,b2,b3,b4,b5,b6;
	JScrollBar bar;
	TableColumn column;
	AudioClip clip;
	
	public WaitRoom() {
		back=Toolkit.getDefaultToolkit().getImage("image/back/cardback.jpg");
		movie1=Toolkit.getDefaultToolkit().getImage("image/back/moon.gif");
		
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
		table1.getTableHeader().setResizingAllowed(false);
		table1.setRowHeight(30);
		table1.setShowVerticalLines(false);
		table1.setIntercellSpacing(new Dimension(0,0));
		
		
		
		JScrollPane js1=new JScrollPane(table1);
		
		
		String[] col2={"ID","닉네임","성별","위치"};
		String[][] row2=new String[0][4];
		
		model2=new DefaultTableModel(row2, col2);
		table2=new JTable(model2);
		JScrollPane js2=new JScrollPane(table2);
		table2.getTableHeader().setReorderingAllowed(false);
		table2.getTableHeader().setResizingAllowed(false);
		
		//채팅
		ta=new JTextArea();
		ta.setEditable(false);
		JScrollPane js3=new JScrollPane(ta);
		bar=js3.getVerticalScrollBar();
		tf=new JTextField();
		box=new JComboBox();
		box.addItem("전체채팅");
		box.setFont(new Font("맑은 고딕",Font.BOLD,13));
		
		
		
		b1=new JButton("방만들기");
		b2=new JButton("방들어가기");
		b3=new JButton("게임신청");
		b4=new JButton("쪽지보내기");
		b5=new JButton("정보보기");
		b6=new JButton("나가기");
		
		b1.setFont(new Font("맑은 고딕",Font.BOLD,15));
		b2.setFont(new Font("맑은 고딕",Font.BOLD,15));
		b3.setFont(new Font("맑은 고딕",Font.BOLD,15));
		b4.setFont(new Font("맑은 고딕",Font.BOLD,15));
		b5.setFont(new Font("맑은 고딕",Font.BOLD,15));
		b6.setFont(new Font("맑은 고딕",Font.BOLD,15));
		
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
		
		p.setBounds(740, 645, 420, 195);

		add(js1);
		add(js2);
		add(js3);
		add(tf);
		add(box);
		
		add(p);
		

		for(int i=0;i<col1.length;i++)
		{
			 column=table1.getColumnModel().getColumn(i);
			 DefaultTableCellRenderer rend=
					 new DefaultTableCellRenderer();
			 if(i==0)
			 {
				 column.setPreferredWidth(250);
				 
				 rend.setHorizontalAlignment(JLabel.LEFT);
			 }
			 if(i==1)
			 {
				 column.setPreferredWidth(100);
				 rend.setHorizontalAlignment(JLabel.CENTER);
			 }
			 if(i==2)
			 {
				 column.setPreferredWidth(100);
				 
				 rend.setHorizontalAlignment(JLabel.CENTER);
			 }
			 column.setCellRenderer(rend);
		}
		
		try {
            File file = new File("wav/WatingRoom_bgm.wav");
            clip = Applet.newAudioClip(file.toURL());
            clip.stop();
            
           
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
}


	
	private String setImage(String string, int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	//paint, paintComponent => 자동호출
	protected void paintComponent(Graphics g) {
	g.drawImage(back, 0, 0, getWidth(),getHeight(),this);
	g.drawImage(movie1,740, 385, 420, 250,this);
	}
}