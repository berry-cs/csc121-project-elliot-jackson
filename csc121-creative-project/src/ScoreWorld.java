import processing.core.PApplet;
import processing.event.MouseEvent;

public class ScoreWorld implements IWorld {
	public final static int size = 32;
	public final static int Xpos = 350;
	public final static int Ypos = 250;
	
	PApplet p;
	
	public ScoreWorld(PApplet p) {
		this.p = p;
	}
	
	public PApplet draw(PApplet p) { 
		int background = p.color(255);
		int text = p.color(255, 0, 255);
		p.background(background);
		p.fill(text);
		p.textSize(size);
		p.text("Congrats!", Xpos, Ypos);
		return p;
	}
	
	public IWorld mousePressed(MouseEvent mev) {
		return new LevelSelectWorld(p);
	}
}
