n = input()
a = sorted(map(int, raw_input().split()))
last = -1
print n
for i in xrange(1,n):
    if a[i]!=a[i-1]:
        print n-i