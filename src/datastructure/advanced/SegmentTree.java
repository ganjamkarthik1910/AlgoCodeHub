package datastructure.advanced;

import foundation.ds.DSAbstract;

/**
 * Segment tree for range sum queries and point updates.
 */
public class SegmentTree extends DSAbstract.SegmentTree {

	private int[] tree;
	private int n;

	public SegmentTree(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Capacity must be positive");
		}

		n = capacity;
		tree = new int[4 * capacity];
		size = capacity;
	}

	@Override
	public void clear() {
		for (int i = 0; i < tree.length; i++) {
			tree[i] = 0;
		}
	}

	@Override
	public void build(int[] values) {
		if (values.length != n) {
			throw new IllegalArgumentException("Values length must match tree capacity");
		}

		build(values, 0, 0, n - 1);
	}

	@Override
	public void update(int index, int value) {
		validate(index);
		update(index, value, 0, 0, n - 1);
	}

	@Override
	public int query(int left, int right) {
		if (left > right) {
			throw new IllegalArgumentException("left must be <= right");
		}

		validate(left);
		validate(right);
		return query(left, right, 0, 0, n - 1);
	}

	private void build(int[] values, int node, int start, int end) {
		if (start == end) {
			tree[node] = values[start];
			return;
		}

		int mid = (start + end) / 2;
		build(values, 2 * node + 1, start, mid);
		build(values, 2 * node + 2, mid + 1, end);
		tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
	}

	private void update(int index, int value, int node, int start, int end) {
		if (start == end) {
			tree[node] = value;
			return;
		}

		int mid = (start + end) / 2;
		if (index <= mid) {
			update(index, value, 2 * node + 1, start, mid);
		} else {
			update(index, value, 2 * node + 2, mid + 1, end);
		}

		tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
	}

	private int query(int left, int right, int node, int start, int end) {
		if (right < start || end < left) {
			return 0;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		return query(left, right, 2 * node + 1, start, mid)
				+ query(left, right, 2 * node + 2, mid + 1, end);
	}

	private void validate(int index) {
		if (index < 0 || index >= n) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + n);
		}
	}
}
