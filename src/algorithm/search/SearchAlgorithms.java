package algorithm.search;

/**
 * Search algorithms — O(n) to O(log n).
 */
public final class SearchAlgorithms {

	private SearchAlgorithms() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	public static int linearSearch(int[] array, int target) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == target) {
				return i;
			}
		}
		return -1;
	}

	public static int binarySearch(int[] sorted, int target) {
		int left = 0;
		int right = sorted.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (sorted[mid] == target) {
				return mid;
			}
			if (sorted[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return -1;
	}

	public static int binarySearchRecursive(int[] sorted, int target) {
		return binarySearchRecursive(sorted, target, 0, sorted.length - 1);
	}

	private static int binarySearchRecursive(int[] sorted, int target, int left, int right) {
		if (left > right) {
			return -1;
		}

		int mid = left + (right - left) / 2;

		if (sorted[mid] == target) {
			return mid;
		}
		if (sorted[mid] < target) {
			return binarySearchRecursive(sorted, target, mid + 1, right);
		}
		return binarySearchRecursive(sorted, target, left, mid - 1);
	}

	/** First index with value >= target (lower bound). */
	public static int lowerBound(int[] sorted, int target) {
		int left = 0;
		int right = sorted.length;

		while (left < right) {
			int mid = left + (right - left) / 2;
			if (sorted[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return left;
	}

	/** First index with value > target (upper bound). */
	public static int upperBound(int[] sorted, int target) {
		int left = 0;
		int right = sorted.length;

		while (left < right) {
			int mid = left + (right - left) / 2;
			if (sorted[mid] <= target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return left;
	}

	/**
	 * Binary search on answer — finds minimum value in [lo, hi] satisfying predicate.
	 * Predicate must be monotonic: false … false true … true.
	 */
	public static int binarySearchOnAnswer(int lo, int hi, foundation.algo.AlgoInterface.IntCondition canDo) {
		int left = lo;
		int right = hi;
		int answer = -1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (canDo.test(mid)) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return answer;
	}
}
