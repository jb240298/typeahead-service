package com.jay.dictionary.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jay.dictionary.constants.DictionaryConfiguration;
import com.jay.dictionary.constants.DictionaryConstants;

@Component
public class Trie {
	
	@Autowired
	private DictionaryConfiguration dictionaryConfiguration;
	
	private static final int TRIE_ARRAY_SIZE = 26;
	
	private static final int ASCII_OF_A = 97;

	private boolean isEnd;
	
	private Trie root[];
	
	private char character;
	
	private Map<String, String> dictionaryConfig;
	
	/*
	 * Trie is the standard data structure used in the industry to match the prefix of a string.
	 * In this module we have already stored all the words of the dictionary into tries.
	 * When user search for a word, this algorithm will check is the exact word exist in the trie or not.
	 */
	public Trie() {
		isEnd = false;
		character = '-';
		root = new Trie[TRIE_ARRAY_SIZE];
	}
	
	@PostConstruct
	public void trie() {
		dictionaryConfig = dictionaryConfiguration.getDictionaryConfiguration();
		if(Boolean.parseBoolean(dictionaryConfig.get(DictionaryConstants.CONFIG_LOG_PRINT_FLAG))) {
			printConfiguration();
		}
	}
	
	public void printConfiguration() {
		System.out.println("***************************************");
		System.out.println("*****************Trie******************");
		System.out.println(DictionaryConstants.EDIT_DISTANCE_THRESHOLD + ": " + dictionaryConfig.get(DictionaryConstants.EDIT_DISTANCE_THRESHOLD));
		System.out.println(DictionaryConstants.SUGGESSION_SIZE + ": " + dictionaryConfig.get(DictionaryConstants.SUGGESSION_SIZE));
		System.out.println("***************************************");
	}
	
	/*
	 * To insert the word into Trie.
	 */
	public void insert(String word) {
		word.toLowerCase();
		Trie temp_root = this;
		for(int i=0; i<word.length(); i++) {
			int index = word.charAt(i) - ASCII_OF_A;
			if(temp_root.getRoot()[index] == null) {
				temp_root.getRoot()[index] = new Trie();
			}
			temp_root = temp_root.getRoot()[index];
			temp_root.setCharacter(word.charAt((i)));
		}
		temp_root.setEnd(true);
	}
	
	/*
	 * To search user provided word exist into the Trie or not.
	 * If it does not exist then we will use EditDistance algorithm to find the neares word.
	 */
	public List<String> search(String word) {
		List<String> words = new ArrayList<String>();
		Trie temp_root = this;
		for(int i=0; i<word.length(); i++) {
			int index = word.charAt(i) - ASCII_OF_A;
			if(temp_root.getRoot()[index] == null) {
				return words;
			}
			temp_root = temp_root.getRoot()[index];
		}
		if(temp_root.isEnd()) {
			words.add(word);
		}
		for(int i=0; i<TRIE_ARRAY_SIZE; i++) {
			if(temp_root.getRoot()[i] == null) continue;
			findAll(word, temp_root.getRoot()[i], words);
		}
		
		int maxCapacity = Integer.parseInt(dictionaryConfig.get(DictionaryConstants.SUGGESSION_SIZE));
		if(words.size() > maxCapacity) {
			words.subList(maxCapacity, words.size()).clear();
		}
		return words;
	}
	
	public void findAll(String words, Trie root, List<String> possibleWords) {
		words = words + root.getCharacter();
		if(root.isEnd()) {
			possibleWords.add(words);
		}
		for(int i=0; i<TRIE_ARRAY_SIZE; i++) {
			if(root.getRoot()[i] == null) continue;
			findAll(words, root.getRoot()[i], possibleWords);
		}
		return;
	}
	
	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public Trie[] getRoot() {
		return root;
	}

	public void setRoot(Trie[] root) {
		this.root = root;
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}
	
}
