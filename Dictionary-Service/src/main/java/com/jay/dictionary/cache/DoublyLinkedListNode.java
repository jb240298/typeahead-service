package com.jay.dictionary.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DoublyLinkedListNode {

	private String key;
	
	private List<String> value;
	
	private DoublyLinkedListNode nextNode;
	
	private DoublyLinkedListNode previousNode;

	public DoublyLinkedListNode() {
		super();
		value = new ArrayList<String>();
	}
	
	public DoublyLinkedListNode(String key, List<String> value) {
		super();
		this.key = key;
		this.value = value;
	}

	public DoublyLinkedListNode(String key, List<String> value, DoublyLinkedListNode nextNode,
			DoublyLinkedListNode previousNode) {
		super();
		this.key = key;
		this.value = value;
		this.nextNode = nextNode;
		this.previousNode = previousNode;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}

	public DoublyLinkedListNode getNextNode() {
		return nextNode;
	}

	public void setNextNode(DoublyLinkedListNode nextNode) {
		this.nextNode = nextNode;
	}

	public DoublyLinkedListNode getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(DoublyLinkedListNode previousNode) {
		this.previousNode = previousNode;
	}
	
	public void removeBindings() {
		this.nextNode = null;
		this.previousNode = null;
	}
	
}
