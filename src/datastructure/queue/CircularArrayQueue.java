package datastructure.queue;

import foundation.ds.DSAbstract;

import java.util.NoSuchElementException;

/**
 * FIFO queue backed by a circular array (ring buffer).
 */
public class CircularArrayQueue<T> extends DSAbstract.Queue<T> {

	private static final int DEFAULT_CAPACITY = 10;

	private Object[] data;
	private int head;
	private int tail;
	private int capacity;

	@SuppressWarnings("unchecked")
	public CircularArrayQueue() {
		capacity = DEFAULT_CAPACITY;
		data = new Object[capacity];
	}

	@Override
	public void clear() {
		head = 0;
		tail = 0;
		size = 0;
	}

	@Override
	public void enqueue(T value) {
		if (size == capacity) {
			resize();
		}

		data[tail] = value;
		tail = (tail + 1) % capacity;
		incrementSize();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}

		T value = (T) data[head];
		data[head] = null;
		head = (head + 1) % capacity;
		decrementSize();
		return value;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}

		return (T) data[head];
	}

	public void printQueue() {
		for (int i = 0; i < size; i++) {
			int index = (head + i) % capacity;
			System.out.print(data[index] + " ");
		}
		System.out.println();
	}

	private void resize() {
		Object[] resized = new Object[capacity * 2];
		for (int i = 0; i < size; i++) {
			resized[i] = data[(head + i) % capacity];
		}

		data = resized;
		head = 0;
		tail = size;
		capacity *= 2;
	}
}
