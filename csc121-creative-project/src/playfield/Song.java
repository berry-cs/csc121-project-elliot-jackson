package playfield;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/** 
 * Represents a 'song'- Including the list
 * of notes in the song and the audio file
 * 
 */
public class Song {
	String title;
	NoteQueue nq;
	long duration;
	
	/** Generic Constructor */
	Song(String title, NoteQueue nq, long duration) {
		this.title = title;
		this.nq = nq;
		this.duration = duration;
	}
	
	/** Loads a song file and returns a built song object */
	public static Song loadFile(String filename) {
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
	
	public static ArrayList<Info> getSongList(String directory) {
		//ArrayList<String> results = new ArrayList<String>();
		ArrayList<Info> results = new ArrayList<Info>();

		File[] files = new File(directory).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : files) {
		    if (file.isFile()) {
		    	String fn = directory + "/" + file.getName();
		    	results.add(new Info(getSongTitle(fn), fn));
		    }
		}
		return results;
	}
	
	/** Getters */
	public String title() {
		return this.title;
	}
	
	public NoteQueue noteQueue() {
		return this.nq;
	}
	
	public static class Info {
		String title;
		String filename;
		Info(String title, String filename) {
			this.title = title;
			this.filename = filename;
		}
		public String title() {return this.title;}
		public String filename() {return this.filename;}
	}
	
	private static String getSongTitle(String filename) {
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
		
		return fileData.remove(0);
	}
}
