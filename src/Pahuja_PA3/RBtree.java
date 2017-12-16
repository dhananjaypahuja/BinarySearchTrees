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

		RBtreeInsertFix(root);
	}

	private void RBtreeInsert(RBNode node) {

			RBNode y = nil;
			RBNode x = root;
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
			RBtreeInsertFix(node);
		}

	public void RBinsert(Patient newPatient) {
		RBNode node = new RBNode(newPatient);
		RBtreeInsert(node);
	}

	public void RBdelete(Patient newPatient) {
		RBNode node = new RBNode(newPatient);
		node = RBtreeSearch(root, newPatient);
		RBtreeDelete(node);
	}

	private void RBtreeInsertFix(RBNode z) {
		RBNode y = nil;
		while (z.parent.color == RED) {
			if (z.parent == z.parent.parent.left) {
				y = z.parent.parent.right;

				if (y.color == RED) {
					z.parent.color = BLACK;
					y.color = BLACK;
					z.parent.parent.color = RED;
					z = z.parent.parent;
				} else if (z == z.parent.right) {
					z = z.parent;
					rotateLeft(z);
				}
				z.parent.color = BLACK;
				z.parent.parent.color = RED;
				rotateRight(z.parent.parent);
			} else if (z.parent == z.parent.parent.right) {
				y = z.parent.parent.left;
				if (y != nil && y.color == RED) {
					z.parent.color = BLACK;
					y.color = BLACK;
					z.parent.parent.color = RED;
					z = z.parent.parent;
				} else if (z == z.parent.left) {
					z = z.parent;
					rotateRight(z);
				}
				z.parent.color = BLACK;
				z.parent.parent.color = RED;
				rotateLeft(z.parent.parent);
			}
		}
		root.color = BLACK;
	}

	private RBNode rotateLeft(RBNode x) {
		RBNode y = x.right;
		x.right = y.left;
		if (y.right != nil) {
			y.left.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == nil) {
			this.root = y;
		} else if (x == x.parent.left) {
			x.parent.left = y;
		} else
			x.parent.right = y;
		y.left = x;
		x.parent = y;
		if (this.root == x) {
			this.root = y;
		}
		return y;
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
			int i = BLACK;
			if (x.color == RED){
			i = RED;
			}
			
			preOrderPrint(x.left);
			System.out.println(x.key + "   Color: " +i);
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
	
	public void printSearchResult(Patient s){
		if(s != null){
			System.out.println("Patient: "+ s + " found!");
		} else {
			System.out.println("Patient not found!");
		}
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
