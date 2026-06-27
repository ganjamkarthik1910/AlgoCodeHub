package datastructure.heap;

import datastructure.array.CustomArray;
import foundation.ds.DSAbstract;

import java.util.NoSuchElementException;

/**
 * Max-heap backed by a custom dynamic array.
 */
public class MaxHeap<T extends Comparable<T>> extends DSAbstract.Heap<T> {

	private final CustomArray<T> heap = new CustomArray<>();

	@Override
	public void clear() {
		heap.clear();
		size = 0;
	}

	@Override
	public void insert(T data) {
		heap.add(data);
		incrementSize();
		bubbleUp(size - 1);
	}

	@Override
	public T extract() {
		if (isEmpty()) {
			throw new NoSuchElementException("Heap is empty");
		}

		T max = heap.get(0);
		T last = heap.removeLast();
		decrementSize();

		if (!isEmpty()) {
			heap.set(0, last);
			bubbleDown(0);
		}

		return max;
	}

	@Override
	public T peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Heap is empty");
		}

		return heap.get(0);
	}

	public void printHeap() {
		heap.printArray();
	}

	private void bubbleUp(int index) {
		while (index > 0) {
			int parent = (index - 1) / 2;

			if (heap.get(index).compareTo(heap.get(parent)) <= 0) {
				break;
			}

			swap(index, parent);
			index = parent;
		}
	}

	private void bubbleDown(int index) {
		while (true) {
			int left = 2 * index + 1;
			int right = 2 * index + 2;
			int largest = index;

			if (left < size && heap.get(left).compareTo(heap.get(largest)) > 0) {
				largest = left;
			}

			if (right < size && heap.get(right).compareTo(heap.get(largest)) > 0) {
				largest = right;
			}

			if (largest == index) {
				break;
			}

			swap(index, largest);
			index = largest;
		}
	}

	private void swap(int i, int j) {
		T temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}
}
