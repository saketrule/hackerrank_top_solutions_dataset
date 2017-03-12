# Enter your code here. Read input from STDIN. Print output to STDOUT
def count(G, s):
    Q, P = [s], set()
    while Q:
        u = Q.pop()
        if u in P:
            continue
        P.add(u)
        for v in G[u]:
            if v in P:continue
            Q.append(v)
    return len(P)

def checkEdge(G, a, b):
    G[a].remove(b)
    G[b].remove(a)
    n = count(G, a)
    #m = count(G, b)
    G[a].add(b)
    G[b].add(a)
    if n%2 == 0:
        return 1
    else:
        return 0

         
t = raw_input().split(' ')
nt, nc, cut = 1, 2, 3  #not test, not a cut, a cut
N, M = int(t[0]), int(t[1])
link = [[0 for i in range(N+1)] for i in range(N+1)]
G = [set() for i in range(N+1)]
L = []
for i in range(M):
    t = raw_input().split(' ')
    a, b = int(t[0]), int(t[1])
    link[a][b] = link[b][a] = nt
    G[a].add(b)
    G[b].add(a)
    L.append([a, b])

num = 0
for i in range(M):
    a, b = L[i][0], L[i][1]
    num += checkEdge(G, a, b)
print(num)