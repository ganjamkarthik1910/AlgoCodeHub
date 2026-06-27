package algorithm.dp;

/**
 * Subsequence DP — LCS, LIS, edit distance.
 */
public final class SubsequenceDP {

	private SubsequenceDP() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	public static int longestCommonSubsequence(String a, String b) {
		int[][] dp = new int[a.length() + 1][b.length() + 1];

		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j <= b.length(); j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		return dp[a.length()][b.length()];
	}

	public static int longestIncreasingSubsequence(int[] array) {
		int[] dp = new int[array.length];
		int best = 0;

		for (int i = 0; i < array.length; i++) {
			dp[i] = 1;

			for (int j = 0; j < i; j++) {
				if (array[j] < array[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}

			best = Math.max(best, dp[i]);
		}

		return best;
	}

	public static int editDistance(String a, String b) {
		int[][] dp = new int[a.length() + 1][b.length() + 1];

		for (int i = 0; i <= a.length(); i++) {
			dp[i][0] = i;
		}
		for (int j = 0; j <= b.length(); j++) {
			dp[0][j] = j;
		}

		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j <= b.length(); j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
				}
			}
		}

		return dp[a.length()][b.length()];
	}
}
