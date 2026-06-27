package program;

import algorithm.backtracking.Backtracking;
import algorithm.bit.BitAlgorithms;
import foundation.algo.Algo;
import foundation.algo.AlgoInterface;
import algorithm.dp.DPBasics;
import algorithm.dp.GridDP;
import algorithm.dp.Knapsack;
import algorithm.dp.SubsequenceDP;
import algorithm.graphalgo.GraphAlgorithms;
import algorithm.greedy.GreedyAlgorithms;
import algorithm.math.MathAlgorithms;
import algorithm.search.SearchAlgorithms;
import algorithm.technique.DifferenceArray;
import algorithm.technique.FastSlowPointer;
import algorithm.technique.MonotonicStack;
import algorithm.technique.PrefixSum;
import algorithm.technique.SlidingWindow;
import algorithm.technique.TwoPointer;
import algorithm.treealgo.TreeAlgorithms;
import algorithm.treealgo.TreeNode;
import datastructure.list.DSNodes.SinglyNode;

import java.util.Scanner;

/**
 * Interactive playground to experience every DSA algorithm category.
 *
 * <p>Run from Eclipse → Run As → Java Application</p>
 * <ul>
 *   <li>No args → interactive menu</li>
 *   <li>Arg {@code all} → run every demo</li>
 *   <li>Arg {@code search}, {@code sort}, {@code technique}, … → run one section</li>
 * </ul>
 *
 * @see DSPlayground data structure demos
 */
public class AlgoPlayground {

	private static final Scanner INPUT = new Scanner(System.in);

	public static void main(String[] args) {
		printBanner();

		if (args.length > 0) {
			runSection(args[0].toLowerCase());
			return;
		}

		runInteractiveMenu();
	}

	private static void runInteractiveMenu() {
		while (true) {
			printMenu();
			System.out.print("Pick a category (0 = exit): ");

			if (!INPUT.hasNextInt()) {
				System.out.println("Please enter a number.\n");
				INPUT.next();
				continue;
			}

			int choice = INPUT.nextInt();
			INPUT.nextLine();

			switch (choice) {
			case 0:
				System.out.println("\nHappy coding. Master one algo at a time.");
				return;
			case 1:
				demoSearch();
				break;
			case 2:
				demoSort();
				break;
			case 3:
				demoTechniques();
				break;
			case 4:
				demoDynamicProgramming();
				break;
			case 5:
				demoGreedy();
				break;
			case 6:
				demoBacktracking();
				break;
			case 7:
				demoGraphAlgorithms();
				break;
			case 8:
				demoTreeAlgorithms();
				break;
			case 9:
				demoBitAndMath();
				break;
			case 10:
				runAll();
				break;
			default:
				System.out.println("Unknown option. Try 1–10 or 0 to exit.\n");
			}

			System.out.println("\n--- Press Enter to continue ---");
			INPUT.nextLine();
		}
	}

	private static void runSection(String name) {
		switch (name) {
		case "all":
			runAll();
			break;
		case "search":
			demoSearch();
			break;
		case "sort":
			demoSort();
			break;
		case "technique", "techniques":
			demoTechniques();
			break;
		case "dp", "dynamic":
			demoDynamicProgramming();
			break;
		case "greedy":
			demoGreedy();
			break;
		case "backtracking", "bt":
			demoBacktracking();
			break;
		case "graph":
			demoGraphAlgorithms();
			break;
		case "tree":
			demoTreeAlgorithms();
			break;
		case "bit", "math":
			demoBitAndMath();
			break;
		default:
			System.out.println("Unknown section: " + name);
			System.out.println("Use: all | search | sort | technique | dp | greedy | backtracking | graph | tree | bit");
		}
	}

	private static void runAll() {
		demoSearch();
		demoSort();
		demoTechniques();
		demoDynamicProgramming();
		demoGreedy();
		demoBacktracking();
		demoGraphAlgorithms();
		demoTreeAlgorithms();
		demoBitAndMath();
		System.out.println("\n=== All algorithm demos complete ===");
	}

	/* ======================== DEMOS ======================== */

	private static void demoSearch() {
		section("Search", "Linear → Binary → Bounds → Search on Answer");

		int[] sorted = { 1, 3, 5, 7, 9, 11 };

		System.out.println("Array: " + format(sorted));
		System.out.println();

		System.out.println("linearSearch(7)        → " + SearchAlgorithms.linearSearch(sorted, 7));
		System.out.println("binarySearch(7)        → index " + SearchAlgorithms.binarySearch(sorted, 7));
		System.out.println("binarySearchRecursive  → index " + SearchAlgorithms.binarySearchRecursive(sorted, 7));
		System.out.println("lowerBound(6)          → index " + SearchAlgorithms.lowerBound(sorted, 6) + "  (first >= 6)");
		System.out.println("upperBound(7)          → index " + SearchAlgorithms.upperBound(sorted, 7) + "  (first > 7)");

		int answer = SearchAlgorithms.binarySearchOnAnswer(1, 100, x -> x * x >= 30);
		System.out.println("binarySearchOnAnswer   → min x where x² >= 30 is " + answer);
	}

	private static void demoSort() {
		section("Sort", "All 7 custom sorters on the same input");

		int[] original = { 64, 34, 25, 12, 22, 11, 90 };
		System.out.println("Input: " + format(original));
		System.out.println();

		for (AlgoInterface.IntSort sorter : Algo.allSorts()) {
			int[] array = original.clone();
			sorter.sort(array);
			System.out.printf("  %-16s → %s%n", sorter.name(), format(array));
		}
	}

	private static void demoTechniques() {
		section("Techniques", "Two Pointer · Sliding Window · Prefix Sum · Monotonic Stack");

		// Two pointer
		int[] sorted = { 1, 2, 3, 4, 6 };
		int[] pair = TwoPointer.twoSumSorted(sorted, 10);
		System.out.println("TwoPointer.twoSumSorted → indices [" + pair[0] + ", " + pair[1] + "] for target 10");
		System.out.println("TwoPointer.isPalindrome(\"racecar\") → " + TwoPointer.isPalindrome("racecar"));
		System.out.println("TwoPointer.maxArea        → " + TwoPointer.maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 }));

		System.out.println();

		// Sliding window
		System.out.println("SlidingWindow.maxSumSubarrayOfSizeK → "
				+ SlidingWindow.maxSumSubarrayOfSizeK(new int[] { 2, 1, 5, 1, 3, 2 }, 3));
		System.out.println("SlidingWindow.longestSubstring       → "
				+ SlidingWindow.longestSubstringWithoutRepeating("abcabcbb") + "  (\"abcabcbb\")");
		System.out.println("SlidingWindow.minSubarrayLen          → "
				+ SlidingWindow.minSubarrayLen(7, new int[] { 2, 3, 1, 2, 4, 3 }));

		System.out.println();

		// Prefix & difference
		PrefixSum prefix = new PrefixSum(new int[] { 1, 2, 3, 4, 5 });
		System.out.println("PrefixSum.rangeSum(1, 3)            → " + prefix.rangeSum(1, 3) + "  (2+3+4)");

		DifferenceArray diff = new DifferenceArray(5);
		diff.rangeUpdate(1, 3, 10);
		System.out.println("DifferenceArray +10 on [1..3]         → " + format(diff.reconstruct()));

		System.out.println();

		// Monotonic stack
		int[] nge = MonotonicStack.nextGreaterElement(new int[] { 2, 1, 2, 4, 3 });
		System.out.println("MonotonicStack.nextGreaterElement   → " + format(nge));

		// Fast / slow pointer
		SinglyNode<Integer> head = buildList(1, 2, 3, 4, 5);
		System.out.println("FastSlowPointer.middleNode            → " + FastSlowPointer.middleNode(head).getData());
		System.out.println("FastSlowPointer.findDuplicate         → "
				+ FastSlowPointer.findDuplicate(new int[] { 1, 3, 4, 2, 2 }));
	}

	private static void demoDynamicProgramming() {
		section("Dynamic Programming", "1D · Knapsack · Subsequence · Grid");

		System.out.println("DPBasics.fibonacci(10)     → " + DPBasics.fibonacci(10));
		System.out.println("DPBasics.climbingStairs(5) → " + DPBasics.climbingStairs(5));
		System.out.println("DPBasics.maxSubarraySum    → "
				+ DPBasics.maxSubarraySum(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
		System.out.println("DPBasics.houseRobber       → "
				+ DPBasics.houseRobber(new int[] { 2, 7, 9, 3, 1 }));

		System.out.println();

		System.out.println("Knapsack.knapsack01        → "
				+ Knapsack.knapsack01(new int[] { 1, 2, 3 }, new int[] { 6, 10, 12 }, 5));
		System.out.println("Knapsack.coinChange        → "
				+ Knapsack.coinChange(new int[] { 1, 2, 5 }, 11) + " coins for amount 11");

		System.out.println();

		System.out.println("SubsequenceDP.LCS          → "
				+ SubsequenceDP.longestCommonSubsequence("abcde", "ace"));
		System.out.println("SubsequenceDP.LIS          → "
				+ SubsequenceDP.longestIncreasingSubsequence(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 }));
		System.out.println("SubsequenceDP.editDistance → "
				+ SubsequenceDP.editDistance("horse", "ros"));

		System.out.println();

		System.out.println("GridDP.uniquePaths(3,3)    → " + GridDP.uniquePaths(3, 3));
		System.out.println("GridDP.minPathSum            → "
				+ GridDP.minPathSum(new int[][] { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } }));
	}

	private static void demoGreedy() {
		section("Greedy", "Intervals · Scheduling · Jumps");

		System.out.println("Greedy.maxActivities  → "
				+ GreedyAlgorithms.maxActivities(new int[] { 1, 2, 3 }, new int[] { 3, 4, 5 }) + " activities");
		System.out.println("Greedy.canJump        → " + GreedyAlgorithms.canJump(new int[] { 2, 3, 1, 1, 4 }));

		int[][] merged = GreedyAlgorithms.mergeIntervals(new int[][] {
				{ 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 }
		});
		System.out.print("Greedy.mergeIntervals → ");
		printMatrix(merged);
	}

	private static void demoBacktracking() {
		section("Backtracking", "Subsets · Permutations · Combinations · N-Queens");

		System.out.println("Backtracking.subsets           → " + Backtracking.subsets(new int[] { 1, 2, 3 }).length + " subsets");
		System.out.println("Backtracking.permutations      → "
				+ Backtracking.permutations(new int[] { 1, 2, 3 }).length + " permutations");
		System.out.println("Backtracking.combinationSum    → "
				+ Backtracking.combinationSumCount(new int[] { 2, 3, 6, 7 }, 7) + " combos for target 7");
		System.out.println("Backtracking.nQueensCount(4)   → " + Backtracking.nQueensCount(4) + " solutions");
	}

	private static void demoGraphAlgorithms() {
		section("Graph Algorithms", "Dijkstra · Topological Sort · Cycle Detection");

		int INF = GraphAlgorithms.INF;
		int[][] weighted = {
				{ 0, 4, INF, INF },
				{ 4, 0, 2, INF },
				{ INF, 2, 0, 3 },
				{ INF, INF, 3, 0 }
		};

		System.out.print("GraphAlgorithms.dijkstra(0)    → ");
		printArray(GraphAlgorithms.dijkstra(weighted, 0));

		int[][] dag = {
				{ 0, 1, 1, 0 },
				{ 0, 0, 1, 1 },
				{ 0, 0, 0, 1 },
				{ 0, 0, 0, 0 }
		};

		System.out.print("GraphAlgorithms.topologicalSort  → ");
		printArray(GraphAlgorithms.topologicalSort(4, dag));
		System.out.println("GraphAlgorithms.hasCycleDirected → " + GraphAlgorithms.hasCycleDirected(4, dag));

		int[][] undirected = {
				{ 0, 1, 0 },
				{ 1, 0, 1 },
				{ 0, 1, 0 }
		};
		System.out.println("GraphAlgorithms.hasCycleUndirected → "
				+ GraphAlgorithms.hasCycleUndirected(3, undirected));
	}

	private static void demoTreeAlgorithms() {
		section("Tree Algorithms", "Depth · Balance · Diameter");

		TreeNode root = Algo.treeNode(1);
		root.left = Algo.treeNode(2);
		root.right = Algo.treeNode(3);
		root.left.left = Algo.treeNode(4);
		root.left.right = Algo.treeNode(5);

		System.out.println("       1");
		System.out.println("      / \\");
		System.out.println("     2   3");
		System.out.println("    / \\");
		System.out.println("   4   5");
		System.out.println();

		System.out.println("TreeAlgorithms.maxDepth    → " + TreeAlgorithms.maxDepth(root));
		System.out.println("TreeAlgorithms.isBalanced  → " + TreeAlgorithms.isBalanced(root));
		System.out.println("TreeAlgorithms.diameter    → " + TreeAlgorithms.diameter(root));
	}

	private static void demoBitAndMath() {
		section("Bit & Math", "Masks · XOR · GCD · Sieve · Fast Power");

		System.out.println("BitAlgorithms.countSetBits(13)   → " + BitAlgorithms.countSetBits(13));
		System.out.println("BitAlgorithms.isPowerOfTwo(16)   → " + BitAlgorithms.isPowerOfTwo(16));
		System.out.println("BitAlgorithms.singleNumber       → "
				+ BitAlgorithms.singleNumber(new int[] { 4, 1, 2, 1, 2 }));

		System.out.println();

		System.out.println("MathAlgorithms.gcd(48, 18)       → " + MathAlgorithms.gcd(48, 18));
		System.out.println("MathAlgorithms.lcm(4, 6)           → " + MathAlgorithms.lcm(4, 6));
		System.out.println("MathAlgorithms.isPrime(17)         → " + MathAlgorithms.isPrime(17));
		System.out.println("MathAlgorithms.fastPower(2,10,1000)→ " + MathAlgorithms.fastPower(2, 10, 1000));

		int primes = 0;
		boolean[] sieve = MathAlgorithms.sieveOfEratosthenes(30);
		for (int i = 2; i < sieve.length; i++) {
			if (sieve[i]) {
				primes++;
			}
		}
		System.out.println("MathAlgorithms.sieve(30)         → " + primes + " primes up to 30");
	}

	/* ======================== UI HELPERS ======================== */

	private static void printBanner() {
		System.out.println();
		System.out.println("  ╔══════════════════════════════════════╗");
		System.out.println("  ║     AlgoCodeHub · Algorithm Lab      ║");
		System.out.println("  ╚══════════════════════════════════════╝");
		System.out.println("  Explore search, sort, DP, graphs & more.");
		System.out.println();
	}

	private static void printMenu() {
		System.out.println("┌──────────────────────────────────────┐");
		System.out.println("│  1  Search                           │");
		System.out.println("│  2  Sort                             │");
		System.out.println("│  3  Techniques (2 ptr, window, …)   │");
		System.out.println("│  4  Dynamic Programming              │");
		System.out.println("│  5  Greedy                           │");
		System.out.println("│  6  Backtracking                     │");
		System.out.println("│  7  Graph Algorithms                 │");
		System.out.println("│  8  Tree Algorithms                  │");
		System.out.println("│  9  Bit & Math                       │");
		System.out.println("│ 10  Run ALL demos                    │");
		System.out.println("│  0  Exit                             │");
		System.out.println("└──────────────────────────────────────┘");
	}

	private static void section(String title, String subtitle) {
		System.out.println();
		System.out.println("══ " + title + " ══════════════════════════");
		System.out.println("   " + subtitle);
		System.out.println();
	}

	private static SinglyNode<Integer> buildList(int... values) {
		if (values.length == 0) {
			return null;
		}

		SinglyNode<Integer> head = new SinglyNode<>(values[0]);
		SinglyNode<Integer> current = head;

		for (int i = 1; i < values.length; i++) {
			current.setNext(new SinglyNode<>(values[i]));
			current = current.getNext();
		}

		return head;
	}

	private static String format(int[] array) {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if (i < array.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	private static void printArray(int[] array) {
		System.out.println(format(array));
	}

	private static void printMatrix(int[][] matrix) {
		for (int[] row : matrix) {
			System.out.print("[" + row[0] + "," + row[1] + "] ");
		}
		System.out.println();
	}
}
