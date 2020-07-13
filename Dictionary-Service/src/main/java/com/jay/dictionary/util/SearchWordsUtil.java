package com.jay.dictionary.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.util.Pair;

@Component
public class SearchWordsUtil {

	@Autowired
	private EditDistanceUtil editDistance;

	public SearchWordsUtil() {
		super();
	}

	public List<String> searchWords(String word, Trie root, List<String> words) {
		List<String> searchedWords = root.search(word);

		if (searchedWords != null && !searchedWords.isEmpty())
			return searchedWords;

		searchedWords = findNearestWords(word, root, words);

		return searchedWords;
	}

	public List<String> findNearestWords(String word, Trie root, List<String> words) {
		int threshold = Math.min(2, (word.length() + 1) / 2);
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
		return searchedWords;
	}

}

class Comp implements Comparator<Pair<String, Integer>> {

	@Override
	public int compare(Pair<String, Integer> p1, Pair<String, Integer> p2) {
		return (p1.getValue() - p2.getValue());
	}

}