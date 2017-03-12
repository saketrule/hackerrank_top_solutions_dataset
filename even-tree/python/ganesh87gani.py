# Enter your code here. Read input from STDIN. Print output to STDOUT
result = 0
mytree = {}

def dpt(node):
	global mytree,result
	mytree[node][1] = 1
	children = []
	traversednodes.append(node)
	for i in xrange(len(mytree[node][0])):
		children.append(dpt(mytree[node][0][i]))
	
	numchildren = 0
	#print node,children
	for i in xrange(len(children)):
		if children[i]%2 == 0:
			result += 1
		else:
			numchildren += children[i]
	return numchildren+1

inarr = raw_input().split()
N = int(inarr[0])
M = int(inarr[1])


for i in xrange(N):
	mytree[i]=[[],0]

for i in xrange(M):
		inarr = raw_input().split()
		mytree[int(inarr[1])-1][0].append(int(inarr[0])-1)
#print mytree

traversednodes = []


count = 0
while len(traversednodes) != N:
	if mytree[count][1] == 0:
		dpt(count)
	count += 1
print result