package com.jay.dictionary.service;

import java.util.List;

public interface DictionaryService {
	
	public List<String> searchWords(String word);
	
	public List<String> getAllWords();
}
