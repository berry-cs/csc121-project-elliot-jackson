import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class StartWorld implements IWorld {
	
	public final static int size = 32;
	public final static int Xpos = 275;
	public final static int Ypos = 250;
	int background;
	int text;

	/** produce a visual rendering of this world on the given window */
	public PApplet draw(PApplet p) { 
		background = p.color(200);
		text = p.color(0, 255, 255);
		p.background(background);
		p.fill(0, 255, 255);
		p.textSize(size);
		p.text("Press Enter to Start", Xpos, Ypos);
		return p;
	}


	public IWorld mousePressed(MouseEvent mev) {
		return new LevelSelectWorld();
	}

	public IWorld keyPressed(KeyEvent kev) {
		if (kev.getKeyCode() == PApplet.ENTER) {
			return new LevelSelectWorld();
		}
		else {
			return this;
		}
	}

}
