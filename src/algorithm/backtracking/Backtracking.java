package algorithm.backtracking;

/**
 * Backtracking templates — subsets, permutations, combinations, N-Queens.
 */
public final class Backtracking {

	private Backtracking() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	public static int[][] subsets(int[] nums) {
		int total = 1 << nums.length;
		int[][] result = new int[total][];

		for (int mask = 0; mask < total; mask++) {
			int[] subset = new int[countBits(mask)];
			int index = 0;

			for (int i = 0; i < nums.length; i++) {
				if ((mask & (1 << i)) != 0) {
					subset[index++] = nums[i];
				}
			}

			result[mask] = subset;
		}

		return result;
	}

	public static int[][] permutations(int[] nums) {
		int n = nums.length;
		int count = factorial(n);
		int[][] result = new int[count][n];
		int[] current = nums.clone();
		int index = 0;

		do {
			result[index++] = current.clone();
		} while (nextPermutation(current));

		return result;
	}

	public static int combinationSumCount(int[] candidates, int target) {
		return combinationSumCount(candidates, target, 0, 0);
	}

	private static int combinationSumCount(int[] candidates, int target, int start, int currentSum) {
		if (currentSum == target) {
			return 1;
		}
		if (currentSum > target) {
			return 0;
		}

		int count = 0;

		for (int i = start; i < candidates.length; i++) {
			count += combinationSumCount(candidates, target, i, currentSum + candidates[i]);
		}

		return count;
	}

	public static int nQueensCount(int n) {
		int[] board = new int[n];
		return solveNQueens(board, 0);
	}

	private static int solveNQueens(int[] board, int row) {
		if (row == board.length) {
			return 1;
		}

		int count = 0;

		for (int col = 0; col < board.length; col++) {
			if (isSafe(board, row, col)) {
				board[row] = col;
				count += solveNQueens(board, row + 1);
			}
		}

		return count;
	}

	private static boolean isSafe(int[] board, int row, int col) {
		for (int i = 0; i < row; i++) {
			if (board[i] == col || Math.abs(board[i] - col) == row - i) {
				return false;
			}
		}
		return true;
	}

	private static int countBits(int mask) {
		int count = 0;
		while (mask > 0) {
			count += mask & 1;
			mask >>= 1;
		}
		return count;
	}

	private static int factorial(int n) {
		int result = 1;
		for (int i = 2; i <= n; i++) {
			result *= i;
		}
		return result;
	}

	private static boolean nextPermutation(int[] nums) {
		int i = nums.length - 2;

		while (i >= 0 && nums[i] >= nums[i + 1]) {
			i--;
		}

		if (i < 0) {
			return false;
		}

		int j = nums.length - 1;
		while (nums[j] <= nums[i]) {
			j--;
		}

		swap(nums, i, j);
		reverse(nums, i + 1, nums.length - 1);
		return true;
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	private static void reverse(int[] nums, int left, int right) {
		while (left < right) {
			swap(nums, left++, right--);
		}
	}
}
