/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/

import java.util.*;
public class Solution {
	public static void main(String args[]){
		Scanner s=new Scanner(System.in);
		int n,m;
		int e1,e2;
		n=s.nextInt();
		m=s.nextInt();
		Tree t=new Tree(n);
		
		for(int i=0;i<m;i++){
			e1=s.nextInt();
			e2=s.nextInt();
			if (e1<e2)
				t.addedge(e1,e2);
			else
				t.addedge(e2,e1);
		}
		
		//t.printall();
		t.count();
		System.out.println(t.removes);
	}
}


class Node{
	int index;
	ArrayList<Node> next=new ArrayList<Node>();
}


class Tree{
	int n;
	Node root;
	Node[] nodes;
	int removes,temp;
	public Tree(int _n){
		n=_n;
		nodes=new Node[n];
		for(int i=0;i<n;i++){
			nodes[i]=new Node();
			nodes[i].index=i+1;
		}
		root=nodes[0];
	}
	
	void addedge(int a,int b){
		nodes[a-1].next.add(nodes[b-1]);
	}
	/*
	void printall(){
		printallhelper(root);
	}
	
	void printallhelper(Node _node){
		System.out.print(_node.index);
		for(Node _next:_node.next){
			printallhelper(_next);
		}
	}
	*/
	int count(){
		return countHelper(root);
	}
	 
	int countHelper(Node node){
		int total =1;
		for (Node next :node.next){
			temp=countHelper(next);
			total+=temp;
			if (temp%2==0 && temp>1)
				removes+=1;
		}
		return total;
	}
}
