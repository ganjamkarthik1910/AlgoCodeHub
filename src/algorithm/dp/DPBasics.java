package algorithm.dp;

/**
 * 1D dynamic programming classics.
 */
public final class DPBasics {

	private DPBasics() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	public static int fibonacci(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n must be non-negative");
		}

		if (n <= 1) {
			return n;
		}

		int prev2 = 0;
		int prev1 = 1;

		for (int i = 2; i <= n; i++) {
			int current = prev1 + prev2;
			prev2 = prev1;
			prev1 = current;
		}

		return prev1;
	}

	public static int climbingStairs(int n) {
		if (n <= 2) {
			return n;
		}

		int prev2 = 1;
		int prev1 = 2;

		for (int i = 3; i <= n; i++) {
			int current = prev1 + prev2;
			prev2 = prev1;
			prev1 = current;
		}

		return prev1;
	}

	public static int houseRobber(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}

		int prev2 = nums[0];
		int prev1 = Math.max(nums[0], nums[1]);

		for (int i = 2; i < nums.length; i++) {
			int current = Math.max(prev1, prev2 + nums[i]);
			prev2 = prev1;
			prev1 = current;
		}

		return prev1;
	}

	public static int maxSubarraySum(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		int best = nums[0];
		int current = nums[0];

		for (int i = 1; i < nums.length; i++) {
			current = Math.max(nums[i], current + nums[i]);
			best = Math.max(best, current);
		}

		return best;
	}
}
