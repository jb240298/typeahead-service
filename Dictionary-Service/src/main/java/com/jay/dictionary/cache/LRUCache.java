package com.jay.dictionary.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jay.dictionary.configuration.DictionaryConfiguration;
import com.jay.dictionary.constants.DictionaryConstants;

@Component
public class LRUCache {

	private DoublyLinkedList listOfMostRecent;
	
	Map<String, DoublyLinkedListNode> cache;
	
	private int cacheCapacity;
	
	private int currentCacheSize;

	@Autowired
	private DictionaryConfiguration dictionaryConfiguration;
	
	private Map<String, String> dictionaryConfig;
	
	public LRUCache() {
		super();
		cache = new HashMap<String, DoublyLinkedListNode>();
		listOfMostRecent = new DoublyLinkedList();
		currentCacheSize = 0;
	}
	
	@PostConstruct
	public void setConfiguration() {
		dictionaryConfig = dictionaryConfiguration.getDictionaryConfiguration();
		cacheCapacity = Integer.parseInt(dictionaryConfig.get(DictionaryConstants.CACHE_CAPACITY));
	}
	
	public List<String> get(String key) {
		if(!cache.containsKey(key)) {
			return null;
		} else {
			updateMostRecent(cache.get(key));
			return cache.get(key).getValue();
		}
	}
	
	public void put(String key, List<String> value) {
		if(cache.containsKey(key)) {
			replaceKey(key, value);
		} else {
			if(this.currentCacheSize >= cacheCapacity) {
				evictLeastRecent();
			} else {
				currentCacheSize += 1;
			}
			DoublyLinkedListNode node = new DoublyLinkedListNode();
			node.setValue(value);
			node.setKey(key);
			cache.put(key, node);
		}
		updateMostRecent(cache.get(key));
	}
	
	public List<String> getMostRecent() {
		if(listOfMostRecent.getHead() == null) return null;
		else return listOfMostRecent.getHead().getValue();
	}
	
	private void updateMostRecent(DoublyLinkedListNode node) {
		listOfMostRecent.setHeadTo(node);
	}
	
	private void replaceKey(String key, List<String> value) {
		cache.get(key).setValue(value);
	}
	
	private void evictLeastRecent() {
		String keyToRemove = listOfMostRecent.getTail().getKey();
		listOfMostRecent.removeTail();
		cache.remove(keyToRemove);
	}
	
}
