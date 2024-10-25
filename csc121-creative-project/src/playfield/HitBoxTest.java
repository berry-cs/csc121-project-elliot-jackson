package playfield;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HitBoxTest {
	
	HitBox hb1 = new HitBox(5,6,10,10);
	HitBox hb2 = new HitBox(0,0,6,5);
	
	HitBox hb3 = new HitBox(0,0,2,2);
	HitBox hb4 = new HitBox(0,1,2,2);
	
	HitBox hb5 = new HitBox(0.5f, 0.5f, 1, 1);
	
	@Test
	void getterTest() {
		assertEquals(5,hb1.x());
		assertEquals(6,hb1.y());
		assertEquals(0,hb2.x());
		assertEquals(0,hb2.y());
		
		assertEquals(10,hb1.width());
		assertEquals(10,hb1.height());
		assertEquals(6,hb2.width());
		assertEquals(5,hb2.height());
		
		assertEquals(10,hb1.centerX());
		assertEquals(11,hb1.centerY());
		assertEquals(3,hb2.centerX());
		assertEquals(2.5,hb2.centerY());
	}
	
	@Test
	void addYTest() {
		hb1.addY(6);
		assertEquals(17,hb1.centerY());
		hb1.addY(-2);
		assertEquals(15,hb1.centerY());
		
		hb2.addY(-3);
		assertEquals(-0.5,hb2.centerY());
		hb2.addY(5.5f);
		assertEquals(5,hb2.centerY());
	}
	
	@Test
	void insideTest() {
		assertTrue(hb3.isInside(0, 0));
		assertTrue(hb3.isInside(2, 0));
		assertTrue(hb3.isInside(0, 2));
		assertTrue(hb3.isInside(2, 2));
		assertTrue(hb3.isInside(1, 1));
		
		assertFalse(hb3.isInside(-0.1f, -0.1f));
		assertFalse(hb3.isInside(2.1f, -0.1f));
		assertFalse(hb3.isInside(-0.1f, 2.1f));
		assertFalse(hb3.isInside(2.1f, 2.1f));
	}
	
	@Test
	void touchingTest() {
		assertTrue(hb3.touching(hb4));
		assertTrue(hb4.touching(hb3));
		hb4.addY(2);
		assertFalse(hb3.touching(hb4));
		assertFalse(hb4.touching(hb3));
	}
	
	@Test
	void containedByTest() {
		assertFalse(hb4.containedBy(hb3));
		assertTrue(hb5.containedBy(hb3));
		
	}
	
	
}
