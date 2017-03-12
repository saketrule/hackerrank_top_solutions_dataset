
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


//https://www.interviewstreet.com/challenges/dashboard/#problem/4fffc24df25cd
public class Solution {
	
	Map<Integer, Node> nodes = new HashMap<Integer, Node>();
	
	static class Node{
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (id != other.id)
				return false;
			return true;
		}
		int id;
		Set<Integer> neighbors;
		public Node(int id){
			this.id = id;
			neighbors = new HashSet<Integer>();
		}
	}
	
	
	public Node getNode(int id){
		if(this.nodes.containsKey(id)){
			return this.nodes.get(id);
		}
		else{
			Node n = new Node(id);
			this.nodes.put(id, n);
			return n;
		}
		
	}
	
	public int countOfRemovedEdges(String[] edges){
		int numOfEdges = 0;
		for(int i = 0; i < edges.length; i++){
			boolean r = removeEdge(edges[i]);
			if(r){
				numOfEdges++;
			}
		}
		return numOfEdges;
		
	}
	
	public boolean removeEdge(String edge){
    	String[] tmpArray = edge.split(" ");
    	
    	int id1 = Integer.parseInt( tmpArray[0] );
    	int id2 = Integer.parseInt( tmpArray[1] );
    	
    	this.nodes.get(id1).neighbors.remove(id2);
    	this.nodes.get(id2).neighbors.remove(id1);
    	
    	int count = this.count(this.nodes.get(id1), new HashSet<Integer>());
    	if(count % 2 == 0){
    		return true;
    	}
    	else{
    		this.nodes.get(id1).neighbors.add(id2);
        	this.nodes.get(id2).neighbors.add(id1);
        	return false;
    		
    	}
    	
    	
	}
	
	public int count(Node n, Set<Integer> visited){
		if(n == null) return -1;
		int result = 1;
		visited.add(n.id);
		for(Integer idx : n.neighbors){
			if(visited.contains(idx)){
				continue;
			}
			result = result + count(this.nodes.get(idx), visited);
		}
		return result;
		
	}
	
	public static void main(String[] args) throws IOException{
		
		Solution tree = new Solution();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String line = br.readLine();
	    int N = Integer.parseInt(line.split(" ")[0]);
	    String[] edges = new String[N-1];
	    
	    for(int i = 0; i < N-1; i++){
	    	String tmp = br.readLine();
	    	edges[i] = tmp;
	    	String[] tmpArray = tmp.split(" ");
	    	
	    	int id1 = Integer.parseInt( tmpArray[0] );
	    	int id2 = Integer.parseInt( tmpArray[1] );
	    	
	    	Node node1 = tree.getNode(id1);
	    	Node node2 = tree.getNode(id2);
	    	
	    	node1.neighbors.add(id2);
	    	node2.neighbors.add(id1);	    	
	    }
	       
	    System.out.println(tree.countOfRemovedEdges(edges));
	        
	}
	

}
