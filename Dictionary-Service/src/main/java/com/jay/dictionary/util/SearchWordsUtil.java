package com.jay.dictionary.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jay.dictionary.configuration.DictionaryConfiguration;
import com.jay.dictionary.constants.DictionaryConstants;

import javafx.util.Pair;

@Component
public class SearchWordsUtil {

	@Autowired
	private EditDistanceUtil editDistance;
	
	@Autowired
	private DictionaryConfiguration dictionaryConfiguration;
	
	private Map<String, String> dictionaryConfig;
	
	@PostConstruct
	public void searchWordsUtil() {
		dictionaryConfig = dictionaryConfiguration.getDictionaryConfiguration();
	}

	public List<String> searchWords(String word, Trie root, List<String> words) {
		List<String> searchedWords = root.search(word);

		if (searchedWords != null && !searchedWords.isEmpty())
			return searchedWords;

		searchedWords = findNearestWords(word, root, words);

		return searchedWords;
	}

	public List<String> findNearestWords(String word, Trie root, List<String> words) {
		int threshold = Integer.parseInt(dictionaryConfig.get(DictionaryConstants.EDIT_DISTANCE_THRESHOLD)); 

		List<Pair<String, Integer>> nearestWords = new ArrayList<Pair<String, Integer>>();
		words.forEach((name) -> {
			int distance = editDistance.findDistanceBetween(word, name);
			Pair<String, Integer> nameWithDistance = new Pair<String, Integer>(name, distance);
			if (distance <= threshold) {
				nearestWords.add(nameWithDistance);
			}
		});
		Collections.sort(nearestWords, new Comp());
		List<String> searchedWords = new ArrayList<String>();
		
		nearestWords.forEach((name) -> {
			searchedWords.add(name.getKey());
		});	
		
		int maxCapacity = Integer.parseInt(dictionaryConfig.get(DictionaryConstants.SUGGESSION_SIZE));
		if(searchedWords.size() > maxCapacity) {
			searchedWords.subList(maxCapacity, searchedWords.size()).clear();
		}
		return searchedWords;
	}

}

class Comp implements Comparator<Pair<String, Integer>> {

	@Override
	public int compare(Pair<String, Integer> p1, Pair<String, Integer> p2) {
		return (p1.getValue() - p2.getValue());
	}

}