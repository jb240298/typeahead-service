package com.jay.dictionary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jay.dictionary.service.DictionaryService;

@RestController
public class DictionaryController {

	@Autowired
	private DictionaryService dictionaryService;
	
	@GetMapping("/search/{word}")
	public ResponseEntity<List<String>> searchWords(@PathVariable String word) {
		List<String> words = dictionaryService.searchWords(word);
		return new ResponseEntity<List<String>>(words, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<String>> getAllWords() {
		List<String> words = dictionaryService.getAllWords();
		return new ResponseEntity<List<String>>(words, HttpStatus.OK);
	}
}
