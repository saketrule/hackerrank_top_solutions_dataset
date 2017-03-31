# Enter your code here. Read input from STDIN. Print output to STDOUT
def score(a):
    b = [a[0]]
    flag = 1
    if len(a)<=1:
        return 0
    for i in range(1,len(a)):
        b.append(b[i-1]+a[i])
    if b[0]==b[len(b)-1]:
        return len(a)-1
    for i in range(0,len(a)-1):
        if 2*b[i]==b[len(a)-1]:
            flag = 0
            l = a[0:i+1]
            r = a[i+1:len(a)]
            l1 = score(l)
            r1 = score(r)
            s = 1+max(l1,r1)
            return s
    if flag==1:
        return 0
t = int(raw_input())
for q in range(0,t):
    n=int(raw_input())
    a = map(int, raw_input().strip().split())
    g = score(a)
    print g