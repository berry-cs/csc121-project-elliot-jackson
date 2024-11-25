import playfield.Note;
import processing.core.PApplet;
import processing.event.MouseEvent;
import java.util.Objects;

public class PauseWorld implements IWorld {
	public final static int size = 32;
	public final static int Xpos = 350;
	public final static int Ypos = 250;
	int background;
	int text; 
	IWorld saved;
	

	public PauseWorld(IWorld saved) {
		this.saved = saved;
	}

	/** produce a visual rendering of this world on the given window */
	public PApplet draw(PApplet p) { 
		background = p.color(255);
		text = p.color(255, 0, 255);
		
		p.background(background);
		p.fill(255, 0, 255);
		p.textSize(size);
		p.text("Paused", Xpos, Ypos);
		return p;
	}


	public IWorld mousePressed(MouseEvent mev) {
		return saved;
	}



}
