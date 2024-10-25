package playfield;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NoteTest {
	
	Note n = new Note(0, 200, 0, 2, 0);

	@Test
	void test() {
		assertEquals(200,n.loc().centerY());
		n.update(1);
		assertEquals(198,n.loc().centerY());
		n.update(11);
		assertEquals(178,n.loc().centerY());
		n.update(100000);
		assertTrue(n.shouldCull);
	}

}
