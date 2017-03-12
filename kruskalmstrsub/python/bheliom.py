# Enter your code here. Read input from STDIN. Print output to STDOUT
def read_weighted_edges(N, M):
    graph = {'nodes': [], 'edges': set(), }
    graph['nodes'] = [k+1 for k in xrange(N)]

    for _ in xrange(M):
        a, b, w = [int(x) for x in raw_input().split()]
        graph['edges'].add((a, b, w))
        graph['edges'].add((b, a, w))
    return graph

def kruskal_mst(graph, start):
    edges = sorted(graph['edges'], key=lambda x: x[2])
    parent = {node: node for node in graph['nodes']}
    rank = {node: 0 for node in graph['nodes']}
    mst = set()

    def find_parent(node):
        if parent[node] != node:
            parent[node] = find_parent(parent[node])
        return parent[node]

    def union(node1, node2):
        root1 = find_parent(node1)
        root2 = find_parent(node2)
        if root1 != root2:
            if rank[root1] > rank[root2]:
                parent[root2] = root1
            else:
                parent[root1] = root2
                if rank[root1] == rank[root2]:
                    rank[root2] += 1
    cost = 0
    for edge in edges:
        node1, node2, weight = edge
        if find_parent(node1) != find_parent(node2):
            union(node1, node2)
            mst.add(edge)
            cost += weight
    return cost

N, M = raw_input().split()
graph = read_weighted_edges(int(N), int(M))
S = input()
print kruskal_mst(graph, S)