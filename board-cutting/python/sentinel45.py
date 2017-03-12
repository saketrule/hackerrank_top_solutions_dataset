t=input()
for _ in xrange(t):
    n,m=map(int,raw_input().split())
    n-=1
    m-=1
    h,v=1,1
    ha=map(int,raw_input().split())
    va=map(int,raw_input().split())
    ha.sort(reverse=True)
    va.sort(reverse=True)
    i=j=0
    mod=int(1e9+7)
    ans=0
    while i<n or j<m:
        c1=c2=-1
        if i<n:
            c1=ha[i]
        if j<m:
            c2=va[j]
        if j>=m or (c1>c2 or (c1==c2 and h>v)):
            ans+=ha[i]*v
            i+=1
            h+=1
        elif i>=n or (c1<c2 or (c1==c2 and h<=v)):
            ans+=va[j]*h
            j+=1
            v+=1      
        ans %= mod
    print ans%mod