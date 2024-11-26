import processing.core.PApplet;
import processing.event.MouseEvent;
import playfield.*;

public class ScoreWorld implements IWorld {
	PlayField pf;
	KeyManager km;

	public final static int size = 32;
	public final static int Xpos = 350;
	public final static int Ypos = 250;
	
	PApplet p;
	
	public ScoreWorld(PApplet p, PlayField pf) {
		this.p = p;
		this.pf = pf;
	}
	
	public PApplet draw(PApplet p) { 
		int background = p.color(255);
		int text = p.color(255, 0, 255);
		p.background(background);
		p.fill(text);
		p.textSize(size);
		p.text("Congrats!", Xpos, Ypos);
		p.text(pf.averageHit(), Xpos, Ypos + 50);
		return p;
	}

	public IWorld mousePressed(MouseEvent mev) {
		return new LevelSelectWorld(p);
	}
}
