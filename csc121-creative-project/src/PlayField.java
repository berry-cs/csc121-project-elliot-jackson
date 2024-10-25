import java.util.ArrayList;

import processing.core.PApplet;

public class PlayField {
	HitBox loc;
	HitBox strumBar;
	HitBox missed;
	ArrayList<Note> notes;
	HitBox spawners[] = {null, null, null, null};
	KeyManager km;
	
	final static int XPOS	= 10;
	final static int YPOS	= 10;
	final static int WIDTH  = 300;
	final static int HEIGHT = 555;
	
	public PlayField(KeyManager km) {
		this.km = km;
		
		loc 		= new HitBox(XPOS, YPOS, 	WIDTH, HEIGHT);
		strumBar 	= new HitBox(XPOS, YPOS+15, WIDTH, 30);
		missed 		= new HitBox(XPOS, YPOS+5, 	WIDTH, 10);
		notes 		= new ArrayList<Note>();
		
		/** Generate Spawners */
		for(int i = 0; i < 4; i++) {
			float x = (WIDTH / 5)* i + loc.x() + WIDTH/10;
			spawners[i] = new HitBox(x, YPOS+HEIGHT-55, 50, 50);
		}
	}
	
	/* PUBLIC METHODS */
	
	public void update() {
		if(km.isSpacePressed()) strum();
		
		for(Note n : notes) {
			n.update();
			if (n.touching(missed) && !n.missed) n.missed = true;
		}
		cullNotes();
	}
	
	public void render(PApplet p) {
		loc.draw(p);
		strumBar.draw(p);
		missed.draw(p);
		for(int i = 0; i < 4; i++) {
			spawners[i].draw(p);
		}
		for(Note n : notes) {
			n.draw(p);
		}
	}
	
	public void randomNote() {
		int s = (int)(Math.random() * (3 + 1));
		notes.add(new Note(spawners[s].centerX(), spawners[s].centerY(), s));
	}
	
	/* PRIVATE HELPER METHODS */
	
	private void strum() {
		for(Note n : notes) {
			int track = n.track();
			if (!km.isNotePressed(track)) continue;
			if (n.containedBy(strumBar)) {
				n.cull();
			}
		}
	}
	
	/** Culls notes by iterating over ArrayList backwards */
	private void cullNotes() {
		for(int i = notes.size() - 1; i >= 0; i--) {
			if (notes.get(i).shouldCull)
				notes.remove(i);
		}
	}
}
