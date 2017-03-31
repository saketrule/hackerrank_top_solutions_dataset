import sys
sys.setrecursionlimit(200000)

def score(A,start,end,total):
    if not total:
        return (end-start)-1
    lsum=A[start]
    halftotal=total/2.0
    for i in range(start+1,end):
        if lsum>=halftotal:
            return 0 if lsum>halftotal else (max(score(A,start,i,halftotal),score(A,i,end,halftotal))+1)
        lsum+=A[i]
    return 0
N=input()
for i in range(N):
    l=input()
    A=[int(val) for val in raw_input().split()]
    print score(A,0,l,sum(A))