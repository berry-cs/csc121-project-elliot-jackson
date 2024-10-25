import java.util.ArrayList;

public class NoteQueue {
	ArrayList<NoteInfo> notes;
	int index;
	
	NoteQueue() {
		notes = new ArrayList<NoteInfo>();
		index = 0;
	}
	
	public void reset() {
		index = 0;
	}
	
	public boolean next() {
		if (index < notes.size()-1) {
			index++;
			return true;
		} else {
			return false;
		}
	}
	
	public int curTrack() {
		//System.out.println("curTrack: " + notes.get(index).track);
		return notes.get(index).track;
	}
	
	public long curTime() {
		//System.out.println("curTrack: " + notes.get(index).time);
		return notes.get(index).time;
	}
	
	public void add(int track, long time) {
		notes.add(new NoteInfo(track, time));
	}
	
	private class NoteInfo {
		int track;
		long time;
		NoteInfo(int track, long time) {
			this.track = track;
			this.time = time;
		}
	}
}
