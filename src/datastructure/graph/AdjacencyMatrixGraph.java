package datastructure.graph;

import foundation.ds.DSAbstract;
import datastructure.list.SinglyLinkedList;
import datastructure.map.ChainedHashMap;
import datastructure.queue.LinkedQueue;
import datastructure.set.HashSet;

/**
 * Directed graph stored as an adjacency matrix.
 */
public class AdjacencyMatrixGraph<T> extends DSAbstract.Graph<T> {

	private final SinglyLinkedList<T> vertices = new SinglyLinkedList<>();
	private final ChainedHashMap<T, Integer> indexOf = new ChainedHashMap<>();
	private int[][] matrix = new int[0][0];

	@Override
	public void clear() {
		vertices.clear();
		indexOf.clear();
		matrix = new int[0][0];
		size = 0;
	}

	@Override
	public void addVertex(T vertex) {
		if (containsVertex(vertex)) {
			return;
		}

		int index = vertices.size();
		vertices.add(vertex);
		indexOf.put(vertex, index);
		expandMatrix(index + 1);
		incrementSize();
	}

	@Override
	public void addEdge(T source, T destination) {
		addVertex(source);
		addVertex(destination);

		int from = indexOf.get(source);
		int to = indexOf.get(destination);
		matrix[from][to] = 1;
	}

	@Override
	public void removeVertex(T vertex) {
		if (!containsVertex(vertex)) {
			return;
		}

		int removeIndex = indexOf.get(vertex);
		int lastIndex = vertices.size() - 1;

		if (removeIndex != lastIndex) {
			T lastVertex = vertices.get(lastIndex);
			vertices.deleteAtPosition(removeIndex);
			indexOf.put(lastVertex, removeIndex);
			indexOf.remove(vertex);

			for (int i = 0; i < matrix.length; i++) {
				matrix[removeIndex][i] = matrix[lastIndex][i];
				matrix[i][removeIndex] = matrix[i][lastIndex];
			}
		} else {
			vertices.deleteAtPosition(removeIndex);
			indexOf.remove(vertex);
		}

		shrinkMatrix(vertices.size());
		decrementSize();
	}

	@Override
	public void removeEdge(T source, T destination) {
		if (!containsVertex(source) || !containsVertex(destination)) {
			return;
		}

		matrix[indexOf.get(source)][indexOf.get(destination)] = 0;
	}

	@Override
	public boolean containsVertex(T vertex) {
		return indexOf.containsKey(vertex);
	}

	@Override
	public boolean containsEdge(T source, T destination) {
		if (!containsVertex(source) || !containsVertex(destination)) {
			return false;
		}

		return matrix[indexOf.get(source)][indexOf.get(destination)] == 1;
	}

	@Override
	public void bfs(T startVertex) {
		if (!containsVertex(startVertex)) {
			System.out.println();
			return;
		}

		HashSet<T> visited = new HashSet<>();
		LinkedQueue<T> queue = new LinkedQueue<>();

		queue.enqueue(startVertex);
		visited.add(startVertex);

		while (!queue.isEmpty()) {
			T current = queue.dequeue();
			System.out.print(current + " ");

			for (int i = 0; i < vertices.size(); i++) {
				T neighbor = vertices.get(i);
				if (hasEdge(current, neighbor) && !visited.contains(neighbor)) {
					visited.add(neighbor);
					queue.enqueue(neighbor);
				}
			}
		}

		System.out.println();
	}

	@Override
	public void dfs(T startVertex) {
		if (!containsVertex(startVertex)) {
			System.out.println();
			return;
		}

		HashSet<T> visited = new HashSet<>();
		dfsRecursive(startVertex, visited);
		System.out.println();
	}

	public void printGraph() {
		System.out.print("    ");
		for (int i = 0; i < vertices.size(); i++) {
			System.out.print(vertices.get(i) + " ");
		}
		System.out.println();

		for (int i = 0; i < vertices.size(); i++) {
			System.out.print(vertices.get(i) + " : ");
			for (int j = 0; j < vertices.size(); j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	private void dfsRecursive(T vertex, HashSet<T> visited) {
		visited.add(vertex);
		System.out.print(vertex + " ");

		for (int i = 0; i < vertices.size(); i++) {
			T neighbor = vertices.get(i);
			if (hasEdge(vertex, neighbor) && !visited.contains(neighbor)) {
				dfsRecursive(neighbor, visited);
			}
		}
	}

	private boolean hasEdge(T source, T destination) {
		return matrix[indexOf.get(source)][indexOf.get(destination)] == 1;
	}

	private void expandMatrix(int newSize) {
		int[][] resized = new int[newSize][newSize];

		for (int i = 0; i < matrix.length; i++) {
			System.arraycopy(matrix[i], 0, resized[i], 0, matrix.length);
		}

		matrix = resized;
	}

	private void shrinkMatrix(int newSize) {
		if (newSize == 0) {
			matrix = new int[0][0];
			return;
		}

		int[][] resized = new int[newSize][newSize];
		for (int i = 0; i < newSize; i++) {
			System.arraycopy(matrix[i], 0, resized[i], 0, newSize);
		}

		matrix = resized;
	}
}
