package algorithm.sort;

import foundation.algo.AlgoAbstract;

public class InsertionSort extends AlgoAbstract.IntSortAlgorithm {

	@Override
	public void sort(int[] array) {
		validate(array);

		for (int i = 1; i < array.length; i++) {
			int key = array[i];
			int j = i - 1;

			while (j >= 0 && array[j] > key) {
				array[j + 1] = array[j];
				j--;
			}

			array[j + 1] = key;
		}
	}

	@Override
	public String name() {
		return "Insertion Sort";
	}
}
