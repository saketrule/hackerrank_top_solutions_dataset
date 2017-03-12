# Enter your code here. Read input from STDIN. Print output to STDOUT
n=input()
stick=map(int,raw_input().split())
stick=sorted(stick)
print n
for i in xrange(1,n):
    if stick[i]==stick[i-1]: continue
    print n-i