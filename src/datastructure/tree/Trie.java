package datastructure.tree;

import foundation.ds.DSAbstract;
import datastructure.map.ChainedHashMap;

/**
 * Prefix tree for fast string search and autocomplete-style problems.
 */
public class Trie extends DSAbstract.Trie {

	private final TrieNode root = new TrieNode();

	private static class TrieNode {

		private final ChainedHashMap<Character, TrieNode> children = new ChainedHashMap<>();
		private boolean endOfWord;
	}

	@Override
	public void clear() {
		root.children.clear();
		root.endOfWord = false;
		size = 0;
	}

	@Override
	public void insert(String word) {
		if (word == null || word.isEmpty()) {
			return;
		}

		TrieNode current = root;

		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);

			if (!current.children.containsKey(ch)) {
				current.children.put(ch, new TrieNode());
			}

			current = current.children.get(ch);
		}

		if (!current.endOfWord) {
			current.endOfWord = true;
			incrementSize();
		}
	}

	@Override
	public boolean search(String word) {
		TrieNode node = findNode(word);
		return node != null && node.endOfWord;
	}

	@Override
	public boolean startsWith(String prefix) {
		return findNode(prefix) != null;
	}

	@Override
	public void delete(String word) {
		if (word == null || word.isEmpty()) {
			return;
		}

		delete(root, word, 0);
	}

	private boolean delete(TrieNode node, String word, int index) {
		if (index == word.length()) {
			if (!node.endOfWord) {
				return false;
			}

			node.endOfWord = false;
			decrementSize();
			return node.children.size() == 0;
		}

		char ch = word.charAt(index);
		TrieNode child = node.children.get(ch);

		if (child == null) {
			return false;
		}

		boolean shouldDeleteChild = delete(child, word, index + 1);

		if (shouldDeleteChild) {
			node.children.remove(ch);
		}

		return !node.endOfWord && node.children.size() == 0;
	}

	private TrieNode findNode(String text) {
		if (text == null) {
			return null;
		}

		TrieNode current = root;

		for (int i = 0; i < text.length(); i++) {
			current = current.children.get(text.charAt(i));
			if (current == null) {
				return null;
			}
		}

		return current;
	}
}
