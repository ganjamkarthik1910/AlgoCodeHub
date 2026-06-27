package foundation.algo;

import algorithm.backtracking.Backtracking;
import algorithm.bit.BitAlgorithms;
import algorithm.dp.DPBasics;
import algorithm.dp.GridDP;
import algorithm.dp.Knapsack;
import algorithm.dp.SubsequenceDP;
import algorithm.graphalgo.GraphAlgorithms;
import algorithm.greedy.GreedyAlgorithms;
import algorithm.math.MathAlgorithms;
import algorithm.search.SearchAlgorithms;
import algorithm.sort.BubbleSort;
import algorithm.sort.CountingSort;
import algorithm.sort.HeapSort;
import algorithm.sort.InsertionSort;
import algorithm.sort.MergeSort;
import algorithm.sort.QuickSort;
import algorithm.sort.SelectionSort;
import algorithm.technique.DifferenceArray;
import algorithm.technique.FastSlowPointer;
import algorithm.technique.MonotonicStack;
import algorithm.technique.PrefixSum;
import algorithm.technique.SlidingWindow;
import algorithm.technique.TwoPointer;
import algorithm.treealgo.TreeAlgorithms;
import algorithm.treealgo.TreeNode;

/**
 * Master factory and entry point for all DSA algorithms.
 */
public final class Algo {

	private Algo() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	/* ======================== SORT ======================== */

	public static BubbleSort bubbleSort() {
		return new BubbleSort();
	}

	public static SelectionSort selectionSort() {
		return new SelectionSort();
	}

	public static InsertionSort insertionSort() {
		return new InsertionSort();
	}

	public static MergeSort mergeSort() {
		return new MergeSort();
	}

	public static QuickSort quickSort() {
		return new QuickSort();
	}

	public static HeapSort heapSort() {
		return new HeapSort();
	}

	public static CountingSort countingSort() {
		return new CountingSort();
	}

	public static AlgoInterface.IntSort[] allSorts() {
		return new AlgoInterface.IntSort[] {
				bubbleSort(), selectionSort(), insertionSort(),
				mergeSort(), quickSort(), heapSort(), countingSort()
		};
	}

	/* ======================== CATEGORY CLASSES (static utilities) ======================== */

	public static Class<?> search() {
		return SearchAlgorithms.class;
	}

	public static Class<?> twoPointer() {
		return TwoPointer.class;
	}

	public static Class<?> slidingWindow() {
		return SlidingWindow.class;
	}

	public static Class<?> prefixSum() {
		return PrefixSum.class;
	}

	public static Class<?> differenceArray() {
		return DifferenceArray.class;
	}

	public static Class<?> fastSlowPointer() {
		return FastSlowPointer.class;
	}

	public static Class<?> monotonicStack() {
		return MonotonicStack.class;
	}

	public static Class<?> dp() {
		return DPBasics.class;
	}

	public static Class<?> knapsack() {
		return Knapsack.class;
	}

	public static Class<?> subsequenceDp() {
		return SubsequenceDP.class;
	}

	public static Class<?> gridDp() {
		return GridDP.class;
	}

	public static Class<?> greedy() {
		return GreedyAlgorithms.class;
	}

	public static Class<?> backtracking() {
		return Backtracking.class;
	}

	public static Class<?> graph() {
		return GraphAlgorithms.class;
	}

	public static Class<?> tree() {
		return TreeAlgorithms.class;
	}

	public static Class<?> bit() {
		return BitAlgorithms.class;
	}

	public static Class<?> math() {
		return MathAlgorithms.class;
	}

	public static TreeNode treeNode(int val) {
		return new TreeNode(val);
	}
}
