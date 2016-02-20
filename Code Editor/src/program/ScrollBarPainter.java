package program;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JMenuBar;
import javax.swing.JScrollBar;
import javax.swing.Painter;

public class ScrollBarPainter implements Painter<JScrollBar> {
	private Color paintColor=Color.white;
public ScrollBarPainter(Color c){
	paintColor=c;
}
@Override
public void paint(Graphics2D g, JScrollBar bar, int width, int height) {
	// TODO Auto-generated method stub
	
	/*g.setColor(paintColor);
	//g.fillRect(0, 0, width, height);
	if(bar.getOrientation()==bar.HORIZONTAL){
		g.clearRect(0, 0, bar.getWidth(),bar.getHeight());
		g.fillRect(0,0,bar.getWidth()-bar.getX(),bar.getHeight());
	//g.fillRect(bar.getHeight()/2,0,bar.getWidth()-bar.getHeight(),bar.getHeight());
	}else{
		g.clearRect(0, 0, bar.getHeight(),bar.getWidth());
		g.fillRect(0,0,bar.getHeight(),bar.getWidth());
		}*/
	g.setColor(paintColor);
	  int tw = bar.getWidth();
      int th = bar.getHeight();
      int radius=bar.getHeight();
	Graphics2D g2 = (Graphics2D) g;
    Paint gp = null;
    if (bar.getOrientation()== JScrollBar.VERTICAL) {
    	 radius=bar.getWidth();
       // gp = new GradientPaint(0, 0, Color.black, tw, 0, Color.WHITE);
       // g2.setPaint(gp);
        g2.fillRoundRect(0, 0, th - 1, tw - 1, radius,radius);

        g2.drawRoundRect(0, 0, th - 1, tw - 1, radius,radius);
    }
    if (bar.getOrientation() == JScrollBar.HORIZONTAL) {
      //  gp = new GradientPaint(0, 0, Color.black, 0, th, Color.WHITE);
      //  g2.setPaint(gp);
        g2.fillRoundRect(0, 0, tw - 1, th - 1, radius,radius);

        g2.drawRoundRect(0, 0, tw - 1, th - 1, radius,radius);
    }
   

}
}
