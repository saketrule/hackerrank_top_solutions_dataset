import heapq
vertex,edge=[int(i) for i in raw_input().strip().split()]
adjList,visited,hq,sm=[[] for i in xrange(vertex)],set(),[],0
for i in xrange(edge):
    start, end, distance=[int(j) for j in raw_input().strip().split()]
    adjList[start-1].append((end-1,distance))
    adjList[end-1].append((start-1,distance))
source=input()-1
visited.add(source)
for i in adjList[source]:
    heapq.heappush(hq,[i[1],i[0]])
while len(hq)>0:
    ar=heapq.heappop(hq)
    distance,end=ar[0],ar[1]
    while len(hq)>0 and end in visited:
        ar=heapq.heappop(hq)
        distance,end=ar[0],ar[1]
    if end not in visited:
        visited.add(end)
        sm+=distance
        for i in adjList[end]:
            heapq.heappush(hq,[i[1],i[0]])  
print sm