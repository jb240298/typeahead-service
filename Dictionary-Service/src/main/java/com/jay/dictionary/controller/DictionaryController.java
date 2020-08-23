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

/**
 * @author Jay
 *
 */
@RestController
@CrossOrigin
public class DictionaryController {

	@Autowired
	private IDictionaryService dictionaryService;
	
	/**
	 * This method receives a word and returns appropriate suggestions
	 * 
	 * @param {@link String} 
	 * @return {@link String} list
	 */
	@GetMapping("/search/{word}")
	public ResponseEntity<List<String>> searchWords(@PathVariable String word) {
		return new ResponseEntity<List<String>>(dictionaryService.searchWords(word), HttpStatus.OK);
	}
	
	/**
	 * This method returns all the words stored in the text file located at resource folder
	 * 
	 * @param
	 * @return {@link String} list
	 */
	@GetMapping("/all")
	public ResponseEntity<List<String>> getAllWords() {
		List<String> words = dictionaryService.getAllWords();
		return new ResponseEntity<List<String>>(words, HttpStatus.OK);
	}
}
