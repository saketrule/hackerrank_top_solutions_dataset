N, M = map(int, raw_input().split())
A = [[-1 for i in range(0, N)] for j in range(0, N)]
edges = []

for m in range(0, M):
    x, y, r = map(int, raw_input().split())
    if A[x-1][y-1] == -1 or A[x-1][y-1] > r:
        A[x-1][y-1] = r
        A[y-1][x-1] = r  
        edges.append({
                'r': r,
                'x': x-1,
                'y': y-1
            })
    
S = int(raw_input())
edges = sorted(edges, key = lambda e: e['r'] )

groups = []
lenGroups = 0
def findGroup(v):
    for i in range(0, len(groups)):
        if v in groups[i]['nodes']:
            return i
    return -1

def addToGroup(g, e):
    #print ',.,.,.,. add to group', g, e
    #print groups
    groups[g]['nodes'].add(e['x'])
    groups[g]['nodes'].add(e['y'])
    groups[g]['edges'].append(e)
    #print groups
    
    
def unionGroups(g1, g2, e):
    #print '-----> union'
    #print groups[g1]['nodes'], groups[g2]['nodes']

    groups[g1]['nodes'].update(groups[g2]['nodes'])
    
    #print ':::::', groups[g1]['nodes']
    
    groups[g1]['edges'].extend(groups[g2]['edges'])
    groups[g1]['edges'].append(e)
    groups.pop(g2)

for e in edges:
    x, y = e['x'], e['y']
    g1 = findGroup(x)
    g2 = findGroup(y)
    
    #print '........................'
    #print 'g1 g2 e', g1, g2, e

    if g1 == -1 and g2 == -1: # new group
        #add new group
        groups.append({
                'nodes': set([x, y]),
                'edges': [e]})
        lenGroups += 2        
    elif g1 != -1 and g2 == -1:  # add to g2 
        addToGroup(g1, e)
        lenGroups += 1
    elif g2 != -1 and g1 == -1: # add to g1
        addToGroup(g2, e)
        lenGroups += 1
    elif g1 == g2:    # same group
        pass      
    else:
        unionGroups(g1, g2, e)
                
    if lenGroups == N and len(groups) == 1:
        break
    #print groups
    #print '~~~~~~~~~~~~~'

        
#print 'sorted edges', edges
#print 'minSpanNodes', minSpanNodes
#print 'minSpanEdges', minSpanEdges
#e1 = minSpanEdges[0]
#print e1, e1['r']

#a = [{'y': 2, 'x': 0, 'r': 3}, {'y': 1, 'x': 2, 'r': 4}, {'y': 3, 'x': 2, 'r': 5}]
#print a[0]['r'], a[1]['r'], a[2]['r']
#print reduce( (lambda e1, e2: e1['r'] + e2['r']), a)

#print groups

def length(edges):
    return reduce(lambda val, e: val + e['r'], edges, 0)

print sum(map( lambda g: length(g['edges']), groups))