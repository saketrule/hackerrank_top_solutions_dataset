__author__ = 'Tinks'
import operator
from itertools import groupby

# Enter your code here. Read input from STDIN. Print output to STDOUT
def search_min_subgraph(S, N, graph_dictionary):
    nodes = [[S,0]]
    weight = 0
    result = []
    while len(nodes)>0 and len(result)< N:
        node = min(nodes, key=operator.itemgetter(1))
        weight += node[1]
        result.append(node[0])
        new_nodes = graph_dictionary[node[0]][1]

        for node in new_nodes:
            if node[0] in [x[0] for x in nodes]:
                for old_node in nodes:
                    if old_node[0] == node[0]:
                        if old_node[1] > node[1]:
                            old_node[1] = node[1]
            else:
                nodes.append(node)

        nodes= [x for x in nodes if x[0] not in result]
    return weight


N, M = [int(x) for x in raw_input().split()]
graph_dictionary = {}

for i in range(N+1):
    graph_dictionary[i] = [i,[]]

for i in range(M):
    x,y,w = [int(x) for x in raw_input().split()]
    graph_dictionary[x][1].append([y,w])
    graph_dictionary[y][1].append([x,w])

S = int(raw_input())
root = graph_dictionary[S]
result = search_min_subgraph(S, N, graph_dictionary)
print  result






