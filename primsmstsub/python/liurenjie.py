class MinHeap:
    def __init__(self):
        self.nodes = []

    def size(self):
        return len(self.nodes)

    def removeMin(self):
        assert len(self.nodes) > 0
        self._swapNode(0, len(self.nodes) - 1)
        ret = self.nodes.pop()
        if len(self.nodes) > 0:
            self._siftDown(0)
        return ret

    def add(self, node):
        assert node is not None
        self.nodes.append(node)
        self._siftUp(len(self.nodes) - 1)

    def _siftUp(self, cur):
        self._checkIndex(cur)
        nodes = self.nodes
        while cur > 0:
            parent = (cur - 1) / 2
            if nodes[parent] > nodes[cur]:
                self._swapNode(parent, cur)
                cur = parent
            else:
                break

    def _siftDown(self, cur):
        self._checkIndex(cur)
        nodes = self.nodes
        while cur < len(nodes):
            leftSon = cur * 2 + 1
            rightSon = leftSon + 1

            if leftSon < len(nodes):
                if rightSon < len(nodes):
                    if nodes[cur] > nodes[leftSon] or nodes[cur] > nodes[rightSon]:
                        if nodes[leftSon] < nodes[rightSon]:
                            self._swapNode(cur, leftSon)
                            cur = leftSon
                        else:
                            self._swapNode(cur, rightSon)
                            cur = rightSon
                    else:
                        break
                else:
                    if nodes[cur] > nodes[leftSon]:
                        self._swapNode(cur, leftSon)
                        cur = leftSon
                    else:
                        break
            else:
                break


    def _swapNode(self, a, b):
        self._checkIndex(a)
        self._checkIndex(b)
        tmpNode = self.nodes[a]
        self.nodes[a] = self.nodes[b]
        self.nodes[b] = tmpNode

    def _checkIndex(self, idx):
        assert idx >= 0 and idx < len(self.nodes), 'Invalid idx:{}'.format(idx)

    def __str__(self):
        return str(self.nodes)

class Edge:
    def __init__(self, s, t, r):
        self.s = s
        self.t = t
        self.r = r

    def __lt__(self, other):
        return self.r < other.r

    def __gt__(self, other):
        return self.r > other.r

    def __str__(self):
        return '[s:{}, t:{}, r:{}]'.format(self.s, self.t, self.r)

    def __repr__(self):
        return self.__str__()

def solve(N, edges, source):
    graph = [{} for x in xrange(0, N + 1)]
    for edge in edges:
        (s, t, r) = edge
        if t not in graph[s] or r < graph[s][t]:
            graph[s][t] = r
            graph[t][s] = r

    minHeap = MinHeap()

    found = [False] * (N + 1)
    found[source] = True
    for t in graph[source]:
        minHeap.add(Edge(source, t, graph[source][t]))

    foundCount = 0
    ans = 0

    while foundCount < (N - 1):
        edge = minHeap.removeMin()
        # print minHeap
        (s, t, r) = (edge.s, edge.t, edge.r)
        if not found[t]:
            assert found[s]
            ans += r
            foundCount += 1
            found[t] = True
            for nextNode in graph[t]:
                if not found[nextNode]:
                    minHeap.add(Edge(t, nextNode, graph[t][nextNode]))

    print ans

(N, M) = map(int, raw_input().split())
edges = [map(int, raw_input().split()) for x in xrange(M)]
source = int(raw_input())

solve(N, edges, source)
