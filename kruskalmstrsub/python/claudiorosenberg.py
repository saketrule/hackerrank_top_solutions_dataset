# Enter your code here. Read input from STDIN. Print output to STDOUTimport numpy

import Queue

def r2i():
    l = raw_input().split(' ')
    return (int(l[0]),int(l[1]))    

def r3i():
    l = raw_input().split(' ')
    return (int(l[0]),int(l[1]), int(l[2]))    

nn, ne = r2i()
lt = {}
ds = {}
vs = {}
for k in range(nn):
    lt[k] = []
mx = 0
for k in range(ne):
    i, j, r = r3i()
    lt[i-1].append((r, j-1))
    lt[j-1].append((r, i-1))
    if r>mx:
        mx=r
mx=mx**2
for k in range(nn):
    vs[k] = 0
s=int(raw_input())-1
q = Queue.PriorityQueue()
for k in range(nn):
    if s == k:
        ds[k]=0
    else:
        ds[k]=mx
    q.put((ds[k], k))
wt=0
while (q.empty() == False):
    (p, u) = q.get()
    if (vs[u] == 1):
        continue
    vs[u] = 1
#    print p, u, ds, vs
    wt = wt + p
    for (r, v) in lt[u]:
        if r < ds[v]:
            ds[v] = r
            q.put((r, v))
print wt