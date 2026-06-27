package foundation.algo;

/**
 * Single source of truth for all DSA algorithm contracts and categories.
 *
 * <pre>
 * Categories for mastering DSA:
 *   1. Search          — linear, binary, bounds
 *   2. Sort            — comparison & non-comparison sorts
 *   3. Technique       — two pointer, sliding window, prefix sum, fast/slow
 *   4. Dynamic Prog.   — 1D, 2D, knapsack, subsequences
 *   5. Greedy          — intervals, jumps, scheduling
 *   6. Backtracking    — subsets, permutations, combinations
 *   7. Graph Algo      — shortest path, topo sort, cycle detection
 *   8. Tree Algo       — depth, balance, diameter
 *   9. Bit             — masks, powers, XOR tricks
 *  10. Math            — gcd, sieve, fast power
 * </pre>
 */
public final class AlgoInterface {

	private AlgoInterface() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	/* ========================================================= SORT */

	public interface IntSort {

		void sort(int[] array);

		String name();
	}

	/* ========================================================= COMMON ARRAY OPS (contracts for technique modules) */

	public interface RangeQuery {

		int query(int left, int right);
	}

	public interface RangeUpdater {

		void update(int index, int value);

		void rangeUpdate(int left, int right, int delta);
	}

	@FunctionalInterface
	public interface IntCondition {

		boolean test(int value);
	}
}
