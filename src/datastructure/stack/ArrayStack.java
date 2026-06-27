package datastructure.stack;

import datastructure.array.CustomArray;
import foundation.ds.DSAbstract;

import java.util.NoSuchElementException;

/**
 * LIFO stack backed by a custom dynamic array.
 */
public class ArrayStack<T> extends DSAbstract.Stack<T> {

	private final CustomArray<T> array = new CustomArray<>();

	@Override
	public void clear() {
		array.clear();
		size = 0;
	}

	@Override
	public void push(T data) {
		array.add(data);
		incrementSize();
	}

	@Override
	public T pop() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack is empty");
		}

		decrementSize();
		return array.removeLast();
	}

	@Override
	public T peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack is empty");
		}

		return array.get(size - 1);
	}

	public void printStack() {
		array.printArray();
	}
}
