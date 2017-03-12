# Enter your code here. Read input from STDIN. Print output to STDOUT

# Enter your code here. Read input from STDIN. Print output to STDOUT

import sys

def do(g):
    g.sort()
    g.count()       
    #for v in g.ordered:
    #    print v.index,':',v.sum
    #print g.cut()
    print g.cut()

class Graph:
    def __init__(self,N):
        self.cuts = 0
        self.vertices = []
        self.ordered = []
        for i in range(0,N):
            self.vertices.append(Vertex(i)) 
        self.root = self.vertices[0]
        self.ordered = self.sort()
    def sort(self):
        self.ordered = self.root.sort()
    def count(self):
        self.root.count()
    def cut(self):
        r = 0
        for v in self.ordered:
            k1 = v.sum
            k2 = self.root.sum - k1
            if k2 > 0 and (k1 % 2 == 0) and (k2 % 2 == 0):
                r += 1
                v.disconnect()
        return r
        
class Vertex:
    def __init__(self,index):
        self.index = index
        self.children = []
        self.ordered = []
        self.parent = None
        self.sum = 1
    def disconnect(self):
        self.parent.children.remove(self)
        self.parent.sum -= self.sum
        self.parent = None
    def addParent(self, p):
        if self.parent != None:
            sys.exit(1) 
        self.parent = p
    def addChild(self, c):
        self.children.append (c)
        c.addParent(self)
    def sort(self):
        list = []
        for c in self.children:
            list.extend(c.sort())
        #print self.index,':',self.sum
        list.append(self)
        return list        
    def count(self):
        for c in self.children:
            c.count()
            self.sum += c.sum
    '''
    def cut(self):
        for v in se
        print 'nnnn ',self.index,' k=',self.sum
        for c in self.children:
            k = c.sum
            if k % 2 == 0 and (self.sum - k) %2 == 0:
                r += 1
                r += c.cut()
                self.sum -= k
                self.children.remove(c)
                r += self.cut()
                return r
                        
        return 0
    '''

N,M = raw_input().split(' ')
N = int(N)
M = int(M)

g = Graph(N)

for i in range(0,M):
    src,snk = raw_input().split(' ')
    src = int(src) - 1
    snk = int(snk) - 1
    if src>snk:
        t = src
        src = snk
        snk = t
    src = g.vertices[src]
    snk = g.vertices[snk]
    src.addChild(snk)
do(g)
