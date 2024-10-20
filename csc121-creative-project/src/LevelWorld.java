import java.util.Objects;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;


public class LevelWorld implements IWorld{
	
	PlayField pf;
	KeyManager km;
	double speed;
	
	public LevelWorld() {
		
		km = new KeyManager();
		pf = new PlayField(5,5, km);
		speed = 1;
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
		LevelWorld other = (LevelWorld) obj;
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
	
	public IWorld keyPressed(KeyEvent kev) {
		if (kev.getKey() == 'p') {
			return new PauseWorld(this);
		}
		km.press(kev.getKey());
		return this;
	}

	public IWorld keyReleased(KeyEvent kev) {
		km.release(kev.getKey());
		return this;
	}
}