#!/usr/bin/env python

answers = [0,
0,
0,
1,
2,
2,
3,
4,
4,
6,
8,
9,
11,
15,
19,
24,
32,
42,
53,
68,
91,
119,
155,
204,
269,
354,
462,
615,
816,
1077,
1432,
1912,
2543,
3385,
4522,
6048,
8078,
10794,
14475,
19385]

def solve(n):
  if n < 0:
    return 0
  if n < 4:
    return 1
  return solve(n-1) + solve(n-4)

if __name__ == "__main__":
  t = int(raw_input())
  #primes = []
  #with open("primes.txt") as f:
  #  for l in f.readlines():
  #    primes.extend(map(int, l.split()))

  for i in xrange(t):
    #s = solve(map(int, raw_input().split()))
    #print sum(1 for p in primes if p <= s)
    print answers[int(raw_input())-1]