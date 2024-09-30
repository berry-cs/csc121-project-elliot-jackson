import processing.core.PApplet;

public class NoteSpawner {
	private HitBox loc;
	private int track;
	
	public NoteSpawner(float centerX, float centerY, int track) {
		loc = HitBox.newByCenter(centerX, centerY, 50, 50);
		this.track = track;
	}
	
	public Note spawn() {
		return new Note(loc.centerX(), loc.centerY(), track);
	}
	
	public void draw(PApplet p) {
		loc.draw(p);
	}
}
