package playfield;

import processing.event.KeyEvent;

public class KeyManager {
	private String noteKeys = "asdf";
	private boolean[] notePress = {false,false,false,false};
	private boolean space = false;
	private boolean spacePressed = false;
	private boolean[] directions = {false,false,false,false};
	private boolean enter = false;
	
	public KeyManager() {}

	public void press(KeyEvent ke) {
		System.out.println((int)ke.getKey() + " | " + ke.getKeyCode());
		if (ke.getKey() != 65535) {
			press(ke.getKey());
			return;
		}
		
		switch(ke.getKeyCode()) {
		case 37:
			press('a'); break;
		case 38:
			press('w'); break;
		case 39:
			press('d'); break;
		case 40:
			press('s'); break;
		case 10:
			enter = true;
		}
		
	}
	
	public void press(char c) {
		if (c == ' ' && !spacePressed) spacePressed = space = true;
		
		int i = noteKeys.indexOf(c);
		if(i < 0) return;
		notePress[i] = true;
	}
	
	public void release(KeyEvent ke) {
		System.out.println((int)ke.getKey() + " | " + ke.getKeyCode());
		if (ke.getKey() != 65535) {
			release(ke.getKey());
			return;
		}
		
		switch(ke.getKeyCode()) {
		case 37:
			release('a'); break;
		case 38:
			release('w'); break;
		case 39:
			release('d'); break;
		case 40:
			release('s'); break;
		case 10:
			enter = false;
		}
		
	}
	
	public void release(char c) {
		if (c == ' ') spacePressed = false;
		
		int i = noteKeys.indexOf(c);
		if(i < 0) return;
		notePress[i] = false;
	}
	
	public boolean isNotePressed(int index) {
		if (index < 0 || index > 3) return false;
		return notePress[index];
	}
	
	public boolean isSpacePressed() {
		boolean s = space && spacePressed;
		space = false;
		return s;
	}
	
	public boolean isEnterPressed() {
		return enter;
	}
}
