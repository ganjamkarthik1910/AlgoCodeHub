package datastructure.map;

import foundation.ds.DSAbstract;

import java.util.Objects;

/**
 * Hash map with separate chaining and automatic rehashing.
 */
public class ChainedHashMap<K, V> extends DSAbstract.Map<K, V> {

	private static final int DEFAULT_CAPACITY = 16;
	private static final double LOAD_FACTOR = 0.75;

	private Entry<K, V>[] buckets;

	@SuppressWarnings("unchecked")
	public ChainedHashMap() {
		buckets = new Entry[DEFAULT_CAPACITY];
	}

	@Override
	public void clear() {
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = null;
		}
		size = 0;
	}

	@Override
	public void put(K key, V value) {
		int index = bucketIndex(key);

		Entry<K, V> current = buckets[index];
		while (current != null) {
			if (Objects.equals(current.key, key)) {
				current.value = value;
				return;
			}
			current = current.next;
		}

		buckets[index] = new Entry<>(key, value, buckets[index]);
		incrementSize();
		resizeIfNeeded();
	}

	@Override
	public V get(K key) {
		Entry<K, V> entry = findEntry(key);
		return entry != null ? entry.value : null;
	}

	@Override
	public V remove(K key) {
		int index = bucketIndex(key);
		Entry<K, V> current = buckets[index];
		Entry<K, V> prev = null;

		while (current != null) {
			if (Objects.equals(current.key, key)) {
				if (prev == null) {
					buckets[index] = current.next;
				} else {
					prev.next = current.next;
				}

				decrementSize();
				return current.value;
			}

			prev = current;
			current = current.next;
		}

		return null;
	}

	@Override
	public boolean containsKey(K key) {
		return findEntry(key) != null;
	}

	/** Single-pass insert; returns {@code true} if the key was absent. */
	public boolean putIfAbsent(K key, V value) {
		int index = bucketIndex(key);
		Entry<K, V> current = buckets[index];

		while (current != null) {
			if (Objects.equals(current.key, key)) {
				return false;
			}
			current = current.next;
		}

		buckets[index] = new Entry<>(key, value, buckets[index]);
		incrementSize();
		resizeIfNeeded();
		return true;
	}

	public void printMap() {
		for (int i = 0; i < buckets.length; i++) {
			Entry<K, V> current = buckets[i];
			if (current == null) {
				continue;
			}

			System.out.print("bucket " + i + ": ");
			while (current != null) {
				System.out.print("[" + current.key + "=" + current.value + "]");
				if (current.next != null) {
					System.out.print(" -> ");
				}
				current = current.next;
			}
			System.out.println();
		}
	}

	private Entry<K, V> findEntry(K key) {
		int index = bucketIndex(key);
		Entry<K, V> current = buckets[index];

		while (current != null) {
			if (Objects.equals(current.key, key)) {
				return current;
			}
			current = current.next;
		}

		return null;
	}

	private int bucketIndex(K key) {
		return Math.floorMod(Objects.hashCode(key), buckets.length);
	}

	private void resizeIfNeeded() {
		if (size <= buckets.length * LOAD_FACTOR) {
			return;
		}

		Entry<K, V>[] oldBuckets = buckets;
		buckets = new Entry[oldBuckets.length * 2];
		size = 0;

		for (Entry<K, V> bucket : oldBuckets) {
			Entry<K, V> current = bucket;
			while (current != null) {
				put(current.key, current.value);
				current = current.next;
			}
		}
	}

	private static class Entry<K, V> {

		private final K key;
		private V value;
		private Entry<K, V> next;

		Entry(K key, V value, Entry<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
}
