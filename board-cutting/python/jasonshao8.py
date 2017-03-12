a = int(raw_input())
for asdfa in range(a):
    x, y = map(int, raw_input().split())

    xCost = map(int, raw_input().split())
    yCost = map(int, raw_input().split())

    hacks = []

    for i in xCost:
        hacks.append((i, 0))
    for i in yCost:
        hacks.append((i, 1))

    pieces = [1, 1]

    ans = 0
    for i in sorted(hacks, reverse=True):
        ans += (i[0] * pieces[1 - i[1]]) % (10**9 + 7)
        ans %= 10**9 + 7
        pieces[i[1]] += 1

    print ans


