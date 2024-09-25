import java.util.Objects;

import processing.core.PApplet;


public class World implements IWorld{
	int x;
	int y;
	
	public World(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int hashCode() {
		return Objects.hash(x, y);
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		World other = (World) obj;
		return x == other.x && y == other.y;
	}
	
	public PApplet draw(PApplet w) {
		w.background(255); // white background
		w.fill(0, 0, 255); // solid blue
		w.rect(this.x, this.y, 30, 15); 
		return w;
	}
	
	public IWorld update(){
		if (this.y < 400) {
			return new World(this.x, this.y + 1);
		}
		else {
			return this;
		}
	}
	
	public IWorld mousePressed() {
		return null;
		
	}
}