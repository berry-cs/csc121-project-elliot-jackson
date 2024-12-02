import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class StartWorld implements IWorld {
	PApplet p;
	RenderObject gameRender;
	
	
	
	public StartWorld(PApplet p) {
		this.p = p;
		RenderList rl = new RenderList(p);
		rl.createAdd("data/images/chalkboard.jpg", p.width, p.height);
		gameRender = rl;
	}

	/** produce a visual rendering of this world on the given window */
	public PApplet draw(PApplet p) { 
		this.p = p;
		gameRender.render();
		p.fill(255, 255, 255);
		p.textSize(32);
		p.text("Rhythym Legends", 275, 250);
		p.text("Press enter to Start", 275, 300);
		return p;
	}


	public IWorld mousePressed(MouseEvent mev) {
		return new LevelSelectWorld(p);
	}

	public IWorld keyPressed(KeyEvent kev) {
		if (kev.getKeyCode() == PApplet.ENTER) {
			return new LevelSelectWorld(p);
		}
		else {
			return this;
		}
	}

}
