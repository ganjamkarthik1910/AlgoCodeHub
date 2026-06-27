package foundation.ds;

import foundation.ds.DSInterface.NodeAccess;

/**
 * Single source of truth for shared abstract implementations.
 * Extend the nested class that matches your data structure.
 */
public final class DSAbstract {

	private DSAbstract() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	/* ========================================================= BASE */

	public abstract static class SizedStructure implements DSInterface.DataStructure {

		protected int size;

		@Override
		public int size() {
			return size;
		}

		@Override
		public boolean isEmpty() {
			return size == 0;
		}

		protected void incrementSize() {
			size++;
		}

		protected void decrementSize() {
			if (size > 0) {
				size--;
			}
		}
	}

	/* ========================================================= LINKED LIST */

	public abstract static class LinkedList<T> extends SizedStructure implements DSInterface.LinkedList<T> {

		protected abstract NodeAccess<T> head();

		protected abstract NodeAccess<T> next(NodeAccess<T> node);

		protected abstract void clearStructure();

		@Override
		public void clear() {
			clearStructure();
			size = 0;
		}

		@Override
		public boolean contains(T data) {
			NodeAccess<T> current = head();

			while (current != null) {
				if (java.util.Objects.equals(current.getData(), data)) {
					return true;
				}
				current = next(current);
			}

			return false;
		}

		@Override
		public T get(int index) {
			validate(index);

			NodeAccess<T> current = head();
			for (int i = 0; i < index; i++) {
				current = next(current);
			}

			return current.getData();
		}

		@Override
		public T peekFirst() {
			NodeAccess<T> first = head();
			return first != null ? first.getData() : null;
		}

		protected void validate(int index) {
			if (index < 0 || index >= size) {
				throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
			}
		}
	}

	public abstract static class LinearLinkedList<T> extends LinkedList<T> {

		@Override
		public T peekLast() {
			NodeAccess<T> current = head();
			if (current == null) {
				return null;
			}

			while (next(current) != null) {
				current = next(current);
			}

			return current.getData();
		}
	}

	public abstract static class CircularLinkedList<T> extends LinkedList<T> {

		protected abstract NodeAccess<T> tail();

		@Override
		public boolean contains(T data) {
			if (isEmpty()) {
				return false;
			}

			NodeAccess<T> current = head();
			NodeAccess<T> start = current;

			do {
				if (java.util.Objects.equals(current.getData(), data)) {
					return true;
				}
				current = next(current);
			} while (current != start);

			return false;
		}

		@Override
		public T peekLast() {
			if (isEmpty()) {
				return null;
			}

			return tail().getData();
		}
	}

	/* ========================================================= STACK / QUEUE / DEQUE */

	public abstract static class Stack<T> extends SizedStructure implements DSInterface.Stack<T> {
	}

	public abstract static class Queue<T> extends SizedStructure implements DSInterface.Queue<T> {
	}

	public abstract static class Deque<T> extends SizedStructure implements DSInterface.Deque<T> {
	}

	/* ========================================================= TREE / HEAP / MAP / GRAPH */

	public abstract static class Tree<T> extends SizedStructure implements DSInterface.Tree<T> {
	}

	public abstract static class Heap<T> extends SizedStructure implements DSInterface.Heap<T> {
	}

	public abstract static class Map<K, V> extends SizedStructure implements DSInterface.Map<K, V> {
	}

	public abstract static class Graph<T> extends SizedStructure implements DSInterface.Graph<T> {
	}

	public abstract static class PriorityQueue<T> extends SizedStructure implements DSInterface.PriorityQueue<T> {
	}

	public abstract static class Set<T> extends SizedStructure implements DSInterface.Set<T> {
	}

	public abstract static class Trie extends SizedStructure implements DSInterface.Trie {
	}

	public abstract static class UnionFind extends SizedStructure implements DSInterface.UnionFind {
	}

	public abstract static class FenwickTree extends SizedStructure implements DSInterface.FenwickTree {
	}

	public abstract static class SegmentTree extends SizedStructure implements DSInterface.SegmentTree {
	}

	public abstract static class LRUCache<K, V> extends SizedStructure implements DSInterface.LRUCache<K, V> {
	}
}
