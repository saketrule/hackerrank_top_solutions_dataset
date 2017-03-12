#!/usr/bin/python
import sys

def get_input(f):
  N, M = f.readline().split()
  N = int(N)

  edges = [0] * (N + 1)
  children = [0] * (N + 1)
  for line in f.readlines():
    f, t = line.split()
    f, t = int(f), int(t)
    edges[f] = t
    children[t] += 1

  return N, edges, children

def resolve(cuts, C, N, edge, children):
  for i in xrange(1, N + 1):
    if children[i] != 0:
      continue
    parent = edge[i]
    children[parent] -= 1
    children[i] = -1
    if C[i] % 2 != 0:
      C[parent] += C[i]
    else:
      cuts += 1
  return cuts

N, edge, children = get_input(sys.stdin)
C = [1] * (N + 1)
cuts = 0
while children[1] != 0:
  cuts = resolve(cuts, C, N, edge, children)
print cuts
