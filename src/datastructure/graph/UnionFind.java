package datastructure.graph;

import foundation.ds.DSAbstract;

/**
 * Disjoint set union (Union-Find) with path compression and union by rank.
 */
public class UnionFind extends DSAbstract.UnionFind {

	private final int[] parent;
	private final int[] rank;
	private int setCount;

	public UnionFind(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Size must be positive");
		}

		parent = new int[n];
		rank = new int[n];
		setCount = n;
		size = n;

		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}

	@Override
	public void clear() {
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
			rank[i] = 0;
		}

		setCount = parent.length;
		size = parent.length;
	}

	@Override
	public int find(int x) {
		validate(x);

		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}

		return parent[x];
	}

	@Override
	public void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);

		if (rootX == rootY) {
			return;
		}

		if (rank[rootX] < rank[rootY]) {
			parent[rootX] = rootY;
		} else if (rank[rootX] > rank[rootY]) {
			parent[rootY] = rootX;
		} else {
			parent[rootY] = rootX;
			rank[rootX]++;
		}

		setCount--;
	}

	@Override
	public boolean connected(int x, int y) {
		return find(x) == find(y);
	}

	@Override
	public int setCount() {
		return setCount;
	}

	private void validate(int x) {
		if (x < 0 || x >= parent.length) {
			throw new IndexOutOfBoundsException("Index: " + x);
		}
	}
}
