package program;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Segment;
import javax.swing.text.StyledDocument;

public class Editor extends CodePane {
public JPanel lineNumbers=new JPanel();
public LineLabels lines=null;
public ArrayList<Character> data=new ArrayList<Character>();
	public Editor() {
	//	this.setFont(getFont().deriveFont(15.0f));
		// TODO Auto-generated constructor stub
		lineNumbers.add(new JLabel("Hello"));
		lineNumbers.setSize(50, 100);
		this.setBackground(Color.decode("#fdf6e3"));
		this.setMinimumSize(new Dimension(100,100));
		this.setPreferredSize(getMinimumSize());
		//this.add(lineNumbers);//,BorderLayout.WEST);
		//this.reshape(50, 0, this.getWidth()-50, this.getHeight());
		//this.setBounds(50, 0, this.getWidth()-50, this.getHeight());
		
	}

	
	public void setLineLabels( LineLabels lin){
		this.lines=lin;
	}
	
	
}
