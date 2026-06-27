package algorithm.technique;

/**
 * Sliding window patterns — fixed and variable size windows.
 */
public final class SlidingWindow {

	private SlidingWindow() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	/** Max sum of any contiguous subarray of size k. */
	public static int maxSumSubarrayOfSizeK(int[] array, int k) {
		if (k <= 0 || k > array.length) {
			throw new IllegalArgumentException("Invalid window size");
		}

		int windowSum = 0;
		for (int i = 0; i < k; i++) {
			windowSum += array[i];
		}

		int best = windowSum;
		for (int i = k; i < array.length; i++) {
			windowSum += array[i] - array[i - k];
			best = Math.max(best, windowSum);
		}

		return best;
	}

	/** Length of longest subarray with sum <= target (non-negative values). */
	public static int longestSubarraySumAtMost(int[] array, int target) {
		int left = 0;
		int sum = 0;
		int best = 0;

		for (int right = 0; right < array.length; right++) {
			sum += array[right];

			while (sum > target && left <= right) {
				sum -= array[left++];
			}

			best = Math.max(best, right - left + 1);
		}

		return best;
	}

	/** Min length of subarray with sum >= target (positive integers). Returns 0 if impossible. */
	public static int minSubarrayLen(int target, int[] array) {
		int left = 0;
		int sum = 0;
		int best = Integer.MAX_VALUE;

		for (int right = 0; right < array.length; right++) {
			sum += array[right];

			while (sum >= target) {
				best = Math.min(best, right - left + 1);
				sum -= array[left++];
			}
		}

		return best == Integer.MAX_VALUE ? 0 : best;
	}

	/** Longest substring without repeating characters. */
	public static int longestSubstringWithoutRepeating(String text) {
		int[] lastSeen = new int[128];
		for (int i = 0; i < 128; i++) {
			lastSeen[i] = -1;
		}

		int left = 0;
		int best = 0;

		for (int right = 0; right < text.length(); right++) {
			char ch = text.charAt(right);

			if (lastSeen[ch] >= left) {
				left = lastSeen[ch] + 1;
			}

			lastSeen[ch] = right;
			best = Math.max(best, right - left + 1);
		}

		return best;
	}
}
