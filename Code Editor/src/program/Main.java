package program;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.ProcessBuilder.Redirect;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Main extends JFrame implements ActionListener {
	private JMenuBar menuBar = new JMenuBar();
	public Editor editor = new Editor();
	public LightScrollPane scrollPanel;
	private HashMap<String, ArrayList<JMenuItem>> menuBarData = new HashMap<String, ArrayList<JMenuItem>>();
	public Path openFilePath;

	public Main() {
		this.setDefaultLookAndFeelDecorated(false);
		// ArrayList<JMenuItem> FileItems= new ArrayList<JMenuItem>();
		JMenu fileMenu = new JMenu("File");
		JMenuItem newPattern = new CMenuItem("New");
		newPattern.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit
				.getDefaultToolkit().getMenuShortcutKeyMask()));
		newPattern.setActionCommand("newFile");
		newPattern.addActionListener(this);
		fileMenu.add(newPattern);
		JMenuItem run = new CMenuItem("Run");
		run.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit
				.getDefaultToolkit().getMenuShortcutKeyMask()));
		run.setActionCommand("run");
		run.addActionListener(this);
		fileMenu.add(run);
		// fileMenu.addSeparator();
		JMenuItem openPattern = new CMenuItem("Open");
		openPattern.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		openPattern.setActionCommand("openFile");
		openPattern.addActionListener(this);
		fileMenu.add(openPattern);
		JMenuItem save = new CMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit
				.getDefaultToolkit().getMenuShortcutKeyMask()));
		save.setActionCommand("save");
		save.addActionListener(this);
		fileMenu.add(save);
		/*
		 * JMenuItem saveAs = new CMenuItem("Save As");
		 * //saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
		 * java.awt.event.InputEvent.CTRL_DOWN_MASK));
		 * saveAs.setActionCommand("saveAs"); saveAs.addActionListener(this);
		 * fileMenu.add(saveAs);
		 */
		menuBar.add(fileMenu);
		// this.setLayout(new BorderLayout());

		menuBar.setEnabled(true);
		this.setJMenuBar(menuBar);

		this.setUndecorated(false);

		this.getRootPane().getContentPane().setVisible(false);
		// this.setUndecorated(true);
		NimbusLookAndFeel myLook = new NimbusLookAndFeel();
		// myLook.installProperty(this.getRootPane(), "background", Color.red);
		// System.out.println(new
		// NimbusLookAndFeel().getSupportsWindowDecorations());
		// UIManager.put("MenuBar[Enabled].backgroundPainter", new
		// MenuBarPainter(Color.red));
		this.setTitle("Code Ninja v0.1");
		try {
			UIManager.setLookAndFeel(myLook);
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getRootPane().setWindowDecorationStyle(this.getRootPane().NONE);
		this.getRootPane().getContentPane().setVisible(true);
		/*
		 * try { for (LookAndFeelInfo info :
		 * UIManager.getInstalledLookAndFeels()) { if
		 * ("Nimbus".equals(info.getName())) {
		 * UIManager.setLookAndFeel(info.getClassName()); break; } } } catch
		 * (Exception e) { // If Nimbus is not available, you can set the GUI to
		 * another look and feel. }
		 */
		// new NimbusLookAndFeel().
		// UIManager.put("InternalFrameTitlePane.background", Color.red);
		UIManager.put("background", Color.decode("#fdf6e3"));
		// UIManager.put("TextPane.background", Color.decode("#002b36"));
		UIManager.put("control", Color.decode("#fdf6e3"));
		// UIManager.put("Panel.background", Color.darkGray);
		UIManager.put("menuText", Color.orange);
		UIManager.put("InternalFrame.activeTitleBackground",
				new ColorUIResource(Color.black));
		UIManager.put("InternalFrame.activeTitleForeground",
				new ColorUIResource(Color.RED));
		UIManager.put("InternalFrame.titleFont", new Font("Dialog", Font.PLAIN,
				11));
		UIManager.put("activeTitleBackground",
				new javax.swing.plaf.ColorUIResource(Color.green));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		this.setSize((int) width / 2, (int) height / 2);
		this.setLocationRelativeTo(null);

		// this.setComponentOrientation();
		// this.setExtendedState(MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.getContentPane().setLayout(new BorderLayout());
		editor.addKeyListener(editor);
		editor.setFocusable(true);
		editor.addMouseListener(editor);
		editor.addMouseMotionListener(editor);

		scrollPanel = new LightScrollPane(editor);
		editor.scroller = scrollPanel.scrollPane;

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(scrollPanel, BorderLayout.CENTER);
		scrollPanel.addKeyListener(editor);

		// this.getContentPane().add(editor);// ,BorderLayout.CENTER);
		this.setVisible(true);
		this.getContentPane().layout();
		// this.addKeyListener(editor);
	}

	public static void main(String[] args) {
		NimbusLookAndFeel myLook = new NimbusLookAndFeel();
		// myLook.installProperty(this.getRootPane(), "background", Color.red);
		// System.out.println(new
		// NimbusLookAndFeel().getSupportsWindowDecorations());
		// UIManager.put("MenuBar[Enabled].backgroundPainter", new
		// MenuBarPainter(Color.red));
		try {
			UIManager.setLookAndFeel(myLook);
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// UIManager.put("MenuBar[Enabled].borderPainter", new
		// MenuBarBorderPainter(Color.decode("#657b83")));
		// UIManager.put("MenuBar:Menu[Selected].backgroundPainter", new
		// RectBKPainter(Color.decode("#eee8d5")));
		// UIManager.put("MenuBar[Enabled].backgroundPainter", new
		// MenuBarPainter(Color.decode("#eee8d5")));
		UIManager.put("MenuBar[Enabled].backgroundPainter", new MenuBarPainter(
				Color.decode("#eee8d5")));

		// UIManager.put("ScrollBar:ScrollBarThumb[MouseOver].backgroundPainter",
		// new ScrollBarPainter(Color.gray));
		// UIManager.put("ScrollBar:ScrollBarThumb[Enabled].backgroundPainter",
		// new ScrollBarPainter(Color.lightGray));

		Main frame = new Main();

		frame.setVisible(true);

	}

	static String readFile(File f, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(f.toPath());
		return new String(encoded, encoding);
	}

	static void saveFile(File f, String data) throws IOException {
		Files.write(f.toPath(), data.getBytes(), new OpenOption[0]);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("run")) {
			// String prg = "import sys";

			/*if (true) {
				JFileChooser fileChoose = new JFileChooser();
				JFrame j = new JFrame();
				j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				final JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (this.openFilePath != null) {
					fc.setCurrentDirectory(this.openFilePath.toFile());

					fc.setSelectedFile(this.openFilePath.toFile());

				}
				// fc.setName("fractal.png");
				int returnVal = fc.showSaveDialog(j);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						String collect = "";

						for (int i = 0; i < this.editor.doc.doc.size(); i++) {
							collect = collect + this.editor.doc.doc.get(i);
						}
						saveFile(file, collect);
						openFilePath = file.toPath();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// println("saving: " + file.getName() + "," +
					// file.getPath() +
					// ".");
					// saveImage(this.image, file.getPath());
				} else {
					// println("Save command cancelled by user.");
				}
			}*/
			String collect = "";

			for (int i = 0; i < this.editor.doc.doc.size(); i++) {
				collect = collect + this.editor.doc.doc.get(i);
			}
			JFrame j = new JFrame();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width = screenSize.getWidth();
			double height = screenSize.getHeight();
			j.setSize((int) width / 2, (int) height / 2);
			j.setLocationRelativeTo(null);
			j.setVisible(true);
			j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JTextArea jt = new JTextArea();
			j.add(jt);
			PrintStream con = new PrintStream(new TextAreaOutputStream(jt));
			// p.getOutputStream().

			System.setOut(con);
			System.setErr(con);
			// System.out.print(b);
			Process p;
			try {

				// p = Runtime.getRuntime().exec("python\n"+collect);
				ProcessBuilder pb = new ProcessBuilder("/usr/bin/script", "/dev/null", "/usr/bin/python");
				
				pb.redirectOutput(Redirect.INHERIT);
				pb.redirectError(Redirect.INHERIT);
				
				p = pb.start();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(p.getInputStream()));
		// String ret = in.readLine();
		// System.out.println("value is : "+ret);

		if (event.getActionCommand().equals("newFile")) {
			this.editor.selectionStart = 0;
			this.editor.selectionEnd = 0;
			this.editor.doc.insertString(0, this.editor.doc.doc.size(), "");
			openFilePath = null;
		}
		if (event.getActionCommand().equals("save")) {
			JFileChooser fileChoose = new JFileChooser();
			JFrame j = new JFrame();
			j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			final JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if (this.openFilePath != null) {
				fc.setCurrentDirectory(this.openFilePath.toFile());

				fc.setSelectedFile(this.openFilePath.toFile());

			}
			// fc.setName("fractal.png");
			int returnVal = fc.showSaveDialog(j);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				try {
					String collect = "";

					for (int i = 0; i < this.editor.doc.doc.size(); i++) {
						collect = collect + this.editor.doc.doc.get(i);
					}
					saveFile(file, collect);
					openFilePath = file.toPath();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// println("saving: " + file.getName() + "," + file.getPath() +
				// ".");
				// saveImage(this.image, file.getPath());
			} else {
				// println("Save command cancelled by user.");
			}
		}
		if (event.getActionCommand().equals("openFile")) {
			JFileChooser fileChoose = new JFileChooser();
			JFrame j = new JFrame();
			j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			final JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			// fc.setName("fractal.png");
			int returnVal = fc.showOpenDialog(j);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				try {
					this.editor.selectionStart = 0;
					this.editor.selectionEnd = 0;
					this.editor.doc.insertString(0, this.editor.doc.doc.size(),
							readFile(file, Charset.defaultCharset()));
					openFilePath = file.toPath();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// println("saving: " + file.getName() + "," + file.getPath() +
				// ".");
				// saveImage(this.image, file.getPath());
			} else {
				// println("Save command cancelled by user.");
			}

		}

	}

}
