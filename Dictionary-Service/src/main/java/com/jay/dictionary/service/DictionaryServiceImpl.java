package com.jay.dictionary.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.jay.dictionary.cache.LRUCache;
import com.jay.dictionary.util.SearchWordsUtil;
import com.jay.dictionary.util.Trie;

@Service
public class DictionaryServiceImpl implements DictionaryService {
	
	private List<String> words;
	
	@Autowired
	private Trie root;
	
	@Autowired
	private SearchWordsUtil searchWordsUtil;
	
	@Autowired
	private LRUCache cache;
	
	@PostConstruct
	public void init(){
		if(words == null) {
			getAllWords();
		}
	}

	@Override
	public List<String> searchWords(String word) {
		if(words == null) getAllWords();
		
		// Fetch from the cache, if it exist then return it.
		List<String> searchedWords = cache.get(word);
		if(searchedWords != null) return searchedWords;
		
		// If it is not available in cache then search in trie.
		searchedWords = root.search(word);
		
		// It it is not available in Trie, apply Edit Distance Algorithm to find closest word
		if(searchedWords != null && !searchedWords.isEmpty()) return searchedWords;
		searchedWords = searchWordsUtil.findNearestWords(word, root, words);
		
		// After computing, store it in cache.
		if(searchedWords != null && !searchedWords.isEmpty()) cache.put(word, searchedWords);
		
		return searchedWords;
	}

	@Override
	public List<String> getAllWords() {
		
		if(words != null) return words;
		
		Resource resource = new ClassPathResource("words_alpha.txt");
		File file;
		
		BufferedReader br;
		words = new ArrayList<String>();

		String st;
		try {
			file = resource.getFile();
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				words.add(st);
				root.insert(st);
			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return words;
	}

}
