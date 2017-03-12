import sys
N,M=sys.stdin.readline().split()
N,M=int(N)+1,int(M)
g=[[] for i in range(N)]
for i in range(M):
    i,j,d=sys.stdin.readline().split()
    i,j,d=int(i),int(j),int(d)
    g[i].append((j,d))
    g[j].append((i,d))
s=int(sys.stdin.readline())
edg=[]
nodes=set([s])
tot=0
for e in g[s]:
    edg.append((e[1],s,e[0]))
while len(nodes)!=N-1:
    edg.sort()
    c=next(e for e in edg if e[2] not in nodes)
    tot=tot+c[0]
    for e in g[c[2]]:
        if e[0] not in nodes:
            edg.append((e[1],c[2],e[0]))
    nodes.add(c[2])
print tot