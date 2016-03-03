package program;

import java.awt.List;
import java.util.ArrayList;

public class SimpleDocument {
	public ArrayList<Character> doc = new ArrayList<Character>();

	public void insertString(int begin, int end, String s) {
		ArrayList<Character> after = new ArrayList<Character>(doc.subList(end,
				doc.size()));
		doc = new ArrayList<Character>(doc.subList(0, begin));
		doc.addAll(stringToCharList(s));
		doc.addAll(after);
	}

	public void deleteString(int begin, int end) {
		ArrayList<Character> after = new ArrayList<Character>(doc.subList(end,
				doc.size() - 1));
		doc = new ArrayList<Character>(doc.subList(0, begin));
		doc.addAll(after);
	}

	public int whatLineHasPos(int pos) {
		int count = 0;
		// System.out.println(s.toString());
		int charNum = 0;
		for (Character c : this.doc) {
			if (charNum >= pos) {
				break;
			}
			charNum++;
			if (new Character(c).equals(new Character('\n'))) {
				count++;
			}
		}
		return count;
	}

	public int whatPosInLineHasPos(int pos) {
		int count = 0;
		// System.out.println(s.toString());
		int charNum = 0;
		int fromStart = 0;
		for (Character c : this.doc) {
			if (charNum >= pos) {
				break;
			}
			charNum++;
			fromStart++;
			if (new Character(c).equals(new Character('\n'))) {
				count++;
				fromStart = 0;
			}
		}
		return fromStart;
	}

	public int posForPosInLine(int posInLine, int line) {
		int count = 0;
		System.out.println("line fetch:" + line);
		int charNum = 0;
		int fromStart = 0;
		for (Character c : this.doc) {
			if (fromStart >= posInLine && line == count) {
				break;
			}
			if (new Character(c).equals(new Character('\n')) && count == line) {

				break;
			}
			charNum++;
			fromStart++;

			if (new Character(c).equals(new Character('\n'))) {
				count++;
				fromStart = 0;
			}
		}
		return charNum;
	}

	public SimpleDocument() {
		doc = new ArrayList<Character>();
	}

	public int tabsForLineForPos(int pos) {
		if (whatLineHasPos(pos) > this.lineCount() - 1 || pos < 0) {
			return 0;
		} else {
			int count = 0;
			int tcount = 0;
			// System.out.println(s.toString());
			int charNum = 0;
			boolean beginning = true;
			for (Character c : this.doc) {
				if (charNum == pos) {
					break;
				}
				if (new Character(c).equals(new Character(' ')) && beginning) {
					tcount++;

				} else {
					beginning = false;
				}
				charNum++;
				if (new Character(c).equals(new Character('\n'))) {
					count++;
					beginning = true;
					tcount = 0;
				}
			}
			System.out.println((int) Math.floor(tcount / 4));
			return (int) Math.floor(tcount / 4);
		}
	}

	private ArrayList<Character> stringToCharList(String s) {
		ArrayList<Character> l = new ArrayList<Character>();
		for (char c : s.toCharArray()) {
			l.add(new Character(c));
		}
		return l;
	}

	public int lineCount() {

		int count = 1;
		// System.out.println(s.toString());
		for (Character c : this.doc) {

			if (new Character(c).equals(new Character('\n'))) {
				count++;
			}
		}
		return count;
	}
}
