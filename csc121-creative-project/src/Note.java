import processing.core.PApplet;

public class Note {
	HitBox loc;
	float speed;
	
	public Note(float centerX, float centerY) {
		loc = HitBox.newByCenter(centerX, centerY, 20, 20);
		speed = 1;
	}
	
	public void update() {
		loc.addY(-speed);
	}
	
	public void draw(PApplet p) {
		p.fill(255,255,0);
		p.rect(loc.x(), loc.y(), 20, 20);
	}
	
}
