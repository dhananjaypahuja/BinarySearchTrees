//package Pahuja_PA2;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BinarySearchTree {
//	public class Node {
//		private Node left;
//		private Node right;
//		private Node parent;
//		private Patient key;
//
//		public Node(Patient element) {
//			right = null;
//			left = null;
//			parent = null;
//			key = element;
//		}
//
//		/**
//		 * @return the right
//		 */
//		public Node getRight() {
//			return right;
//		}
//
//		/**
//		 * @param right
//		 *            the right to set
//		 */
//		public void setRight(Node right) {
//			this.right = right;
//		}
//
//		/**
//		 * @return the left
//		 */
//		public Node getLeft() {
//			return left;
//		}
//
//		/**
//		 * @param left
//		 *            the left to set
//		 */
//		public void setLeft(Node left) {
//			this.left = left;
//		}
//
//		/**
//		 * @return the parent
//		 */
//		public Node getParent() {
//			return parent;
//		}
//
//		/**
//		 * @param parent
//		 *            the parent to set
//		 */
//		public void setParent(Node parent) {
//			this.parent = parent;
//		}
//
//		/**
//		 * @return the key
//		 */
//		public Patient getKey() {
//			return key;
//		}
//
//		/**
//		 * @param key
//		 *            the key to set
//		 */
//		public void setKey(Patient key) {
//			this.key = key;
//		}
//	}
//
//	private Node root;
//
//	public BinarySearchTree(ArrayList<Patient> patients) {
//		root = null;
//
//		for (Patient element : patients) {
//			Node node = new Node(element);
//			treeInsert(node);
//		}
//	}
//
//	// Prints BST
//	public void inorderTreeWalk(Node x) {
//		if (x != null) {
//			inorderTreeWalk(x.getLeft());
//			System.out.println(x.getKey());
//			inorderTreeWalk(x.getRight());
//		}
//	}
//
//	public void inorderTreeWalk() {
//		inorderTreeWalk(root);
//	}
//
//	
//	public Node search(Node x, Patient target) // Patient or int target.key?
//	{
//		if (x == null || target.getId() == x.getKey().getId())
//			return x;
//		if (target.getId() < x.getKey().getId()) // <||>
//			return search(x.getLeft(), target);
//		return search(x.getRight(), target);
//	}
//
//	//search a Node
//	public Node search(Patient value) {
//		return search(root, value);
//
//	}
//
//	// finds the minimum value in the tree
//	public Node treeMinimum(Node x) {
//		while (x.left != null) {
//			x = x.left;
//		}
//		return x;
//	}
//
//	// insert a Node
//	public void treeInsert(Node z) {
//		Node y = null;
//		Node x = this.root;
//		while (x != null) {
//			y = x;
//			if (z.key.id < y.key.id) {
//				x = x.left;
//			} else {
//				x = x.right;
//			}
//		}
//		z.parent = y;
//		if (y == null) {
//			this.root = z;
//		} else if (z.key.id < y.key.id) {
//			y.left = z;
//		} else {
//			y.right = z;
//		}
//	}
//
//	// simply inserts a new Node into the tree
//	public void insert(Patient value) {
//		Node node = new Node(value);
//		treeInsert(node);
//
//	}
//
//	//used to maintain tree properties
//	public void transplant(Node u, Node v) {
//		if (u.parent == null) {
//			this.root = v;
//		} else if (u == u.parent.left) {
//			u.parent.left = v;
//		} else {
//			u.parent.right = v;
//		}
//
//		if (v != null) {
//			v.parent = u.parent;
//		}
//	}
//
//	// deletes a Node
//	public void treeDelete(Node z) {
//		if (root == null)
//			System.out.println("Empty BSTree");
//		Node y;
//		if (z.left == null) {
//			transplant(z, z.right);
//		} else if (z.right == null) {
//			transplant(z, z.left);
//		} else {
//			y = treeMinimum(z.right);
//			if (y.parent != z) {
//				transplant(y, y.right);
//				y.right = z.right;
//				y.right.parent = y;
//			}
//
//			transplant(z, y);
//			y.left = z.left;
//			y.left.parent = y;
//		}
//	}
//
//	// overloading delete method
//	public void delete(Patient value) {
//		Node x = search(value);
//		if (x != null) {
//			treeDelete(x);
//		}
//	}
//}
