package foundation.ds;

import datastructure.advanced.FenwickTree;
import datastructure.advanced.LRUCache;
import datastructure.advanced.SegmentTree;
import datastructure.array.CustomArray;
import datastructure.deque.LinkedDeque;
import datastructure.graph.AdjacencyListGraph;
import datastructure.graph.AdjacencyMatrixGraph;
import datastructure.graph.UnionFind;
import datastructure.heap.MaxHeap;
import datastructure.heap.MinHeap;
import datastructure.list.CircularLinkedList;
import datastructure.list.DoublyCircularLinkedList;
import datastructure.list.DoublyLinkedList;
import datastructure.list.SinglyLinkedList;
import datastructure.map.ChainedHashMap;
import datastructure.queue.CircularArrayQueue;
import datastructure.queue.LinkedQueue;
import datastructure.queue.MaxPriorityQueue;
import datastructure.queue.MinPriorityQueue;
import datastructure.set.HashSet;
import datastructure.stack.ArrayStack;
import datastructure.stack.LinkedStack;
import datastructure.tree.AVLTree;
import datastructure.tree.BinarySearchTree;
import datastructure.tree.Trie;

/**
 * Master factory for all custom (non-built-in) data structures.
 * Every category you need for DSA practice lives here.
 */
public final class DS {

	private DS() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	/* ======================== 1. ARRAY ======================== */

	public static <T> CustomArray<T> array() {
		return new CustomArray<>();
	}

	@SafeVarargs
	public static <T> CustomArray<T> array(T... values) {
		CustomArray<T> array = new CustomArray<>();
		for (T value : values) {
			array.add(value);
		}
		return array;
	}

	/* ======================== 2. LINKED LIST ======================== */

	public static <T> SinglyLinkedList<T> singlyList() {
		return new SinglyLinkedList<>();
	}

	@SafeVarargs
	public static <T> SinglyLinkedList<T> singlyList(T... values) {
		SinglyLinkedList<T> list = new SinglyLinkedList<>();
		for (T value : values) {
			list.add(value);
		}
		return list;
	}

	public static <T> DoublyLinkedList<T> doublyList() {
		return new DoublyLinkedList<>();
	}

	@SafeVarargs
	public static <T> DoublyLinkedList<T> doublyList(T... values) {
		DoublyLinkedList<T> list = new DoublyLinkedList<>();
		for (T value : values) {
			list.add(value);
		}
		return list;
	}

	public static <T> CircularLinkedList<T> circularList() {
		return new CircularLinkedList<>();
	}

	@SafeVarargs
	public static <T> CircularLinkedList<T> circularList(T... values) {
		CircularLinkedList<T> list = new CircularLinkedList<>();
		for (T value : values) {
			list.add(value);
		}
		return list;
	}

	public static <T> DoublyCircularLinkedList<T> doublyCircularList() {
		return new DoublyCircularLinkedList<>();
	}

	@SafeVarargs
	public static <T> DoublyCircularLinkedList<T> doublyCircularList(T... values) {
		DoublyCircularLinkedList<T> list = new DoublyCircularLinkedList<>();
		for (T value : values) {
			list.add(value);
		}
		return list;
	}

	/* ======================== 3. STACK ======================== */

	public static <T> LinkedStack<T> linkedStack() {
		return new LinkedStack<>();
	}

	public static <T> ArrayStack<T> arrayStack() {
		return new ArrayStack<>();
	}

	@SafeVarargs
	public static <T> ArrayStack<T> stack(T... values) {
		ArrayStack<T> stack = new ArrayStack<>();
		for (T value : values) {
			stack.push(value);
		}
		return stack;
	}

	/* ======================== 4. QUEUE ======================== */

	public static <T> LinkedQueue<T> linkedQueue() {
		return new LinkedQueue<>();
	}

	public static <T> CircularArrayQueue<T> circularQueue() {
		return new CircularArrayQueue<>();
	}

	@SafeVarargs
	public static <T> LinkedQueue<T> queue(T... values) {
		LinkedQueue<T> queue = new LinkedQueue<>();
		for (T value : values) {
			queue.enqueue(value);
		}
		return queue;
	}

	public static <T extends Comparable<T>> MinPriorityQueue<T> minPriorityQueue() {
		return new MinPriorityQueue<>();
	}

	public static <T extends Comparable<T>> MaxPriorityQueue<T> maxPriorityQueue() {
		return new MaxPriorityQueue<>();
	}

	/* ======================== 5. DEQUE ======================== */

	public static <T> LinkedDeque<T> deque() {
		return new LinkedDeque<>();
	}

	@SafeVarargs
	public static <T> LinkedDeque<T> deque(T... values) {
		LinkedDeque<T> deque = new LinkedDeque<>();
		for (T value : values) {
			deque.addLast(value);
		}
		return deque;
	}

	/* ======================== 6. TREE ======================== */

	public static <T extends Comparable<T>> BinarySearchTree<T> bst() {
		return new BinarySearchTree<>();
	}

	public static <T extends Comparable<T>> AVLTree<T> avlTree() {
		return new AVLTree<>();
	}

	public static Trie trie() {
		return new Trie();
	}

	/* ======================== 7. HEAP ======================== */

	public static <T extends Comparable<T>> MinHeap<T> minHeap() {
		return new MinHeap<>();
	}

	public static <T extends Comparable<T>> MaxHeap<T> maxHeap() {
		return new MaxHeap<>();
	}

	/* ======================== 8. HASH MAP / SET ======================== */

	public static <K, V> ChainedHashMap<K, V> map() {
		return new ChainedHashMap<>();
	}

	public static <T> HashSet<T> set() {
		return new HashSet<>();
	}

	@SafeVarargs
	public static <T> HashSet<T> set(T... values) {
		HashSet<T> set = new HashSet<>();
		for (T value : values) {
			set.add(value);
		}
		return set;
	}

	/* ======================== 9. GRAPH ======================== */

	public static <T> AdjacencyListGraph<T> graph() {
		return new AdjacencyListGraph<>();
	}

	public static <T> AdjacencyMatrixGraph<T> matrixGraph() {
		return new AdjacencyMatrixGraph<>();
	}

	public static UnionFind unionFind(int n) {
		return new UnionFind(n);
	}

	/* ======================== 10. ADVANCED ======================== */

	public static FenwickTree fenwickTree(int n) {
		return new FenwickTree(n);
	}

	public static SegmentTree segmentTree(int n) {
		return new SegmentTree(n);
	}

	public static <K, V> LRUCache<K, V> lruCache(int capacity) {
		return new LRUCache<>(capacity);
	}
}
