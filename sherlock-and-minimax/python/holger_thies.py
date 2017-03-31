
def findM(A, P, Q):
    A.sort()   
    B = [x for x in A if x>=P and x <= Q]
    beforeB = -1
    afterB = -1
    for i in range(len(A)-1):
        if A[i] < P and A[i+1] >= P:
            beforeB = A[i]
        if A[i] <= Q and A[i+1] > Q:
            afterB = A[i+1]
    if len(B) == 0 and afterB == -1:
        return Q
    if beforeB > -1: B = [beforeB]+B
    if afterB > -1:  B = B+[afterB]

    M = P
    d = min(abs(P-B[0]), B[1]-P)
    for i in range(len(B)-1):
        d0 = (B[i+1]-B[i])//2
        if d0 > d and B[i]+d0>=P and B[i]+d0<=Q:
            d = d0
            M = B[i]+d
    d1 = min(abs(B[-1]-Q), Q-B[-2])
    if d1>d:
        M = Q
    return M

N = input()
A = map(int, raw_input().split(" "))
P, Q = map(int, raw_input().split(" "))
print findM(A,P,Q)
