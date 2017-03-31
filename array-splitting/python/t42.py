from bisect import bisect_left, bisect_right

def solve(a):
    n = len(a)
    sa = [0] * (n + 1)
    for i in xrange(n):
        sa[i + 1] = sa[i] + a[i]

    def f(lo, hi):
        if hi - lo == 1: return 0
        s = sa[hi] - sa[lo]
        if s == 0: return hi - lo - 1
        if s % 2 == 1: return 0
        half = s // 2
        mid_v = sa[lo] + half
        
        i = bisect_left(sa, mid_v, lo+1, hi+1)
        if sa[i] != mid_v: return 0
        right = f(i, hi)

        j = bisect_right(sa, mid_v, lo+1, hi+1)
        left = f(lo, j - 1)
        
        return 1 + max(left, right)        
        
    return f(0, n)

def read_ints(): return map(int, raw_input().split())

def main():
    for _ in xrange(input()):
        _n = input()
        a = read_ints()
        print solve(a)

main()
