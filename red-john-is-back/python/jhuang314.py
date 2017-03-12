# Enter your code here. Read input from STDIN. Print output to STDOUT

def isPrime(N):
    if N < 2:
        return False
    if N == 2:
        return True
    if not N & 1:
        return False
    for x in xrange(3, int(N**0.5) + 1, 2):
        if N % x == 0:
            return False
    return True

def choose(n, k):
    if 0 <= k <= n:
        ntok = 1
        ktok = 1
        for t in xrange(1, min(k, n - k) + 1):
            ntok *= n
            ktok *= t
            n -= 1
        return ntok // ktok
    else:
        return 0


T = int(raw_input())

for case in range(T):
    N = int(raw_input())

    Horiz = 0
    total = 0
    while Horiz * 4 <= N:
        Vert = N - Horiz * 4
        total += choose(Vert + Horiz, Horiz)
        Horiz +=1
    print sum (1 for x in xrange(total+1) if isPrime(x))
