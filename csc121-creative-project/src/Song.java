import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Song {
	String title;
	NoteQueue nq;
	long duration;
	
	Song(String title, NoteQueue nq, long duration) {
		this.title = title;
		this.nq = nq;
		this.duration = duration;
	}
	
	static Song loadFile(String filename) {
		ArrayList<String> fileData = new ArrayList<String>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			while(line != null) {
				fileData.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			return null;
		}
		
		String title = fileData.remove(0);
		String musicFileName = fileData.remove(0);
		
		NoteQueue nq = new NoteQueue();
		String[] line;
		int track;
		long time = 0;
		
		while(fileData.size() > 0) {
			line = fileData.remove(0).split(" ");
			track = Integer.parseInt(line[0]);
			time = Long.parseLong(line[1]);
			nq.add(track, time);
		}
		return new Song(title, nq, time);
	}
	
	
	/** Getters */
	public String title() {
		return this.title;
	}
	
	public NoteQueue noteQueue() {
		return this.nq;
	}
}
