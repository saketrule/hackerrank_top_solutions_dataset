from collections import defaultdict
from heapq import *


def prim_weight(graph, v_start):
    q, visited = [(0, v_start)], set()
    mst_weight = 0
    while q:
        w, currentnode = heappop(q)
        if currentnode not in visited:
            mst_weight += w
        visited.add(currentnode)
        for n in graph[currentnode]:
            if n in visited:
                continue
            heappush(q, (graph[currentnode][n], n))
    return mst_weight


V, E = map(int, raw_input().split(' '))
graph = defaultdict(dict)
for e in xrange(E):
    u, v, w = map(int, raw_input().split(' '))
    graph[u][v] = graph[v][u] = w
v_start = int(raw_input())
print prim_weight(graph, v_start)
