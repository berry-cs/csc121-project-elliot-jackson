import processing.core.PApplet;
import processing.event.MouseEvent;

public class PauseWorld implements IWorld {

	IWorld saved;
	

	public PauseWorld(IWorld saved) {
		this.saved = saved;
	}

	/** produce a visual rendering of this world on the given window */
	public PApplet draw(PApplet p) { 
		p.background(200);
		p.fill(255, 0, 255);
		p.textSize(32);
		p.text("Paused", 350, 250);
		return p;
	}


	public IWorld mousePressed(MouseEvent mev) {
		return saved;
	}



}
