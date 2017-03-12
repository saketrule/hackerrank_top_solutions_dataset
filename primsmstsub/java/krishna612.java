import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


//655 720 520 550 610 640 555
public class Solution {
	
	class Edge implements Comparable{
		int x,y,w;
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getW() {
			return w;
		}
		public void setW(int w) {
			this.w = w;
		}
		
		public Edge(int x, int y, int w){
			this.x = x;
			this.y = y;
			this.w = w;
		}
		
		@Override
		public int compareTo(Object obj) {
			int value = -2;
			Edge edge = (Edge)obj;
			if(this.w==edge.getW()){
				if(this.x+this.y == edge.x+edge.y)
				  value = 0;
				else if(this.x+this.y > edge.x+edge.y)
				  value = 1;
				else 
				  value = -1;
			}
			else if(this.w>edge.getW())
				value = 1;
			else 
				value = -1;
			return value;
		}
		
		public String toString(){
			return this.getX()+"("+this.getW()+")"+this.getY();
		}
	}//end of edge class
	
	class EdgeGraph{

		List<Edge> edgeList = new ArrayList<Edge>();
		int noOfVertices;
		
		public int getNoOfVertices() {
			return noOfVertices;
		}

		public void setNoOfVertices(int noOfVertices) {
			this.noOfVertices = noOfVertices;
		}

		public List<Edge> getEdgeList() {
			return edgeList;
		}

		public void setEdgeList(List<Edge> edgeList) {
			this.edgeList = edgeList;
		}
		
		public void addEdge(Solution.Edge e){
			edgeList.add(e);
		}
		
		public List<Edge> allEdgesIncidentToVertex(int vertex){
			List<Edge> incidentEdgeList = new ArrayList<Edge>();
			for(Edge e: edgeList){
				if(e.getX()==vertex || e.getY()==vertex){
					incidentEdgeList.add(e);
					//System.out.println("edge added: "+e);
				}
			}
			return incidentEdgeList;
		}

	}//end of edgegraph class

	
	class MinHeap{
		
		PriorityQueue<Solution.Edge> heap = new PriorityQueue<Solution.Edge>();
		
		public void insert(Solution.Edge e){
			heap.add(e);
		}
		
		public Solution.Edge getMin(){
		    return heap.peek();
		}
		
		public Edge remove(){
			return heap.poll();
		}
		
		public int getSize(){
			return heap.size();
		}
	}//end of MinHeap class
	
	class PrimsAlgo{
		Solution.EdgeGraph MST,graph;
		Solution.MinHeap minHeap;
		Solution prim = new Solution();
		boolean[] isPresent;
		
		public PrimsAlgo(Solution.EdgeGraph graph){
			this.graph = graph;
			MST = prim.new EdgeGraph();
			minHeap = prim.new MinHeap();
			isPresent = new boolean[graph.getNoOfVertices()];
		}
		
		public Solution.EdgeGraph generateMST(int startingVertex){
			int vertex = startingVertex;
			int count = 0;
			List<Edge> list = graph.allEdgesIncidentToVertex(vertex);
			isPresent[vertex] = true;
			count++;
			for(Edge e: list){
				minHeap.insert(e); //first iteration so we can insert all edges
			}
			while(count<graph.getNoOfVertices() && minHeap.getSize()!=0){
				vertex = -1;
				Edge minEdge = minHeap.remove();
				//one of the vertices of the edge will be definitely not present in vertex set
				if(isPresent[minEdge.getX()]==false){
					vertex = minEdge.getX();
				}
				else if(isPresent[minEdge.getY()]==false){
					vertex = minEdge.getY();
				}
				//now insert all valid edges to list
				if(vertex!=-1){
				  isPresent[vertex] = true;
				  count++;
				  MST.addEdge(minEdge);
				  list = graph.allEdgesIncidentToVertex(vertex);
				  for(Edge e: list){
					 if( !(isPresent[e.getX()] && isPresent[e.getY()]) ){
						minHeap.insert(e);
					 }
				  }
			    }
			}
			return MST;
		}
		
	}

    public static void main(String[] args) throws IOException {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int N,M,x,y,w;
		Solution prim = new Solution();
		Solution.EdgeGraph graph = prim.new EdgeGraph();
		Solution.Edge edge;
		String[] inputStr = reader.readLine().split(" ");
		N = Integer.parseInt(inputStr[0]);
		M = Integer.parseInt(inputStr[1]);
		graph.setNoOfVertices(N+1);
		for(int i=0;i<M;i++){
			inputStr = reader.readLine().split(" ");
			x = Integer.parseInt(inputStr[0]);
			y = Integer.parseInt(inputStr[1]);
			w = Integer.parseInt(inputStr[2]);
			edge = prim.new Edge(x-1,y-1,w);
			graph.addEdge(edge);
		}
		int finalVertex = Integer.parseInt(reader.readLine());
		Solution.PrimsAlgo algo = prim.new PrimsAlgo(graph);
		Solution.EdgeGraph MST = algo.generateMST(finalVertex-1);
		List<Edge> mstList = MST.getEdgeList();
		long sum=0;
		//System.out.println("debug: printing eges of MST");
		for(Edge itr: mstList){
			//System.out.print(itr.getX()+"("+itr.getW()+")"+itr.getY());
			//System.out.println();
			sum+=itr.getW();
		}
		System.out.println(sum);
	}
}
