import sys
N,M=sys.stdin.readline().split()
N,M=int(N)+1,int(M)
edg=[]
for i in range(M):
    i,j,d=sys.stdin.readline().split()
    i,j,d=int(i),int(j),int(d)
    edg.append((d,i+j,i,j))
n=[set([x]) for x in range(N+1)]
edges=0
tot=0
while edges!=N-2:
    edg.sort()
    d,_,i,j=edg[0]
    del edg[0]
    if i not in n[j]:
        n[i] |= n[j]
        for ii in n[j]:
            n[ii] = n[i]
        tot+=d
        edges+=1
print tot