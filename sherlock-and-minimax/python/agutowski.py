n = input()
a = sorted(map(int, raw_input().split()))
def f(m):
    res = 2e9
    for x in a:
        res = min(res, abs(x-m))
    return res
p,q = map(int, raw_input().split())
res,maks = p,f(p)
for i in xrange(1,n):
    m = (a[i]+a[i-1])/2
    if m < p or m > q:
        continue
    if min(m-a[i-1], a[i]-m) > maks:
        maks = min(m-a[i-1], a[i]-m)
        res = m
    elif min(m-a[i-1], a[i]-m) == maks:
        res = min(res,m)
if f(q) > maks:
    maks = f(q)
    res = q
elif f(q) == maks:
    res = min(res,q)
print res