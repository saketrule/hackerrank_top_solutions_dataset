# Enter your code here. Read input from STDIN. Print output to STDOUT
def printPrimMST(key):
    sum = 0
    for i in range(l[0]):
        if(key[i]==float("inf")):
            key[i] = 0
        sum = sum + key[i]   
    print sum

def minKey(key,mstset):
    minvalue = float("inf")
    for i in range(l[0]):
        if(key[i]<=minvalue and mstset[i]==0):
            minvalue = key[i]
            minidx = i            
    return minidx

def PrimMST(graph,start):
    key = []
    mstset = []
    for i in range(l[0]):
        key.append(float("inf"))
        mstset.append(0)   
    key[start] = 0;
    for i in range(l[0]):
        u = minKey(key,mstset)
        mstset[u] = 1
        for j in range(l[0]):
            if(graph[u][j]!= float("inf") and mstset[j]==0 and key[j]>=graph[u][j]):
                key[j] = graph[u][j]
                
    printPrimMST(key)
        
    

l = map(int,raw_input().split())
graph = [[float("inf") for x in range(l[0])] for x in range(l[0])]
for i in range(l[1]):
    a = map(int,raw_input().split())
    if(graph[a[0]-1][a[1]-1] == 0 or graph[a[0]-1][a[1]-1] > a[2]):
        graph[a[0]-1][a[1]-1] = a[2]
        graph[a[1]-1][a[0]-1] = a[2]
start = input()-1
PrimMST(graph,start)
            
        
        