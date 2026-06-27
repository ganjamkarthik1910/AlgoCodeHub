package datastructure.queue;

import foundation.ds.DSAbstract;
import datastructure.heap.MinHeap;

import java.util.NoSuchElementException;

/**
 * Priority queue (min) backed by a custom min-heap.
 */
public class MinPriorityQueue<T extends Comparable<T>> extends DSAbstract.PriorityQueue<T> {

	private final MinHeap<T> heap = new MinHeap<>();

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
