def prim(G, source):
    dist = [float("inf")] * len(G)
    dist[source] = 0
    intree = [False] * len(G)
    cost = 0
    while True:
        minw = float("inf")
        minind = -1
        for i in xrange(len(G)):
            if intree[i] == False:
                if dist[i] < minw:
                    minw = dist[i]
                    minind = i
        if minind == -1:
            break
        cost += minw
        intree[minind] = True
        for n, d in G[minind].iteritems():
            dist[n] = min(dist[n], d)
    return cost
                

N, M = map(int, raw_input().split())
G = {i: {} for i in xrange(N)}
for _ in xrange(M):
    x, y, w = map(int, raw_input().split())
    x -= 1
    y -= 1
    try:
        minw = min(G[x][y], w)
        G[x][y] = minw
        G[y][x] = minw
    except KeyError:
        G[x][y] = w
        G[y][x] = w
source = input() - 1
print prim(G, source)
        