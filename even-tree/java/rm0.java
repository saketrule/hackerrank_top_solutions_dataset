import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Solution {

	private static class TreeNode<T> {
		private T value;
		private List<TreeNode<T>> children;
		private int numDescendants;
		private TreeNode<T> parent;
		
		public TreeNode() {
			super();
			children = new ArrayList<TreeNode<T>>();
			numDescendants = 0;
			parent = null;
		}
		
		public TreeNode(T value) {
			this();
			setValue(value);
		}
		
		public TreeNode(T value, TreeNode<T> parent) {
			this();
			setValue(value);
			this.parent = parent;
		}
		
		public void setValue(T value) {
			this.value = value;
		}
		
		public void setParent(TreeNode<T> parent) {
			this.parent = parent;
		}
		
		public T getValue() {
			return this.value;
		}
		
		public TreeNode<T> getParent() {
			return this.parent;
		}
		
		public List<TreeNode<T>> getChildren() {
			return this.children;
		}
		
		public int getNummberOfDescendants() {
			return numDescendants;
		}
		
		public void addDescendants(int adjustBy) {
			// adjustBy can be negative.
			numDescendants += adjustBy;
			if (this.parent != null) this.parent.addDescendants(adjustBy);
		}
		
		public void addChild(TreeNode<T> child) {
			child.parent = this;
			children.add(child);
			int newDescendants = 1 + child.getNummberOfDescendants();
			// Now adjust number of descendants up the chain
			this.addDescendants(newDescendants);
		}
		
		public void addChild(T value) {
			TreeNode<T> child = new TreeNode<T>(value);
			addChild(child);
		}
		
		public int getNumberOfChildren() {
			return getChildren().size();
		}
		
		public boolean hasChildren() {
			return (getNumberOfChildren() > 0);
		}
		
		public TreeNode<T> removeChild(T value) {
			Iterator<TreeNode<T>> itr = this.children.iterator();
			while (itr.hasNext()) {
				TreeNode<T> tn = itr.next();
				if (tn.getValue().equals(value)) {
					// remove
					return removeChild(tn);
				}
			}
			return null;
		}

		public TreeNode<T> removeChild(TreeNode<T> childNode) {
			// remove
			if (this.children.remove(childNode)) {
				childNode.parent = null;
				addDescendants(-1 * (1 + childNode.getNummberOfDescendants()));
				return childNode;
			}
			return null;
		}

		public TreeNode<T> find(T value) {
			if (this.getValue().equals(value)) return this;
			
			Iterator<TreeNode<T>> itr = this.children.iterator();
			while (itr.hasNext()) {
				TreeNode<T> tn = itr.next().find(value);
				if (tn != null) return tn;
			}
			return null;

		}
		
		public String toString() {
			return getValue().toString();
		}
		
		public String toStringTree() {
			String str = toString() + " " + "[" + getNumberOfChildren() + "/" + getNummberOfDescendants() + "] ";
			
			if (hasChildren()) str += " (";
			
			Iterator<TreeNode<T>> itr = this.children.iterator();
			while (itr.hasNext()) {
				str += itr.next().toStringTree();
			}

			if (hasChildren()) str += ") ";
			return str;
		}
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		TreeNode<Integer> root = new TreeNode<Integer>();
		
		int vertices = in.nextInt();
		int edges = in.nextInt();
		
		for(int i = 0; i < edges; i++) {
			Integer child = in.nextInt();
			Integer parent = in.nextInt();
			
			if (i == 0) {
				// root
				root = new TreeNode<Integer>(parent);
				root.addChild(child);
			} else {
				TreeNode<Integer> insertAt = root.find(parent);
				if (insertAt != null) {
					insertAt.addChild(child);
				}
			}
			
		}
		
//		System.out.println(root.toStringTree());
		
		List<TreeNode<Integer>> allRoots = new ArrayList<TreeNode<Integer>>();
		allRoots.add(root);
		
		evenTree(root, allRoots);
		
		System.out.println(allRoots.size() - 1);
		
//		Iterator<TreeNode<Integer>> itr = allRoots.iterator();
//		while (itr.hasNext()) {
//			System.out.println(itr.next().toStringTree());
//		}
//
	}
	
	private static <T> boolean evenTree(TreeNode<T> thisRoot, List<TreeNode<T>> allRoots) {
		Iterator<TreeNode<T>> itr = thisRoot.children.iterator();
		// Post-order traveral recursively.
		while (itr.hasNext()) {
			TreeNode<T> thisChild = itr.next();
			boolean breakThisSubTree = evenTree(thisChild, allRoots);
			if (breakThisSubTree) {
				thisChild.parent = null;
				allRoots.add(thisChild);
				thisRoot.addDescendants(-1 * (1 + thisChild.getNummberOfDescendants()));
				itr.remove();
			}
		}
		// Now see where we are with this node.
		int treeSize = 1 + thisRoot.getNummberOfDescendants();
		if ((treeSize % 2) == 0 && thisRoot.getParent() != null) {
			// Even number of vertices in this subtree.  Break it up.
			return true;
		}
		return false;
	}
	
}
