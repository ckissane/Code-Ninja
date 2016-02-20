package program;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;

public class CMenuItem extends JMenuItem {

	public CMenuItem() {
		
		// TODO Auto-generated constructor stub
	}

	public CMenuItem(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
	}

	public CMenuItem(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public CMenuItem(Action a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public CMenuItem(String text, Icon icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}

	public CMenuItem(String text, int mnemonic) {
		super(text, mnemonic);
		// TODO Auto-generated constructor stub
	}
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.white);
		this.setBackground(Color.white);
		this.setFont(new Font("Copperplate Gothic", Font.PLAIN, 13));
		//g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//g.setColor(Color.darkGray);
		//g.setFont(new Font("Purisa", Font.PLAIN, 13));
		//g.drawString(this.getText(), 5, (int) (this.getHeight()-6));
	}

}
