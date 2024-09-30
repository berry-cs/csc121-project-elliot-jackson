import java.util.Objects;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;


public class World implements IWorld{
	
	PlayField pf;
	
	public World() {
		
		pf = new PlayField(5,5);
	}
	
	public int hashCode() {
		return Objects.hash(pf);
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		World other = (World) obj;
		return Objects.equals(pf, other.pf);
	}
	
	public PApplet draw(PApplet p) {
		p.background(200);
		pf.draw(p);
		return p;
	}
	
	public IWorld update(){
		pf.update();
		return this;
	}
	
	public IWorld mousePressed(MouseEvent mev) {
		pf.randomNote();
		return this;
	}
}