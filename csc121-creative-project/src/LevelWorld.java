import java.util.Objects;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import playfield.*;

public class LevelWorld implements IWorld{
	
	PlayField pf;
	KeyManager km;
	double speed;
	RenderObject gameRender;
	int score = PlayField.score;
	
	public LevelWorld(PApplet p, String filename) {
		
		Song s = Song.loadFile(filename);
		if (s == null) {
			System.out.println("Can't load file");
		}
		
		for(Song.Info i : Song.getSongList("data/levels")) {
			System.out.println(i.title());
		}
		
		km = new KeyManager();
		pf = new PlayField(s, km);
		
		RenderList renderList = new RenderList(p);
		renderList.createAdd("data/images/playfield_bottom.png", p.width, p.height);
		renderList.createAdd(pf.getNoteArray());
		renderList.createAdd("data/images/playfield_top.png", p.width, p.height);
		gameRender = renderList;
		pf.start();
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
		gameRender.render();
		p.fill(255, 255, 255);
		p.textSize(32);
		p.text("" + score, 550, 250);
		return p;
	}
	
	public IWorld update(){
		pf.unpause();
		pf.update(); 
		return this;
	}
	
	public IWorld mousePressed(MouseEvent mev) {
		return this;
	}
	
	public IWorld keyPressed(KeyEvent kev) {
		if (kev.getKey() == 'p') {
			pf.pause();
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