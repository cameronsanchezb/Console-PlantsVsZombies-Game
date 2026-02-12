package tp1.p2.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tp1.p2.control.Level;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.LevelCastException;
import tp1.p2.control.exceptions.RecordException;
import tp1.p2.view.Messages;

public class Record {
	
	private Level level;
	private int score;
	private int scoreIni;
	
	private static List<Record> records = new ArrayList<>();
	
	private Record() {
	}
	
	private Record (Level lvl, int sc){
		level = lvl;
		score = sc;
	}
	
	public static Record cargarRecord(Level lvl) throws GameException {
		Record rcrd = new Record();
		rcrd.level = lvl;
		String line;
		String[] words = new String[2];
		try (BufferedReader input = new BufferedReader(new FileReader(Messages.RECORD_FILENAME));) {
			while((line = input.readLine()) != null) {
				words = line.trim().split(":");
				records.add(new Record(Level.valueOfIgnoreCase(words[0]), Integer.parseInt(words[1])));
			}
			boolean initialized = false;
			for(Record record : records) {
				if (rcrd.level.equals(record.level)) {
					rcrd.score = record.score;
					initialized = true;
				}
			}
			if(!initialized) rcrd.score = 0;
			rcrd.scoreIni = rcrd.score;
		}  
		catch (NumberFormatException e) {
			throw new RecordException(Messages.RECORD_READ_ERROR);
		}
		catch (IOException e){
			throw new RecordException(Messages.RECORD_READ_ERROR);
		} catch (LevelCastException e) {
			throw new RecordException(Messages.RECORD_READ_ERROR);
		}
		return rcrd;
	}
	
	public void guardarRecord() throws GameException{
		StringBuilder str = new StringBuilder();
		boolean levelAlreadyExists = false;
		for(Record record : records) {
			if (this.level.equals(record.level)) {
				levelAlreadyExists = true;
				if(isNewRecord()) record = this;
			}
		}
		if(!levelAlreadyExists) records.add(this);
		
		try (BufferedWriter output = new BufferedWriter(new FileWriter(Messages.RECORD_FILENAME))){
			for(Record record : records) {
				str.append(record.level);
				str.append(":");
				str.append(String.valueOf(record.score));
				output.write(str.toString());
				output.newLine();
			}
		} 
		catch (IOException e) {
			throw new RecordException(Messages.RECORD_WRITE_ERROR);
		}
	}
	
	public int getRecord() {
		return score;
	}

	public void updateRecord(int scoreAct){
		if(scoreAct > score) {
			score = scoreAct;
		}
	}
	
	public boolean isNewRecord() {
		return score > scoreIni;
	}
	
}
