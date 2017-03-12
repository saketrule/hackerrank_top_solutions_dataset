import heapq
from pprint import pprint

INF = float('inf')  

class Forest():
    
    def __init__(self, n):
        self.sets = {}
        self.reps = {}
        self.ranks = {}
        for i in xrange(1, n+1):
            self.create_set(i)
        
    def create_set(self, a):
        if a not in self.reps:
            self.reps[a] = a
            self.ranks[a] = 0
            self.sets[a] = {a}
        else:
            raise Exception('{0} is already in the Forest'.format(a))          
            
    def find_set(self, a):

        while self.reps[a] != a:
            a = self.reps[a]
        return a          
        
    def merge_set(self, a, b):  
        
        a = self.find_set(a)
        b = self.find_set(b)
        
        if self.ranks[a] != self.ranks[b]:
            a, b = (a, b) if self.ranks[a] >= self.ranks[b] else (b, a)
        else:
            a, b = (a, b) if a > b else (b, a)

        if a != b:
            self.ranks[a] = self.ranks[a] + self.ranks.pop(b) + 1
            self.sets[a] = self.sets[a].union(self.sets.pop(b))
            self.reps[b] = a

    def print_state(self):
        
        print "Forest State:"
        print "self.sets: "
        pprint(self.sets)
        print "self.reps: "
        pprint(self.reps)
        print "self.ranks: "
        pprint(self.ranks)


def get_row():
    return tuple( map(int, raw_input().strip().split()) )

def build_elem():
    
    u, v, w = get_row()
    return (w, u + v, (u, v))

    
N, M = get_row()

S = [build_elem() for _ in xrange(M)]
heapq.heapify(S)

F = Forest(N)
#F.print_state()
tree_weight = 0
while S and len(F.sets) > 1:

    (w, _, (u, v)) = heapq.heappop(S)
    if F.find_set(u) != F.find_set(v):

        F.merge_set(u, v)
        tree_weight += w
#        F.print_state()

print tree_weight
             
