/* Enter your code here. Read input from STDIN. Print output to STDOUT */
//programme by shivi
#include<iostream>
#include<vector>
using namespace std;
template<typename T>
class Bag
{
private:
	class Node
	{
	public:
		T item;
		Node *next;
	Node(T it,Node *x)
		{
		item=it;
		next=x;
		}
	};
	Node *start;
	int N;
public:

	Bag()
	{
		start=NULL;
		N=0;
	}

	
	void Add(T item)
	{
		Node *x=new Node(item,start);
		start=x;
		N++;
	}

	T Del()
	{
		Node *y=start;
		start=start->next;
		T k=y->item;
		delete y;
		return k;
	}

	vector<int> Print()
	{
		vector<int> v;
		Node *x=start;
		while(x!=NULL)
		{
			v.push_back(x->item);
			x=x->next;
		}
		return  v;
	}

	int Size()
	{return N;}

	bool isEmpty()
	{return N==0;}
	
	vector<int> traverse()
	{
		Node *x=start;
		vector<int> v;
		while(x!=NULL)
		{
		v.push_back(x->item)	;
		x=x->next;
		}
		
		return v;
	}

};


class UndirectedGraph
{
	private:
	int V;
	int E;
	Bag<int> *adj;
	
	public:
	UndirectedGraph(int v)
	{
		V=v;
		E=0;
		adj=new Bag<int>[V];
	}
	
	void AddEdge(int p,int q)
	{
		adj[q].Add(p);
		E++;
	}
	

	vector<int> Adjacent(int i)
	{
		return adj[i].traverse();
	}
	int vertices()
	{
		return V;
	}
};

class DepthFirst
{
	private:
	vector<bool> marked;
	int cut;
	int even;
	
	public:
	DepthFirst(UndirectedGraph U)
	{
		for(int i=0;i<U.vertices();++i)
		marked.push_back(0);
		cut=0;even=0;
		for(int j=0;j<U.vertices();++j)
		{
		vector<int> v=U.Adjacent(j);
		
		for(int i=0;i<U.vertices();++i)
		marked[i]=0;
		
		for(int k=0;k<v.size();++k)
			{
			dfs(U,v[k]);
			if(cut%2==0) even++;
			cut=0;
			}
			
		}
	}
	
	void dfs(UndirectedGraph U,int s)
	{
		cut++;
		marked[s]=1;
		vector<int> v=U.Adjacent(s);
		for(int k=0;k<v.size();++k)
		if(!marked[v[k]])
			dfs(U,v[k]);
	}
	
	int Even()
	{
		return even;
	}
};
int main()
{
	int N,E,x,y;
	cin>>N;
UndirectedGraph U(N+1)	;
cin>>E;

for(int i=0;i<E;++i)
{
	cin>>x>>y;
	U.AddEdge(x,y);
}
DepthFirst D(U);

cout<<D.Even()<<endl;
}
