package algorithm.graphalgo;

import datastructure.queue.LinkedQueue;

/**
 * Graph algorithms — shortest path, topological sort, cycle detection.
 */
public final class GraphAlgorithms {

	public static final int INF = Integer.MAX_VALUE / 2;

	private GraphAlgorithms() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	/**
	 * Dijkstra's shortest path from source (O(V²) — good for learning).
	 * adj[u][v] = weight, use INF for no edge.
	 */
	public static int[] dijkstra(int[][] adj, int source) {
		int n = adj.length;
		int[] dist = new int[n];
		boolean[] visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			dist[i] = INF;
		}
		dist[source] = 0;

		for (int count = 0; count < n; count++) {
			int u = -1;
			int best = INF;

			for (int i = 0; i < n; i++) {
				if (!visited[i] && dist[i] < best) {
					best = dist[i];
					u = i;
				}
			}

			if (u == -1) {
				break;
			}

			visited[u] = true;

			for (int v = 0; v < n; v++) {
				if (adj[u][v] != INF && dist[u] + adj[u][v] < dist[v]) {
					dist[v] = dist[u] + adj[u][v];
				}
			}
		}

		return dist;
	}

	/** Kahn's algorithm — returns topological order or empty if cycle exists. */
	public static int[] topologicalSort(int n, int[][] adj) {
		int[] indegree = new int[n];

		for (int u = 0; u < n; u++) {
			for (int v = 0; v < n; v++) {
				if (adj[u][v] == 1) {
					indegree[v]++;
				}
			}
		}

		LinkedQueue<Integer> queue = new LinkedQueue<>();
		for (int i = 0; i < n; i++) {
			if (indegree[i] == 0) {
				queue.enqueue(i);
			}
		}

		int[] order = new int[n];
		int index = 0;

		while (!queue.isEmpty()) {
			int u = queue.dequeue();
			order[index++] = u;

			for (int v = 0; v < n; v++) {
				if (adj[u][v] == 1) {
					if (--indegree[v] == 0) {
						queue.enqueue(v);
					}
				}
			}
		}

		return index == n ? order : new int[0];
	}

	public static boolean hasCycleDirected(int n, int[][] adj) {
		int[] state = new int[n];

		for (int i = 0; i < n; i++) {
			if (state[i] == 0 && dfsCycle(i, adj, state)) {
				return true;
			}
		}

		return false;
	}

	private static boolean dfsCycle(int u, int[][] adj, int[] state) {
		state[u] = 1;

		for (int v = 0; v < adj.length; v++) {
			if (adj[u][v] == 0) {
				continue;
			}

			if (state[v] == 1 || (state[v] == 0 && dfsCycle(v, adj, state))) {
				return true;
			}
		}

		state[u] = 2;
		return false;
	}

	public static boolean hasCycleUndirected(int n, int[][] adj) {
		boolean[] visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			if (!visited[i] && dfsUndirected(i, -1, adj, visited)) {
				return true;
			}
		}

		return false;
	}

	private static boolean dfsUndirected(int u, int parent, int[][] adj, boolean[] visited) {
		visited[u] = true;

		for (int v = 0; v < adj.length; v++) {
			if (adj[u][v] == 0) {
				continue;
			}

			if (!visited[v]) {
				if (dfsUndirected(v, u, adj, visited)) {
					return true;
				}
			} else if (v != parent) {
				return true;
			}
		}

		return false;
	}
}
