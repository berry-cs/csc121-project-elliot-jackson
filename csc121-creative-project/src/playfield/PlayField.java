package playfield;
import java.util.ArrayList;

public class PlayField {
	
	// Objects used by PlayField
	HitBox loc;
	HitBox strumBar;
	HitBox missed;
	ArrayList<Note> notes;
	Song song;
	KeyManager km;
	NoteQueue nq;
	
	// Static information
	final static int XPOS	= 110;
	final static int YPOS	= 35;
	final static int WIDTH  = 265;
	final static int HEIGHT = 530;
	
	// Note spawn information
	int spawnY; // Y-coordinate of note spawn locations
	float spawnX[] = {0,0,0,0}; // X-coordinates for note spawn locations
	
	// Status Variables
	public boolean running = false;	// Blocks the execution of update(). Set to True when level is over
	long startTime;				// Initialized during start()
	long runningTime;			// Initialized at the start of update()
	long stopTime = -1;			// The time in ms the PlayField should stop updating. Is set when NoteQueue's end is reached  
	
	// Pause Variables
	boolean paused;				// Temporarily pauses update()
	long pauseStartTime;		// Time of pause
	long pauseOffsetTime = 0;	// Total time paused- used to offset note movement
	
	// Note Speed information
	long timeDelta = 8000;		// The time in ms it takes for a note to reach strumBar from spawn
	float noteDelta;			// The distance from spawn to strumBar (initialized in constructor)
	
	
	// Score information
	public static int score = 0;
	ArrayList<Boolean> streak = new ArrayList<Boolean>();
	static int MaxScore;
	
	public PlayField(Song s, KeyManager km) {
		this.song = s;
		this.nq = s.nq;
		this.km = km;
		
		loc 		= new HitBox(XPOS, YPOS, 	WIDTH, HEIGHT);
		strumBar 	= new HitBox(XPOS, YPOS+15, WIDTH, 30);
		missed 		= new HitBox(XPOS, YPOS, 	WIDTH, 15);
		notes 		= new ArrayList<Note>();
		
		spawnY = YPOS+HEIGHT-55;
		noteDelta = spawnY-strumBar.centerY();
		
		/** Generate Spawn x coords */
		for(int i = 0; i < 4; i++) {
			spawnX[i] = (WIDTH / 5) * 1.05f * (i+1) + loc.x();
		}
	}
	
	/* PUBLIC METHODS */
	
	public boolean isRunning() {
		return this.running;
	}
	
	/* Sets 'running' bool to true and initializes startTime */
	public void start() {
		running = true;
		startTime = System.currentTimeMillis();
		nq.reset();
	}
	
	public void update() {
		if (!running) return;
		if (paused) return;
		
		runningTime = System.currentTimeMillis()-startTime-pauseOffsetTime;
		
		if (runningTime > stopTime && stopTime != -1) running = false;
		
		if(km.isSpacePressed()) strum();
		
		for(Note n : notes) {
			n.update(runningTime);
			if (n.loc().touching(missed) && !n.missed) n.cull();
		}
		
		spawnNotes();
		cullNotes();
	}
	
	public ArrayList<Note> getNoteArray() {
		return this.notes;
	}

	public void pause() {
		if (paused) return;
		paused = true;
		pauseStartTime = System.currentTimeMillis();
	}
	
	public void unpause() {
		if (!paused) return;
		paused = false;
		pauseOffsetTime += System.currentTimeMillis() - pauseStartTime;
	}

	/* PRIVATE HELPER METHODS */
	
	private void strum() {
		for(Note n : notes) {
			int track = n.track();
			if (!km.isNotePressed(track)) continue;
			if (n.loc().containedBy(strumBar)) {
				n.cull();
				score = score + 5;
			}
		}
	}
	
	/** Spawns available notes from the NoteQueue */
	private void spawnNotes() {
		if(stopTime != -1) return;	// Return if we have already reached the end of the queue
		int track;
		float speed;
		while(runningTime > nq.curTime()) {
			track = nq.curTrack();
			speed = noteDelta/timeDelta;
			notes.add(new Note(spawnX[track],spawnY,track,speed,runningTime));
			
			// If we have reached the end of the queue, set stoptime and exit while loop
			if (!nq.next()) {
				stopTime = runningTime + timeDelta;
				break;
			}
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
