import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import playfield.*;


public interface RenderObject {
	public void render(PApplet p);
}

class RenderList implements RenderObject {
	ArrayList<RenderObject> list;

	RenderList() {
		list = new ArrayList<RenderObject>();
	}

	// Adding an existing RenderObject
	void add(RenderObject ro) {
		list.add(ro);
	}

	// Create and add an image from filename
	void createAdd(String filename, int width, int height) {
		this.add(new image(filename, width, height));
	}

	// Create and add a notesRenderer from ArrayList of Note objects
	void createAdd(ArrayList<Note> notes) {
		this.add(new notesRenderer(notes));
	}

	public void render(PApplet p) {
		for(RenderObject ro : list) {
			ro.render(p);
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

	public void render(PApplet p) {
		p.noFill();
		p.rect(hb.x(), hb.y(), hb.width(), hb.height());
	}
}


class notesRenderer implements RenderObject {
	ArrayList<Note> notes;
	image ro[] = {null,null,null,null};

	notesRenderer(ArrayList<Note> notes) {
		this.notes = notes;

		// Load note images from data
		for(int i=0; i<4; i++) {
			ro[i] = new image("data/images/"+Integer.toString(i)+"-note.png", Note.SIZE, Note.SIZE);
		}
	}

	public void render(PApplet p) {
		//System.out.println(notes);
		for(Note n : notes) {
			ro[n.track()].render(p, n.x(), n.y());
		}
	}
}


class image implements RenderObject {
	PImage img;
	String filename;
	int width;
	int height;

	image( String filename, int width, int height) {
		this.filename = filename;
		this.img = null;
		this.width = width;
		this.height = height;
	}
	
	private void loadImage(PApplet p) {
		if (img == null) { 
			img = p.loadImage(filename); 
			img.resize(width, height); 
		}
	}

	public void render(PApplet p) {
		loadImage(p);
		p.image(img, 0, 0);
	}

	public void render(PApplet p, float x, float y) {
		loadImage(p);
		p.image(img, x, y);
	}

}
