package datastructure.stack;

import foundation.ds.DSAbstract;
import datastructure.list.SinglyLinkedList;

import java.util.NoSuchElementException;

/**
 * LIFO stack backed by a singly linked list (push/pop at head).
 */
public class LinkedStack<T> extends DSAbstract.Stack<T> {

	private final SinglyLinkedList<T> list = new SinglyLinkedList<>();

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public void push(T data) {
		list.addAtPosition(0, data);
	}

	@Override
	public T pop() {
		if (list.isEmpty()) {
			throw new NoSuchElementException("Stack is empty");
		}

		T data = list.peekFirst();
		list.deleteAtPosition(0);
		return data;
	}

	@Override
	public T peek() {
		if (list.isEmpty()) {
			throw new NoSuchElementException("Stack is empty");
		}

		return list.peekFirst();
	}

	public void printStack() {
		list.printList();
	}
}
