package datastructure.tree;

import foundation.ds.DSAbstract;
import datastructure.queue.LinkedQueue;

/**
 * Binary search tree for learning traversals and search.
 */
public class BinarySearchTree<T extends Comparable<T>> extends DSAbstract.Tree<T> {

	private Node root;

	private class Node {

		T data;
		Node left;
		Node right;

		Node(T data) {
			this.data = data;
		}
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public void insert(T data) {
		root = insert(root, data);
	}

	private Node insert(Node node, T data) {
		if (node == null) {
			incrementSize();
			return new Node(data);
		}

		int cmp = data.compareTo(node.data);
		if (cmp < 0) {
			node.left = insert(node.left, data);
		} else if (cmp > 0) {
			node.right = insert(node.right, data);
		}

		return node;
	}

	@Override
	public void delete(T data) {
		root = delete(root, data);
	}

	private Node delete(Node node, T data) {
		if (node == null) {
			return null;
		}

		int cmp = data.compareTo(node.data);
		if (cmp < 0) {
			node.left = delete(node.left, data);
		} else if (cmp > 0) {
			node.right = delete(node.right, data);
		} else {
			decrementSize();

			if (node.left == null) {
				return node.right;
			}
			if (node.right == null) {
				return node.left;
			}

			Node min = minNode(node.right);
			node.data = min.data;
			node.right = removeMin(node.right);
		}

		return node;
	}

	private Node removeMin(Node node) {
		if (node.left == null) {
			return node.right;
		}

		node.left = removeMin(node.left);
		return node;
	}

	private Node minNode(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	@Override
	public boolean contains(T data) {
		return contains(root, data);
	}

	private boolean contains(Node node, T data) {
		if (node == null) {
			return false;
		}

		int cmp = data.compareTo(node.data);
		if (cmp == 0) {
			return true;
		}
		if (cmp < 0) {
			return contains(node.left, data);
		}
		return contains(node.right, data);
	}

	@Override
	public int height() {
		return height(root);
	}

	private int height(Node node) {
		if (node == null) {
			return 0;
		}

		return 1 + Math.max(height(node.left), height(node.right));
	}

	@Override
	public void inorderTraversal() {
		inorder(root);
		System.out.println();
	}

	private void inorder(Node node) {
		if (node == null) {
			return;
		}

		inorder(node.left);
		System.out.print(node.data + " ");
		inorder(node.right);
	}

	@Override
	public void preorderTraversal() {
		preorder(root);
		System.out.println();
	}

	private void preorder(Node node) {
		if (node == null) {
			return;
		}

		System.out.print(node.data + " ");
		preorder(node.left);
		preorder(node.right);
	}

	@Override
	public void postorderTraversal() {
		postorder(root);
		System.out.println();
	}

	private void postorder(Node node) {
		if (node == null) {
			return;
		}

		postorder(node.left);
		postorder(node.right);
		System.out.print(node.data + " ");
	}

	@Override
	public void levelOrderTraversal() {
		if (root == null) {
			System.out.println();
			return;
		}

		LinkedQueue<Node> queue = new LinkedQueue<>();
		queue.enqueue(root);

		while (!queue.isEmpty()) {
			Node current = queue.dequeue();
			System.out.print(current.data + " ");

			if (current.left != null) {
				queue.enqueue(current.left);
			}
			if (current.right != null) {
				queue.enqueue(current.right);
			}
		}

		System.out.println();
	}
}
