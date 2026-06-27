package datastructure.set;

import foundation.ds.DSAbstract;
import datastructure.map.ChainedHashMap;

/**
 * Hash set backed by a custom chained hash map.
 */
public class HashSet<T> extends DSAbstract.Set<T> {

	private static final Object PRESENT = new Object();

	private final ChainedHashMap<T, Object> map = new ChainedHashMap<>();

	@Override
	public void clear() {
		map.clear();
		size = 0;
	}

	@Override
	public void add(T element) {
		map.putIfAbsent(element, PRESENT);
		size = map.size();
	}

	@Override
	public boolean remove(T element) {
		if (map.remove(element) == null) {
			size = map.size();
			return false;
		}

		size = map.size();
		return true;
	}

	@Override
	public boolean contains(T element) {
		return map.containsKey(element);
	}

	public void printSet() {
		map.printMap();
	}
}
