import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class StartWorld implements IWorld {
	PApplet p;

	/** produce a visual rendering of this world on the given window */
	public PApplet draw(PApplet p) { 
		this.p = p;
		p.background(200);
		p.fill(0, 255, 255);
		p.textSize(32);
		p.text("Press Enter to Start", 275, 250);
		return p;
	}


	public IWorld mousePressed(MouseEvent mev) {
		return new LevelWorld(p);
	}

	public IWorld keyPressed(KeyEvent kev) {
		if (kev.getKeyCode() == PApplet.ENTER) {
			return new LevelWorld(p);
		}
		else {
			return this;
		}
	}

}
