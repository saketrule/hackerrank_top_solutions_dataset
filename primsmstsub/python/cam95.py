# Enter your code here. Read input from STDIN. Print output to STDOUT

import Queue as qu
import heapq as hp
import sys
from collections import defaultdict

def prims(nodes,edges,start):
    mst = []
    mstd = 0
    used = set([start])
    usable = graph[start]#[:]
    hp.heapify(usable)
    
    while usable:
        cost, n1, n2 = hp.heappop(usable)
        if n2 not in used:
            used.add(n2)
            mst.append((n1,n2,cost))
            mstd += cost
            
            for i in graph[n2]:
                if i[2] not in used:
                    #print i
                    hp.heappush(usable, i)
    return mstd

graph = defaultdict(list)
nodes = []

N, M = map (int, raw_input().split())

for i in range(N):
    v = str(i+1)
    nodes.append(v)

for _ in range(M):
    A, B, C = map(int, raw_input().split())
    graph[str(A)].append((C, str(A), str(B)))
    graph[str(B)].append((C, str(B), str(A)))
    
start = str(input())

print prims(nodes, graph, start)

    