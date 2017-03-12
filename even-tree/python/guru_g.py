# Enter your code here. Read input from STDIN. Print output to STDOUT

V_E = raw_input()

V_E = V_E.split()
num_V = int(V_E[0])
num_E = int(V_E[1])

tree = {}

def add_to_tree(source, dest, tree):
    if source in tree:
        tree[source].append(dest)
    else:
        tree[source] = [dest]

def first_non_empty(tree):
    for i in range(num_V):
        if tree[i+1]:
            return i+1

for i in range(num_E):
    E = raw_input()
    E = E.split()
    v1 = int(E[0])
    v2 = int(E[1])
    
    add_to_tree(v1, v2, tree)
    add_to_tree(v2, v1, tree)

for k in range(1,num_V+1):
    neighbors = tree[k]
    for n in neighbors:
        tree[n].remove(k)

node_sizes = {} # A dictionary of sizes => nodes

def fill_tree_sizes(start):
    neighbors = tree[start]
    if not neighbors:
        if 1 in node_sizes:
            node_sizes[1].append(start)
        else:
            node_sizes[1] = [start]
        return 1
    else:
        tot = 1

        for n in neighbors:
            tot = tot + fill_tree_sizes(n)
            
        if tot in node_sizes:
            node_sizes[tot].append(start)
        else:
            node_sizes[tot] = [start]
        return tot
    
fill_tree_sizes(1)
to_remove = 0

for k in node_sizes.keys():
    if k % 2 == 0 and k != num_V:
        to_remove = to_remove + len(node_sizes[k])
        
print to_remove