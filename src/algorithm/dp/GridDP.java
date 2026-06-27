package algorithm.dp;

/**
 * Grid DP — paths, min path sum.
 */
public final class GridDP {

	private GridDP() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	public static int uniquePaths(int rows, int cols) {
		if (rows <= 0 || cols <= 0) {
			return 0;
		}

		int[][] dp = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			dp[i][0] = 1;
		}
		for (int j = 0; j < cols; j++) {
			dp[0][j] = 1;
		}

		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < cols; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}

		return dp[rows - 1][cols - 1];
	}

	public static int minPathSum(int[][] grid) {
		if (grid.length == 0 || grid[0].length == 0) {
			return 0;
		}

		int rows = grid.length;
		int cols = grid[0].length;
		int[][] dp = new int[rows][cols];
		dp[0][0] = grid[0][0];

		for (int j = 1; j < cols; j++) {
			dp[0][j] = dp[0][j - 1] + grid[0][j];
		}

		for (int i = 1; i < rows; i++) {
			dp[i][0] = dp[i - 1][0] + grid[i][0];

			for (int j = 1; j < cols; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
			}
		}

		return dp[rows - 1][cols - 1];
	}
}
