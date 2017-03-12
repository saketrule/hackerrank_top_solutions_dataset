s=10**9+7
t=input()
for i in xrange(t):
    m,n=map(int,raw_input().split())
    a=map(int,raw_input().split())
    b=map(int,raw_input().split())
    a.sort(reverse=True)
    a.append(-100)
    b.sort(reverse=True)
    b.append(-100)
    q=1
    w=1
    x=0
    y=0
    ans=0
    while(a[x]!=-100 or b[y]!=-100):
        if(a[x]>b[y]):
            ans=ans+a[x]*q
            w=w+1
            x=x+1
        elif(a[x]<b[y]):
            ans=ans+b[y]*w
            q=q+1
            y=y+1
        elif(a[x]==b[y]):
            if(w>q):
                ans=ans+a[x]*q
                w=w+1
                x=x+1
            else:
                ans=ans+b[y]*w
                q=q+1
                y=y+1
    print ans%s