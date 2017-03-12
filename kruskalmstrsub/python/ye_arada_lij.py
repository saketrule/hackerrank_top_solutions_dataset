
    
#=======================================================================
# Union-Find
#=======================================================================

class ArrayUnionFind:
    """Holds the three "arrays" for union find"""
    def __init__(self, S):
        self.group = dict((s,s) for s in S) # group[s] = id of its set
        self.size = dict((s,1) for s in S) # size[s] = size of set s
        self.items = dict((s,[s]) for s in S) # item[s] = list of items in set s
        
def make_union_find(S):
    """Create a union-find data structure"""
    return ArrayUnionFind(S)
    
def find(UF, s):
    """Return the id for the group containing s"""
    return UF.group[s]

def union(UF, a,b):
    """Union the two sets a and b"""
    assert a in UF.items and b in UF.items
    # make a be the smaller set
    if UF.size[a] > UF.size[b]:
        a,b = b,a
    # put the items in a into the larger set b
    for s in UF.items[a]:
        UF.group[s] = b
        UF.items[b].append(s)
    # the new size of b is increased by the size of a
    UF.size[b] += UF.size[a]
    # remove the set a (to save memory)
    del UF.size[a]
    del UF.items[a]

#=======================================================================
# Kruskal MST
#=======================================================================

def kruskal_mst(G):
    """Return a minimum spanning tree using kruskal's algorithm"""
    # sort the list of edges in G by their length
    Edges = [(u, v, G[u][v]['length']) for u,v in G.edges()]
    Edges.sort(cmp=lambda x,y: cmp(x[2],y[2]))

    UF = make_union_find(G.nodes())  # union-find data structure

    # for edges in increasing weight
    mst = [] # list of edges in the mst
    for u,v,d in Edges:
        setu = find(UF, u)
        setv = find(UF, v)
        # if u,v are in different components
        if setu != setv:
            mst.append((u,v))
            union(UF, setu, setv)
    return mst

class Graph:
    def __init__(self):
        self.edgs = {}
    def __getitem__(self,i):
        return self.edgs[i]
    def edges(self):
        e = set()
        for i in self.edgs.keys():
            for j in self.edgs[i]:
                e.add(tuple(sorted((i,j))))
        return list(e)
    def nodes(self):
        return self.edgs.keys()



N , M = map(int,raw_input().split())
G = Graph()
for i in range(M):
    x , y , r = map(int,raw_input().split())
    if x in G.edgs:
        G.edgs[x][y] = {'length':r}
    else:
        G.edgs[x] = {y:{'length':r}}
    if y in G.edgs:
        G.edgs[y][x] = {'length':r}
    else:
        G.edgs[y] = {x:{'length':r}}
S = input() 
mst = kruskal_mst(G)
answer = 0
for a,b in mst:
    answer += G[a][b]['length']
print answer
    

    
#=======================================================================
# Union-Find
#=======================================================================

class ArrayUnionFind:
    """Holds the three "arrays" for union find"""
    def __init__(self, S):
        self.group = dict((s,s) for s in S) # group[s] = id of its set
        self.size = dict((s,1) for s in S) # size[s] = size of set s
        self.items = dict((s,[s]) for s in S) # item[s] = list of items in set s
        
def make_union_find(S):
    """Create a union-find data structure"""
    return ArrayUnionFind(S)
    
def find(UF, s):
    """Return the id for the group containing s"""
    return UF.group[s]

def union(UF, a,b):
    """Union the two sets a and b"""
    assert a in UF.items and b in UF.items
    # make a be the smaller set
    if UF.size[a] > UF.size[b]:
        a,b = b,a
    # put the items in a into the larger set b
    for s in UF.items[a]:
        UF.group[s] = b
        UF.items[b].append(s)
    # the new size of b is increased by the size of a
    UF.size[b] += UF.size[a]
    # remove the set a (to save memory)
    del UF.size[a]
    del UF.items[a]

#=======================================================================
# Kruskal MST
#=======================================================================

def kruskal_mst(G):
    """Return a minimum spanning tree using kruskal's algorithm"""
    # sort the list of edges in G by their length
    Edges = [(u, v, G[u][v]['length']) for u,v in G.edges()]
    Edges.sort(cmp=lambda x,y: cmp(x[2],y[2]))

    UF = make_union_find(G.nodes())  # union-find data structure

    # for edges in increasing weight
    mst = [] # list of edges in the mst
    for u,v,d in Edges:
        setu = find(UF, u)
        setv = find(UF, v)
        # if u,v are in different components
        if setu != setv:
            mst.append((u,v))
            union(UF, setu, setv)
    return mst

class Graph:
    def __init__(self):
        self.edgs = {}
    def __getitem__(self,i):
        return self.edgs[i]
    def edges(self):
        e = set()
        for i in self.edgs.keys():
            for j in self.edgs[i]:
                e.add(tuple(sorted((i,j))))
        return list(e)
    def nodes(self):
        return self.edgs.keys()



N , M = map(int,raw_input().split())
G = Graph()
for i in range(M):
    x , y , r = map(int,raw_input().split())
    if x in G.edgs:
        G.edgs[x][y] = {'length':r}
    else:
        G.edgs[x] = {y:{'length':r}}
    if y in G.edgs:
        G.edgs[y][x] = {'length':r}
    else:
        G.edgs[y] = {x:{'length':r}}
S = input() 
mst = kruskal_mst(G)
answer = 0
for a,b in mst:
    answer += G[a][b]['length']
print answer
    
