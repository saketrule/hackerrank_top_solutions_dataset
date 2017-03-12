def prims(graph, snode):
    mstG = {}
    mst = set([snode])   
    while len(mst) != len(graph):
        edges = []
        for v in mst:
            for edge, cost in graph[v]:
                if edge not in mst:
                    edges.append((v, edge, cost))
        v, e, cost = min(edges, key=lambda t: t[2])
        mst.add(e)
        if v in mstG:
            mstG[v].add((e,cost))
        else:
            mstG[v] = set([(e,cost)])
    return mstG

n, e = [int(d) for d in raw_input().split(' ')]
graph = dict([(key, set()) for key in range(1,n+1)])
for _ in range(0,e):
    node, edge, cost = [int(d) for d in raw_input().split(' ')]
    graph[node].add((edge,cost))
    graph[edge].add((node,cost))
snode = int(raw_input())
mincost = 0
mst = prims(graph, snode)
for key in mst:
    for edge,cost in mst[key]:
        mincost+=cost
print mincost