package program;

import java.awt.Color;
import org.python.util.InteractiveConsole;
import java.util.ArrayList;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class SyntaxHighlighter {
	public ArrayList<String> redWords = new ArrayList<String>();
	public ArrayList<String> yellowWords = new ArrayList<String>();
	public ArrayList<String> orangeWords = new ArrayList<String>();
	public ArrayList<String> violetWords = new ArrayList<String>();
	public Color cyan = Color.decode("#2aa198");
	public Color red = Color.decode("#dc322f");
	public Color yellow = Color.decode("#b58900");
	public Color blue = Color.decode("#268bd2");
	public Color orange = Color.decode("#cb4b16");
	public Color violet = Color.decode("#6c71c4");
	public int language = Language.NONE;

	public SyntaxHighlighter() {
		// redWords.add("import");
	}

	public void setLanguage(int l) {
		redWords = new ArrayList<String>();
		yellowWords = new ArrayList<String>();
		this.language = Language.NONE;
		switch (l) {
		case Language.HTML:

			break;
		case Language.CSS:

			break;
		case Language.JS:

			break;
		case Language.PYTHON:
			this.orangeWords.add("import");
			this.orangeWords.add("from");
			this.orangeWords.add("as");
			this.orangeWords.add("for");
			this.orangeWords.add("if");
			this.orangeWords.add("else");
			this.orangeWords.add("in");
			this.violetWords.add("range");
			this.violetWords.add("print");
			break;
		}
	}

	public SyntaxHighlighter(int l) {
		setLanguage(l);

	}

	public ArrayList<Color> parse(ArrayList<Character> doc) {
		// new RSyntaxTextArea().get;
		String collect = "";

		for (int i = 0; i < doc.size(); i++) {
			collect = collect + doc.get(i);
		}
		ArrayList<Color> returnVal = new ArrayList<Color>();
		parse("", 0, collect.length(), collect, false, false, returnVal);

		return returnVal;
	}

	private void parse(String track, int start, int end, String doc, boolean inString, boolean inString2,
			ArrayList<Color> past) {

		String portion = doc.substring(start, end);
		while (portion.length() > 0) {

			if (new Character(portion.charAt(0)).equals(new Character('\"'))) {
				if (inString || inString2) {
					past.add(cyan);
					// parse("", start + 1, end, doc, false, inString2, past);
					track = "";
					inString = false;
				} else {
					past.add(cyan);
					// parse("", start + 1, end, doc, true, inString2, past);
					track = "";
					inString = true;
				}
			} else {
				if (inString) {
					past.add(cyan);
					// parse("", start + 1, end, doc, true, inString2, past);
					track = "";
					inString = true;
				} else if (new Character(portion.charAt(0)).equals(new Character('\''))) {
					if (inString || inString2) {
						past.add(cyan);
						// parse("", start + 1, end, doc, inString, false,
						// past);
						track = "";
						inString2 = false;
					} else {
						past.add(cyan);
						// parse("", start + 1, end, doc, inString, true, past);
						track = "";
						inString2 = true;
					}
				} else {
					String newReg = "[\n\\s\\{\\}\\(\\)\\+\\-\\/\\*]";
					boolean newTrack = false;
					past.add(Color.decode("#657b83"));
					if (("" + portion.charAt(0)).matches(newReg)) {

						newTrack = true;
					}
					for (String word : redWords) {
						if ((track + portion.charAt(0)).equals(word)
								&& ((portion + " ").charAt(1) + "").matches(newReg)) {
							for (int i = 0; i < (track + portion.charAt(0)).length(); i++) {
								past.set(past.size() - 1 - i, red);

							}

							newTrack = true;
						}
					}
					if ((track + portion.charAt(0)).matches("[1234567890\\.]+")
							&& ((portion + " ").charAt(1) + "").matches(newReg)) {
						for (int i = 0; i < (track + portion.charAt(0)).length(); i++) {
							past.set(past.size() - 1 - i, blue);

						}
						newTrack = true;
					}

					for (String word : yellowWords) {
						if ((track + portion.charAt(0)).equals(word)
								&& ((portion + " ").charAt(1) + "").matches(newReg)) {
							for (int i = 0; i < (track + portion.charAt(0)).length(); i++) {
								past.set(past.size() - 1 - i, yellow);
							}
							newTrack = true;
						}
					}
					for (String word : orangeWords) {
						if ((track + portion.charAt(0)).equals(word)
								&& ((portion + " ").charAt(1) + "").matches(newReg)) {
							for (int i = 0; i < (track + portion.charAt(0)).length(); i++) {
								past.set(past.size() - 1 - i, orange);
							}
							newTrack = true;
						}
					}
					for (String word : violetWords) {
						if ((track + portion.charAt(0)).equals(word)
								&& ((portion + " ").charAt(1) + "").matches(newReg)) {
							for (int i = 0; i < (track + portion.charAt(0)).length(); i++) {
								past.set(past.size() - 1 - i, violet);
							}
							newTrack = true;
						}
					}

					track = newTrack ? "" : (track + portion.charAt(0));
					// inString=inString;
					// parse(newTrack ? "" : (track + portion.charAt(0)), start
					// + 1, end, doc, false, inString2, past);

				}
			}
			start++;
			portion = doc.substring(start, end);
		}

	}
}
