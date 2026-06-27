package datastructure.advanced;

import foundation.ds.DSAbstract;

/**
 * Fenwick tree (Binary Indexed Tree) for prefix/range sum queries.
 */
public class FenwickTree extends DSAbstract.FenwickTree {

	private int[] tree;

	public FenwickTree(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Size must be positive");
		}

		tree = new int[n + 1];
		size = n;
	}

	@Override
	public void clear() {
		for (int i = 0; i < tree.length; i++) {
			tree[i] = 0;
		}
	}

	@Override
	public void update(int index, int delta) {
		validate(index);

		for (int i = index + 1; i < tree.length; i += i & -i) {
			tree[i] += delta;
		}
	}

	@Override
	public int prefixSum(int index) {
		validate(index);

		int sum = 0;
		for (int i = index + 1; i > 0; i -= i & -i) {
			sum += tree[i];
		}

		return sum;
	}

	@Override
	public int rangeSum(int left, int right) {
		if (left > right) {
			throw new IllegalArgumentException("left must be <= right");
		}

		validate(left);
		validate(right);

		return prefixSum(right) - (left == 0 ? 0 : prefixSum(left - 1));
	}

	private void validate(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}
}
