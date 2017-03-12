#include <iostream>
#include<list>
using namespace std;
#define max 1000000;
class graph{
	public:
	int v;
	list <int> *adj;
	list<int >*wt;
	void addEdge(int v1,int v2,int wt1);
	int prims();
	graph(int v);
	
	};
 graph::graph(int v)
{
	this->v = v;
	adj = new list<int>[v];
	wt = new list<int>[v];
}
void graph::addEdge(int v1,int v2,int wt1)
{
	adj[v1].push_back(v2);
	adj[v2].push_back(v1);
	wt[v1].push_back(wt1);
	wt[v2].push_back(wt1);
}

int graph::prims()
{
	bool *visited;
	int *setmst,i,count,min,indx=0,last=0,j,sum=0;
	list<int> ::iterator it,itw;
	visited = new bool[v];
	setmst = new int[v];
	for(i=0;i<v;i++)
	{setmst[i]=max;visited[i]=false;}
	
	setmst[0]=0;
	//visited[0]=true;
	for(i=0;i<v;i++)
	{
		min= max;
		for(j=0;j<v;j++)
		{
			if(min > setmst[j] && !visited[j])
			{
				indx = j;
				min = setmst[j];
			}
		}
		 visited[indx] = true;
		 for(it=adj[indx].begin(),itw= wt[indx].begin();it!=adj[indx].end();it++,itw++)
		 {
		 	//cout <<indx <<" "<<*it <<" "<<setmst[*it] <<" " <<*itw<<endl;
		 	if(setmst[*it] > *itw && !visited[*it])
		 	{
		 		setmst[*it] = *itw;
			}
		 }
		 
	}
	for(i=0;i<v;i++)
	sum = sum +setmst[i];
	return sum;
}
int main() {
	
	graph *g;
	int n,m,i,v1,v2,t,j,wt;
   
	cin >>n>>m;
	g = new graph(n);
	for(i=0;i<m;i++)
	{
		cin >> v1>>v2>>wt;
		g->addEdge(v1-1,v2-1,wt);
	}
	cout << g->prims();
	
    
	return 0;
}