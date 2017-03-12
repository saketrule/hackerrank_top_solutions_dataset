#include <cmath>
#include <cstdio>
#include <vector>
#include <list>
#include <set>
#include <iostream>
#include <algorithm>
#include <fstream>
#include <iomanip>
#ifdef RR_VS
	#include "Header.h"
	#include <conio.h>
#endif
using namespace std;

///============================================================

typedef unsigned int uint;
typedef unsigned long ulong;
typedef unsigned long long ulonglong;

class ExceptionBase {};
class ApplicationEx: public ExceptionBase {};


namespace RRGraph
{
	class UnionFind {
	private:
		struct Node {
			uint   prev, level;
			Node() :prev(0), level(0){}
		};
		const uint N;
		uint count;
		std::vector<Node> Z;

		uint Find(uint v) {
			if (Z[v].prev == v) return v;

			Z[v].prev = Find(Z[v].prev);
			return Z[v].prev;
		}

	public:
		UnionFind(uint n):N(n),Z(n),count(n) {
			for (uint i = 0; i < N; ++i)  
				Z[i].prev=i; 
		}

		bool isConnected(uint v1, uint v2){ return Find(v1)==Find(v2);}

		void Join(uint v1, uint v2){
			uint v1Root = Find(v1);
			uint v2Root = Find(v2);
			if (v1Root == v2Root) return;

			--count;

			if (Z[v1Root].level > Z[v2Root].level)
			{
				Z[v2Root].prev = v1Root;
				return;
			}
			Z[v1Root].prev = v2Root;
			if (Z[v1Root].level != Z[v2Root].level) return;
			Z[v2Root].level++;
		}

		uint Count() {return count;}
	};

enum class GraphType{Directed, UnDirected};
	
	template <typename T>
	class GraphBase
	{
	public:
		GraphBase(GraphType type):graphType(type){}
		GraphBase(uint v, GraphType type):graphType(type){}
		virtual ~GraphBase(){}

		const T& GetDataRefConst() const {return data;}
		T& GetDataRef() {return data;}
		GraphType GetGraphType() const {return graphType;}
		uint GetV() const {return data.size();}

	protected:
		GraphBase(const GraphBase& b):graphType(b.graphType),data(b.data){}
		GraphType graphType;
		T data;
	};

	struct Connection3ui_t {
		uint v1, v2;
		uint cost;
		Connection3ui_t(uint m1, uint m2, uint cost_) :v1(m1), v2(m2), cost(cost_) {}
		inline bool operator<(const Connection3ui_t &other) const { 
			if (cost!=other.cost)
				return (cost<other.cost); 
			return (v1+v2+cost<other.cost+other.v1+other.v2);
		}
	};

	struct Connection2ui_t {
		uint Dst;
		uint Cost;
		Connection2ui_t(uint dst, uint cost_) : Dst(dst), Cost(cost_) {}
		inline bool operator<(const Connection2ui_t &other) const { return (Cost>other.Cost); }
	};

	typedef  std::vector<Connection3ui_t> EdgeListDataType;
	class EdgeList: public GraphBase<EdgeListDataType>
	{
	public:
		EdgeList(GraphType type,bool readCost);
		EdgeList Clone() const {return EdgeList(*this);}
		uint GetE() const {return data.size();}
		uint GetV() const {return V;}

	protected:
		EdgeList(GraphType type);
		uint V;
	};

	EdgeList::EdgeList(GraphType type,bool readCost):GraphBase(type)
	{
		uint v,e;
		cin >> v >> e;
		data.reserve(e);

		for (uint i=0 ; i<e ; ++i)
		{
			uint v1,v2,c=1;
			cin >> v1 >> v2;
			if (readCost)
				cin >> c;

			data.push_back(Connection3ui_t(v1-1,v2-1,c));
		}
		V = v;
	}

	
	class Kruskal
	{
	public:
		ulong Run(EdgeList& el) const
		{
			auto &edgesVec = el.GetDataRef();
			uint V = el.GetV(),E=el.GetDataRefConst().size();

			std::sort(edgesVec.begin(), edgesVec.end());

			UnionFind connectionMap(V);
			uint AcceptedEdges = 0, position = 0, Wynik = 0;

			while (position<E)
			{
				if (AcceptedEdges == V-1) break;
				auto& C = edgesVec[0];
				do {
					C = edgesVec[position++];
				} while (connectionMap.isConnected(C.v1,C.v2));
				Wynik += C.cost;
				connectionMap.Join(C.v1, C.v2);
				AcceptedEdges++;
			}
			if (AcceptedEdges != V-1) throw ApplicationEx();
			return Wynik;
		}
	private:

	};
}


////====================================


using namespace RRGraph;


static int solutionFunc() 
{
#ifdef RR_VS
	freopen("E:/W_Trakcie/zawieszone/Zadania/App/Dane/HackerRank/Kruskal.txt", "r", stdin);
#endif  

	EdgeList el(GraphType::Directed,true);
	const Kruskal Alg;
	ulong wynik = Alg.Run(el);

	cout << wynik;

	
	return 0;
}

#ifdef RR_VS
int simpleSolution(){	solutionFunc(); _getche(); return 0; }
#else  
int main(int argc, char** argv) {	solutionFunc(); }
#endif  



