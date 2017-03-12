import java.util.*;

public class Solution {

	public static void main(String[] args){
	        Scanner in = new Scanner(System.in);
	        
	        int numVertices = in.nextInt();
	        int numEdges = in.nextInt();
	       // int minEdges = 0;
	        
	        Map<Integer,HashSet<Integer>> adjList = new HashMap<Integer,HashSet<Integer>>();
	        for(int i = 1;i <= numVertices;i++){
	            adjList.put(i,new HashSet<Integer>());
	        }
	        
	        Integer[] setCardinality = new Integer[numVertices + 1];
	        Arrays.fill(setCardinality,1);
	        Integer[] neighbourCount = new Integer[numVertices + 1];
	        Arrays.fill(neighbourCount,0);
	        
	        for(int i = 0;i < numEdges;i++){
	            int u = in.nextInt();
	            int v = in.nextInt();
	            
	            adjList.get(u).add(v);
	            ++neighbourCount[u];
	            adjList.get(v).add(u);
	            ++neighbourCount[v];
	        }
	       
//	        System.out.println("Set Cardinality : ");
//	        System.out.println(Arrays.asList(setCardinality));
//	        System.out.println("Neighbour Count : ");
//	        System.out.println(Arrays.asList(neighbourCount));
//	        System.out.println("Adjacency List : ");
//	        System.out.println(adjList);
//	        System.out.println("//////////////////////////////////////////////////////");
	        
	        boolean allEven = true;
	        int ans = 0;
	        
	        while(allEven){
	        	
	        	allEven = false;
	        	
	        	for(int i = 1;i <= numVertices;i++){
		            //only if child has one edge left AND has odd number of vertices attached to it
	        		if(neighbourCount[i] == 1  &&  setCardinality[i] == 1){
		                //get parent of the child
		            	int parent = adjList.get(i).iterator().next();
		                //remove edge
		                adjList.get(parent).remove(i);
		                adjList.get(i).remove(parent);
		                //count added edges
		               // minEdges++;
		                //changing set cardinality of parent and child as result of adding child to parent
		                setCardinality[parent] = setCardinality[i] == setCardinality[parent]?0:1;
		                setCardinality[i] = 0;
		                //changing neighbor count as result of deleting edge
		                neighbourCount[parent] -= 1;
		                neighbourCount[i] = 0;
		                //loop again since some parent modified
		                allEven = true; 
		                
		             
	        		}   //if parent has only one edge AND has even number of vertices attached to it
		                if(neighbourCount[i] == 1 && setCardinality[i] == 0){
                                    int v = adjList.get(i).iterator().next();
                                    adjList.get(i).remove(v);
                                    adjList.get(v).remove(i);
                                    neighbourCount[i] = 0;
                                    neighbourCount[v] -= 1;
                                    ans++;
                                    allEven = true;
		                }
	        }
	        
	        
//	        System.out.println("Set Cardinality : ");
//	        System.out.println(Arrays.asList(setCardinality));
//	        System.out.println("Neighbour Count : ");
//	        System.out.println(Arrays.asList(neighbourCount));
//	        System.out.println("Adjacency List : ");
//	        System.out.println(adjList);
//	        System.out.println();
	        
	    }
	        System.out.println(ans);
	        //System.out.println(numEdges - minEdges);
	}
}
