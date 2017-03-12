m = int(1e9+7)
T = input()
for t in xrange(T):
    raw_input()
    x = sorted(map(int, raw_input().split()))
    y = sorted(map(int, raw_input().split()))
    a = 1
    b = 1
    res = 0
    while len(x)*len(y)>0:
        if x[-1] > y[-1]:
            res += (x[-1]*a)%m
            x.pop()
            b += 1
        else:
            res += (y[-1]*b)%m
            y.pop()
            a += 1
    for c in x:
        res += (c*a)%m
    for c in y:
        res += (c*b)%m
    print res%m