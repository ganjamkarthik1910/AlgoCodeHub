package datastructure.list;

import foundation.ds.DSInterface.DoublyNodeAccess;
import foundation.ds.DSInterface.NodeAccess;

/**
 * Shared node types for all linked list implementations.
 */
public final class DSNodes {

	private DSNodes() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	public static class SinglyNode<T> implements NodeAccess<T> {

		private T data;
		private SinglyNode<T> next;

		public SinglyNode(T data) {
			this.data = data;
		}

		@Override
		public T getData() {
			return data;
		}

		public SinglyNode<T> getNext() {
			return next;
		}

		public void setNext(SinglyNode<T> next) {
			this.next = next;
		}
	}

	public static class DoublyNode<T> implements DoublyNodeAccess<T> {

		private T data;
		private DoublyNode<T> next;
		private DoublyNode<T> prev;

		public DoublyNode(T data) {
			this.data = data;
		}

		@Override
		public T getData() {
			return data;
		}

		@Override
		public DoublyNodeAccess<T> prev() {
			return prev;
		}

		@Override
		public DoublyNodeAccess<T> next() {
			return next;
		}

		public DoublyNode<T> getNext() {
			return next;
		}

		public DoublyNode<T> getPrev() {
			return prev;
		}

		public void setNext(DoublyNode<T> next) {
			this.next = next;
		}

		public void setPrev(DoublyNode<T> prev) {
			this.prev = prev;
		}
	}
}
