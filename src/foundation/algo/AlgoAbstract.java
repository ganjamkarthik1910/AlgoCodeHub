package foundation.algo;

/**
 * Shared helpers for algorithm implementations.
 */
public final class AlgoAbstract {

	private AlgoAbstract() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	public abstract static class IntSortAlgorithm implements AlgoInterface.IntSort {

		protected static void swap(int[] array, int i, int j) {
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}

		protected static int[] copy(int[] array) {
			int[] copy = new int[array.length];
			System.arraycopy(array, 0, copy, 0, array.length);
			return copy;
		}

		protected static void validate(int[] array) {
			if (array == null) {
				throw new IllegalArgumentException("Array cannot be null");
			}
		}
	}
}
