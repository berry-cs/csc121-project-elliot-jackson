import java.util.ArrayList;

import processing.core.PApplet;

public class PlayField {
	HitBox loc;
	HitBox strumBar;
	HitBox missed;
	HitBox spawners[] = {null, null, null, null};
	ArrayList<Note> notes;
	
	KeyManager km;
	
	public PlayField(float locX, float locY, KeyManager km) {
		loc = new HitBox(locX, locY, 300, 555);
		strumBar = new HitBox(loc.x(), loc.y()+15, loc.width(), 30);
		missed = new HitBox(loc.x(), loc.y()+5, loc.width(), 10);
		notes = new ArrayList<Note>();
		this.km = km;
		
		/** Generate Spawners */
		for(int i = 0; i < 4; i++) {
			float x = (loc.width() / 5)* i + (loc.width() / 5);
			//spawners[i] = new NoteSpawner(x, loc.height()-50, i);
			spawners[i] = new HitBox(x, loc.height()-50, 50, 50);
		}
	}
	
	public void update() {
		if(km.isSpacePressed()) strum();
		
		for(Note n : notes) {
			n.update();
			if (n.touching(missed) && !n.missed) {
				n.missed = true;
				
			}
		}
		
		cullNotes();
	}
	
	public void draw(PApplet p) {
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
