# Enter your code here. Read input from STDIN. Print output to STDOUT
from collections import defaultdict
from Queue import PriorityQueue

N, M, G, S = None, None, None, None

def read_test():
  global N, M, G, S
  N, M = map(int, raw_input().split())
  G = defaultdict(lambda: {})
  for _ in xrange(M):
    x, y, c = map(int, raw_input().split())
    x, y = x-1, y-1
    if y in G[x]:
      c = min(c, G[x][y])
    G[x][y] = c
    G[y][x] = c
  S = int(raw_input())-1

def solve_test():
  global N, M, S, G
  Q = PriorityQueue(N*N)
  Q.put((0, S))
  visited = set()
  D = [-1] * N
  D[S] = 0
  
  while not Q.empty():
    cost, x = Q.get()
    visited.add(x)
    for y in G[x]:
      if y not in visited and (D[y] == -1 or D[y] > G[x][y]):
        D[y] = G[x][y]
        Q.put((D[y], y))
        
  filter(lambda x: x!=-1, D)
  return sum(D)
        

if __name__ == '__main__':
  read_test()
  sol = solve_test()
  print sol