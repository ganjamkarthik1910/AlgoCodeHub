package algorithm.technique;

/**
 * Prefix sum — O(1) range sum queries after O(n) preprocessing.
 */
public final class PrefixSum {

	private final int[] prefix;

	public PrefixSum(int[] array) {
		prefix = new int[array.length + 1];

		for (int i = 0; i < array.length; i++) {
			prefix[i + 1] = prefix[i] + array[i];
		}
	}

	public int rangeSum(int left, int right) {
		if (left < 0 || right >= prefix.length - 1 || left > right) {
			throw new IndexOutOfBoundsException();
		}

		return prefix[right + 1] - prefix[left];
	}

	public static int[] build(int[] array) {
		int[] prefix = new int[array.length + 1];

		for (int i = 0; i < array.length; i++) {
			prefix[i + 1] = prefix[i] + array[i];
		}

		return prefix;
	}

	public static int query(int[] prefix, int left, int right) {
		return prefix[right + 1] - prefix[left];
	}
}
