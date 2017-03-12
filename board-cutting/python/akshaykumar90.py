import sys
from collections import defaultdict

MOD = 1000000007

T = int(sys.stdin.readline().rstrip())

class Counter:
    def __init__(self):
        self.x = 0
        self.y = 0

    def __repr__(self):
        return "(%d, %d)" % (self.x, self.y)

for __ in range(T):
    sys.stdin.readline()

    costs = defaultdict(Counter)

    for xi in map(int, sys.stdin.readline().split()):
        costs[xi].x += 1

    for yi in map(int, sys.stdin.readline().split()):
        costs[yi].y += 1

    total = 0
    x = y = 1
    for c in sorted(costs.keys(), reverse=True):
        ctr = costs[c]
        total += c * (ctr.x * y + ctr.y * ctr.x + x * ctr.y)
        x += ctr.x
        y += ctr.y

    print total % MOD
