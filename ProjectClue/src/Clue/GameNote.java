package Clue;

import java.awt.*;
import javax.swing.*;

public class GameNote extends JPanel{
	JPanel jpNote;
	JTextField nt1,nt2,nt3,nt4,nt5,nt6,
	nt7,nt8,nt9,nt10,nt11,nt12;
	JCheckBox sus1,sus2,sus3,sus4,wp1,wp2,wp3,wp4,pl1,pl2,pl3,pl4;
	Box box = Box.createVerticalBox();
	
	public GameNote() {
	//추리노트
			jpNote=new JPanel();
			jpNote.setLayout(new FlowLayout());
			JScrollPane jsMemo=new JScrollPane(
					box, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			//jpNote.setLayout(new GridBagLayout());			
			//jsMemo.setPreferredSize(new Dimension());
			JLabel lb1 = new JLabel("용의자");
			JLabel lb2 = new JLabel("무기");
			JLabel lb3 = new JLabel("장소");
			sus1=new JCheckBox("미실");
			sus2=new JCheckBox("길태미");
			sus3=new JCheckBox("오달수");
			sus4=new JCheckBox("신민아");
			wp1=new JCheckBox("활");
			wp2=new JCheckBox("삽");
			wp3=new JCheckBox("비소");
			wp4=new JCheckBox("은장도");
			pl1=new JCheckBox("뒷간");
			pl2=new JCheckBox("헛간");
			pl3=new JCheckBox("안채");
			pl4=new JCheckBox("텃밭");
			nt1=new JTextField("내용을 입력하세요",20);
			nt2=new JTextField();
			nt3=new JTextField();
			nt4=new JTextField();
			nt5=new JTextField();
			nt6=new JTextField();
			
			//jsMemo.setBounds(865, 105, 320, 570);
			
			box.add(lb1);
			box.add(sus1); box.add(nt1);
			box.add(sus2); box.add(nt2);
			box.add(sus3); box.add(nt3);
			box.add(sus4); box.add(nt4);
			box.add(lb2);
			box.add(wp1); box.add(nt5);
			box.add(wp2); box.add(nt6);
			/*box.add(wp3); box.add(nt7);
			box.add(wp4); box.add(nt8);
			box.add(lb3);
			box.add(pl1); box.add(nt9);
			box.add(pl2); box.add(nt10);
			box.add(pl3); box.add(nt11);
			box.add(pl4); box.add(nt12);*/
	}
	

}
