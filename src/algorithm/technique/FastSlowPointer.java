package algorithm.technique;

import datastructure.list.DSNodes.SinglyNode;

/**
 * Fast & slow pointer (Floyd's) — cycle detection, middle node.
 */
public final class FastSlowPointer {

	private FastSlowPointer() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	public static <T> boolean hasCycle(SinglyNode<T> head) {
		SinglyNode<T> slow = head;
		SinglyNode<T> fast = head;

		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();

			if (slow == fast) {
				return true;
			}
		}

		return false;
	}

	public static <T> SinglyNode<T> middleNode(SinglyNode<T> head) {
		SinglyNode<T> slow = head;
		SinglyNode<T> fast = head;

		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}

		return slow;
	}

	/** Find duplicate in array [1..n] where values are in range (Floyd on indices). */
	public static int findDuplicate(int[] array) {
		int slow = array[0];
		int fast = array[0];

		do {
			slow = array[slow];
			fast = array[array[fast]];
		} while (slow != fast);

		slow = array[0];
		while (slow != fast) {
			slow = array[slow];
			fast = array[fast];
		}

		return slow;
	}
}
