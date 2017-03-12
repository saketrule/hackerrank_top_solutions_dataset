def read():
    n, m = raw_input().split()
    n, m = int(n), int(m)
    E = []
    for i in range(m):
        x, y, r = raw_input().split()
        x, y, r = int(x) - 1, int(y) - 1, int(r)
        E.append((x, y, r))
    return n, m, E

def root(parent, index):
    while parent[index] != index:
        index = parent[index]
    return index

def merge(parent, x, y):
    parent[root(parent, x)] = root(parent, y)
    
def solve(n, m, E):
    result = 0
    E = sorted(E, key=lambda x:x[-1])
    parent = [i for i in range(n)]
    for x, y, r in E:
        if root(parent, x) != root(parent, y):
            result += r
            merge(parent, x, y)
    return result
    
def main():
    n, m, E = read()
    print solve(n, m, E)
    
main()
