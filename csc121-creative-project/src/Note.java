import processing.core.PApplet;

public class Note {
	private HitBox loc;
	private float speed;
	private int track;
	
	public boolean shouldCull;
	public boolean missed;
	
	public Note(float centerX, float centerY, int track) {
		loc = new HitBox(centerX-10,centerY-10,20,20);
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
		
		if(loc.y() < -20) {
			shouldCull = true;
		}
	}
	
	public void draw(PApplet p) {
		p.fill(255,255,0);
		if (missed) p.fill(255,0,0);
		p.rect(loc.x(), loc.y(), 20, 20);
	}
	
	public int track() {
		return track;
	}
	
}
