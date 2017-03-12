# Enter your code here. Read input from STDIN. Print output to STDOUT
import Queue

def find(v):
    if rep[v] == v:
        return v
    rep[v] = find(rep[v])
    return rep[v]

n,m = map(int, raw_input().split())
pq = Queue.PriorityQueue()
rep = {}
for _ in xrange(m):
    x,y,r = map(int, raw_input().split())
    pq.put((r, (x,y)))
    if x not in rep:
        rep[x] = x
    if y not in rep:
        rep[y] = y

raw_input()    

res = 0
while pq.qsize() > 0:
    a = pq.get()
    r = a[0]
    u, v = a[1]
    p_v, p_u = find(v), find(u)
    if p_v != p_u:
        res += r
        rep[p_v] = p_u

print res