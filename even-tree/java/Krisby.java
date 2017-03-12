/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/




import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution {
	static String probStmtDir = "E:\\Krishnanew\\InterviewStreet\\Problems\\";
	static String probStmtFile =
		probStmtDir +  "tree\\EvenTree.txt";
	
	static int V;
	static int E;
	static Node[] nodes; 
	static boolean[] seen;
	static Node root;
	static int countEdgesBroken = 0;
	public static void main(String[] args) throws IOException 
	{
		read();
		countNodesAndBreak(root);
		System.out.println(countEdgesBroken);
	}
	
	
	static class Node
	{
		int v;
		
		List<Node> children = new ArrayList<Node>();
		public Node(int v) {
			this.v = v;
		}
	}
	static void read() throws IOException
	{
		BufferedReader in = //new BufferedReader(new FileReader(probStmtFile));
			new BufferedReader(new InputStreamReader(System.in));
	    
		String line1 = in.readLine();
		V = getIntsInString(line1, " ")[0];//
		E = getIntsInString(line1, " ")[1];//
		
		seen = new boolean[V + 1];
		nodes = new Node[V + 1];
		
		for (int i = 1; i<= V; i++ ) {
			nodes[i] = new Node(i);
		}
		// setting edges as parent child relation.
		// 
		line1 = in.readLine();
		int u = getIntsInString(line1, " ")[0];//
	    int v = getIntsInString(line1, " ")[1];//
	    
	    root = nodes[u];
	    
	    addChild(u, v);
	    
		for (int i = 1; i < E; i++)
		{
			line1 = in.readLine();
			u = getIntsInString(line1, " ")[0];//
		    v = getIntsInString(line1, " ")[1];//
		    
		    if (seen[u]) addChild(u, v);
		    else addChild(v, u);
		    
		    //System.out.println("adding edge :" + u + "->" + v);
		    
		}
	}
	
	static void BreakTree()
	{
		
	}
	
	static int countNodesAndBreak(Node x) 
	{
		int count = 1;
		
		List<Node> childrenToRemove = new ArrayList<Node>();
		for (Node subtree: x.children) {
			int subtreeCount = countNodesAndBreak(subtree);
			
			if (subtreeCount % 2 == 0) {
				
				countEdgesBroken++;
				childrenToRemove.add(subtree);
			}
			else {
				count += subtreeCount;
			}
			
		}
		x.children.removeAll(childrenToRemove);
		
		return count;
	}
	
	
	static void addChild(int parent, int child)
	{
		nodes[parent].children.add(nodes[child]);
	}
	
	static int[] getIntsInString(String str, String sp)
	{
		String[] nm = str.split(sp);
		
		int[] ints = new int[nm.length];
		int i =0;
		for (String s:nm){
			ints[i++] = Integer.parseInt(s.trim());
		}
	     
	     return ints;
	}
}
