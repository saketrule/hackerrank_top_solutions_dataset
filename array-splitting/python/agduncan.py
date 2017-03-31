from math import log
#from collections import Counter

def MaxScore(N,A):
    Acum = [0]*N
    Acum[0] = A[0]
    for i in xrange(N):
        Acum[i] = Acum[i-1] + A[i]
    
    if Acum[-1]==0:
        return N-1
    elif Acum[-1]%2==0:
        a = Acum[-1]/2
        while a%2==0:
            a /= 2
    else:
        return 0
    
    spl = set()
    for i in xrange(N):
        if Acum[i]%a==0:
            spl.add(Acum[i]/a)
    
    M = Acum[-1]/a
    maxd = int(log(M,2))
    d = 0
    curr = [M]
    a = M
    while d<maxd:
        a /= 2
        prev = curr
        curr = []
        for c in prev:
            ch1,ch2 = c+a,c-a
            if ch1 in spl:
                curr.append(ch1)
            if ch2 in spl:
                curr.append(ch2)
        if not curr:
            break
        d += 1
    
    return d

for _ in xrange(input()):
    _N = input()
    _A = map(int,raw_input().strip().split())
    print MaxScore(_N,_A)
