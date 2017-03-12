import heapq

N,M = map(int,raw_input().split())
edges = {}
adj = {}

for m in range(M):
    x,y,r = map(int,raw_input().split())
    
    if (edges.has_key((x,y)) and edges[(x,y)] > r) or not edges.has_key((x,y)):
        edges[(x,y)] = r
        
    if (edges.has_key((y,x)) and edges[(y,x)] > r) or not edges.has_key((y,x)):
        edges[(y,x)] = r
        
S = int(raw_input())        
        
for edge in edges.keys():
    x,y,r = edge[0],edge[1],edges[edge]
    
    if adj.has_key(x):
        adj[x].append((r,y))
    else:
        adj[x] = [(r,y)] 

V = set([S])        
queue = adj[S]
heapq.heapify(queue)
weight = 0

while len(V) < N:
    r,y = heapq.heappop(queue)
    
    if y not in V:
        weight += r
        V.add(y)
        
        for e in adj[y]:
            heapq.heappush(queue,e)
            
print weight
        
    
    
     
    