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

		public RBNode(Patient element) {
			this.key = element;
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

	public RBtree(ArrayList<Patient> test) {
		// TODO Auto-generated constructor stub
		root = nil;

		for (Patient element : test) {
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

	public void RBinsert(Patient newPatient) {
		RBNode node = new RBNode(newPatient);
		RBtreeInsert(node);
		RBtreeInsertFix(node);
	}

	public void RBdelete(Patient newPatient) {
		RBNode node = new RBNode(newPatient);
		node = RBtreeSearch(root, newPatient);
		RBtreeDelete(node);
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

	public void preOrderPrint(RBNode x) {
		if (x != nil) {
			preOrderPrint(x.left);
			System.out.println(x.key);
			preOrderPrint(x.right);
		}
	}

	public void preOrderPrint() {
		preOrderPrint(root);
	}

	public void RBTransplant(RBNode u, RBNode v) {
		if (u.parent == nil) {
			this.root = v;
		} else if (u == u.parent.left) {
			u.parent.left = v;
		} else {
			u.parent.right = v;
		}

		v.parent = u.parent;
	}

	public void RBtreeDelete(RBNode z) {
		RBNode y = z;
		RBNode x = new RBNode();
		int colorOG = y.color;
		if (z.left == nil) {
			x = z.right;
			RBTransplant(z, z.right);
		} else if (z.right == nil) {
			RBTransplant(z, z.left);
		} else {
			y = treeMinimum(z.right);
			colorOG = y.color;
			x = y.right;
			if (y.parent == z) {
				x.parent = y;
			} else {
				RBTransplant(y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}

			RBTransplant(z, y);
			y.left = z.left;
			y.left.parent = y;
			y.color = z.color;
		}
		if (colorOG == BLACK) {
			RBtreeDeleteFix(x);
		}
	}

	public void RBtreeDeleteFix(RBNode x) {
		RBNode w = new RBNode();
		while (x != root && x.color == BLACK) {
			if (x == x.parent.left) {
				w = x.parent.right;
				if (w.color == RED) { // Case1
					w.color = BLACK;
					x.parent.color = RED;
					rotateLeft(x.parent);
					w = x.parent.right;
				}
				if (w.left.color == BLACK && w.right.color == BLACK) { // Case2
					w.color = RED;
					x = x.parent;
				} else if (w.right.color == BLACK) { // Case3
					w.left.color = BLACK;
					w.color = RED;
					rotateRight(w);
					w = x.parent.right;
				}
				// Case4
				w.color = x.parent.color;
				x.parent.color = BLACK;
				w.right.color = BLACK;
				rotateLeft(x.parent);
				x = root;
			} else {
				w = x.parent.left;
				if (w.color == RED) { // Case1
					w.color = BLACK;
					x.parent.color = RED;
					rotateRight(x.parent);
					w = x.parent.left;
				}
				if (w.right.color == BLACK && w.left.color == BLACK) { // Case2
					w.color = RED;
					x = x.parent;
				} else if (w.left.color == BLACK) { // Case3
					w.right.color = BLACK;
					w.color = RED;
					rotateLeft(w);
					w = x.parent.left;
				}
				// Case4
				w.color = x.parent.color;
				x.parent.color = BLACK;
				w.left.color = BLACK;
				rotateRight(x.parent);
				x = root;
			}
		}
		x.color = BLACK;
	}

	public RBNode treeMinimum(RBNode x) {
		while (x.left != nil) {
			x = x.left;
		}
		return x;
	}

	public RBNode RBtreeSearch(RBNode x, Patient target) // Patient or int
															// target.key?
	{
		if (x == nil || target.id == x.key.id)
			return x;
		if (target.id < x.key.id) // <||>
			return RBtreeSearch(x.left, target);
		return RBtreeSearch(x.right, target);
	}

	public Patient RBsearch(Patient value) {
		return RBtreeSearch(root, value).key;
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
