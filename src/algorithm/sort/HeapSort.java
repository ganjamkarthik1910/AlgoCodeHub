package algorithm.sort;

import foundation.algo.AlgoAbstract;

public class HeapSort extends AlgoAbstract.IntSortAlgorithm {

	@Override
	public void sort(int[] array) {
		validate(array);

		int n = array.length;

		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(array, n, i);
		}

		for (int i = n - 1; i > 0; i--) {
			swap(array, 0, i);
			heapify(array, i, 0);
		}
	}

	@Override
	public String name() {
		return "Heap Sort";
	}

	private static void heapify(int[] array, int heapSize, int root) {
		int largest = root;
		int left = 2 * root + 1;
		int right = 2 * root + 2;

		if (left < heapSize && array[left] > array[largest]) {
			largest = left;
		}

		if (right < heapSize && array[right] > array[largest]) {
			largest = right;
		}

		if (largest != root) {
			swap(array, root, largest);
			heapify(array, heapSize, largest);
		}
	}
}
