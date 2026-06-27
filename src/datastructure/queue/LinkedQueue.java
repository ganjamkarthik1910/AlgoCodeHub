package datastructure.queue;

import foundation.ds.DSAbstract;
import datastructure.list.SinglyLinkedList;

import java.util.NoSuchElementException;

/**
 * FIFO queue backed by a singly linked list (enqueue at tail, dequeue from head).
 */
public class LinkedQueue<T> extends DSAbstract.Queue<T> {

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
	public void enqueue(T data) {
		list.add(data);
	}

	@Override
	public T dequeue() {
		if (list.isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}

		T data = list.peekFirst();
		list.deleteAtPosition(0);
		return data;
	}

	@Override
	public T peek() {
		if (list.isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}

		return list.peekFirst();
	}

	public void printQueue() {
		list.printList();
	}
}
