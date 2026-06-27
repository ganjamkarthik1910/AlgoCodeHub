package algorithm.technique;

/**
 * Difference array — O(1) range updates, prefix to get final array.
 */
public final class DifferenceArray {

	private final int[] diff;

	public DifferenceArray(int length) {
		diff = new int[length + 1];
	}

	public void rangeUpdate(int left, int right, int delta) {
		if (left < 0 || right >= diff.length - 1 || left > right) {
			throw new IndexOutOfBoundsException("Invalid range: [" + left + ", " + right + "]");
		}

		diff[left] += delta;
		diff[right + 1] -= delta;
	}

	public int[] reconstruct() {
		int[] result = new int[diff.length - 1];
		result[0] = diff[0];

		for (int i = 1; i < result.length; i++) {
			result[i] = result[i - 1] + diff[i];
		}

		return result;
	}
}
