package datastructure.list;

import foundation.ds.DSAbstract;
import foundation.ds.DSInterface.NodeAccess;
import datastructure.list.DSNodes.DoublyNode;

public class DoublyCircularLinkedList<T> extends DSAbstract.CircularLinkedList<T> {

	private DoublyNode<T> head;

	public DoublyCircularLinkedList() {
	}

	public DoublyCircularLinkedList(T data) {
		head = new DoublyNode<>(data);
		head.setNext(head);
		head.setPrev(head);
		size = 1;
	}

	public DoublyNode<T> getHead() {
		return head;
	}

	@Override
	public void add(T data) {
		DoublyNode<T> newNode = new DoublyNode<>(data);

		if (head == null) {
			head = newNode;
			head.setNext(head);
			head.setPrev(head);
			incrementSize();
			return;
		}

		DoublyNode<T> tail = head.getPrev();
		tail.setNext(newNode);
		newNode.setPrev(tail);
		newNode.setNext(head);
		head.setPrev(newNode);
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

		DoublyNode<T> newNode = new DoublyNode<>(data);

		if (position == 0) {
			if (head == null) {
				head = newNode;
				head.setNext(head);
				head.setPrev(head);
				incrementSize();
				return;
			}

			DoublyNode<T> tail = head.getPrev();
			newNode.setNext(head);
			newNode.setPrev(tail);
			tail.setNext(newNode);
			head.setPrev(newNode);
			head = newNode;
			incrementSize();
			return;
		}

		DoublyNode<T> current = head;
		for (int i = 0; i < position - 1; i++) {
			current = current.getNext();
		}

		newNode.setNext(current.getNext());
		newNode.setPrev(current);
		current.getNext().setPrev(newNode);
		current.setNext(newNode);
		incrementSize();
	}

	@Override
	public void delete(T data) {
		if (head == null) {
			return;
		}

		DoublyNode<T> current = head;

		do {
			if (java.util.Objects.equals(current.getData(), data)) {
				if (current.getNext() == current) {
					head = null;
					size = 0;
					return;
				}

				current.getPrev().setNext(current.getNext());
				current.getNext().setPrev(current.getPrev());

				if (current == head) {
					head = current.getNext();
				}

				decrementSize();
				return;
			}

			current = current.getNext();
		} while (current != head);
	}

	@Override
	public void deleteAtPosition(int position) {
		if (position < 0 || position >= size) {
			throw new IndexOutOfBoundsException("Position: " + position + ", Size: " + size);
		}

		if (position == 0) {
			if (head.getNext() == head) {
				head = null;
				size = 0;
				return;
			}

			DoublyNode<T> tail = head.getPrev();
			tail.setNext(head.getNext());
			head.getNext().setPrev(tail);
			head = head.getNext();
			decrementSize();
			return;
		}

		DoublyNode<T> current = head;
		for (int i = 0; i < position; i++) {
			current = current.getNext();
		}

		current.getPrev().setNext(current.getNext());
		current.getNext().setPrev(current.getPrev());
		decrementSize();
	}

	@Override
	protected void clearStructure() {
		head = null;
	}

	@Override
	protected NodeAccess<T> head() {
		return head;
	}

	@Override
	protected NodeAccess<T> next(NodeAccess<T> node) {
		return ((DoublyNode<T>) node).getNext();
	}

	@Override
	protected NodeAccess<T> tail() {
		return head != null ? head.getPrev() : null;
	}

	@Override
	public void printList() {
		if (head == null) {
			System.out.println();
			return;
		}

		DoublyNode<T> current = head;

		do {
			System.out.print(current.getData());
			current = current.getNext();

			if (current != head) {
				System.out.print(" <-> ");
			}
		} while (current != head);

		System.out.println();
	}
}
