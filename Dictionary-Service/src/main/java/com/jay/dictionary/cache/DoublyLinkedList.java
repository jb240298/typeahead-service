package com.jay.dictionary.cache;

import org.springframework.stereotype.Component;

@Component
public class DoublyLinkedList {

	private DoublyLinkedListNode head;
	
	private DoublyLinkedListNode tail;

	public DoublyLinkedList() {
		super();
	}
	
	public DoublyLinkedListNode getHead() {
		return head;
	}

	public void setHead(DoublyLinkedListNode head) {
		this.head = head;
	}

	public DoublyLinkedListNode getTail() {
		return tail;
	}

	public void setTail(DoublyLinkedListNode tail) {
		this.tail = tail;
	}

	void removeTail() {
		if(tail == null) {
			return;
		}
		
		if(tail == head) {
			head = null;
			tail = null;
			return;
		}
		
		tail = tail.getPreviousNode();
		tail.setNextNode(null);
	}
	
	void setHeadTo(DoublyLinkedListNode node) {
		if(head == node) {
			return;
		}
		
		if(head == null) {
			head = node;
			tail = node;
			return;
		}
		
		if(head == tail) {
			head = node;
			head.setNextNode(tail);
			tail.setPreviousNode(head);
			return;
		}
		
		DoublyLinkedListNode prevNode = node.getPreviousNode();
		DoublyLinkedListNode nextNode = node.getNextNode();
		
		if(prevNode != null) {
			prevNode.setNextNode(node);
		}
		if(nextNode != null) {
			nextNode.setPreviousNode(node);
		}
		
		if(tail == node) {
			removeTail();
		}
		
		node.removeBindings();
		head.setPreviousNode(node);
		node.setNextNode(head);
		head = node;
	}
	
}
