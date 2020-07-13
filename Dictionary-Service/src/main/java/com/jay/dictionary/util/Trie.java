package com.jay.dictionary.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Trie {
	
	private static final int TRIE_ARRAY_SIZE = 26;
	
	private static final int ASCII_OF_A = 97;

	private boolean isEnd;
	
	private Trie root[];
	
	private char character;
	
	public Trie() {
		isEnd = false;
		character = '-';
		root = new Trie[TRIE_ARRAY_SIZE];
	}
	
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
//		for(int i=0; i<TRIE_ARRAY_SIZE; i++) {
//			if(temp_root.getRoot()[i] == null) continue;
//			findAll(word, temp_root.getRoot()[i], words);
//		}
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
