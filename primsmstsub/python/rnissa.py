from heapq import *
from collections import defaultdict


def tree_cost(edges):
    sum(map(lambda x: x[1], edges))


def add_accessible_edges(a_edges, edges, new_node):
    for edge in edges[new_node]:
        dest_node = edge[0]
        cost = edge[1]
        heappush(a_edges, (cost, (new_node, dest_node)))

def select_edge(a_edges, a_nodes):
    while True:
        cost, edge = heappop(a_edges)
        dest_node = edge[1]
        if dest_node not in a_nodes:
            return cost, dest_node


def solve(edges, S, N):
    selected_edges = []
    a_nodes = [S]
    a_edges = []  # priority queue
    add_accessible_edges(a_edges, edges, S)

    total_cost = 0
    for e in xrange(N - 1):
        cost, new_node = select_edge(a_edges, a_nodes)
        total_cost += cost
        a_nodes.append(new_node)
        add_accessible_edges(a_edges, edges, new_node)

    return total_cost


if __name__ == '__main__':
    edges = defaultdict(list)
    N, M = map(int, raw_input().strip().split())
    for _ in xrange(M):
        x, y, r = map(int, raw_input().strip().split())
        edges[x].append((y, r))
        edges[y].append((x, r))

    S = int(input())

    print solve(edges, S, N)
