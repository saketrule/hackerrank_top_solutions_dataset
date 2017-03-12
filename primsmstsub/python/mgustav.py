# Enter your code here. Read input from STDIN. Print output to STDOUT

from Queue import PriorityQueue

read_ints = lambda: [int(x) for x in raw_input().split()]

n, m = read_ints()
g = [[] for i in range(n)]

for i in range(m):
    a, b, c = read_ints()
    a -= 1
    b -= 1
    g[a].append((b, c))
    g[b].append((a, c))
    
pq = PriorityQueue()
done = set()

def discover(i):
    for e in g[i]:
        b, c = e
        if not b in done:
            pq.put((c, b))
    done.add(i)
    
sol = 0
discover(0)
while not pq.empty():
    c, i = pq.get()
    if not i in done:
        sol += c
        discover(i)
        
print sol