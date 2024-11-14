package playfield;

/**
 * Represents a single note in a level
 */
public class Note {
	private HitBox loc;		// Position in coordinate space
	private float speed;	// Speed (in pixels/sec)
	private int track;		// Which track the note belongs to
	
	long curTime;	// Keeps track of current runtime
	
	public boolean shouldCull;
	public boolean missed;
	
	public final static int SIZE = 25;
	
	public Note(float centerX, float centerY, int track, float speed, long curTime) {
		loc = new HitBox(centerX-(SIZE/2),centerY-(SIZE/2),SIZE,SIZE);
		this.speed = speed;
		this.track = track;
		this.curTime = curTime;
		shouldCull = false;
		missed = false;
	}
	
	public void update(long curTime) {
		long delta = curTime - this.curTime; // Calculate difference in time between last update and this one
		this.curTime = curTime;				 // Update this.curTime with the new current time 
		
		loc.addY(-speed*delta); // Calculate distance from speed and time delta and subtract it from y-coord.
		
		// If the note is entirely above x-axis, it should be culled
		if(loc.y() < -SIZE) {
			shouldCull = true;
		}
	}
	
	public void cull() {
		shouldCull = true;
	}
	
	public HitBox loc() {
		return this.loc;
	}
	
	public int track() {
		return track;
	}
	
	public float x() {
		return loc.x();
	}
	
	public float y() {
		return loc.y();
	}
	
}
