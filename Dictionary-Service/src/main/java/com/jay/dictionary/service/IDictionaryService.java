package com.jay.dictionary.service;

import java.util.List;

public interface IDictionaryService {
	
	public List<String> searchWords(String word);
	
	public List<String> getAllWords();
}
