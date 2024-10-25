import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KeyManagerTest {
	
	KeyManager km = new KeyManager();
	
	@Test
	void test() {
		char[] noteKeys = {'a','s','d','f'};
		
		// Sequentially Press all of the note keys
		for(int i = 0; i<4; i++) {
			km.press(noteKeys[i]);
			for(int j=0; j<4; j++)
				assertEquals(j<=i,km.isNotePressed(j));
		}
		
		// Sequentially release all of the note keys
		for(int i = 0; i<4; i++) {
			km.release(noteKeys[i]);
			for(int j=0; j<4; j++)
				assertEquals(j>i,km.isNotePressed(j));
		}
		
		// Press space and check isSpacePressed()
		km.press(' ');
		assertTrue(km.isSpacePressed());
		// Subsequent checks to isSpacePressed() should be false
		assertFalse(km.isSpacePressed());
		// Recalling km.press(' ') should not work
		km.press(' ');
		assertFalse(km.isSpacePressed());
		// Releasing space and re-pressing will make isSpacePressed() return true
		km.release(' ');
		km.press(' ');
		assertTrue(km.isSpacePressed());
		// But only once
		assertFalse(km.isSpacePressed());
		
		
		
	}
}
