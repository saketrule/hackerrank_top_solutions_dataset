# https://www.hackerrank.com/challenges/sherlock-and-minimax


def minimax(l, pq):
    p, q = pq
    l.sort()
    m = [p, q]

    for i in range(len(l)):
        mid = (l[i] + l[i-1]) / 2
        if mid >= p and mid <= q:
            m.append(mid)
    nm = lambda m: [min([abs(i - m) for i in l]), m]
    m = map(nm, m)

    from functools import cmp_to_key
    cmpf = lambda a, b: b[1]-a[1] if a[0] == b[0] else a[0]-b[0]
    m = max(m, key=cmp_to_key(cmpf))

    return m[1]

if __name__ == "__main__":
    n = input()
    l = map(int, raw_input().split())
    pq = map(int, raw_input().split())
    print minimax(l, pq)
