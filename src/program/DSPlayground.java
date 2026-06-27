package program;

import foundation.ds.DS;

/**
 * Runnable demos for every custom data structure category.
 * Run this class from Eclipse to explore each structure step by step.
 *
 * @see AlgoPlayground algorithm demos
 */
public class DSPlayground {

	public static void main(String[] args) {
		demoArray();
		demoLinkedLists();
		demoStack();
		demoQueue();
		demoDeque();
		demoTrees();
		demoHeaps();
		demoMapAndSet();
		demoGraphs();
		demoAdvanced();

		System.out.println("\n=== All demos complete ===");
	}

	/* ======================== 1. ARRAY ======================== */

	private static void demoArray() {
		section("1. Custom Array");

		var array = DS.array(10, 20, 30);
		array.add(40);
		System.out.println("size: " + array.length());
		System.out.print("values: ");
		array.printArray();
		System.out.println("get(2): " + array.get(2));
	}

	/* ======================== 2. LINKED LIST ======================== */

	private static void demoLinkedLists() {
		section("2. Linked Lists");

		var singly = DS.singlyList(1, 2, 3);
		System.out.print("Singly:       ");
		singly.printList();

		var doubly = DS.doublyList("A", "B", "C");
		System.out.print("Doubly:       ");
		doubly.printList();

		var circular = DS.circularList(10, 20, 30);
		System.out.print("Circular:     ");
		circular.printList();

		var doublyCircular = DS.doublyCircularList(5, 6, 7);
		System.out.print("Doubly-Circ:  ");
		doublyCircular.printList();

		System.out.println("contains(2): " + singly.contains(2));
		System.out.println("peekLast:    " + doubly.peekLast());
	}

	/* ======================== 3. STACK ======================== */

	private static void demoStack() {
		section("3. Stack");

		var linked = DS.linkedStack();
		linked.push(1);
		linked.push(2);
		linked.push(3);
		System.out.print("Linked stack: ");
		linked.printStack();
		System.out.println("pop: " + linked.pop() + ", peek: " + linked.peek());

		var array = DS.stack(10, 20);
		array.push(30);
		System.out.print("Array stack:  ");
		array.printStack();
	}

	/* ======================== 4. QUEUE ======================== */

	private static void demoQueue() {
		section("4. Queue");

		var linked = DS.queue(1, 2, 3);
		System.out.print("Linked queue:    ");
		linked.printQueue();
		System.out.println("dequeue: " + linked.dequeue() + ", peek: " + linked.peek());

		var circular = DS.<Integer>circularQueue();
		circular.enqueue(10);
		circular.enqueue(20);
		circular.enqueue(30);
		System.out.print("Circular queue:  ");
		circular.printQueue();

		var minPq = DS.<Integer>minPriorityQueue();
		minPq.offer(30);
		minPq.offer(10);
		minPq.offer(20);
		System.out.println("Min PQ poll: " + minPq.poll() + " (smallest first)");

		var maxPq = DS.<Integer>maxPriorityQueue();
		maxPq.offer(30);
		maxPq.offer(10);
		maxPq.offer(20);
		System.out.println("Max PQ poll: " + maxPq.poll() + " (largest first)");
	}

	/* ======================== 5. DEQUE ======================== */

	private static void demoDeque() {
		section("5. Deque");

		var deque = DS.deque(2, 3);
		deque.addFirst(1);
		deque.addLast(4);
		System.out.print("Deque:        ");
		deque.printDeque();
		System.out.println("removeFirst: " + deque.removeFirst() + ", removeLast: " + deque.removeLast());
		System.out.println("peekFirst: " + deque.peekFirst() + ", peekLast: " + deque.peekLast());
	}

	/* ======================== 6. TREE ======================== */

	private static void demoTrees() {
		section("6. Trees");

		var bst = DS.<Integer>bst();
		bst.insert(5);
		bst.insert(3);
		bst.insert(7);
		bst.insert(1);
		bst.insert(9);
		System.out.print("BST inorder:  ");
		bst.inorderTraversal();
		System.out.println("BST contains(7): " + bst.contains(7) + ", height: " + bst.height());

		var avl = DS.<Integer>avlTree();
		for (int value : new int[] { 10, 20, 30, 40, 50, 25 }) {
			avl.insert(value);
		}
		System.out.print("AVL inorder:  ");
		avl.inorderTraversal();
		System.out.println("AVL height (balanced): " + avl.height());

		var trie = DS.trie();
		trie.insert("cat");
		trie.insert("car");
		trie.insert("card");
		System.out.println("Trie search(\"car\"):     " + trie.search("car"));
		System.out.println("Trie startsWith(\"ca\"):  " + trie.startsWith("ca"));
		System.out.println("Trie search(\"dog\"):     " + trie.search("dog"));
	}

	/* ======================== 7. HEAP ======================== */

	private static void demoHeaps() {
		section("7. Heap");

		var minHeap = DS.<Integer>minHeap();
		minHeap.insert(20);
		minHeap.insert(5);
		minHeap.insert(15);
		System.out.print("Min heap:     ");
		minHeap.printHeap();
		System.out.println("extract min: " + minHeap.extract());

		var maxHeap = DS.<Integer>maxHeap();
		maxHeap.insert(20);
		maxHeap.insert(5);
		maxHeap.insert(15);
		System.out.print("Max heap:     ");
		maxHeap.printHeap();
		System.out.println("extract max: " + maxHeap.extract());
	}

	/* ======================== 8. HASH MAP / SET ======================== */

	private static void demoMapAndSet() {
		section("8. Hash Map & Set");

		var map = DS.<String, Integer>map();
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		System.out.println("Map get(\"two\"): " + map.get("two"));
		System.out.println("Map containsKey(\"one\"): " + map.containsKey("one"));
		map.printMap();

		var set = DS.set(1, 2, 3, 2);
		System.out.println("Set contains(3): " + set.contains(3));
		set.remove(2);
		System.out.println("Set after remove(2), size: " + set.size());
	}

	/* ======================== 9. GRAPH ======================== */

	private static void demoGraphs() {
		section("9. Graph");

		var listGraph = DS.<String>graph();
		listGraph.addEdge("A", "B");
		listGraph.addEdge("A", "C");
		listGraph.addEdge("B", "D");
		System.out.println("Adjacency list:");
		listGraph.printGraph();
		System.out.print("BFS from A: ");
		listGraph.bfs("A");
		System.out.print("DFS from A: ");
		listGraph.dfs("A");

		var matrixGraph = DS.<Character>matrixGraph();
		matrixGraph.addEdge('X', 'Y');
		matrixGraph.addEdge('X', 'Z');
		matrixGraph.addEdge('Y', 'Z');
		System.out.println("Adjacency matrix:");
		matrixGraph.printGraph();

		var uf = DS.unionFind(5);
		uf.union(0, 1);
		uf.union(2, 3);
		System.out.println("UnionFind connected(0,1): " + uf.connected(0, 1));
		System.out.println("UnionFind connected(0,2): " + uf.connected(0, 2));
		System.out.println("UnionFind set count: " + uf.setCount());
	}

	/* ======================== 10. ADVANCED ======================== */

	private static void demoAdvanced() {
		section("10. Advanced");

		var bit = DS.fenwickTree(5);
		bit.update(1, 5);
		bit.update(2, 3);
		bit.update(3, 7);
		System.out.println("Fenwick prefixSum(2): " + bit.prefixSum(2));
		System.out.println("Fenwick rangeSum(1,3): " + bit.rangeSum(1, 3));

		var seg = DS.segmentTree(5);
		seg.build(new int[] { 1, 3, 5, 7, 9 });
		System.out.println("SegmentTree query(1,3): " + seg.query(1, 3));
		seg.update(2, 10);
		System.out.println("SegmentTree query(1,3) after update index 2 -> 10: " + seg.query(1, 3));

		var cache = DS.<String, Integer>lruCache(2);
		cache.put("a", 1);
		cache.put("b", 2);
		cache.get("a");
		cache.put("c", 3);
		System.out.println("LRU get(\"b\") after eviction: " + cache.get("b"));
		System.out.println("LRU get(\"a\"): " + cache.get("a"));
		System.out.println("LRU get(\"c\"): " + cache.get("c"));
		System.out.print("LRU order (MRU first): ");
		cache.printCache();
	}

	private static void section(String title) {
		System.out.println("\n=== " + title + " ===");
	}
}
