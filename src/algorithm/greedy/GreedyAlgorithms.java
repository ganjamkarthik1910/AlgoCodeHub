package algorithm.greedy;

import algorithm.dp.Knapsack;

/**
 * Greedy algorithms — intervals, jumps, scheduling.
 */
public final class GreedyAlgorithms {

	private GreedyAlgorithms() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	public static int maxActivities(int[] start, int[] end) {
		int n = start.length;
		int[][] activities = new int[n][2];

		for (int i = 0; i < n; i++) {
			activities[i][0] = start[i];
			activities[i][1] = end[i];
		}

		sortByEnd(activities);

		int count = 0;
		int lastEnd = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			if (activities[i][0] >= lastEnd) {
				count++;
				lastEnd = activities[i][1];
			}
		}

		return count;
	}

	public static int[][] mergeIntervals(int[][] intervals) {
		if (intervals.length == 0) {
			return new int[0][0];
		}

		sortByStart(intervals);

		int[][] temp = new int[intervals.length][2];
		int index = 0;
		temp[index] = intervals[0];

		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] <= temp[index][1]) {
				temp[index][1] = Math.max(temp[index][1], intervals[i][1]);
			} else {
				temp[++index] = intervals[i];
			}
		}

		int[][] result = new int[index + 1][2];
		System.arraycopy(temp, 0, result, 0, index + 1);
		return result;
	}

	public static boolean canJump(int[] nums) {
		int farthest = 0;

		for (int i = 0; i < nums.length; i++) {
			if (i > farthest) {
				return false;
			}

			farthest = Math.max(farthest, i + nums[i]);

			if (farthest >= nums.length - 1) {
				return true;
			}
		}

		return true;
	}

	public static int minCoins(int[] coins, int amount) {
		return Knapsack.coinChange(coins, amount);
	}

	private static void sortByEnd(int[][] activities) {
		for (int i = 1; i < activities.length; i++) {
			int[] key = activities[i];
			int j = i - 1;

			while (j >= 0 && activities[j][1] > key[1]) {
				activities[j + 1] = activities[j];
				j--;
			}

			activities[j + 1] = key;
		}
	}

	private static void sortByStart(int[][] intervals) {
		for (int i = 1; i < intervals.length; i++) {
			int[] key = intervals[i];
			int j = i - 1;

			while (j >= 0 && intervals[j][0] > key[0]) {
				intervals[j + 1] = intervals[j];
				j--;
			}

			intervals[j + 1] = key;
		}
	}
}
