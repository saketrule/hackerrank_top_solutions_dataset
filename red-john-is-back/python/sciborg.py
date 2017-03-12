# Enter your code here. Read input from STDIN. Print output to STDOUT
import sys
def nCk(n, k):
    if k < 0 or k > n:
        return 0
    if k > n - k: # take advantage of symmetry
        k = n - k
    c = 1
    for i in range(1,k+1):
        c = c * (n - (k - i))
        c = c // i
    return c

T = int(raw_input())
for _ in range(T):
    N = int(raw_input())
    H = int(N / 4)
    M = 1
    for h in range(1,H+1):
        #if N-4*h >= h: M += nCk(N-4*h+1,h)
        #else: M += nCk(h+1,N-4*h)
        M += nCk(N-3*h, h)
    primes = {2}
    P = 0
    for m in range(2,M+1):
        isP = True
        for i in range(2,int(m**0.5) + 1):
            if m%i == 0:
                isP = False
                break
        if isP: P += 1
    sys.stderr.write("N:%d M:%d P:%d\n"%(N,M,P))
    print P