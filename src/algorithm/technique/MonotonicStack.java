package algorithm.technique;

import datastructure.stack.LinkedStack;

/**
 * Monotonic stack — next greater/smaller element patterns.
 */
public final class MonotonicStack {

	private MonotonicStack() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	/** For each index, find next greater element (-1 if none). */
	public static int[] nextGreaterElement(int[] array) {
		int[] result = new int[array.length];
		LinkedStack<Integer> stack = new LinkedStack<>();

		for (int i = 0; i < array.length; i++) {
			while (!stack.isEmpty() && array[stack.peek()] < array[i]) {
				result[stack.pop()] = array[i];
			}
			stack.push(i);
		}

		while (!stack.isEmpty()) {
			result[stack.pop()] = -1;
		}

		return result;
	}

	/** Daily temperatures — days until warmer. */
	public static int[] dailyTemperatures(int[] temperatures) {
		int[] answer = new int[temperatures.length];
		LinkedStack<Integer> stack = new LinkedStack<>();

		for (int i = 0; i < temperatures.length; i++) {
			while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
				int prev = stack.pop();
				answer[prev] = i - prev;
			}
			stack.push(i);
		}

		return answer;
	}
}
