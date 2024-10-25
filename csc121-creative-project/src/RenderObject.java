import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import playfield.*;


public interface RenderObject {
	public void render();
}

class RenderList implements RenderObject {
	ArrayList<RenderObject> list;
	PApplet p;
	
	RenderList(PApplet p) {
		this.p = p;
		list = new ArrayList<RenderObject>();
	}
	
	// Adding an existing RenderObject
	void add(RenderObject ro) {
		list.add(ro);
	}
	
	// Create and add an image from filename
	void createAdd(String filename, int width, int height) {
		this.add(new image(p, filename, width, height));
	}
	
	// Create and add a notesRenderer from ArrayList of Note objects
	void createAdd(ArrayList<Note> notes) {
		this.add(new notesRenderer(p, notes));
	}
	
	public void render() {
		for(RenderObject ro : list) {
			ro.render();
		}
	}
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
		
		// Load note images from data
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


class image implements RenderObject {
	PApplet p;
	PImage img;
	
	image(PApplet p, String filename, int width, int height) {
		this.p = p;
		img = p.loadImage(filename);
		img.resize(width, height);
	}

	public void render() {
		p.image(img, 0, 0);
	}
	
	public void render(float x, float y) {
		p.image(img, x, y);
	}
}
