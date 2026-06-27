package datastructure.queue;

import foundation.ds.DSAbstract;
import datastructure.heap.MaxHeap;

import java.util.NoSuchElementException;

/**
 * Priority queue (max) backed by a custom max-heap.
 */
public class MaxPriorityQueue<T extends Comparable<T>> extends DSAbstract.PriorityQueue<T> {

	private final MaxHeap<T> heap = new MaxHeap<>();

	@Override
	public int size() {
		return heap.size();
	}

	@Override
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	@Override
	public void clear() {
		heap.clear();
		size = 0;
	}

	@Override
	public void offer(T data) {
		heap.insert(data);
		size = heap.size();
	}

	@Override
	public T poll() {
		if (heap.isEmpty()) {
			throw new NoSuchElementException("Priority queue is empty");
		}

		T value = heap.extract();
		size = heap.size();
		return value;
	}

	@Override
	public T peek() {
		if (heap.isEmpty()) {
			throw new NoSuchElementException("Priority queue is empty");
		}

		return heap.peek();
	}

	public void printQueue() {
		heap.printHeap();
	}
}
