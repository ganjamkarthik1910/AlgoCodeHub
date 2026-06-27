package algorithm.sort;

import foundation.algo.AlgoAbstract;

public class MergeSort extends AlgoAbstract.IntSortAlgorithm {

	@Override
	public void sort(int[] array) {
		validate(array);

		if (array.length <= 1) {
			return;
		}

		sort(array, 0, array.length - 1);
	}

	private void sort(int[] array, int left, int right) {
		if (left >= right) {
			return;
		}

		int mid = left + (right - left) / 2;
		sort(array, left, mid);
		sort(array, mid + 1, right);
		merge(array, left, mid, right);
	}

	private void merge(int[] array, int left, int mid, int right) {
		int[] temp = new int[right - left + 1];
		int i = left;
		int j = mid + 1;
		int k = 0;

		while (i <= mid && j <= right) {
			if (array[i] <= array[j]) {
				temp[k++] = array[i++];
			} else {
				temp[k++] = array[j++];
			}
		}

		while (i <= mid) {
			temp[k++] = array[i++];
		}

		while (j <= right) {
			temp[k++] = array[j++];
		}

		System.arraycopy(temp, 0, array, left, temp.length);
	}

	@Override
	public String name() {
		return "Merge Sort";
	}
}
