#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>
#include <functional>
#include <limits>

#define assert(cond)    do { if (!(cond)) throw "Assert!"; } while (false)

class FindUnion
{
    struct FindUnionNode
    {
        /// @brief This constructs a root node.
        FindUnionNode(int id)
            : m_parent(id)
            , m_rank(0)
        {}

        int m_parent;   ///< Identifier of the set representant.
        int m_rank;     ///< Upper bound of the height of the tree below us (single node has rank 0). This would be the height of the tree below us, if we weren't doing path compression.
    };

private:
    std::vector<FindUnionNode> m_nodes;  ///< Collection of nodes.

public:
    FindUnion(int numberOfElements)
    {
        m_nodes.reserve(numberOfElements);
        for (int i = 0; i < numberOfElements; ++i)
        {
            m_nodes.push_back(FindUnionNode(i));
        }
    }

    int findRoot(int element)
    {
        FindUnionNode& node = m_nodes[element];
        if (node.m_parent == element)
            return element;   // we are the root

        // compress the path (we don't update rank)
        int root = findRoot(node.m_parent);
        node.m_parent = root;
        return root;
    }

    void join(int element0, int element1)
    {
        int root0 = findRoot(element0);
        int root1 = findRoot(element1);

        // if we already are in the same set - nothing to do.
        if (root0 == root1)
            return;

        // if the ranks are equal - bind one tree to the other, and bump the rank
        if (m_nodes[root0].m_rank == m_nodes[root1].m_rank)
        {
            m_nodes[root1].m_parent = root0;
            m_nodes[root0].m_rank++;
        }

        // if the ranks are not equal - bind tree with lower rank to one with higher rank
        if (m_nodes[root0].m_rank > m_nodes[root1].m_rank)
        {
            m_nodes[root1].m_parent = root0;
        }
        else
        {
            m_nodes[root0].m_parent = root1;
        }
    }
};

struct Edge
{
    Edge(int u, int v)
        : u(u), v(v)
    {
    }

    // we could add edge id here to be able to match edges with some other data
    int u;      // start of edge
    int v;      // end of edge
};

template<typename C = int>
struct WeightedEdge : Edge
{
    WeightedEdge(int u, int v, C cost)
        : Edge(u, v)
        , cost(cost)
    {}

    // we could add edge id here to be able to match edges with some other data
    C cost;     // "cost" of edge

    bool operator<(const WeightedEdge& other) const
    {
        return this->cost < other.cost;
    }

    bool operator>(const WeightedEdge& other) const
    {
        return this->cost > other.cost;
    }
};

/// @brief Graph is represented as:
///        - number of nodes,
///        - list of undirected edges (begin, end, cost).
template<typename C = int>
struct EdgeListGraph
{
    EdgeListGraph(int numberOfNodes)
        : numberOfNodes(numberOfNodes)
    {
    }

    void addEdge(int u, int v, C cost)
    {
        assert(u < numberOfNodes);
        assert(v < numberOfNodes);
        edges.push_back(WeightedEdge<C>(u, v, cost));
    }

    int numberOfNodes;  // number of nodes
    std::vector< WeightedEdge<C> > edges;
};

template<typename C = int>
std::vector< WeightedEdge<C> > mstKruskal(const EdgeListGraph<C>& graph)
{
    FindUnion FindUnion(graph.numberOfNodes);
    std::vector< WeightedEdge<C> > sourceEdges(graph.edges.begin(), graph.edges.end());
    std::vector< WeightedEdge<C> > acceptedEdges;

    std::sort(sourceEdges.begin(), sourceEdges.end());

    int currentEdge = 0;
    int numberOfNodesInMST = 1;
    while (numberOfNodesInMST < graph.numberOfNodes)
    {
        WeightedEdge<C> edge = sourceEdges[currentEdge];
        currentEdge++;

        int root0 = FindUnion.findRoot(edge.u);
        int root1 = FindUnion.findRoot(edge.v);
        if (root0 == root1)
            continue;

        FindUnion.join(root0, root1);
        acceptedEdges.push_back(edge);
        numberOfNodesInMST++;
    }

    return acceptedEdges;
}

template<typename C = int>
int mstKruskalCost(const EdgeListGraph<C>& graph)
{
    std::vector< WeightedEdge<C> > acceptedEdges = mstKruskal(graph);

    int cost = 0;
    for (auto edge : acceptedEdges)
    {
        cost += edge.cost;
    }

    return cost;
}

int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */   

    int N; // node count
    int M; // edge count
    std::cin >> N;
    std::cin >> M;

    EdgeListGraph<int> edgeListGraph(N);

    for (int i = 0; i < M; ++i)
    {
        int p, k, w;
        std::cin >> p >> k >> w;
        p--;
        k--;

        edgeListGraph.addEdge(p, k, w);
    }

    int S; // start node (what is this for?)
    std::cin >> S;
    S--;
    
    int kruskalCost = mstKruskalCost(edgeListGraph);
    std::cout << kruskalCost << std::endl;

    return 0;
}
