#!/usr/bin/env python

def edgeweight(edgenum, nearvertex):
  # determine far vertex
  farvertex = edges[edgenum][0] if edges[edgenum][1] == nearvertex else edges[edgenum][1]
  # determine all edges connected to far vertex excluding EDGE
  nearedges = []
  for e in enumerate(edges):
    if (e[1][0] == farvertex or e[1][1] == farvertex) and e[1][0] != nearvertex and e[1][1] != nearvertex:
      nearedges.append(e[0])
  # if no other edges: memoize and return 1
  # else: memoize and return 1 + sum of weights of connected edges
  if len(nearedges) == 0:
    weights[edgenum] = 1
    return 1
  else:
    w = 1 + sum([edgeweight(e, farvertex) for e in nearedges])
    weights[edgenum] = w
    return w

# input number of edges
N = int(raw_input().split()[1])

# input edge tuples
edges = []
for i in range(N):
  a,b = raw_input().split()
  edges.append((int(a),int(b)))

weights = [0] * N

# find all edges connected to node 1
connectededges = []
for e in enumerate(edges):
  if e[1][0] == 1 or e[1][1] == 1:
    connectededges.append(e[0])

# calculate weight from 1 for connected each edge
for e in connectededges:
  edgeweight(e, 1)

# output the number of even weighted edges
print len(filter(lambda x: x % 2 == 0, weights))