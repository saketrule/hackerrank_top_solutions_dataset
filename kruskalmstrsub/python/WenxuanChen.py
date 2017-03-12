import heapq

def find(x,ar):
    i=x
    while ar[i]>0:
        i=ar[i]
    return i

def union(a,b,ar):
    if ar[a]<ar[b]:
        ar[b]=a
    elif ar[a]>ar[b]:
        ar[a]=b
    else:
        ar[a]=b
        ar[b]-=1
    return ar

vertex,edge=[int(i) for i in raw_input().strip().split()]
hq,sm,kruskal=[],0,[0 for i in xrange(vertex)]
for i in xrange(edge):
    start, end, distance=[int(j) for j in raw_input().strip().split()]
    heapq.heappush(hq,[distance,start-1,end-1])
source=input()-1
while len(hq)>0:
    ar=heapq.heappop(hq)
    distance,start,end=ar[0],find(ar[1],kruskal),find(ar[2],kruskal)
    if start==end:
        continue
    else:
        kruskal=union(start,end,kruskal)
        sm+=distance 
print sm