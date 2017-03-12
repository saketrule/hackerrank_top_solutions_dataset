N,M = map(int,raw_input().split())
if N%2:
    print 0
    exit()
A = [[] for i in xrange(N+1)]
for i in xrange(M):
    u,v = map(int,raw_input().split())
    A[u].append(v)
    A[v].append(u)
def f(r,p=-1):
    C = [f(c,r) for c in A[r] if c!=p]
    s = 1+sum(c[0] for c in C)
    x = sum(c[1] for c in C)
    return s,x+(s%2==0)

print f(1)[1]-1
