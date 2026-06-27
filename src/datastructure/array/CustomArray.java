package datastructure.array;

/**
 * Custom resizable array — foundation for heap, stack, queue, and matrix structures.
 * No {@code java.util} collections used.
 */
public class CustomArray<T> {

	private static final int DEFAULT_CAPACITY = 10;

	private Object[] data;
	private int size;

	public CustomArray() {
		data = new Object[DEFAULT_CAPACITY];
	}

	public CustomArray(int capacity) {
		data = new Object[Math.max(capacity, DEFAULT_CAPACITY)];
	}

	public void add(T value) {
		ensureCapacity(size + 1);
		data[size++] = value;
	}

	public T get(int index) {
		validate(index);
		return (T) data[index];
	}

	public void set(int index, T value) {
		validate(index);
		data[index] = value;
	}

	public T removeLast() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("Array is empty");
		}

		T value = (T) data[--size];
		data[size] = null;
		return value;
	}

	public int length() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
		size = 0;
	}

	public void printArray() {
		for (int i = 0; i < size; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}

	private void ensureCapacity(int minCapacity) {
		if (minCapacity <= data.length) {
			return;
		}

		Object[] resized = new Object[data.length * 2];
		System.arraycopy(data, 0, resized, 0, size);
		data = resized;
	}

	private void validate(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}
}
