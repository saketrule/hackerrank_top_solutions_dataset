# Enter your code here. Read input from STDIN. Print output to STDOUT
from itertools import combinations

t = int(raw_input().strip())
for i in xrange(t):
    a = input()
    b = map(int, raw_input().split())

    score = 0

    c = {b[0]:0}
    for i in xrange(1,a):
        b[i] += b[i-1]
        c[b[i]] = i
    d = {0:True}      

    if b[-1]==0:        
        score = a-1
        
    elif b[-1]%2==0:
        e = b[-1]/2
        while d:
            for a in d.keys():
                if (a+e) in c:
                    d[a+e] = True
                else:
                    del d[a]
            if d:    
                score += 1
            if e%2==0:
                e /= 2
            else:
                d = None

    print score
