def check(node,n1,n2,looper):
    global count
    if looper[n1]!=0 and (looper[n1]==looper[n2]):
        return False
    if looper[n1]!=looper[n2] and looper[n1]!=0 and looper[n2]!=0:
        to_change=min(looper[n1],looper[n2])
        a=max(looper[n1],looper[n2])
        for i in range(len(looper)):
            if looper[i]==a:
                looper[i]=to_change
    if looper[n1]!=looper[n2] and(looper[n1]==0 or looper[n2]==0):
        to_change=max(looper[n1],looper[n2])
        looper[n1]=to_change
        looper[n2]=to_change
    if looper[n1]==looper[n2] and looper[n1]==0:
        looper[n1]=count
        looper[n2]=count
        count+=1
    return True
nodes,edges=raw_input().split(" ")
nodes=int(nodes)
edges=int(edges)
l=[]
looper=[0]*nodes
count=1
node=[0]*nodes
while edges>=1:
    l.append(list(map(int,raw_input().split(" "))))
    edges-=1
l.sort(key=lambda x: x[2])
for i in range(0,len(l)-1):
    x=l[i]
    y=l[i+1]
    if x[2]==y[2]:
        s1=sum(x)
        s2=sum(y)
        if s2<s1:
            temp=x
            l[i]=l[i+1]
            l[i+1]=x
counter=0
disp=0
while True:
    to_add=l[counter]
    n1=to_add[0]-1
    n2=to_add[1]-1
    wt=to_add[2]
    if check(node,n1,n2,looper):
        disp+=wt
        node[n1]=1
        node[n2]=1
    counter+=1
    if counter==len(l):
        break
print disp