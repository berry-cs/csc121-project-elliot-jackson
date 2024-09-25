import processing.core.PApplet;

public class Note {
	int x;
	int y;
	int speed;
	
	public Note(int x, int y) {
		this.x = x;
		this.y = y;
		speed = 1;
	}
	
	public void update() {
		this.y -= speed;
	}
	
	public void draw(PApplet p) {
		p.fill(255,255,0);
		p.rect(x, y, 20, 20);
	}
	
}
