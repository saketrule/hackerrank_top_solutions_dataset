# Enter your code here. Read input from STDIN. Print output to STDOUT

import sys
sys.setrecursionlimit(10**6)

read_ints = lambda: [int(x) for x in raw_input().split()]

n, m = read_ints()
uf = range(n)

def find(i):
    if uf[i] == i:
        return i
    uf[i] = find(uf[i])
    return uf[i]

edges = []
for i in range(m):
    a, b, c = read_ints()
    a -= 1
    b -= 1
    edges.append((c, a, b))

sol = 0
edges = sorted(edges)
for e in edges:
    c, a, b = e
    fa, fb = find(a), find(b)
    if fa != fb:
        sol += c
        uf[fa] = fb
        
print sol