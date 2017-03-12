# Enter your code here. Read input from STDIN. Print output to STDOUT

line = raw_input().split()
vCount = int(line[0])
eCount = int(line[1])
# print vCount, eCount
Matrix = {(x,y):0 for x in range(vCount) for y in range(vCount)}

rootV = -1
# init edges in the adj Martrix
for e in range(eCount):
  line = raw_input().split()
  v1 = int(line[0]) - 1
  v2 = int(line[1]) - 1
  if v1 < v2:
    Matrix[(v1, v2)] = 1
  else:
    Matrix[(v2, v1)] = 1
  rootV = v2

def printNodes(nodeslist):
  print 'Nodes:'
  for n in nodeslist:
    print 1+n.data, '[',len(n.child),']',' size = ', n.count
  print ''

# compute the node count of the subtrees
def computeNodeSize(nodeslist):
  for n in reversed(nodeslist):
    # compute the node count for each subtree
    n.count = 1
    for c in n.child:
      n.count += c.count

# Build the tree from adjacency matrix
class Node(object):
    def __init__(self):
        self.child = []
        self.data = None
        self.count = 1

done = []
verts = []
verts.append(rootV)

root = Node()
root.data = verts[0]
nodeslist = []
nodeslist.append(root)



current = 0
v1 = verts[0]
while(True):
  node = nodeslist[current]
  #print 'processing vertex : ', v1 + 1
  for v in range(vCount):
    if Matrix[(v1, v)] != 0 or Matrix[(v, v1)] != 0:
      if (done.count(v) != 0): continue
      childNew = Node()
      childNew.data = v
      verts.append(v)
      nodeslist.append(childNew)
      node.child.append(childNew)
  #print 'done vertex : ', [x+1 for x in verts]
  done.append(verts.pop(0))
  if len(verts) != 0:
    v1 = verts[0]
  else:
    break
  current = current + 1

computeNodeSize(nodeslist)
# printNodes(nodeslist)

# For every child that has even number size, disconnect from parent
result = 0
for n in nodeslist:
  if n != root and n.count % 2 == 0:
    result += 1

print result

