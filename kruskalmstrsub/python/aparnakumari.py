# Enter your code here. Read input from STDIN. Print output to STDOUT
from operator import itemgetter
def find(i):
    if(parent[i] == -1):
        return i
    else:
        return find(parent[i])
    
def union(xroot,yroot):
    if(xroot>yroot):
        parent[yroot] = xroot
    else:
        parent[xroot] = yroot
        
def kruskalAlgorithm():
    result = []
    x = 0
    i = 0
    while(x<node-1):
        xroot = find(mylist[i][0])
        yroot = find(mylist[i][1])
        if(xroot != yroot):
            result.append(mylist[i][2])
            x = x + 1
            union(xroot,yroot)
        i = i + 1
    print sum(result)  
    
a = map(int,raw_input().split())
node = a[0]
edge = a[1]
mylist = []
for i in range(edge):
    x = map(int,raw_input().split())
    mylist.append(x)
mylist = sorted(mylist,key = itemgetter(2))
parent = list()
for i in range(node+1):
    parent.append(-1)
kruskalAlgorithm()
    
    
    
    
    