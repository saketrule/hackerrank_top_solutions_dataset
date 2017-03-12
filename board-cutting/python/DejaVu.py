# Enter your code here. Read input from STDIN. Print output to STDOUT
t = int(raw_input())
for k in range(t):
    m, n = [int(i) for i in raw_input().split()]
    a = [int(i) for i in raw_input().split()]
    b = [int(i) for i in raw_input().split()]
    m -= 1
    n -= 1
    a.sort()
    b.sort()
    c1 = 1
    c2 = 1
    s = 0
    i = m-1
    j = n-1
    while i >= 0 and j >= 0:
        if a[i] >= b[j]:
            s += a[i]*c2
            c1 += 1
            i -= 1
        else:
            s += b[j]*c1
            c2 += 1
            j -= 1
    while i >= 0:
        s += a[i]*c2
        c1 += 1
        i -= 1
    while j >= 0:
        s += b[j]*c1
        c2 += 1
        j -= 1
    print s%(1000000007)