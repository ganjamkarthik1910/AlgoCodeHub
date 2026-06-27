package foundation.ds;

/**
 * Single source of truth for all data structure contracts.
 */
public final class DSInterface {

	private DSInterface() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	/* ========================================================= COMMON */

	public interface DataStructure {

		int size();

		boolean isEmpty();

		void clear();
	}

	public interface NodeAccess<T> {

		T getData();
	}

	public interface DoublyNodeAccess<T> extends NodeAccess<T> {

		DoublyNodeAccess<T> prev();

		DoublyNodeAccess<T> next();
	}

	/* ========================================================= LINKED LIST */

	public interface LinkedList<T> extends DataStructure {

		void add(T data);

		void addAtPosition(int position, T data);

		void delete(T data);

		void deleteAtPosition(int position);

		boolean contains(T data);

		T get(int index);

		T peekFirst();

		T peekLast();

		void printList();
	}

	/* ========================================================= STACK */

	public interface Stack<T> extends DataStructure {

		void push(T data);

		T pop();

		T peek();
	}

	/* ========================================================= QUEUE */

	public interface Queue<T> extends DataStructure {

		void enqueue(T data);

		T dequeue();

		T peek();
	}

	/* ========================================================= DEQUE */

	public interface Deque<T> extends DataStructure {

		void addFirst(T data);

		void addLast(T data);

		T removeFirst();

		T removeLast();

		T peekFirst();

		T peekLast();
	}

	/* ========================================================= BINARY TREE */

	public interface Tree<T> extends DataStructure {

		void insert(T data);

		void delete(T data);

		boolean contains(T data);

		int height();

		void inorderTraversal();

		void preorderTraversal();

		void postorderTraversal();

		void levelOrderTraversal();
	}

	/* ========================================================= HEAP */

	public interface Heap<T> extends DataStructure {

		void insert(T data);

		T extract();

		T peek();
	}

	/* ========================================================= HASH MAP */

	public interface Map<K, V> extends DataStructure {

		void put(K key, V value);

		V get(K key);

		V remove(K key);

		boolean containsKey(K key);
	}

	/* ========================================================= GRAPH */

	public interface Graph<T> extends DataStructure {

		void addVertex(T vertex);

		void addEdge(T source, T destination);

		void removeVertex(T vertex);

		void removeEdge(T source, T destination);

		boolean containsVertex(T vertex);

		boolean containsEdge(T source, T destination);

		void bfs(T startVertex);

		void dfs(T startVertex);
	}

	/* ========================================================= PRIORITY QUEUE */

	public interface PriorityQueue<T> extends DataStructure {

		void offer(T data);

		T poll();

		T peek();
	}

	/* ========================================================= SET */

	public interface Set<T> extends DataStructure {

		void add(T element);

		boolean remove(T element);

		boolean contains(T element);
	}

	/* ========================================================= TRIE */

	public interface Trie extends DataStructure {

		void insert(String word);

		boolean search(String word);

		boolean startsWith(String prefix);

		void delete(String word);
	}

	/* ========================================================= UNION FIND */

	public interface UnionFind extends DataStructure {

		int find(int x);

		void union(int x, int y);

		boolean connected(int x, int y);

		int setCount();
	}

	/* ========================================================= FENWICK TREE (BIT) */

	public interface FenwickTree extends DataStructure {

		void update(int index, int delta);

		int prefixSum(int index);

		int rangeSum(int left, int right);
	}

	/* ========================================================= SEGMENT TREE */

	public interface SegmentTree extends DataStructure {

		void build(int[] values);

		void update(int index, int value);

		int query(int left, int right);
	}

	/* ========================================================= LRU CACHE */

	public interface LRUCache<K, V> extends DataStructure {

		V get(K key);

		void put(K key, V value);

		int capacity();
	}
}
