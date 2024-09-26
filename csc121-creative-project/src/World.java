import java.util.ArrayList;
import java.util.Objects;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;


public class World implements IWorld{
	int x;
	int y;
	
	ArrayList<Note> notes;
	
	public World(int x, int y) {
		this.x = x;
		this.y = y;
		
		notes = new ArrayList<Note>();
		notes.add(new Note(50,200));
		
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
		for(Note n : notes) {
			n.draw(w);
		}
		
		return w;
	}
	
	public IWorld update(){
		
		for(Note n : notes) {
			n.update();
		}
		
		/*
		if (this.y < 400) {
			return new World(this.x, this.y + 1);
		}
		else {
			return this;
		}
		*/
		return this;
	}
	
	public IWorld mousePressed(MouseEvent mev) {
		System.out.println("press");
		notes.remove(0);
		notes.add(new Note(50,200));
		return this;
	}
}