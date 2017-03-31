from bisect import bisect

N = input()
A = sorted(map(int, raw_input().split()))
P,Q = map(int, raw_input().split())

def valid(M):
    if M>=P and M<=Q:
        return True
    return False

# generate list of candidates
M=set()
for i in xrange(N-1):
    mean,odd=divmod(A[i+1]-A[i],2)
    if valid(A[i]+mean):
        M.add(A[i]+mean) 
    if odd and valid(A[i+1]-mean):
        M.add(A[i+1]-mean)
M.add(P)
M.add(Q)

M = sorted(M)
min_m,min_val=-1,-1
for m in M:
    i=bisect(A,m)
    if i==0:
        x=A[0]-m
    elif i==N:
        x=m-A[-1]
    else:
        x=min(m-A[i-1],A[i]-m)
    if x>min_val:
        min_val=x
        min_m=m
print min_m
