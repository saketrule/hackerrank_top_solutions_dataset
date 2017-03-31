def max_min(A, P, Q):
    n = len(A)
    A = sorted(A)
    val = float('-inf')
    M = None
    for i in range(1, n):
        m = (A[i-1] + A[i]) / 2
        d = m - A[i-1]
        if d > val and m >= P and m <= Q:
            val = d
            M = m
    mP = min(abs(a - P) for a in A)
    if mP >= val:
        val = mP
        M = P
    mQ = min(abs(a - Q) for a in A)
    if mQ > val:
        val = mQ
        M = Q
    return M
    

N = input()
A = [int(s) for s in raw_input().split()]
P, Q = [int(s) for s in raw_input().split()]
print(max_min(A, P, Q))