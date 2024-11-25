import java.util.ArrayList;
import java.util.Objects;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import playfield.*;

public class LevelSelectWorld implements IWorld{
	
	ArrayList<Song.Info> songList;
	int index;
	
	
	int background;
	int text;
	
	public LevelSelectWorld() {
		PApplet p = new PApplet();
		background = p.color(200);
		text = p.color(0);
		
		songList = Song.getSongList("data/levels");
		
		index = 0;
	}
	
	public PApplet draw(PApplet p) {
		p.background(background);
		p.fill(text);
		
		int Ypos = 200;
		int Xpos = 300;
		for(Song.Info i : Song.getSongList("data/levels")) {
			p.text(i.title(), Xpos, Ypos);
			Ypos += 30;
		}
		p.text(">", 280, 200+index*30);
		
		return p;
	}
	
	public IWorld update(){
		return this;
	}
	
	public IWorld mousePressed(MouseEvent mev) {
		return this;
	}
	
	public IWorld keyPressed(KeyEvent kev) {
		switch(kev.getKey()) {
		case 'w':
			index = Math.max(index-1, 0);
			break;
		case 's':
			index = Math.min(index+1, songList.size()-1);
			break;
		case ' ':
			return new LevelWorld(songList.get(index).filename());
		}
		return this;
	}
}