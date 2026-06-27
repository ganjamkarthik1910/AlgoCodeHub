package datastructure.tree;

import foundation.ds.DSAbstract;
import datastructure.queue.LinkedQueue;

/**
 * Self-balancing AVL binary search tree.
 */
public class AVLTree<T extends Comparable<T>> extends DSAbstract.Tree<T> {

	private Node root;

	private class Node {

		T data;
		Node left;
		Node right;
		int height;

		Node(T data) {
			this.data = data;
			height = 1;
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
		} else {
			return node;
		}

		updateHeight(node);
		return balance(node);
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

			if (node.left == null || node.right == null) {
				node = node.left != null ? node.left : node.right;
			} else {
				Node min = minNode(node.right);
				node.data = min.data;
				node.right = removeMin(node.right);
			}
		}

		if (node == null) {
			return null;
		}

		updateHeight(node);
		return balance(node);
	}

	private Node removeMin(Node node) {
		if (node.left == null) {
			return node.right;
		}

		node.left = removeMin(node.left);
		updateHeight(node);
		return balance(node);
	}

	private Node minNode(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	@Override
	public boolean contains(T data) {
		Node current = root;

		while (current != null) {
			int cmp = data.compareTo(current.data);
			if (cmp == 0) {
				return true;
			}
			current = cmp < 0 ? current.left : current.right;
		}

		return false;
	}

	@Override
	public int height() {
		return nodeHeight(root);
	}

	@Override
	public void inorderTraversal() {
		inorder(root);
		System.out.println();
	}

	@Override
	public void preorderTraversal() {
		preorder(root);
		System.out.println();
	}

	@Override
	public void postorderTraversal() {
		postorder(root);
		System.out.println();
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

	private void inorder(Node node) {
		if (node == null) {
			return;
		}

		inorder(node.left);
		System.out.print(node.data + " ");
		inorder(node.right);
	}

	private void preorder(Node node) {
		if (node == null) {
			return;
		}

		System.out.print(node.data + " ");
		preorder(node.left);
		preorder(node.right);
	}

	private void postorder(Node node) {
		if (node == null) {
			return;
		}

		postorder(node.left);
		postorder(node.right);
		System.out.print(node.data + " ");
	}

	private int nodeHeight(Node node) {
		return node == null ? 0 : node.height;
	}

	private void updateHeight(Node node) {
		node.height = 1 + Math.max(nodeHeight(node.left), nodeHeight(node.right));
	}

	private int balanceFactor(Node node) {
		return nodeHeight(node.left) - nodeHeight(node.right);
	}

	private Node balance(Node node) {
		int factor = balanceFactor(node);

		if (factor > 1) {
			if (balanceFactor(node.left) < 0) {
				node.left = rotateLeft(node.left);
			}
			return rotateRight(node);
		}

		if (factor < -1) {
			if (balanceFactor(node.right) > 0) {
				node.right = rotateRight(node.right);
			}
			return rotateLeft(node);
		}

		return node;
	}

	private Node rotateRight(Node y) {
		Node x = y.left;
		Node t2 = x.right;

		x.right = y;
		y.left = t2;

		updateHeight(y);
		updateHeight(x);
		return x;
	}

	private Node rotateLeft(Node x) {
		Node y = x.right;
		Node t2 = y.left;

		y.left = x;
		x.right = t2;

		updateHeight(x);
		updateHeight(y);
		return y;
	}
}
