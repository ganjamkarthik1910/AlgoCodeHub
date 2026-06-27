package algorithm.sort;

import foundation.algo.AlgoAbstract;

/**
 * Non-comparison sort for small non-negative integer ranges.
 */
public class CountingSort extends AlgoAbstract.IntSortAlgorithm {

	@Override
	public void sort(int[] array) {
		validate(array);

		if (array.length == 0) {
			return;
		}

		int max = array[0];
		for (int value : array) {
			if (value < 0) {
				throw new IllegalArgumentException("Counting sort requires non-negative integers");
			}
			max = Math.max(max, value);
		}

		int[] count = new int[max + 1];
		for (int value : array) {
			count[value]++;
		}

		int index = 0;
		for (int value = 0; value <= max; value++) {
			while (count[value]-- > 0) {
				array[index++] = value;
			}
		}
	}

	@Override
	public String name() {
		return "Counting Sort";
	}
}
