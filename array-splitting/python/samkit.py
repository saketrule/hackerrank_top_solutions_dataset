#!/usr/bin/env python

import bisect
import sys

sys.setrecursionlimit(20000)

# sum array
# 0 1 2 3  4  5  6  7
# 2 4 6 8 10 12 14 16

# fracture[1] = 0
# 1 -> 0
# 3 -> 1
# 5 -> 2
# 7 -> 3

#  4  5  6  7
# 10 12 14 16

# 10 12 14 16
# 10 22 36 52

#

def index(a, x, start, end):
    'Locate the leftmost value exactly equal to x'
    i = bisect.bisect_left(a, x, start, end + 1)
    if i != len(a) and a[i] == x:
        return i
    return None

def max_divide(array, start, end, offset = 0):
    if start >= end:
        return 0

    #print 'max:', 'start:', start, 'end:', end, 'offset:', offset
    l = array[start]
    r = array[end]

    toSearch = (r + offset)
    if toSearch % 2 != 0:
        return 0

    toSearch = toSearch / 2

    found = index(array, toSearch, start, end)

    #print '\tfound:', found, 'toSEarch:', toSearch, 'arr:', (array[start:end+1] if end - start + 1 < 30 else [])

    if found is None:
        return 0

    return 1 + max(max_divide(array, start, found, offset), max_divide(array, found + 1, end, array[found]))


T = int(raw_input())

for t in xrange(T):
    N = int(raw_input())

    As = [int(i) for i in raw_input().split()]
    array = reduce(lambda x, y: x + [x[-1] + y], As, [0])[1:]
    print max_divide(array, 0, len(array) - 1)