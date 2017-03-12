/* Enter your code here. Read input from STDIN. Print output to STDOUT */
#include <stdio.h>
#include <vector>
#include <map>
using namespace std;
class Edge
{
public:
  int src,dest;
  Edge(int s, int d)
  {
    src = s;
    dest = d;
  }
};
class Component
{
public:
  vector<int> vertices;
  int label;
  vector<Edge> outgoingEdges;
};
int n,m;
vector<int> graph[101];
bool edges[101][101];
vector<Component> components;
map<int,int> comp;
int compCount = 0;
int result = 0;
void removeEdge(int src, int dest)
{
  edges[src][dest] = false;
  edges[dest][src] = false;
}
bool isValidEdge(int src, int dest)
{
  return edges[src][dest] || edges[dest][src];
}
void updateOutgoingEdges(Component *c)
{
  int next = 0;
  while(next<c->outgoingEdges.size())
  {
    if(!isValidEdge(c->outgoingEdges[next].src,c->outgoingEdges[next].dest))
    {
//       printf("Removing edge %d %d\n",c->outgoingEdges[next].src,c->outgoingEdges[next].dest);
      c->outgoingEdges.erase(c->outgoingEdges.begin()+next);
    }
    else
      next++;
  }
}
Component* getComponentFromVertex(int v)
{
  int label = comp[v];
  int size = components.size();
  for(int i=0;i<size;i++)
  {
    if(components[i].label==label)
      return &components[i];
  }
}
int getComponentIndexFromVertex(int v)
{
  int label = comp[v];
  int size = components.size();
  for(int i=0;i<size;i++)
  {
    if(components[i].label==label)
      return i;
  }
}
int min(int a, int b)
{
  if(a<b)
    return a;
  return b;
}
void mergeComponents(Component *c1, Component *c2)
{
  c1->label = min(c1->label,c2->label);
  int count = c2->outgoingEdges.size();
  for(int i=0;i<count;i++)
  {
    c1->outgoingEdges.push_back(c2->outgoingEdges[i]);
  }
  count = c2->vertices.size();
  for(int i=0;i<count;i++)
  {
    c1->vertices.push_back(c2->vertices[i]);
  }
  count = c1->vertices.size();
  for(int i=0;i<count;i++)
  {
    comp[c1->vertices[i]] = c1->label;
  }
}
void printStatus()
{
  int size = components.size();
  printf("##Components %d##\n",size);
  for(int i=0;i<size;i++)
  {
    printf("Index:%d\n",i);
    printf("\tComponent %d:\n",components[i].label);
    printf("\tVertices: ");
    for(int j=0;j<components[i].vertices.size();j++)
    {
      printf("%d ",components[i].vertices[j]);
    }
    printf("\n");
    printf("\tEdges:\n");
    for(int j=0;j<components[i].outgoingEdges.size();j++)
    {
      printf("\t\t%d %d\n",components[i].outgoingEdges[j].src,components[i].outgoingEdges[j].dest);
    }
  }
}
int getResult()
{
  int next  = 0;
//   printStatus();
  int s = components.size();
  while(!components.empty())
  {
//     printf("%d\n",next);
    int size = components.size();
    if(next>=size)
      next%=size;
    Component *c = &components[next];
    updateOutgoingEdges(c);
    if(c->outgoingEdges.empty())
    {
      components.erase(components.begin()+next);
//       printf("#Empty Component Erase#\n");
//       printStatus();
    }
    else if(c->outgoingEdges.size()==1)
    {
      if(c->vertices.size()%2==0)
      {
	removeEdge(c->outgoingEdges[0].src,c->outgoingEdges[0].dest);
	result++;
	compCount++;
// 	printf("#Component Erase Even Vertices#\n");
	components.erase(components.begin()+next);
      }
      else
      {
	Edge *e = &(c->outgoingEdges[0]);
	int index = getComponentIndexFromVertex(e->dest);
	Component* dest = &components[index];
	removeEdge(e->src,e->dest);
	mergeComponents(c,dest);
	updateOutgoingEdges(c);
// 	dest->outgoingEdges.clear();
	components.erase(components.begin()+index);
// 	printf("#Merge#\n");
// 	printStatus();
	next++;
      }
//       printStatus();
    }
    else
    {
      next++;
    }
    if(s!=components.size())
    {
//       printStatus();
      s = components.size();
    }
  }
  return result;
}
void initialize()
{
  for(int i=1;i<=n;i++)
  {
    comp.insert(pair<int,int>(i,i));
    Component c;
    c.label = i;
    c.vertices.push_back(i);
    components.push_back(c);
  }
  for(int i=1;i<=n;i++)
    for(int j=1;j<=n;j++)
      edges[i][j] = false;
}
bool existsEdge(vector<Edge> *edges, int src, int dest)
{
  int size = (*edges).size();
  for(int i=0;i<size;i++)
  {
    if(((*edges)[i].src==src && (*edges)[i].dest==dest) || ((*edges)[i].src==dest && (*edges)[i].dest==src))
      return true;
  }
  return false;
}
int main()
{
  scanf("%d%d",&n,&m);
  int v1,v2;
  initialize();
  for(int i=0;i<m;i++)
  {
    scanf("%d%d",&v1,&v2);
    edges[v1][v2] = true;
    edges[v2][v1] = true;
    graph[v1].push_back(v2);
    graph[v2].push_back(v1);
    if(!existsEdge(&(components[v1-1].outgoingEdges),v1,v2))
      components[v1-1].outgoingEdges.push_back(Edge(v1,v2));
    if(!existsEdge(&(components[v2-1].outgoingEdges),v2,v1))
      components[v2-1].outgoingEdges.push_back(Edge(v2,v1));
  }
  printf("%d\n",getResult());
}