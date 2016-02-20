package program;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LineLabels extends JPanel {
	public Editor editor;
	public JLabel lines=new JLabel();
	public LineLabels(Editor e){
		this.setBackground(Color.decode("#eee8d5"));
		editor=e;
		this.setSize(50, editor.getHeight());
		this.setMinimumSize(new Dimension(50, 50));
		this.setPreferredSize(new Dimension(50, 50));
		this.add(lines);
		
	}
	public void setCount(int count){
		this.lines.setFont(editor.getFont());
		this.setPreferredSize(new Dimension(50, editor.getHeight()));
		this.setSize(50, editor.getHeight());
		String htmlText="<html>";
		for(int i=0;i<count;i++){
			htmlText+=(i + 1)+"<br>";
		}
		htmlText+="</html>";
		this.lines.setText(htmlText);
	
		
	}
	/*public void paint(Graphics g){
		this.setFont(editor.getFont());
		this.setPreferredSize(new Dimension(50, editor.getHeight()));
		this.setSize(50, editor.getHeight());
		super.paint(g);
		g.setColor(Color.BLUE);
		int count=editor.lineCount();
		for(int i=0;i<count;i++){
			g.drawString(i+1 + "", (int) (g.getFontMetrics().charWidth(' ')), (i + 1) * g.getFontMetrics().getHeight());
		}
	}*/
}
