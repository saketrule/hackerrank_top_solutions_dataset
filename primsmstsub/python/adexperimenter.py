import sys, heapq, itertools


verticesHeap = []               # list of entries arranged in a heap
entry_finder = {}               # mapping of tasks to entries
REMOVED = '<removed-task>'      # placeholder for a removed task
counter = itertools.count()     # unique sequence count

def add_vertex(task, priority=0):
    'Add a new task or update the priority of an existing task'
    if task in entry_finder:
        remove_task(task)
    count = next(counter)
    entry = [priority, count, task]
    entry_finder[task] = entry
    heapq.heappush(verticesHeap, entry)

def remove_task(task):
    'Mark an existing task as REMOVED.  Raise KeyError if not found.'
    entry = entry_finder.pop(task)
    entry[-1] = REMOVED

def pop_vertex():
    'Remove and return the lowest priority task. Raise KeyError if empty.'
    while verticesHeap:
        priority, count, task = heapq.heappop(verticesHeap)
        if task is not REMOVED:
            del entry_finder[task]
            return task
    return None
    #raise KeyError('pop from an empty priority queue')

################################################################################



def printMap(msg, arr):
    print msg
    for item in arr:
        print arr[item]

def printArray(msg, arr):
    print msg
    for item in arr:
        print item

N, M = map(int, raw_input().split())
A = [[-1 for i in range(0, N)] for j in range(0, N)]

# intialize vertices
vertices = {}
for v in range(0, N):
    vertices[v] = {
        'id': v,
        'adjVertices': set([]),
        'edgeToMinTree': None,
        'isInMinTree': False,
        'distToMinTree': sys.maxint
    }

# intialize adjacency matrix
for m in range(0, M):
    x, y, r = map(int, raw_input().split())
    if A[x-1][y-1] == -1 or A[x-1][y-1] > r:
        A[x-1][y-1] = r
        A[y-1][x-1] = r        
        vertices[x-1]['adjVertices'].add(y-1)
        vertices[y-1]['adjVertices'].add(x-1)

#print vertices       
#printMap('vertices', vertices)

S = int(raw_input())-1

# initialize vertices heap

add_vertex(S, 0)

#heapq.heappush(verticesHeap, (0, vertices[S]) )

nodesInTree, edgesInTree = [], []
nextHeapVertex = vertices[pop_vertex()]

while len(nodesInTree) != N and nextHeapVertex != None:
    #print nextHeapVertex
    
    nextVertex = nextHeapVertex#vertices[nextHeapVertex]
    nextVertexId = nextVertex['id']
    nextVertex['isInMinTree'] = True

    if nextVertex['edgeToMinTree'] != None:
        edgesInTree.append({
                'start': nextVertexId,
                'end': nextVertex['edgeToMinTree'],
                'edgeLen': A[nextVertexId][nextVertex['edgeToMinTree']]
            }
        )

    #print 'nextVertex :', nextVertex

    for adjId in nextVertex['adjVertices']:
        adjVertex = vertices[adjId]
        if adjVertex['isInMinTree'] == False:
            curDist = A[adjId][nextVertexId]
            if curDist != -1 and curDist < adjVertex['distToMinTree']:
                adjVertex['distToMinTree'] = curDist
                adjVertex['edgeToMinTree'] = nextVertexId
                add_vertex(adjId, curDist)
                #print '-----.........'

    #printArray('vertices heap:', verticesHeap)
    #print '============================================'

    nextHeapVertex = None
    if len(verticesHeap) > 0:
        index = pop_vertex()
        if index in vertices:
            nextHeapVertex = vertices[index]
        #print 'aaa....'
        while nextHeapVertex != None and nextHeapVertex['isInMinTree'] == True:
            nextHeapVertex = vertices[pop_vertex()]
            #print 'baa....'

#print edgesInTree            
print sum(map( lambda e: e['edgeLen'], edgesInTree))
