package algorithm.technique;

/**
 * Two-pointer patterns — sorted arrays, palindromes, partitioning.
 */
public final class TwoPointer {

	private TwoPointer() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	/** Classic: find two indices where sorted[i] + sorted[j] == target. */
	public static int[] twoSumSorted(int[] sorted, int target) {
		int left = 0;
		int right = sorted.length - 1;

		while (left < right) {
			int sum = sorted[left] + sorted[right];

			if (sum == target) {
				return new int[] { left, right };
			}
			if (sum < target) {
				left++;
			} else {
				right--;
			}
		}

		return new int[] { -1, -1 };
	}

	/** Remove duplicates in-place from sorted array, return new length. */
	public static int removeDuplicates(int[] sorted) {
		if (sorted.length == 0) {
			return 0;
		}

		int write = 1;

		for (int read = 1; read < sorted.length; read++) {
			if (sorted[read] != sorted[read - 1]) {
				sorted[write++] = sorted[read];
			}
		}

		return write;
	}

	/** Check if string is palindrome using two pointers. */
	public static boolean isPalindrome(String text) {
		int left = 0;
		int right = text.length() - 1;

		while (left < right) {
			if (text.charAt(left) != text.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}

		return true;
	}

	/** Move zeroes to end while maintaining relative order. */
	public static void moveZeroes(int[] array) {
		int write = 0;

		for (int read = 0; read < array.length; read++) {
			if (array[read] != 0) {
				int temp = array[write];
				array[write] = array[read];
				array[read] = temp;
				write++;
			}
		}
	}

	/** Container with most water — max area between vertical lines. */
	public static int maxArea(int[] heights) {
		int left = 0;
		int right = heights.length - 1;
		int best = 0;

		while (left < right) {
			int width = right - left;
			int height = Math.min(heights[left], heights[right]);
			best = Math.max(best, width * height);

			if (heights[left] < heights[right]) {
				left++;
			} else {
				right--;
			}
		}

		return best;
	}
}
