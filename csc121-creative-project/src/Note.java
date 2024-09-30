import processing.core.PApplet;

public class Note {
	private HitBox loc;
	private float speed;
	
	public boolean shouldCull;
	
	public Note(float centerX, float centerY) {
		loc = HitBox.newByCenter(centerX, centerY, 20, 20);
		speed = 1;
		shouldCull = false;
	}
	
	public void update() {
		loc.addY(-speed);
		
		if(loc.y() < -20) {
			shouldCull = true;
		}
	}
	
	public void draw(PApplet p) {
		p.fill(255,255,0);
		p.rect(loc.x(), loc.y(), 20, 20);
	}
	
}
