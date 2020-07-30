package pack1;

//Java implementation of search and insert operations on trie.
public class TrieTree {

	// Alphabet size (# of symbols)
	static final int ALPHABET_SIZE = 26;

	// Trie node
	static class TrieNode {
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];

		// isEndOfWord is true if the node represents the end of a word.
		boolean isEndOfWord;
		String number = "";

		/**
		 * <i>Constructor
		 */
		TrieNode() {

			isEndOfWord = false;
			for (int i = 0; i < ALPHABET_SIZE; i++)
				children[i] = null;
		}
	};

	private static TrieNode root;

	public TrieTree() {
		root = new TrieNode();
	}

	/**
	 * <i>This method will determine if the given node is leaf or not;
	 * 
	 * @param root
	 * @return boolean
	 */
	static boolean isLeaf(TrieNode root) {

		for (TrieNode tmp : root.children)
			if (tmp != null)
				return false;

		return true;
	}

	/**
	 * The goal of this method, is to return all the words that matches your prefix.
	 * <p>
	 * First it checks if the given prefix is in the tree or not; if it is, it will
	 * call <b>"wordSuggestion"</b> method to find all the words matching to the
	 * prefix. Otherwise, it will notify you that there is no word begining with the
	 * given prefix.
	 * 
	 * @param prefix
	 */
	void autoSuggestion(String prefix) {

		TrieNode tmp = root;
		int index;
		for (int i = 0; i < prefix.length(); i++) {

			index = prefix.charAt(i) - 'a';
			if (tmp.children[index] == null) {
				System.out.println("didn't find the word in trie tree!");
				return;
			}
			tmp = tmp.children[index];
		}

		if (tmp.isEndOfWord && isLeaf(tmp)) {
			System.out.println(prefix);
			return;
		}

		if (!isLeaf(tmp)) {
			String Prefix = prefix;
			wordSuggestion(tmp, Prefix);
		}
	}

	static void wordSuggestion(TrieNode root, String prefix) {

		if (root.isEndOfWord)
			System.out.println(prefix);

		if (isLeaf(root))
			return;

		for (int i = 0; i < ALPHABET_SIZE; i++)
			if (root.children[i] != null) {

				String word = prefix;
				word += String.valueOf((char) (97 + i));
				wordSuggestion(root.children[i], word);
			}
	}

	/**
	 * <i>This method adds characters into the tree; If not present, inserts name
	 * into trie. If the name is prefix of trie node, just marks leaf node.
	 * 
	 * @param name
	 */
	void add(String name) {

		int index;
		TrieNode Current = root;

		for (int i = 0; i < name.length(); i++) {

			index = name.charAt(i) - 'a';

			if (Current.children[index] == null)
				Current.children[index] = new TrieNode();

			Current = Current.children[index];
		}

		// mark last node as leaf
		Current.isEndOfWord = true;
	}

	/**
	 * <i>Returns <b>"true"</b> if name presents in trie, else <b>"false"</b>.
	 * 
	 * @param name
	 * @return boolean
	 */
	boolean search(String name) {

		int index;
		TrieNode Current = root;

		for (int i = 0; i < name.length(); i++) {

			index = name.charAt(i) - 'a';

			if (Current.children[index] == null)
				return false;

			Current = Current.children[index];
		}
		return (Current != null && Current.isEndOfWord);
	}

	/**
	 * <i>This method removes the given word.
	 * 
	 * @param name
	 */
	void remove(String name) {

		int index;
		TrieNode Current = root;

		for (int i = 0; i < name.length(); i++) {

			index = name.charAt(i) - 'a';

			if (Current.children[index] == null)
				return;
			else
				Current = Current.children[index];
		}
		Current.isEndOfWord = false;
	}

	public static void main(String[] args) {
		TrieTree tree = new TrieTree();
		tree.add("ali");
		tree.add("abbas");
		tree.add("amin");
		tree.add("aria");
		tree.add("arian");
		tree.add("armin");
		tree.add("arianba");
		tree.add("ben");
		tree.add("zebra");
		System.out.println(tree.search("ali"));
		System.out.println(tree.search("abbas"));
		System.out.println(tree.search("amin"));
		System.out.println(tree.search("armin"));
		System.out.println(tree.search("aria"));
		System.out.println(tree.search("arian"));
		System.out.println(tree.search("arianba"));
		System.out.println(tree.search("ben"));
		System.out.println(tree.search("zebra"));
		tree.autoSuggestion("ar");
	}
}
