import java.util.ArrayList;

import processing.core.PApplet;

public class PlayField {
	
	// Objects used by PlayField
	HitBox loc;
	HitBox strumBar;
	HitBox missed;
	ArrayList<Note> notes;
	KeyManager km;
	NoteQueue nq;
	
	// Static information
	final static int XPOS	= 10;
	final static int YPOS	= 10;
	final static int WIDTH  = 300;
	final static int HEIGHT = 555;
	
	// Note spawn information
	int spawnY; // Y-coordinate of note spawn locations
	float spawnX[] = {0,0,0,0}; // X-coordinates fo note spawn locations
	
	// Status Variables
	boolean running = false;	// Blocks the execution of update()
	long startTime;				// Initialized during start()
	long runningTime;			// Initialized at the start of update()
	
	// Note Speed information
	float timeDelta = 5000;		// The time in ms it takes for a note to reach strumBar from spawn
	float noteDelta;			// The distance from spawn to strumBar (initialized in constructor)
	
	public PlayField(NoteQueue nq, KeyManager km) {
		this.nq = nq;
		this.km = km;
		
		loc 		= new HitBox(XPOS, YPOS, 	WIDTH, HEIGHT);
		strumBar 	= new HitBox(XPOS, YPOS+15, WIDTH, 30);
		missed 		= new HitBox(XPOS, YPOS+5, 	WIDTH, 10);
		notes 		= new ArrayList<Note>();
		
		spawnY = YPOS+HEIGHT-55;
		noteDelta = spawnY-strumBar.centerY();
		
		/** Generate Spawn x coords */
		for(int i = 0; i < 4; i++) {
			spawnX[i] = (WIDTH / 5)* i + loc.x() + WIDTH/10;
		}
	}
	
	/* PUBLIC METHODS */
	
	/* Sets 'running' bool to true and initializes startTime */
	public void start() {
		running = true;
		startTime = System.currentTimeMillis();
	}
	
	public void update() {
		if (!running) return;
		
		runningTime = System.currentTimeMillis()-startTime;
		
		if(km.isSpacePressed()) strum();
		
		for(Note n : notes) {
			n.update(runningTime);
			if (n.touching(missed) && !n.missed) n.missed = true;
		}
		
		spawnNotes();
		cullNotes();
	}
	
	public void render(PApplet p) {
		loc.draw(p);
		strumBar.draw(p);
		missed.draw(p);
		for(int i = 0; i < 4; i++) {
			//spawners[i].draw(p);
		}
		for(Note n : notes) {
			n.draw(p);
		}
	}

	/* PRIVATE HELPER METHODS */
	
	private void strum() {
		for(Note n : notes) {
			int track = n.track();
			if (!km.isNotePressed(track)) continue;
			if (n.containedBy(strumBar)) {
				n.cull();
			}
		}
	}
	
	/** Spawns available notes from the NoteQueue */
	private void spawnNotes() {
		int track;
		float speed;
		while(runningTime > nq.curTime()) {
			track = nq.curTrack();
			speed = noteDelta/timeDelta;
			notes.add(new Note(spawnX[track],spawnY,track,speed,runningTime));
			nq.next();
		}
	}
	
	/** Culls notes by iterating over ArrayList backwards */
	private void cullNotes() {
		for(int i = notes.size() - 1; i >= 0; i--) {
			if (notes.get(i).shouldCull)
				notes.remove(i);
		}
	}
}
