package algorithm.sort;

import foundation.algo.AlgoAbstract;

public class QuickSort extends AlgoAbstract.IntSortAlgorithm {

	@Override
	public void sort(int[] array) {
		validate(array);

		if (array.length <= 1) {
			return;
		}

		quickSort(array, 0, array.length - 1);
	}

	private void quickSort(int[] array, int low, int high) {
		if (low >= high) {
			return;
		}

		int pivotIndex = partition(array, low, high);
		quickSort(array, low, pivotIndex - 1);
		quickSort(array, pivotIndex + 1, high);
	}

	private int partition(int[] array, int low, int high) {
		int mid = low + (high - low) / 2;
		swap(array, mid, high);

		int pivot = array[high];
		int i = low - 1;

		for (int j = low; j < high; j++) {
			if (array[j] <= pivot) {
				i++;
				swap(array, i, j);
			}
		}

		swap(array, i + 1, high);
		return i + 1;
	}

	@Override
	public String name() {
		return "Quick Sort";
	}
}
