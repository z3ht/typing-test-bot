import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TypingTest {
	
	private static HashMap<Character, Integer> specialKeys = new HashMap<>();
	private static List<Character> upperCaseKeys = new ArrayList<>();
	
	public static void main(String[] args) {
		
		Long startTime = System.currentTimeMillis();
		
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		List<String> input = new ArrayList<>();
		try {
			FileReader reader = new FileReader("Typing Test Text.txt");
			BufferedReader buffered = new BufferedReader(reader);
			
			String line;
			while ((line = buffered.readLine()) != null) {
				if (line.isEmpty())
					continue;
				input.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		initializeSpecialKeys();
		initializeUpperCaseKeys();
		
		try {
			Thread.sleep(1000 - (System.currentTimeMillis()-startTime));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		startTime = System.currentTimeMillis();
		int counter = 0;
		while (System.currentTimeMillis() - startTime < 10) {  // Change 10 to 60000 if you would like to do a one minute test
			String line = input.get(counter % input.size());
			for (char c : line.toCharArray()) {
				long beginTime = System.currentTimeMillis();
				boolean isCaps = (c >= 65 && c <= 90) || upperCaseKeys.contains(c);
				int key;
				try {
					key = specialKeys.get(c);
				} catch (NullPointerException e) {
					key = KeyEvent.getExtendedKeyCodeForChar(c);
				}
				
				if (isCaps)
					robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(key);
				robot.keyRelease(key);
				if (isCaps)
					robot.keyRelease(KeyEvent.VK_SHIFT);
				try {
					Thread.sleep(30 - (System.currentTimeMillis() - beginTime));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			counter += 1;
		}
	}
	
	private static void initializeUpperCaseKeys() {
		upperCaseKeys.add('~');
		upperCaseKeys.add('!');
		upperCaseKeys.add('@');
		upperCaseKeys.add('#');
		upperCaseKeys.add('$');
		upperCaseKeys.add('%');
		upperCaseKeys.add('^');
		upperCaseKeys.add('&');
		upperCaseKeys.add('*');
		upperCaseKeys.add('(');
		upperCaseKeys.add(')');
		upperCaseKeys.add('_');
		upperCaseKeys.add('+');
		upperCaseKeys.add('{');
		upperCaseKeys.add('}');
		upperCaseKeys.add('|');
		upperCaseKeys.add(':');
		upperCaseKeys.add('"');
		upperCaseKeys.add('<');
		upperCaseKeys.add('>');
		upperCaseKeys.add('?');
	}
	
	private static void initializeSpecialKeys() {
		specialKeys.put('`', KeyEvent.VK_BACK_QUOTE);
		specialKeys.put('~', KeyEvent.VK_BACK_QUOTE);
		specialKeys.put('!', KeyEvent.VK_1);
		specialKeys.put('@', KeyEvent.VK_2);
		specialKeys.put('#', KeyEvent.VK_3);
		specialKeys.put('$', KeyEvent.VK_4);
		specialKeys.put('%', KeyEvent.VK_5);
		specialKeys.put('^', KeyEvent.VK_6);
		specialKeys.put('&', KeyEvent.VK_7);
		specialKeys.put('*', KeyEvent.VK_8);
		specialKeys.put('(', KeyEvent.VK_9);
		specialKeys.put(')', KeyEvent.VK_0);
		specialKeys.put('{', KeyEvent.VK_OPEN_BRACKET);
		specialKeys.put('}', KeyEvent.VK_CLOSE_BRACKET);
		specialKeys.put('[', KeyEvent.VK_OPEN_BRACKET);
		specialKeys.put(']', KeyEvent.VK_CLOSE_BRACKET);
		specialKeys.put(';', KeyEvent.VK_SEMICOLON);
		specialKeys.put(':', KeyEvent.VK_SEMICOLON);
		specialKeys.put('\'', KeyEvent.VK_QUOTE);
		specialKeys.put('"', KeyEvent.VK_QUOTE);
		specialKeys.put('<', KeyEvent.VK_COMMA);
		specialKeys.put(',', KeyEvent.VK_COMMA);
		specialKeys.put('.', KeyEvent.VK_PERIOD);
		specialKeys.put('>', KeyEvent.VK_PERIOD);
		specialKeys.put('\\', KeyEvent.VK_BACK_SLASH);
		specialKeys.put('|', KeyEvent.VK_BACK_SLASH);
		specialKeys.put('/', KeyEvent.VK_SLASH);
		specialKeys.put('?', KeyEvent.VK_SLASH);
	}
	
}

