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

import com.jay.dictionary.util.SearchWordsUtil;
import com.jay.dictionary.util.Trie;

@Service
public class DictionaryServiceImpl implements DictionaryService {
	
	private List<String> words;
	
	@Autowired
	private Trie root;
	
	@Autowired
	private SearchWordsUtil searchWordsUtil;
	
	@PostConstruct
	public void init(){
		if(words == null) {
			getAllWords();
		}
	}

	@Override
	public List<String> searchWords(String word) {
		if(words == null) getAllWords();
		List<String> searchedWords = root.search(word);
		if(searchedWords != null && !searchedWords.isEmpty()) return searchedWords;
		searchedWords = searchWordsUtil.findNearestWords(word, root, words);
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
