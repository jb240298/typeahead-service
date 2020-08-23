package com.jay.dictionary.service;

import java.util.List;

public interface IDictionaryService {
	
	/**
	 * This method receives a word and returns appropriate suggestions
	 * 
	 * @param {@link String} 
	 * @return {@link String} list
	 */
	public List<String> searchWords(String word);
	
	/**
	 * This method returns all the words stored in the text file located at resource folder
	 * 
	 * @param
	 * @return {@link String} list
	 */
	public List<String> getAllWords();
}
