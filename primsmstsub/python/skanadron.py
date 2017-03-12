numVerts, numEdges = map(int, raw_input().split())
edges = []
for j in range(0,numEdges):
    start, end, weight = (map(int, raw_input().split()))
    edges.append([weight, start, end])

edges.sort()
unvisited = [x for x in range(1,numVerts+1)]
startNode = input()
unvisited.remove(startNode)
weight = 0

while(len(unvisited) > 0):
    for edge in edges:
        if unvisited.count(edge[1]) + unvisited.count(edge[2]) == 1:
            weight += edge[0]
            try:
                unvisited.remove(edge[1])
            except:
                unvisited.remove(edge[2])
            break
            
print weight
        