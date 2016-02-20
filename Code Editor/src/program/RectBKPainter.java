package program;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.Painter;

public class RectBKPainter implements Painter<JComponent> {
	private Color paintColor=Color.white;
public RectBKPainter(Color c){
	paintColor=c;
}
@Override
public void paint(Graphics2D g, JComponent bar, int width, int height) {
	// TODO Auto-generated method stub
	g.setColor(paintColor);
	g.fillRect(0, 0, width, height);
	g.fillRect(bar.getX(),bar.getY(),bar.getWidth(),bar.getHeight());
	//g.setColor(Color.decode("#657b83"));
	//g.fillRect(0, height-1, width, 1);
	
}
}
