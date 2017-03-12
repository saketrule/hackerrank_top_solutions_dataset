# Enter your code here. Read input from STDIN. Print output to STDOUT
#!/usr/bin/env python
import sys

def read_ints():
    return [int(x) for x in sys.stdin.readline().split()]

n, m = read_ints()
G = {}
for i in range(n+1):
    G[i] = []
for i in range(m):
    a, b = read_ints()
    G[min(a,b)].append(max(a,b))

# Returns (num cuts, is even)
def countem(G, i):
    if not G[i]:  # No children
        return (0, False)
    mycuts = 0
    is_even = False
    for j in G[i]:
        child_cuts, child_is_even = countem(G, j)
        mycuts += child_cuts
        if child_is_even:
            mycuts += 1
        else:
            is_even = not is_even
    return mycuts, is_even

cuts, _ = countem(G, 1)
print cuts
