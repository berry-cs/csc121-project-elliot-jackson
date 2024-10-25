import processing.core.PApplet;

public class Note {
	private HitBox loc;
	private float speed;
	private int track;
	
	public boolean shouldCull;
	public boolean missed;
	
	final static int SIZE = 20;
	
	public Note(float centerX, float centerY, int track) {
		loc = new HitBox(centerX-(SIZE/2),centerY-(SIZE/2),SIZE,SIZE);
		speed = 1;
		this.track = track;
		shouldCull = false;
		missed = false;
	}
	
	public void cull() {
		shouldCull = true;
	}
	
	public boolean containedBy(HitBox hb) {
		return loc.containedBy(hb);
	}

	public boolean touching(HitBox hb) {
		return loc.touching(hb);
	}
	
	public void update() {
		loc.addY(-speed);
		
		if(loc.y() < -SIZE) {
			shouldCull = true;
		}
	}
	
	public void draw(PApplet p) {
		p.fill(255,255,0);
		if (missed) p.fill(255,0,0);
		p.rect(loc.x(), loc.y(), SIZE, SIZE);
	}
	
	public int track() {
		return track;
	}
	
}
