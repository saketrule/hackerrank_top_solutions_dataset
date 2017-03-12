/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Solution {
	public static int N;
	public static int M;
	public static Map <Integer,List<Integer>> graph = new HashMap <Integer, List<Integer>> ();
	public static boolean[] visited = new boolean [0];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner("C:\\Documents and Settings\\Aman\\Desktop\\X.txt");
        String line = sc.nextLine();
        N = Integer.parseInt (line.split(" ")[0]);
    	M = Integer.parseInt (line.split(" ")[1]);
        for (int i=1; i<=M; i++) {
        	line = sc.nextLine();
        	int a = Integer.parseInt (line.split(" ")[0]);
        	int b = Integer.parseInt (line.split(" ")[1]);
        	List<Integer> dests = graph.get(a);
        	if (dests!=null) {
        		dests.add (b);
        	} else {
        		List<Integer> bs = new ArrayList <Integer> ();
        		bs.add (b);
        		graph.put(a, bs);
        	}
        	dests = graph.get(b);
        	if (dests!=null) {
        		dests.add (a);
        	} else {
        		List<Integer> as = new ArrayList <Integer> ();
        		as.add (a);
        		graph.put(b, as);
        	}
        }
        for (int i:graph.keySet()) {
        	List<Integer> dest = graph.get(i);
        	Iterator<Integer> it = dest.iterator();
        	while (it.hasNext()) {
        		int j=it.next();
        		visited = new boolean [N+1];
        		visited[i]=true;
        		//System.out.println (dfs(j));
        		if (dfs(j)%2==0) {
        			it.remove();
        			List<Integer> dest1 = graph.get(j);
        			dest1.remove(new Integer(i));
        			//System.out.println ("Removing " + i + " " + j);
        		}
        	}
        }
        int count=0;
        visited = new boolean [N+1];
        for (int i:graph.keySet()) {
        	if (!visited[i]) {
        		dfs(i);
        		count ++;
        	}
        }
        System.out.println(count-1);
    }
    public static int dfs (int source) {
    	int count=1;
    	visited[source]=true;
    	for (int i:graph.get(source)) {
    		if (!visited[i])
    			count+=dfs(i);
    	}
    	return count;
    }
}