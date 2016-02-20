package program;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Paint;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.metal.MetalScrollBarUI;

public class EditPanel extends JPanel {
public Editor editor=new Editor();
public JScrollPane scrollPanel;
public LineLabels linesLs=new LineLabels(editor);
public JPanel sPanel=new JPanel();
	public EditPanel() {
		//editor.setLineLabels(linesLs);
		//this.addKeyListener(editor);
		//this.addMouseListener(editor);
		//this.addMouseMotionListener(editor);
		//editor.setBackground(Color.decode("#002b36"));
		//scrollPanel.getViewport().setLayout(new BorderLayout());
		//sPanel.setLayout(new BorderLayout());
		
		//insidePanel.add(lines,BorderLayout.WEST);
		//sPanel.add(editor,BorderLayout.CENTER);
	//	insidePanel.doLayout();
		
		//sPanel.add(editor,BorderLayout.CENTER);
		//sPanel.add(linesLs,BorderLayout.WEST);
		
		//scrollPanel.getViewport().add(lines,BorderLayout.WEST);
		//lines.
		
		//scrollPanel.getViewport().doLayout();
		
	//scrollPanel.getHorizontalScrollBar().setUI(new MetalScrollBarUI());
		scrollPanel=new JScrollPane(editor);
//scrollPanel.add(sPanel);
		
		this.setLayout(new BorderLayout());
		this.add(scrollPanel,BorderLayout.CENTER);
		//this.add(linesLs,BorderLayout.WEST);
		//scrollPanel.paintAll(getGraphics());
	//sPanel.doLayout();
	}

	public EditPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public EditPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public EditPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	public void paint(Graphics g){
		super.paint(g);
		//scrollPanel.getViewport().paintAll(getGraphics());
	}
	private static ScrollBarUI newScrollBarUI() {

		  return new BasicScrollBarUI() {
			  public void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		            super.paintThumb(g, c, thumbBounds);
		            
		            int tw = thumbBounds.width;
		            int th = thumbBounds.height;
		            int radius=thumbBounds.height;
		            g.translate(thumbBounds.x, thumbBounds.y);

		            Graphics2D g2 = (Graphics2D) g;
		            Paint gp = null;
		            if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL) {
		                gp = new GradientPaint(0, 0, Color.black, tw, 0, Color.WHITE);
		            }
		            if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL) {
		                gp = new GradientPaint(0, 0, Color.black, 0, th, Color.WHITE);
		            }
		            g2.setPaint(gp);
		            g2.fillRoundRect(0, 0, tw - 1, th - 1, radius,radius);

		            g2.drawRoundRect(0, 0, tw - 1, th - 1, radius,radius);
		        }
		   /* @Override
		    protected JButton createDecreaseButton(int orientation) {
		      return createZeroButton();
		    }

		    @Override
		    protected JButton createIncreaseButton(int orientation) {
		      return createZeroButton();
		    }

		    private JButton createZeroButton() {
		      JButton jbutton = new JButton();
		      //jbutton.setPreferredSize(new Dimension(0, 0));
		      //jbutton.setMinimumSize(new Dimension(0, 0));
		      //jbutton.setMaximumSize(new Dimension(0, 0));
		      return jbutton;
		    }*/

		  };

		}

}
