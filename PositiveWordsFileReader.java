package edu.csupomona.cs356.project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class PositiveWordsFileReader {

	private ArrayList<String> positiveWords;
	private File wordFile;
	private Scanner readFile;
	
	private static PositiveWordsFileReader instance;
	
	private PositiveWordsFileReader() {
		try {
			
		wordFile = new File("C:/Users/Tim/workspace/cs356Proj2/src/edu/csupomona/cs356/project2/positive_words.txt");
		readFile = new Scanner(wordFile);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		readTheWords();
	}
	
	public static PositiveWordsFileReader getInstance() {
		if (instance == null) {
			instance = new PositiveWordsFileReader();
		}
		
		return instance;
	}
	
	private void readTheWords() {
		
		positiveWords = new ArrayList<String>();
		while (readFile.hasNextLine()) {
			String word = readFile.nextLine();
			positiveWords.add(word);
		}
	}
	
	public ArrayList<String> getTheWords() {
		return positiveWords;
	}
}
