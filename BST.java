package pack1;

public class BST {

	private Node root;
	private int size;

	/**
	 * <i>BST's constructor
	 */
	public BST() {
		this.size = 0;
		this.root = null;
	}

	/**
	 * <i>Checks if the tree is empty.
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return (this.size == 0);
	}

	/**
	 * <i>Clears all the nodes connected to the root and resets the root to null.
	 */
	public void clear() {
		this.root.left = null;
		this.root.right = null;
		this.root = null;
		this.size = 0;
	}

	/**
	 * <i>This function finds the depth of a binary search tree (it's encapsulated).
	 * <p>
	 * Complexity : O(nlog(n)).
	 */
	public int getDepth() {
		int tmp = 0;
		int height = 0;
		height = getDepthRec(this.root, height, tmp);
		return height;
	}

	/**
	 * This method finds the depth of a tree.
	 * <p>
	 * to explain it briefly, it goes to every leaf in the tree, holds the height of
	 * the leaf(in "tmp" variable) and chooses the maxium height that its been
	 * in(puts it in "height" variable) and returns it.
	 * <p>
	 * Complexity: O(nlog(n)).
	 * 
	 * @param root
	 * @param height
	 * @param tmp
	 * @return int (height of the tree)
	 */
	private int getDepthRec(Node root, int height, int tmp) {

		if (root == null)
			return 0;

		if (root.left == null && root.right == null) {
			if (tmp > height) {
				height = tmp;
			}
			tmp = 0;
			return height;
		}

		else if (root.left == null && root.right != null) {
			tmp++;
			height = getDepthRec(root.right, height, tmp);
		}

		else if (root.left != null && root.right == null) {
			tmp++;
			height = getDepthRec(root.left, height, tmp);
		}

		else {
			tmp++;
			height = getDepthRec(root.left, height, tmp);
			height = getDepthRec(root.right, height, tmp);
		}
		return height;
	}

	/**
	 * This method adds the given element (it's encapsulated).
	 * <p>
	 * Complexity: O(log(n)).
	 * 
	 * @param dat
	 */
	public void add(int dat) {
		size++;
		root = addRec(root, dat);
	}

	public Node addRec(Node root, int dat) {

		if (root == null) {
			root = new Node(dat);
			return root;
		}

		if (root.data > dat) {
			root.left = addRec(root.left, dat);
		} else {
			root.right = addRec(root.right, dat);
		}
		return root;
	}

	/**
	 * This method deletes the given element (it's encapsulated).
	 * <p>
	 * Complexity: O(log(n)).
	 * 
	 * @param dat
	 */
	public void delete(int dat) {
		this.size--;
		this.root = deleteRec(this.root, dat);
	}

	private Node deleteRec(Node root, int dat) {

		if (root == null) {
			return root;
		}

		if (root.data > dat) {
			root.left = deleteRec(root.left, dat);
		}

		if (root.data < dat) {
			root.right = deleteRec(root.right, dat);
		}

		if (root.data == dat) {

			if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			}

			root.data = infFinder(root);
			root.left = deleteRec(root.left, root.data);
		}
		return root;
	}

	/**
	 * <i>Finds the infimum for delete method to switch the data.
	 */
	private int infFinder(Node root) {

		int tmp = root.data;
		Node node = root;
		node = node.left;

		while (node.right != null) {
			tmp = node.right.data;
			node = node.right;
		}
		return tmp;
	}

	/**
	 * This method prints the tree's elements with the hierarchy below
	 * <p>
	 * 1- Parent node <i>2- Left child <i>3- Right child
	 */
	public void preOrder() {
		System.out.print("PreOrder printing : ");
		preOrderRec(this.root);
		System.out.println();
	}

	private void preOrderRec(Node root) {

		if (root == null) {
			return;
		}

		System.out.print(root.data + " ");
		preOrderRec(root.left);
		preOrderRec(root.right);
	}

	/**
	 * <i>This method makes the tree balanced. First it stores all of the tree's
	 * datas in an array traversing with "inOrder" algorithm, then clears the tree
	 * and rebuilds it correctly.
	 * <p>
	 * <i>Space Complexity: O(2n). <br>
	 * <i>Time Complexity: O(2nlog(n)).
	 */
	public void getBalanced() {

		if (this.isEmpty()) {
			System.out.println("This tree is empty!");
			return;
		}

		int[] el = new int[this.size()];
		sort(0, el, this.root);
		clear();
		rebuild(0, el.length, el);
	}

	/**
	 * <i>This method, basically rebuilds the tree from the array.
	 * <p>
	 * We've passed a sorted array of the tree's elements(If a tree's being travesed
	 * through with "In-Order" algorithm, it will return a sorted list of tree's
	 * elements). Thus, if we pick the middle element every time we want to add an
	 * element to the tree, it's like building a tree with "BFS" traversing
	 * algorithm, so it will bel built in the shortest amount of time.
	 * 
	 * <p>
	 * Complexity: O(nlog(n)).
	 * @param b (begin index)
	 * @param e (end index)
	 * @param elements
	 */
	private void rebuild(int b, int e, int[] elements) {

		if (b == e) {
			return;
		}

		int middle = (b + e) / 2;

		add(elements[middle]);

		rebuild(b, middle, elements);
		rebuild(middle + 1, e, elements);
	}

	/**
	 * <i>It's basically the "In-Order" algorithm with storing all the elements in
	 * the array. The "index", is the index of the array(initially 0).
	 * <p>
	 * The method, returns the "index" to itself to fill the next empty indices in
	 * the array. In the end, it returns the last index which we don't need, so we
	 * won't store it(This method is only called in "getBalanced" method).
	 * 
	 * @param index
	 * @param elements
	 * @param root
	 * @return
	 */
	private int sort(int index, int[] elements, Node root) {

		if (root == null)
			return index;

		index = sort(index, elements, root.left);
		elements[index++] = root.data;
		index = sort(index, elements, root.right);
		return index;
	}

	/**
	 * <i>this method prints the tree's elements with the hierarchy below :
	 * <p>
	 * 1- Left child <br>
	 * 2- Parent node <br>
	 * 3- Right child
	 */
	public void inOrder() {
		System.out.print("InOrder printing : ");
		inOrderRec(this.root);
		System.out.println();
	}

	private void inOrderRec(Node root) {

		if (root == null) {
			return;
		}

		inOrderRec(root.left);
		System.out.print(root.data + " ");
		inOrderRec(root.right);
	}

	/**
	 * <i>this method prints the tree's elements with the hierarchy below
	 * <p>
	 * 1- Left child <br>
	 * 2- Right child <br>
	 * 3- Parent node
	 */
	public void postOrder() {
		System.out.print("PostOrder printing : ");
		postOrderRec(this.root);
		System.out.println();
	}

	private void postOrderRec(Node root) {

		if (root == null) {
			return;
		}

		postOrderRec(root.left);
		postOrderRec(root.right);
		System.out.print(root.data + " ");
	}

	/**
	 * <i>returns the BST's size (Amount of nodes).
	 */
	public int size() {
		return this.size;
	}

	class Node {

		Node left, right;
		int data;

		Node(int data) {

			this.data = data;
			left = right = null;
		}
	};

	public static void main(String[] args) {

		BST bst = new BST();
		bst.add(0);
		bst.add(1);
		bst.add(3);
		bst.add(5);
		bst.add(6);
		bst.add(7);
//		bst.preOrder();
//		bst.postOrder();
		System.out.print("depth is : ");
		System.out.println(bst.getDepth());
		bst.inOrder();
		bst.getBalanced();
		System.out.print("depth is : ");
		System.out.println(bst.getDepth());
		bst.inOrder();
//		bst.preOrder();
	}
}
