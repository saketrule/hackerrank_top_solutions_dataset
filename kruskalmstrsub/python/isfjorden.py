""" Single-source MST calculation for graphs without negative-weight edges

Test case:
5 7
1 2 20
1 3 50
1 4 70
1 5 90
2 3 30
3 4 40
4 5 60
2

-> 150
"""

INFTY = 1<<20

n = 0 # no nodes
m = 0 # no edges

def kruskal(source):
    # initialization of MST (A in CLRS) and the nodes' sets
    # MST: a list or set of edges in the MST, initially empty
    MST = []
    # the nodes' sets: implemented here as simple colours from {0..n-2}, colour[u], initially distinct
    # find-set(u) = colour[u], union of sets = make colour[v] <- colour[u]
    colour = [i for i in range(n)]

    # process the sets
    edges.sort(key=lambda x: x[2]) # sort the edges by weight
    # print edges

    for (u, v, w) in edges:
	# print colour
	if colour[u] != colour[v]:
	    MST.append((u, v, w))
	    # set union on the colours of u, v: change the colour of the v-set into that of the u-set
	    # print "changing colour " + str(colour[v]) + " into " + str(colour[u])
	    old_colour = colour[v]
	    for i in range(0, u) + range(u+1, n):
		if colour[i] == old_colour:
		    colour[i] = colour[u]

    return MST

# INPUT
n, m = map(int, raw_input().strip().split())
#graph = [ {} for i in range(n) ] # graph as adjacency list (list of hash tables);
                                  # an element in the hash graph[u] has type v:weight_uv
edges = []

# undirected edges given in input
for i in range(m):
    # edge: (u, v, w), undirected
    (u, v, w) = map(int, raw_input().strip().split())
    edges.append((u-1, v-1, w))

source = input() - 1

MST = kruskal(source)
# print MST

# compute the cost of the MST
cost_MST = 0
for i in MST:
    cost_MST += i[2]
print cost_MST
