def go():
    res = [1,1,1,1]
    for i in range(4, 41):
        res.append(res[-1]+res[-4])
    mx = res[-1]
    l = [True]*(mx+1)
    l[0] = l[1] = False
    z = [False]*(mx//2)
    for i in range(2,int(mx**0.5)+1):
        if l[i]: l[i*i::i] = z[:(mx//i)-i+1]
    for i in range(input()):
        print sum(l[:res[input()]+1])

go()
