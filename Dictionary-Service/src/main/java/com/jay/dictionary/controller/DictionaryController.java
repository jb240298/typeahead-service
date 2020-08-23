package com.jay.dictionary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jay.dictionary.service.IDictionaryService;

@RestController
@CrossOrigin
public class DictionaryController {

	@Autowired
	private IDictionaryService iDictionaryService;
	
	@GetMapping("/search/{word}")
	public ResponseEntity<List<String>> searchWords(@PathVariable String word) {
		List<String> words = iDictionaryService.searchWords(word);
		return new ResponseEntity<List<String>>(words, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<String>> getAllWords() {
		List<String> words = iDictionaryService.getAllWords();
		return new ResponseEntity<List<String>>(words, HttpStatus.OK);
	}
}
