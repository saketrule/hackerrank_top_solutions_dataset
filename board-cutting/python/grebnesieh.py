T = input()
for i in range(0, T):
    [A, B] = [int(x) for x in raw_input().split(" ")]
    [ca, cb, C] = [1, 1, 0]
    a = sorted([int(x) for x in raw_input().split(" ")])[::-1]
    b = sorted([int(x) for x in raw_input().split(" ")])[::-1]
    t = len(a) + len(b)
    for j in range(0, t):
        if len(a) == 0:
            C += ca * b.pop(0)
        elif len(b) == 0:
            C += cb * a.pop(0)
        elif a[0] > b[0]:
            C += cb * a.pop(0)
            ca += 1
        elif b[0] > a[0]:
            C += ca * b.pop(0)
            cb += 1
        elif a[0] == b[0]:
            if ca >= cb:
                C += cb * a.pop(0)
                ca += 1
            else:
                C += ca * b.pop(0)
                cb += 1
    print C%1000000007