t=input()
a=map(int,raw_input().split())
a.sort()
b={}
for i in a:
    if i in b.keys():
        b[i]=b[i]+1
    else:
        b[i]=1
c=b.keys()
c.sort()
for i in c:
    print t
    t=t-b[i]