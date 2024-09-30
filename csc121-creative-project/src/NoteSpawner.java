import processing.core.PApplet;

public class NoteSpawner {
	HitBox loc;
	
	public NoteSpawner(float centerX, float centerY) {
		loc = HitBox.newByCenter(centerX, centerY, 50, 50);
	}
	
	public Note spawn() {
		return new Note(loc.centerX(), loc.centerY());
	}
	
	public void draw(PApplet p) {
		loc.draw(p);
	}
}
