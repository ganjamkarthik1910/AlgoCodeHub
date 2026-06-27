package datastructure.deque;

import foundation.ds.DSAbstract;
import datastructure.list.DoublyLinkedList;

import java.util.NoSuchElementException;

/**
 * Double-ended queue backed by a doubly linked list.
 */
public class LinkedDeque<T> extends DSAbstract.Deque<T> {

	private final DoublyLinkedList<T> list = new DoublyLinkedList<>();

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
	public void addFirst(T data) {
		list.addAtPosition(0, data);
	}

	@Override
	public void addLast(T data) {
		list.add(data);
	}

	@Override
	public T removeFirst() {
		if (list.isEmpty()) {
			throw new NoSuchElementException("Deque is empty");
		}

		T data = list.peekFirst();
		list.deleteAtPosition(0);
		return data;
	}

	@Override
	public T removeLast() {
		if (list.isEmpty()) {
			throw new NoSuchElementException("Deque is empty");
		}

		T data = list.peekLast();
		list.deleteAtPosition(list.size() - 1);
		return data;
	}

	@Override
	public T peekFirst() {
		if (list.isEmpty()) {
			throw new NoSuchElementException("Deque is empty");
		}

		return list.peekFirst();
	}

	@Override
	public T peekLast() {
		if (list.isEmpty()) {
			throw new NoSuchElementException("Deque is empty");
		}

		return list.peekLast();
	}

	public void printDeque() {
		list.printList();
	}
}
