import java.util.ArrayList;

import processing.core.PApplet;

public class PlayField {
	HitBox loc;
	HitBox strumBar;
	NoteSpawner spawners[] = {null, null, null, null};
	ArrayList<Note> notes;
	
	
	
	public PlayField(float locX, float locY) {
		loc = new HitBox(locX, locY, 300, 555);
		strumBar = new HitBox(loc.x(), loc.y(), loc.width(), 100);
		notes = new ArrayList<Note>();
		
		/** Generate Spawners */
		for(int i = 0; i < 4; i++) {
			float x = (loc.width() / 5)* i + (loc.width() / 5);
			spawners[i] = new NoteSpawner(x, loc.height()-50);
		}
	}
	
	public void update() {
		for(Note n : notes) {
			n.update();
		}
	}
	
	public void draw(PApplet p) {
		loc.draw(p);
		strumBar.draw(p);
		for(int i = 0; i < 4; i++) {
			spawners[i].draw(p);
		}
		for(Note n : notes) {
			n.draw(p);
		}
	}
	
	public void randomNote() {
		int s = (int)(Math.random() * (3 + 1));
		notes.add(spawners[s].spawn());
	}
}
