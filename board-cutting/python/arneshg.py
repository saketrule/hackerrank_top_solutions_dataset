t = input()
for i in range(t):
    m,n = map(int,raw_input().split())
    ar1 = map(int,raw_input().split())
    ar2 = map(int,raw_input().split())
    ar1.sort(reverse=True)
    ar2.sort(reverse=True)
    i=0
    j=0
    ans=0
    while i<m-1 and j<n-1:
        if ar1[i]>=ar2[j]:
            ans += ar1[i]*(j+1)
            i += 1
        else:
            ans += ar2[j]*(i+1)
            j += 1
    while i<m-1:
        ans += ar1[i]*(j+1)
        i += 1
    while j<n-1:
        ans += ar2[j]*(i+1)
        j += 1
    print ans%1000000007