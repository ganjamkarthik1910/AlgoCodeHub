package datastructure.graph;

import foundation.ds.DSAbstract;
import datastructure.list.SinglyLinkedList;
import datastructure.map.ChainedHashMap;
import datastructure.queue.LinkedQueue;
import datastructure.set.HashSet;

/**
 * Directed graph using an adjacency list (hash map + singly linked lists).
 */
public class AdjacencyListGraph<T> extends DSAbstract.Graph<T> {

	private final SinglyLinkedList<T> vertices = new SinglyLinkedList<>();
	private final ChainedHashMap<T, SinglyLinkedList<T>> adjacency = new ChainedHashMap<>();

	@Override
	public void clear() {
		vertices.clear();
		adjacency.clear();
		size = 0;
	}

	@Override
	public void addVertex(T vertex) {
		if (containsVertex(vertex)) {
			return;
		}

		vertices.add(vertex);
		adjacency.put(vertex, new SinglyLinkedList<>());
		incrementSize();
	}

	@Override
	public void addEdge(T source, T destination) {
		addVertex(source);
		addVertex(destination);

		SinglyLinkedList<T> neighbors = adjacency.get(source);
		if (!neighbors.contains(destination)) {
			neighbors.add(destination);
		}
	}

	@Override
	public void removeVertex(T vertex) {
		if (!containsVertex(vertex)) {
			return;
		}

		vertices.delete(vertex);
		adjacency.remove(vertex);
		decrementSize();

		for (int i = 0; i < vertices.size(); i++) {
			adjacency.get(vertices.get(i)).delete(vertex);
		}
	}

	@Override
	public void removeEdge(T source, T destination) {
		if (!adjacency.containsKey(source)) {
			return;
		}

		adjacency.get(source).delete(destination);
	}

	@Override
	public boolean containsVertex(T vertex) {
		return adjacency.containsKey(vertex);
	}

	@Override
	public boolean containsEdge(T source, T destination) {
		if (!adjacency.containsKey(source)) {
			return false;
		}

		return adjacency.get(source).contains(destination);
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

			SinglyLinkedList<T> neighbors = adjacency.get(current);
			for (int i = 0; i < neighbors.size(); i++) {
				T neighbor = neighbors.get(i);
				if (!visited.contains(neighbor)) {
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
		for (int i = 0; i < vertices.size(); i++) {
			T vertex = vertices.get(i);
			System.out.print(vertex + " -> ");
			adjacency.get(vertex).printList();
		}
	}

	private void dfsRecursive(T vertex, HashSet<T> visited) {
		visited.add(vertex);
		System.out.print(vertex + " ");

		SinglyLinkedList<T> neighbors = adjacency.get(vertex);
		for (int i = 0; i < neighbors.size(); i++) {
			T neighbor = neighbors.get(i);
			if (!visited.contains(neighbor)) {
				dfsRecursive(neighbor, visited);
			}
		}
	}
}
