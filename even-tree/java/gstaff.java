/* Solves the Even Tree problem by working greedily up from the leafs */

import java.util.*;

class Solution{

	private static int N;
	private static int M;
	private static int components;
	private static ArrayList<Node> nodes;

	private static class Node {
		private Node parent;
		private ArrayList<Node> children;

		private int ID;

		private Node() {
			parent = null;
			children = new ArrayList<Node>(100);
		}

		private void remove() {
			nodes.remove(this);
			if (parent != null)
				parent.children.remove(this);
			int size = children.size();
			for (int i = 0; i < size; i++){
				children.get(0).remove();
			}
		}

		private boolean isCandidate() {
			if (children.size() == 0)
				return false;
			for(Node child: children) {
				if (child.children.size() != 0)
					return false;
			}
			return true;
		}

		private boolean isComponent() {
			if (children.size() < 1 || children.size() % 2 == 0)
				return false;
			return true;
		}

		private void reform() {
			if (parent != null) {
				for(Node child: children) {
					parent.children.add(child);
					child.parent = parent;
				}
				children.clear();
			}
		}
	}

	public static void main( String args[] ) {

		Scanner in = new Scanner(System.in);

		N = in.nextInt();
		M = in.nextInt();
		components = 0;
		nodes = new ArrayList<Node>(N);

		for(int i = 0; i < N; i++) {
			nodes.add(new Node());
		}

		//construct the graph
		for(int i = 0; i < M; i++) {
			int first = in.nextInt() - 1;
			int second = in.nextInt() - 1;
			if (second < first) {
				int temp = first;
				first = second;
				second = temp;
			}

			nodes.get(first).children.add(nodes.get(second));
			nodes.get(second).parent = nodes.get(first);
		}

		while(!nodes.isEmpty()){
			for(int i = 0; i < nodes.size(); i++) {
				if (nodes.get(i).isCandidate()) {
					if (nodes.get(i).isComponent())
						countComponent(nodes.get(i));
					else
						nodes.get(i).reform();
				}
			}
		}

		System.out.println( components - 1 );
	}

	private static void countComponent(Node node) {
		components ++;
		node.remove();
	}

}