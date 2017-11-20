package Pahuja_PA2;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
	public class Node {
		private Node left;
		private Node right;
		private Node parent;
		private Patient key;

		public Node(Patient element) {
			right = null;
			left = null;
			parent = null;
			key = element;
		}

		/**
		 * @return the right
		 */
		public Node getRight() {
			return right;
		}

		/**
		 * @param right
		 *            the right to set
		 */
		public void setRight(Node right) {
			this.right = right;
		}

		/**
		 * @return the left
		 */
		public Node getLeft() {
			return left;
		}

		/**
		 * @param left
		 *            the left to set
		 */
		public void setLeft(Node left) {
			this.left = left;
		}

		/**
		 * @return the parent
		 */
		public Node getParent() {
			return parent;
		}

		/**
		 * @param parent
		 *            the parent to set
		 */
		public void setParent(Node parent) {
			this.parent = parent;
		}

		/**
		 * @return the key
		 */
		public Patient getKey() {
			return key;
		}

		/**
		 * @param key
		 *            the key to set
		 */
		public void setKey(Patient key) {
			this.key = key;
		}
	}

	private Node root;

	public BinarySearchTree(ArrayList<Patient> patients) {
		root = null;

		for (Patient element : patients) {
			Node node = new Node(element);
			treeInsert(node);
		}
	}

	// Prints BST
	public void inorderTreeWalk(Node x) {
		if (x != null) {
			inorderTreeWalk(x.getLeft());
			System.out.println(x.getKey());
			inorderTreeWalk(x.getRight());
		}
	}

	public void inorderTreeWalk() {
		inorderTreeWalk(root);
	}

	// Search BST for target value
	public Node search(Node x, Patient target) // Patient or int target.key?
	{
		if (x == null || target.getId() == x.getKey().getId())
			return x;
		if (target.getId() < x.getKey().getId()) // <||>
			return search(x.getLeft(), target);
		return search(x.getRight(), target);
	}

	// //searches the BST for a specific key
	// public Node search( Node x, Patient target)
	// {
	// while( x != null && target.getId() != x.key.id)
	// {
	//
	// if(target.getId() < x.key.id)
	// {
	// x = x.left;
	// }
	// else
	// {
	// x = x.right;
	// }
	//
	// }
	// return x;
	// }

	// simply inserts a new Node into the tree
	public Node search(Patient value) {
		return search(root, value);

	}

	// finds the minimum value in the tree, located at the left-most node
	public Node treeMinimum(Node x) {
		while (x.left != null) {
			x = x.left;
		}
		return x;
	}

	// inserts a Node, ensuring BST property is still followed
	public void treeInsert(Node z) {
		Node y = null;
		Node x = this.root;
		while (x != null) {
			y = x;
			if (z.key.id < y.key.id) { // z.key < y.key
				x = x.left;
			} else {
				x = x.right;
			}
		}
		z.parent = y;
		if (y == null) {
			this.root = z;
		} else if (z.key.id < y.key.id) { // z.key < y.key
			y.left = z;
		} else {
			y.right = z;
		}
	}

	// simply inserts a new Node into the tree
	public void insert(Patient value) {
		Node node = new Node(value);
		treeInsert(node);

	}

	// replaces subtree rooted at u as a child of parents with another subtree
	// rooted at v
	// acts as a helper method for treeDelete()
	public void transplant(Node u, Node v) {
		if (u.parent == null) {
			this.root = v;
		} else if (u == u.parent.left) {
			u.parent.left = v;
		} else {
			u.parent.right = v;
		}

		if (v != null) {
			v.parent = u.parent;
		}
	}

	// deletes a Node from the tree, ensures following BST property after
	// deletion
	public void treeDelete(Node z) {
		if (root == null)
            System.out.println("Empty BSTree");
		Node y;
		if (z.left == null) {
			transplant(z, z.right);
		} else if (z.right == null) {
			transplant(z, z.left);
		} else {
			y = treeMinimum(z.right);
			if (y.parent != z) {
				transplant(y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}

			transplant(z, y);
			y.left = z.left;
			y.left.parent = y;
		}
	}

	// simply inserts a new Node into the tree
	// overloading delete method
	public void delete(Patient value) {
		Node x = search(value);
		if (x != null) {
			treeDelete(x);
		}

	}

}
