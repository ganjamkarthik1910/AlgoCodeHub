package algorithm.dp;

/**
 * Knapsack family — 0/1, unbounded, and coin change.
 */
public final class Knapsack {

	private Knapsack() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	public static int knapsack01(int[] weights, int[] values, int capacity) {
		int n = weights.length;
		int[][] dp = new int[n + 1][capacity + 1];

		for (int i = 1; i <= n; i++) {
			for (int w = 0; w <= capacity; w++) {
				dp[i][w] = dp[i - 1][w];

				if (weights[i - 1] <= w) {
					dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
				}
			}
		}

		return dp[n][capacity];
	}

	public static int unboundedKnapsack(int[] weights, int[] values, int capacity) {
		int[] dp = new int[capacity + 1];

		for (int w = 1; w <= capacity; w++) {
			for (int i = 0; i < weights.length; i++) {
				if (weights[i] <= w) {
					dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
				}
			}
		}

		return dp[capacity];
	}

	public static int coinChange(int[] coins, int amount) {
		int[] dp = new int[amount + 1];

		for (int i = 1; i <= amount; i++) {
			dp[i] = amount + 1;
		}

		for (int i = 1; i <= amount; i++) {
			for (int coin : coins) {
				if (coin <= i) {
					dp[i] = Math.min(dp[i], dp[i - coin] + 1);
				}
			}
		}

		return dp[amount] > amount ? -1 : dp[amount];
	}
}
