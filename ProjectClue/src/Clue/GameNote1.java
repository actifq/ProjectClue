package Clue;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;
	
	  /* public class GameNote1 extends JFrame {
	    // private static final long serialVersionUID = 1L;
	    private JTable table;

	    public GameNote1() {
	        Object[] columnNames = {"구분", "종류", "메모"};
	        Object[][] data = {
	            {"용의자", false, ""},
	            {"무기", false, ""},
	            {"방",  false, ""}
	        };
	        DefaultTableModel model = new DefaultTableModel(data, columnNames);
	        table = new JTable(model) {

	            private static final long serialVersionUID = 1L;

	            @Override
	            public Class getColumnClass(int column) {
	                switch (column) {
	                    case 0:
	                        return String.class;
	                    case 1:
	                        return Boolean.class;  
	                    default:
	                        return String.class;
	                }
	            }
	        };
	        table.setPreferredScrollableViewportSize(table.getPreferredSize());
	        JScrollPane scrollPane = new JScrollPane(table);
	        getContentPane().add(scrollPane);
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {

	            @Override
	            public void run() {
	            	GameNote1 frame = new GameNote1();
	                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	                frame.pack();
	                frame.setLocation(150, 150);
	                frame.setVisible(true);
	            }
	        });
	    }*/
