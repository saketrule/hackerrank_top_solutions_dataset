import Queue

def r2i():
    l = raw_input().split(' ')
    return (int(l[0]),int(l[1]))    

def r3i():
    l = raw_input().split(' ')
    return (int(l[0]),int(l[1]), int(l[2]))    


nn, ne = r2i()
lt = {}
mx = 100000000
for k in range(nn):
    lt[k] = []
for k in range(ne):
    i, j, r = r3i()
    lt[i-1].append((r, j-1))
    lt[j-1].append((r, i-1))
s=int(raw_input())-1
q = Queue.PriorityQueue()
se = []
sn = set([s])
ds = {}
for v in range(nn):
    if v!=s:
        q.put((mx, v))
        ds[v]=mx
for (w, v) in lt[s]:
    if (w < ds[v]):
        q.put((w, v))
        ds[v]=w
#print ds
while (len(sn)<nn):
    (w, u) = q.get()
    if (u in sn):
        continue;
#    print (w,u)
    se.append(w)
    sn.add(u)
    for (w, v) in lt[u]:
        if (v not in sn and w < ds[v]):
            q.put((w, v))
            ds[v]=w
print sum(se)