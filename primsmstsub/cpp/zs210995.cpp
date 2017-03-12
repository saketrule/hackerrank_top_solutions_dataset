#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>
#include <functional>
#include <limits>

#define assert(cond)    do { if (!(cond)) throw "Assert!"; } while (false)

/// @brief Set's edge "twin" edge (index of correlated edge in edge.v's neighbour list)
template<typename EdgeType>
void setTwinEdge(EdgeType& edge, int twinEdge)
{
}

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
///        - array of nodes' neighbours,
///        - node A's neighbours is a list of directed edges (A, B, cost).
///        - each bidirectional edge appears in the graph twice: as (A, B, cost), and as (B, A, cost).
template<typename Edge>
struct NeighbourListGraph
{
    NeighbourListGraph(int numberOfNodes)
        : numberOfNodes(numberOfNodes)
        , neighbours(numberOfNodes)
    {
    }

    /// @brief Returns index of the edge in edge.u neighbour's list.
    int addDirectedEdge(const Edge& edge)
    {
        assert(edge.u < numberOfNodes);
        assert(edge.v < numberOfNodes);
        neighbours[edge.u].push_back(edge);
        return neighbours[edge.u].size() - 1;
    }

    /// @brief Returns index of the added edge in edge.u neighbour's list, and index of the added edge in edge.v neighbour's list.
    std::pair<int, int> addBidirectionalEdge(const Edge& edge)
    {
        Edge twinEdge = edge;
        twinEdge.u = edge.v;
        twinEdge.v = edge.u;
        int uIdx = addDirectedEdge(edge);
        int vIdx = addDirectedEdge(twinEdge);

        setTwinEdge(neighbours[edge.u][uIdx], vIdx);
        setTwinEdge(neighbours[edge.v][vIdx], uIdx);

        return std::make_pair(uIdx, vIdx);
    }

    int numberOfNodes;  // number of nodes
    std::vector< std::vector< Edge > > neighbours;

    /// @brief Creates a transposed graph.
    /// @note  Doesn't take care of twin edges.
    NeighbourListGraph dumbTranspose() const
    {
        NeighbourListGraph transposedGraph(numberOfNodes);

        for (unsigned int n = 0; n < neighbours.size(); ++n)
        {
            for (unsigned int e = 0; e < neighbours[n].size(); ++e)
            {
                Edge edge = neighbours[n][e];
                edge.u = neighbours[n][e].v;
                edge.v = neighbours[n][e].u;
                transposedGraph.addDirectedEdge(edge);
            }
        }

        return transposedGraph;
    }
};

template<typename C = int>
std::vector< WeightedEdge<C> > mstPrim(const NeighbourListGraph< WeightedEdge<C> >& graph)
{
    std::vector< WeightedEdge<C> > acceptedEdges;
    std::priority_queue< WeightedEdge<C>, std::vector< WeightedEdge<C> >, std::greater< WeightedEdge<C> > > workingEdges;
    std::vector<bool> visited(graph.numberOfNodes);
    std::vector<int> minDistances(graph.numberOfNodes, std::numeric_limits<int>::max());

    visited[0] = true;
    minDistances[0] = 0;
    for (size_t i = 0; i < graph.neighbours[0].size(); ++i)
    {
        workingEdges.push(graph.neighbours[0][i]);
    }

    int numberOfNodesInMST = 1;
    while (numberOfNodesInMST < graph.numberOfNodes)
    {
        WeightedEdge<C> edge = workingEdges.top();
        workingEdges.pop();

        if (visited[edge.v])
            continue;

        visited[edge.v] = true;
        for (size_t i = 0; i < graph.neighbours[edge.v].size(); ++i)
        {
            WeightedEdge<C> newEdge = graph.neighbours[edge.v][i];
            if (newEdge.cost > minDistances[newEdge.v])
                continue;
            minDistances[newEdge.v] = newEdge.cost;
            workingEdges.push(newEdge);
        }

        acceptedEdges.push_back(edge);
        numberOfNodesInMST++;
    }

    return acceptedEdges;
}

template<typename C = int>
int mstPrimCost(const NeighbourListGraph< WeightedEdge<C> >& graph)
{
    std::vector< WeightedEdge<C> > acceptedEdges = mstPrim(graph);

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

    NeighbourListGraph< WeightedEdge<int> > neighbourListGraph(N);

    for (int i = 0; i < M; ++i)
    {
        int p, k, w;
        std::cin >> p >> k >> w;
        p--;
        k--;

        neighbourListGraph.addBidirectionalEdge( WeightedEdge<int>(p, k, w) );
    }

    int S; // start node (what is this for?)
    std::cin >> S;
    S--;
    
    int primCost = mstPrimCost(neighbourListGraph);
    std::cout << primCost << std::endl;

    return 0;
}
