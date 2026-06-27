package datastructure.list;

import foundation.ds.DSAbstract;
import foundation.ds.DSInterface.NodeAccess;
import datastructure.list.DSNodes.DoublyNode;

public class DoublyLinkedList<T> extends DSAbstract.LinearLinkedList<T> {

	private DoublyNode<T> head;
	private DoublyNode<T> tail;

	public DoublyLinkedList() {
	}

	public DoublyLinkedList(T data) {
		head = tail = new DoublyNode<>(data);
		size = 1;
	}

	public DoublyNode<T> getHead() {
		return head;
	}

	@Override
	public void add(T data) {
		DoublyNode<T> newNode = new DoublyNode<>(data);

		if (head == null) {
			head = tail = newNode;
		} else {
			tail.setNext(newNode);
			newNode.setPrev(tail);
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

		DoublyNode<T> newNode = new DoublyNode<>(data);

		if (position == 0) {
			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
			incrementSize();
			return;
		}

		DoublyNode<T> current = nodeAt(position - 1);
		DoublyNode<T> next = current.getNext();

		newNode.setNext(next);
		newNode.setPrev(current);
		current.setNext(newNode);
		next.setPrev(newNode);
		incrementSize();
	}

	@Override
	public void delete(T data) {
		DoublyNode<T> current = head;

		while (current != null && !java.util.Objects.equals(current.getData(), data)) {
			current = current.getNext();
		}

		if (current == null) {
			return;
		}

		unlink(current);
		decrementSize();
	}

	@Override
	public void deleteAtPosition(int position) {
		if (position < 0 || position >= size) {
			throw new IndexOutOfBoundsException("Position: " + position + ", Size: " + size);
		}

		unlink(nodeAt(position));
		decrementSize();
	}

	@Override
	public T get(int index) {
		validate(index);
		return nodeAt(index).getData();
	}

	@Override
	public T peekLast() {
		return tail != null ? tail.getData() : null;
	}

	/** O(1) unlink when the node reference is known (e.g. LRU cache). */
	public void removeNode(DoublyNode<T> node) {
		if (node == null) {
			return;
		}

		unlink(node);
		decrementSize();
	}

	/** O(1) move an existing node to the head. */
	public void moveToFront(DoublyNode<T> node) {
		if (node == null || head == node) {
			return;
		}

		unlink(node);
		node.setPrev(null);
		node.setNext(head);
		head.setPrev(node);
		head = node;
	}

	/** O(1) insert at head; returns the new node for external indexing. */
	public DoublyNode<T> prependNode(T data) {
		DoublyNode<T> newNode = new DoublyNode<>(data);

		if (head == null) {
			head = tail = newNode;
		} else {
			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
		}

		incrementSize();
		return newNode;
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
		return ((DoublyNode<T>) node).getNext();
	}

	@Override
	public void printList() {
		DoublyNode<T> current = head;

		while (current != null) {
			System.out.print(current.getData());

			if (current.getNext() != null) {
				System.out.print(" <-> ");
			}

			current = current.getNext();
		}

		System.out.println();
	}

	private DoublyNode<T> nodeAt(int index) {
		if (index < size / 2) {
			DoublyNode<T> current = head;
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}
			return current;
		}

		DoublyNode<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.getPrev();
		}
		return current;
	}

	private void unlink(DoublyNode<T> node) {
		if (node.getPrev() != null) {
			node.getPrev().setNext(node.getNext());
		} else {
			head = node.getNext();
		}

		if (node.getNext() != null) {
			node.getNext().setPrev(node.getPrev());
		} else {
			tail = node.getPrev();
		}
	}
}
