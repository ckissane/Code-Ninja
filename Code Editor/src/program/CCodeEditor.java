package program;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.peer.KeyboardFocusManagerPeer;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

public class CCodeEditor extends JComponent implements MouseListener, MouseInputListener, MouseMotionListener,
		MouseWheelListener, KeyListener, KeyboardFocusManagerPeer {
	public String text = "";
	public int selectionStart = 0;
	public int selectionEnd = 0;

	public CCodeEditor() {
		// TODO Auto-generated constructor stub
		this.setMinimumSize(new Dimension(100, 100));
	}

	@Override
	public void clearGlobalFocusOwner(Window arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Component getCurrentFocusOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Window getCurrentFocusedWindow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCurrentFocusOwner(Component arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCurrentFocusedWindow(Window arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

		System.out.println(arg0.getKeyChar());
		// TODO Auto-generated method stub
		int line = 0;
		int charInLine = 0;
		for (char c : this.text.toCharArray()) {

			if (new Character(c).equals(new Character('\n'))) {
				line++;
			} else {

				charInLine++;
			}
		}
		if (arg0.getKeyCode() == arg0.VK_BACK_SPACE) {
			if (this.selectionEnd != this.selectionStart) {
				this.text = this.text.substring(0, this.selectionStart) + this.text.substring(selectionEnd);
				this.selectionEnd = this.selectionStart;
			} else {
				if (this.selectionStart != 0) {
					this.text = this.text.substring(0, this.selectionStart - 1) + this.text.substring(selectionEnd);
					this.selectionStart--;
					this.selectionEnd = this.selectionStart;
				}
			}
		} else {
			this.text = this.text.substring(0, this.selectionStart) + arg0.getKeyChar() + ""
					+ this.text.substring(selectionEnd);
			this.selectionStart++;
			this.selectionEnd = this.selectionStart;
		}
		this.repaint();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLUE);
		int line = 0;
		int charInLine = 0;
		double charX = 0;
		for (char c : this.text.toCharArray()) {

			if (new Character(c).equals(new Character('\n'))) {
				line++;
				charX = 0;
			} else {
				g.drawString(c + "", (int) (charX + 10), (line + 1) * g.getFontMetrics().getHeight());
				charX += g.getFontMetrics().charWidth(c);
				charInLine++;
			}
		}
	}

}
