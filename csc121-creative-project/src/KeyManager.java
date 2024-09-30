public class KeyManager {
	private String noteKeys = "asdf";
	private boolean[] notePress = {false,false,false,false};
	private boolean space = false;
	private boolean spacePressed = false;
	
	public KeyManager() {}


	public void press(char c) {
		if (c == ' ' && !spacePressed) spacePressed = space = true;
		
		int i = noteKeys.indexOf(c);
		if(i < 0) return;
		notePress[i] = true;
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
}
