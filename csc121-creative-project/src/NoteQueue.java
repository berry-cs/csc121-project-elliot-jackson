import java.util.ArrayList;

public class NoteQueue {
	ArrayList<NoteInfo> notes;
	int index;
	
	NoteQueue() {
		notes = new ArrayList<NoteInfo>();
		index = 0;
	}
	
	public static NoteQueue loadFile() {
		NoteQueue nq = new NoteQueue();
		
			nq.add(0, 1000);
			nq.add(1, 1000);
			nq.add(2, 2000);
			nq.add(3, 2000);
		
		
		for(int i = 5; i<200; i++) {
			nq.add(i%4, 1000*i);
		}
		
		return nq;
	}
	
	public void reset() {
		index = 0;
	}
	
	public boolean next() {
		if (index < notes.size()-2) {
			index++;
			return true;
		} else {
			return false;
		}
	}
	
	public int curTrack() {
		return notes.get(index).track;
	}
	
	public int curTime() {
		return notes.get(index).time;
	}
	
	public void add(int track, int time) {
		notes.add(new NoteInfo(track, time));
	}
	
	private class NoteInfo {
		int track;
		int time;
		NoteInfo(int track, int time) {
			this.track = track;
			this.time = time;
		}
	}
}
