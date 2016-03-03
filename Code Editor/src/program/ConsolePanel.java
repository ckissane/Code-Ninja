package program;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

import javax.script.ScriptException;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import org.python.util.JLineConsole;
import org.python.util.InteractiveInterpreter;
import org.python.util.PythonInterpreter;

import jep.Jep;
import jep.JepException;
import jep.JepScriptEngine;
import program.CodePane.BlinkTimer;
import program.QuickTerminal.Command;
import program.QuickTerminal.CommandListener;
import program.QuickTerminal.UserInput;
import program.TextAreaOutputStream.Appender;

public class ConsolePanel extends JPanel implements MouseListener,
		MouseMotionListener, KeyListener {
	public JScrollPane scroller;
	public SimpleDocument doc = new SimpleDocument();
	public ConsolePanelOutputStream stream;
	public File tempFile;
	FileOutputStream out = null;
	public int selectionStart = 0;
	public int selectionEnd = 0;
	private BlinkTimer cursorTimer = new BlinkTimer(500, this);
	public InteractiveInterpreter interp = new InteractiveInterpreter();
	public org.python.util.InteractiveConsole con = new org.python.util.InteractiveConsole();
	public String lineStorage = "";

	public Insets getMargin() {
		return new Insets(5, 5, 5, 5);
	}

	public ConsolePanel() {
		try {
			this.setFont(Font.createFont(Font.TRUETYPE_FONT,
					new File("ProFont.ttf")).deriveFont(Font.PLAIN, 20));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties props = new Properties();
		System.setProperty("file.encoding", "UTF-8");
		System.setProperty("python.console.encoding", "UTF-8");
		System.setProperty("python.path",
				"C:/Users/cannon/AppData/Local/Programs/Python/Python35-32");

		props.setProperty("file.encoding", "UTF-8");
		props.setProperty("python.console.encoding", "UTF-8");
		System.out.println(System.getProperty("python.path"));
		System.out.println(System.getProperty("java.library.path"));
		/*
		 * try { // new JepScriptEngine().eval("print('hello')"); jep. new
		 * Jep().runScript("print('hello')"); } catch (JepException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * PythonInterpreter.initialize(System.getProperties(), props, new
		 * String[] { "" });
		 */
		// InteractiveConsole.initialize(System.getProperties(), props, new
		// String[] {""});

		stream = new ConsolePanelOutputStream(this);
		PrintStream con = new PrintStream(this.stream);
		// p.getOutputStream().
		/*
		 * try { tempFile=File.createTempFile("temp", "txt");
		 * 
		 * } catch (IOException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */
		// System.setOut(con);
		// System.setErr(con);

		// System.out.println("hellooooo");

		interp.setOut(stream);
		interp.setErr(stream);
		// interp.exec("print(\"helloh\")");
		this.enableInputMethods(true);
		this.setFocusable(true);
		this.setAutoscrolls(true);

		// cmd = new Command(this);

	}

	public int lineCount() {

		int count = 1;
		// System.out.println(s.toString());
		for (Character c : this.doc.doc) {

			if (new Character(c).equals(new Character('\n'))) {
				count++;
			}
		}
		return count;
	}

	public void paint(Graphics g) {
		double lineStartY = this.getMargin().top
				+ this.getFontMetrics(this.getFont()).getDescent();
		boolean selectedLine = false;
		double lineEndY = this.getMargin().top;
		Point caretPos = new Point(0, 0);
		double lineNumberAlignment = new String(lineCount() + "").length()
				* g.getFontMetrics().stringWidth("0");
		// System.out.println(new String(lineCount()+"").length());
		// System.out.println(this.getBounds());
		this.setBackground(Color.decode("#fdf6e3"));
		if (this.isFocusOwner()) {
			// this.setBackground(Color.decode("#00f6e3"));
		}
		// this.
		// this.setLocation(50, 0);
		// System.out.println("paint");
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// g.setColor(Color.WHITE);
		// g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2.setColor(Color.decode("#657b83"));
		// g.drawLine(0,0,mousePos.x,mousePos.y);
		int line = 1;
		double lineY = g.getFontMetrics().getHeight();
		int charInLine = 0;
		int maxLine = 0;
		double charX = 50;

		if (selectionEnd == 0) {
			caretPos = new Point((int) (this.getMargin().left
					+ this.getInsets().left + 50),
					(int) (this.getMargin().top + g2.getFontMetrics()
							.getDescent()));
			selectedLine = true;
		}
		// this.getDocument().
		g2.drawString(line + "",
				(int) (this.getMargin().left + this.getInsets().left
						+ lineNumberAlignment / 2
						- g.getFontMetrics().stringWidth(line + "") + 25),
				(int) (lineY + this.getMargin().top));
		for (int i = 0; i < this.doc.doc.size()+lineStorage.length(); i++) {

			Character c = ' ';
if(i<this.doc.doc.size()){
	c =this.doc.doc.get(i);
}else{
	c=lineStorage.charAt(i-this.doc.doc.size());
}
			if (new Character(c).equals(new Character('\n'))) {
				if (i < Math.max(selectionStart, selectionEnd)
						&& i > Math.min(selectionStart, selectionEnd) - 1) {
					g2.setColor(Color.decode("#eee8d5"));
					g2.fillRect((int) (charX + this.getMargin().left + this
							.getInsets().left), (int) (lineY
							+ this.getMargin().top
							- g2.getFontMetrics().getHeight() + g2
							.getFontMetrics().getDescent()), 5, g2
							.getFontMetrics().getHeight());
				}
				lineEndY = (int) (lineY + this.getMargin().top + g2
						.getFontMetrics().getDescent());
				if (selectedLine) {

					g2.setColor(Color.decode("#eee8d5"));
					g2.fillRect(
							(int) (this.getMargin().left + this.getInsets().left),
							(int) lineStartY, 45, (int) (lineEndY - lineStartY));

					g2.setColor(Color.decode("#657b83"));
					g2.drawString(line + "", (int) (this.getMargin().left
							+ this.getInsets().left + lineNumberAlignment / 2
							- g.getFontMetrics().stringWidth(line + "") + 25),
							(int) (lineStartY - (-g2.getFontMetrics()
									.getHeight() + g2.getFontMetrics()
									.getDescent())));
					selectedLine = false;
				}
				lineStartY = (int) (lineY + this.getMargin().top + g2
						.getFontMetrics().getDescent());
				line++;
				lineY += g.getFontMetrics().getHeight();
				charX = 50;
				if (selectionEnd == i + 1) {
					selectedLine = true;
					caretPos = new Point((int) (this.getMargin().left
							+ this.getInsets().left + charX),
							(int) (this.getMargin().top + lineY
									- g2.getFontMetrics().getHeight() + g2
									.getFontMetrics().getDescent()));

				}
				g2.setColor(Color.decode("#657b83"));
				g2.drawString(
						line + "",
						(int) (this.getMargin().left + this.getInsets().left
								+ lineNumberAlignment / 2
								- g.getFontMetrics().stringWidth(line + "") + 25),
						(int) (lineY + this.getMargin().top));
			} else {
				if (charX + g2.getFontMetrics().charWidth(c) < this.getWidth()
						- this.getMargin().right) {

				} else {
					lineY += g2.getFontMetrics().getHeight();
					charX = 50;
				}
				if (i < Math.max(selectionStart, selectionEnd)
						&& i > Math.min(selectionStart, selectionEnd) - 1) {
					g2.setColor(Color.decode("#eee8d5"));
					g2.fillRect((int) (charX + this.getMargin().left + this
							.getInsets().left), (int) (lineY
							+ this.getMargin().top
							- g2.getFontMetrics().getHeight() + g2
							.getFontMetrics().getDescent()), g2
							.getFontMetrics().charWidth(c), g2.getFontMetrics()
							.getHeight());
				}
				g2.setColor(Color.decode("#657b83"));

				g2.drawString(
						c + "",
						(int) (charX + this.getMargin().left + this.getInsets().left),
						(int) (lineY + this.getMargin().top));

				charX += g2.getFontMetrics().charWidth(c);
				if (selectionEnd == i + 1) {
					selectedLine = true;
				}
				if (selectionEnd == i + 1) {

					caretPos = new Point((int) (this.getMargin().left
							+ this.getInsets().left + charX),
							(int) (this.getMargin().top + lineY
									- g2.getFontMetrics().getHeight() + g2
									.getFontMetrics().getDescent()));

				}
				charInLine++;
			}
		}
		lineEndY = lineY;
		if (selectedLine) {
			lineEndY = (int) (lineY + this.getMargin().top + g2
					.getFontMetrics().getDescent());
			if (selectedLine) {

				g2.setColor(Color.decode("#eee8d5"));
				g2.fillRect(
						(int) (this.getMargin().left + this.getInsets().left),
						(int) lineStartY, 45, (int) (lineEndY - lineStartY));

				g2.setColor(Color.decode("#657b83"));
				g2.drawString(
						line + "",
						(int) (this.getMargin().left + this.getInsets().left
								+ lineNumberAlignment / 2
								- g.getFontMetrics().stringWidth(line + "") + 25),
						(int) (lineStartY - (-g2.getFontMetrics().getHeight() + g2
								.getFontMetrics().getDescent())));
				selectedLine = false;
			}
			selectedLine = false;
		}
		g2.setColor(Color.decode("#657b83"));
		if (cursorTimer.value == 1 && this.isFocusOwner()) {
			g2.fillRect(caretPos.x, caretPos.y, 2, g2.getFontMetrics()
					.getHeight());
		}
		this.setPreferredSize(new Dimension(100, Math.max(
				(int) (lineY + this.getMargin().bottom), 200)));
		scroller.revalidate();
		this.revalidate();

		// this.getParent().p
	}

	public class BlinkTimer implements ActionListener {
		private Timer cursorTimer;
		public int value = 0;
		private JComponent user;

		public BlinkTimer(int time, JComponent u) {
			cursorTimer = new Timer(time, this);
			cursorTimer.start();
			user = u;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			value = (value + 1) % 2;
			this.user.repaint();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			interp.eval(lineStorage);
			lineStorage = "";
			this.selectionStart = this.doc.doc.size();
			this.selectionEnd = this.selectionStart;
		} else {
			lineStorage = lineStorage + e.getKeyChar();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.grabFocus();
		this.requestFocusInWindow();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
