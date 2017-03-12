# Enter your code here. Read input from STDIN. Print output to STDOUT

def solve(n,rr,s):
    G=[[] for _ in range(n)]
    r=[list(k) for k in rr]
    r.sort(key=lambda x:x[2]+0.00000000001*(sum(x)))
    for [a,b,w] in r:
        G[a-1].append([b-1,w])
    A=[]
    connexes=[]
    k=0
    while len(A)<n-1:
        a=r[k]
        i=0
        while i<len(connexes):
            if a[0] in connexes[i]:
                break
            i+=1
        j=0
        while j<len(connexes):
            if a[1] in connexes[j]:
                break
            j+=1
        if i==len(connexes):
            if j==len(connexes):
                connexes.append([a[0],a[1]])
            else:
                connexes[j].append(a[0])
            A.append(a)
        elif j==len(connexes):
            connexes[i].append(a[1])
            A.append(a)
        elif i!=j:
            connexes[i]+=connexes[j]
            connexes=connexes[:j]+connexes[j+1:]
            A.append(a)
           
        k+=1
    return sum([A[k][2] for k in range(n-1)])






[n,m]=map(int,raw_input().strip().split())
r=[]
for _ in range(m):
    r.append(map(int,raw_input().strip().split()))
s=int(raw_input())
print(solve(n,r,s))