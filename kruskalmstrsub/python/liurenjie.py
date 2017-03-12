class DisjointSet:
    def __init__(self, n):
        self.n = n
        self.root = [x for x in xrange(n + 1)]

    def union(self, a, b):
        self._checkIndex(a)
        self._checkIndex(b)
        rootA = self.findRoot(a)
        rootB = self.findRoot(b)

        if rootA == rootB:
            return False
        else:
            self.root[rootA] = rootB
            return True


    def findRoot(self, idx):
        self._checkIndex(idx)

        stack = []
        cur = idx
        while self.root[cur] != cur:
            stack.append(cur)
            cur = self.root[cur]

        while len(stack) > 0:
            cur = stack.pop()
            self.root[cur] = self.root[self.root[cur]]

        return self.root[idx]

    def _checkIndex(self, idx):
        assert idx >= 0 and idx <= self.n, 'Invalid idx:{}'.format(idx)

def solve(N, edges, source):
    ds = DisjointSet(N)

    edges.sort(cmp = lambda e1, e2: sum(e1) - sum(e2) if e1[2] == e2[2] else e1[2] - e2[2], reverse = True)

    foundCnt = 0
    ans = 0
    while foundCnt < (N - 1):
        (s, t, r) = edges.pop()
        if ds.union(s, t):
            foundCnt += 1
            ans += r

    print ans

(N, M) = map(int, raw_input().split())
edges = [map(int, raw_input().split()) for m in xrange(M)]
solve(N, edges, 0)
