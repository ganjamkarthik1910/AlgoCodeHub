package datastructure.advanced;

import foundation.ds.DSAbstract;
import datastructure.list.DSNodes.DoublyNode;
import datastructure.list.DoublyLinkedList;
import datastructure.map.ChainedHashMap;

/**
 * LRU cache using a custom hash map + doubly linked list with O(1) get/put.
 */
public class LRUCache<K, V> extends DSAbstract.LRUCache<K, V> {

	private final int capacity;
	private final ChainedHashMap<K, V> values = new ChainedHashMap<>();
	private final ChainedHashMap<K, DoublyNode<K>> nodeMap = new ChainedHashMap<>();
	private final DoublyLinkedList<K> order = new DoublyLinkedList<>();

	public LRUCache(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Capacity must be positive");
		}

		this.capacity = capacity;
	}

	@Override
	public int capacity() {
		return capacity;
	}

	@Override
	public void clear() {
		values.clear();
		nodeMap.clear();
		order.clear();
		size = 0;
	}

	@Override
	public V get(K key) {
		if (!values.containsKey(key)) {
			return null;
		}

		V value = values.get(key);
		touch(key);
		return value;
	}

	@Override
	public void put(K key, V value) {
		if (values.containsKey(key)) {
			values.put(key, value);
			touch(key);
			return;
		}

		if (size >= capacity) {
			evictLru();
		}

		values.put(key, value);
		nodeMap.put(key, order.prependNode(key));
		incrementSize();
	}

	public void printCache() {
		order.printList();
	}

	private void touch(K key) {
		DoublyNode<K> node = nodeMap.get(key);
		if (node != null) {
			order.moveToFront(node);
		}
	}

	private void evictLru() {
		K lruKey = order.peekLast();
		DoublyNode<K> lruNode = nodeMap.get(lruKey);
		order.removeNode(lruNode);
		nodeMap.remove(lruKey);
		values.remove(lruKey);
		decrementSize();
	}
}
