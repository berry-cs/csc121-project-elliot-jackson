import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import playfield.*;


public interface RenderObject {
	public void render();
}


class hitBoxRenderer implements RenderObject {
	PApplet p;
	HitBox hb;
	
	hitBoxRenderer(PApplet p, HitBox hb) {
		this.p = p;
		this.hb = hb;
	}
	
	public void render() {
		p.noFill();
		p.rect(hb.x(), hb.y(), hb.width(), hb.height());
	}
}

class notesRenderer implements RenderObject {
	PApplet p;
	ArrayList<Note> notes;
	image ro[] = {null,null,null,null};
	
	notesRenderer(PApplet p, ArrayList<Note> notes) {
		this.p = p;
		this.notes = notes;
		
		for(int i=0; i<4; i++) {
			ro[i] = new image(p,"data/images/"+Integer.toString(i)+"-note.png", Note.SIZE, Note.SIZE);
		}
	}
	
	public void render() {
		for(Note n : notes) {
			ro[n.track()].render(n.x(), n.y());
		}
	}
}


class listRO implements RenderObject {
	ArrayList<RenderObject> list;
	PApplet p;
	
	listRO(PApplet p) {
		this.p = p;
		list = new ArrayList<RenderObject>();
	}
	
	void add(RenderObject ro) {
		list.add(ro);
	}
	
	void add(ArrayList<RenderObject> l) {
		for(RenderObject ro : l) {
			list.add(ro);
		}
	}
	
	public void render() {
		for(RenderObject ro : list) {
			ro.render();
		}
	}
}


class image implements RenderObject {
	PApplet p;
	PImage img;
	
	image(PApplet p, String filename, int width, int height) {
		this.p = p;
		img = p.loadImage(filename);
		img.resize(width, height);
	}
	
	image(PApplet p, String filename) {
		this.p = p;
		img = p.loadImage(filename);
	}
	
	public void render() {
		p.image(img, 0, 0);
	}
	
	public void render(float x, float y) {
		p.image(img, x, y);
	}
}
