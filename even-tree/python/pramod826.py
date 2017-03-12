# Enter your code here. Read input from STDIN. Print output to STDOUT
def bfs(i,p):
    sum=1
    for j in range(n+1):
        if j!=i and j!=p and table[i][j]==1:
            sum+=int(bfs(j,i))
    snode[i]=sum
    #print i,sum
    return sum
       
n,m=raw_input().split()
n=int(n)
m=int(m)
table=[ [0 for i in range(n+1)] for j in range(n+1)]
for i in range(m):
    j,k=raw_input().split()
    j,k=int(j),int(k)
    table[j][k]=table[k][j]=1

var=0
for i in range(n+1):
    cnt=0
    for j in range(n+1):
        if table[i][j]==1:
            cnt+=1
    if cnt==1:
        var=i
        break
#print var
snode=[0 for i in range(n+1)]
bfs(var,0)
#for i in range(n+1):
    #print snode[i]

cnt=0
for j in range(1,n+1):
    #print snode[j],
    if snode[j]%2==0:
        cnt+=1

print cnt-1

 

