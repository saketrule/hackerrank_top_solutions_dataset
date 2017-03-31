import sys
from bisect import bisect_left


def index(a, x, lo=0, hi=None):
    if hi is None:
        hi = len(a)
    'Locate the leftmost value exactly equal to x'
    i = bisect_left(a, x, lo=lo, hi=hi)
    if i != len(a) and a[i] == x:
        return i
    raise ValueError


def get_score(l, u):
    if l >= u:
        return 0
    
    diff = arr_sum[l - 1] if l > 0 else 0
    max_sum = arr_sum[u] - diff
    if max_sum % 2:
        return 0
    else:
        try:
            i = index(arr_sum, diff + max_sum / 2, l, u + 1)
        except ValueError:
            return 0
    
        return 1 + max(get_score(l, i), get_score(i + 1, u))
    
sys.setrecursionlimit(100000)
iter_stdin = iter(sys.stdin)

next(iter_stdin)

for _ in iter_stdin:
    arr = map(int, next(iter_stdin).rstrip('\n').split())
    prev_sum = 0
    arr_sum = []
    for el in arr:
        prev_sum += el
        arr_sum.append(prev_sum)
    
    print get_score(0, len(arr_sum) - 1)
