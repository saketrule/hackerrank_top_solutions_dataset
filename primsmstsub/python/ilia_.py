# Enter your code here. Read input from STDIN. Print output to STDOUT

from collections import defaultdict
from heapq import *

n,m = [int(i) for i in raw_input().strip().split()]


nodes = range(1,n+1)

edges = []
for i in range(0,m):
    s,e,p = [int(j) for j in raw_input().strip().split()]
    edges.append((s,e,p))


start = input()

def prim( nodes, edges, start ):
    conn = defaultdict( list )
    for n1,n2,c in edges:
        conn[ n1 ].append( (c, n1, n2) )
        conn[ n2 ].append( (c, n2, n1) )

    mst = []
    used = set( [start] )
    usable_edges = conn[ start ][:]
    heapify( usable_edges )

    while usable_edges:
        cost, n1, n2 = heappop( usable_edges )
        if n2 not in used:
            used.add( n2 )
            mst.append( ( n1, n2, cost ) )

            for e in conn[ n2 ]:
                if e[ 2 ] not in used:
                    heappush( usable_edges, e )
    return mst


print sum([x[2] for x in prim( nodes, edges, start )])
