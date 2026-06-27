package algorithm.treealgo;

/**
 * Tree algorithms — depth, balance, diameter.
 */
public final class TreeAlgorithms {

	private TreeAlgorithms() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	public static int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
	}

	public static boolean isBalanced(TreeNode root) {
		return heightCheck(root) != -1;
	}

	private static int heightCheck(TreeNode node) {
		if (node == null) {
			return 0;
		}

		int left = heightCheck(node.left);
		if (left == -1) {
			return -1;
		}

		int right = heightCheck(node.right);
		if (right == -1) {
			return -1;
		}

		if (Math.abs(left - right) > 1) {
			return -1;
		}

		return 1 + Math.max(left, right);
	}

	public static int diameter(TreeNode root) {
		int[] best = new int[1];
		diameterHeight(root, best);
		return best[0];
	}

	private static int diameterHeight(TreeNode node, int[] best) {
		if (node == null) {
			return 0;
		}

		int left = diameterHeight(node.left, best);
		int right = diameterHeight(node.right, best);
		best[0] = Math.max(best[0], left + right);
		return 1 + Math.max(left, right);
	}

	public static boolean isSameTree(TreeNode a, TreeNode b) {
		if (a == null && b == null) {
			return true;
		}
		if (a == null || b == null) {
			return false;
		}

		return a.val == b.val && isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
	}
}
