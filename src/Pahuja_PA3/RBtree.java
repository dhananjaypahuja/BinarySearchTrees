package Pahuja_PA3;

import java.util.ArrayList;

public class RBtree {

	private final int RED = 0;
	private final int BLACK = 1;
	
	public RBNode root;
	public RBNode nil = new RBNode();

	public class RBNode {
		RBNode left;
		RBNode right;
		RBNode parent;
		Patient key;
		int color;

		public RBNode() {
			this.key = null;
			this.parent = null;
			this.left = null;
			this.right = null;
			this.color = BLACK;
		}
		
		public RBNode(Patient node) {
			this.key = node;
			this.parent = nil;
			this.left = nil;
			this.right = nil;
			this.color = BLACK;
		}

		// public String toString() {
		// return this.key + " ";
		// }

		public String toString() {
			return "Color:" + color;
		}
	}

	public RBtree(ArrayList<Patient> patients) {
		root = nil;

		for (Patient element : patients) {
			RBNode node = new RBNode(element);
			RBtreeInsert(node);
		}

		RBtreeInsertFix(root.left);
		RBtreeInsertFix(root.right);
	}

	private void RBtreeInsert(RBNode node) {

		if (root == nil) {
			root = node;
			node.color = BLACK;
			node.parent = new RBNode(new Patient("DummyNode", 0));
		} else {
			RBNode y = nil;
			RBNode x = root;
			node.color = RED;
			while (x != nil) {
				y = x;
				if (node.key.id < y.key.id) {
					x = x.left;
				} else {
					x = x.right;
				}
			}
			node.parent = y;
			if (y == nil) {
				this.root = node;
			} else if (node.key.id < y.key.id) {
				y.left = node;
			} else {
				y.right = node;
			}
			node.left = nil;
			node.right = nil;
			node.color = RED;
		}
	}

	public void insert(Patient element) {
		RBNode node = new RBNode(element);
		RBtreeInsert(node);
		RBtreeInsertFix(node);
	}

	private void RBtreeInsertFix(RBNode node) {
		while (node.parent.color == RED) {
			RBNode y = nil;
			if (node.parent == node.parent.parent.left) {
				y = node.parent.parent.right;

				if (y != nil && y.color == RED) {
					node.parent.color = BLACK;
					y.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
				} else if (node == node.parent.right) {
					node = node.parent;
					rotateLeft(node);
				}
				node.parent.color = BLACK;
				node.parent.parent.color = RED;
				rotateRight(node.parent.parent);
			} else if (node.parent == node.parent.parent.right) {
				y = node.parent.parent.left;
				if (y != nil && y.color == RED) {
					node.parent.color = BLACK;
					y.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
				} else if (node == node.parent.left) {
					node = node.parent;
					rotateRight(node);
				}
				node.parent.color = BLACK;
				node.parent.parent.color = RED;
				rotateLeft(node.parent.parent);
			}
		}
		root.color = BLACK;
	}

	private RBNode rotateLeft(RBNode node) {
		RBNode temp = node.right;
		node.right = temp.left;
		if (temp.right != nil) {
			temp.left.parent = node;
		}
		temp.parent = node.parent;
		if (node.parent == nil) {
			this.root = temp;
		} else if (node == node.parent.left) {
			node.parent.left = temp;
		} else
			node.parent.right = temp;
		temp.left = node;
		node.parent = temp;
		if (this.root == node) {
			this.root = temp;
		}
		return temp;
	}

	private RBNode rotateRight(RBNode node) {
		RBNode temp = node.left;
		node.left = temp.right;
		if (temp.left != nil) {
			temp.right.parent = node;
		}
		temp.parent = node.parent;
		if (node.parent == nil) {
			this.root = temp;
		} else if (node == node.parent.right) {
			node.parent.right = temp;
		} else
			node.parent.left = temp;
		temp.right = node;
		node.parent = temp;
		if (this.root == node) {
			this.root = temp;
		}
		return temp;
	}

	public void inorderTreeWalk(RBNode x) {
		if (x != nil) {
			inorderTreeWalk(x.left);
			System.out.println(x.key);
			inorderTreeWalk(x.right);
		}
	}

	public void inorderTreeWalk() {
		inorderTreeWalk(root);
	}

	public static void preOrderPrint(RBNode root) {
		
		preOrderPrint(root.left);
		System.out.print(root + "\n");
		preOrderPrint(root.right);
	}
}

// private RBNode rotateRight(RBNode node) {
// RBNode temp = node.left;
// node.left = temp.right;
// if (node.left != null) {
// node.left.parent = node;
// }
//
// temp.right = node;
// temp.parent = node.parent;
// node.parent = temp;
// if (this.root == node) {
// this.root = temp;
// }
//
// return temp;
// }
//
// private RBNode rotateLeft(RBNode node) {
// RBNode temp = node.right;
// node.right = temp.left;
// if (node.right != null) {
// node.right.parent = node;
// }
//
// temp.left = node;
// temp.parent = node.parent;
// node.parent = temp;
// if (this.root == node) {
// this.root = temp;
// }
//
// return temp;
// }
