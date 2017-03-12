class Node(object):
	def __init__(self, name):
		self.name = name
		self.parent = self
		self.rank = 0

def find_root(node):
	if node.parent == node:
		return node
	return find_root(node.parent)

def get_node(nodes_list, node_name):
	# NOTE: assumes a definate match for the purposes of this problem.
    return [x for x in nodes_list if x.name == node_name][0]

n_count, e_count = [int(x) for x in raw_input().strip().split()]

nodes = [Node(x) for x in range(1, n_count+1)]
edges = []

# Read the nodes into tuples. 
for i in range(0, e_count):
	values = [x for x in raw_input().strip().split()]
	edge = int(values[0]), int(values[1]), float(values[2])
	edges.append(edge)


tree = []
# Sort by distance. 
for edge in sorted(edges, key=lambda tup: tup[2]):
	n1, n2, _ = edge
	node1, node2 = get_node(nodes, n1), get_node(nodes, n2) 
	node1_root, node2_root = find_root(node1), find_root(node2)
	
	# Check we don't have a cycle.
	if node1_root != node2_root:
		if node1_root.rank < node2_root.rank:
			node1_root.parent = node2_root
		elif node1_root.rank > node2_root.rank:
			node2_root.parent = node1_root
		else:
			node2_root.parent = node1_root
			node1_root.rank = node1_root.rank + 1
			
		tree.append(edge)
	
#for item in tree:		
#	print item

print int(sum([item[2] for item in tree]))


