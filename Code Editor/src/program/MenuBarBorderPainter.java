package program;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JMenuBar;
import javax.swing.Painter;

public class MenuBarBorderPainter implements Painter<Object> {
	private Color paintColor=Color.white;
public MenuBarBorderPainter(Color c){
	paintColor=c;
}
@Override
public void paint(Graphics2D g, Object bar, int width, int height) {
	// TODO Auto-generated method stub
	g.setColor(paintColor);
	g.fillRect(0, height-1,width,1);
	//g.fillRect(bar.getX(),bar.getY()+bar.getHeight()-1,bar.getWidth(),1);
}
}
