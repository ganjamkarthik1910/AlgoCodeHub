package datastructure.list;

import foundation.ds.DSAbstract;
import foundation.ds.DSInterface.NodeAccess;
import datastructure.list.DSNodes.SinglyNode;

public class SinglyLinkedList<T> extends DSAbstract.LinearLinkedList<T> {

	private SinglyNode<T> head;
	private SinglyNode<T> tail;

	public SinglyLinkedList() {
	}

	public SinglyLinkedList(T data) {
		head = tail = new SinglyNode<>(data);
		size = 1;
	}

	public SinglyNode<T> getHead() {
		return head;
	}

	@Override
	public void add(T data) {
		SinglyNode<T> newNode = new SinglyNode<>(data);

		if (head == null) {
			head = tail = newNode;
		} else {
			tail.setNext(newNode);
			tail = newNode;
		}

		incrementSize();
	}

	@Override
	public void addAtPosition(int position, T data) {
		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException("Position: " + position + ", Size: " + size);
		}

		if (position == size) {
			add(data);
			return;
		}

		SinglyNode<T> newNode = new SinglyNode<>(data);

		if (position == 0) {
			newNode.setNext(head);
			head = newNode;
			incrementSize();
			return;
		}

		SinglyNode<T> current = head;
		for (int i = 0; i < position - 1; i++) {
			current = current.getNext();
		}

		newNode.setNext(current.getNext());
		current.setNext(newNode);
		incrementSize();
	}

	@Override
	public void delete(T data) {
		if (head == null) {
			return;
		}

		if (java.util.Objects.equals(head.getData(), data)) {
			head = head.getNext();
			if (head == null) {
				tail = null;
			}
			decrementSize();
			return;
		}

		SinglyNode<T> current = head;
		while (current.getNext() != null && !java.util.Objects.equals(current.getNext().getData(), data)) {
			current = current.getNext();
		}

		if (current.getNext() != null) {
			if (current.getNext() == tail) {
				tail = current;
			}
			current.setNext(current.getNext().getNext());
			decrementSize();
		}
	}

	@Override
	public void deleteAtPosition(int position) {
		if (position < 0 || position >= size) {
			throw new IndexOutOfBoundsException("Position: " + position + ", Size: " + size);
		}

		if (position == 0) {
			head = head.getNext();
			if (head == null) {
				tail = null;
			}
			decrementSize();
			return;
		}

		SinglyNode<T> current = head;
		for (int i = 0; i < position - 1; i++) {
			current = current.getNext();
		}

		if (current.getNext() == tail) {
			tail = current;
		}

		current.setNext(current.getNext().getNext());
		decrementSize();
	}

	@Override
	public T peekLast() {
		return tail != null ? tail.getData() : null;
	}

	@Override
	protected void clearStructure() {
		head = tail = null;
	}

	@Override
	protected NodeAccess<T> head() {
		return head;
	}

	@Override
	protected NodeAccess<T> next(NodeAccess<T> node) {
		return ((SinglyNode<T>) node).getNext();
	}

	@Override
	public void printList() {
		SinglyNode<T> current = head;

		while (current != null) {
			System.out.print(current.getData());

			if (current.getNext() != null) {
				System.out.print(" -> ");
			}

			current = current.getNext();
		}

		System.out.println();
	}
}
