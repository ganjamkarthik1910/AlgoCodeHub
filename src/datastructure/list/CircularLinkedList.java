package datastructure.list;

import foundation.ds.DSAbstract;
import foundation.ds.DSInterface.NodeAccess;
import datastructure.list.DSNodes.SinglyNode;

public class CircularLinkedList<T> extends DSAbstract.CircularLinkedList<T> {

	private SinglyNode<T> head;
	private SinglyNode<T> tail;

	public CircularLinkedList() {
	}

	public CircularLinkedList(T data) {
		head = tail = new SinglyNode<>(data);
		head.setNext(head);
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
			head.setNext(head);
		} else {
			tail.setNext(newNode);
			newNode.setNext(head);
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
			if (head == null) {
				head = tail = newNode;
				head.setNext(head);
			} else {
				tail.setNext(newNode);
				newNode.setNext(head);
				head = newNode;
			}

			incrementSize();
			return;
		}

		SinglyNode<T> current = head;
		for (int i = 0; i < position - 1; i++) {
			current = current.getNext();
		}

		newNode.setNext(current.getNext());
		current.setNext(newNode);

		if (newNode.getNext() == head) {
			tail = newNode;
		}

		incrementSize();
	}

	@Override
	public void delete(T data) {
		if (head == null) {
			return;
		}

		if (java.util.Objects.equals(head.getData(), data)) {
			if (head == tail) {
				head = tail = null;
				size = 0;
				return;
			}

			tail.setNext(head.getNext());
			head = head.getNext();
			decrementSize();
			return;
		}

		SinglyNode<T> current = head;
		while (current.getNext() != head && !java.util.Objects.equals(current.getNext().getData(), data)) {
			current = current.getNext();
		}

		if (current.getNext() != head) {
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
			if (head == tail) {
				head = tail = null;
				size = 0;
				return;
			}

			tail.setNext(head.getNext());
			head = head.getNext();
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
	protected NodeAccess<T> tail() {
		return tail;
	}

	@Override
	public void printList() {
		if (head == null) {
			System.out.println();
			return;
		}

		SinglyNode<T> current = head;

		do {
			System.out.print(current.getData());
			current = current.getNext();

			if (current != head) {
				System.out.print(" -> ");
			}
		} while (current != head);

		System.out.println();
	}
}
