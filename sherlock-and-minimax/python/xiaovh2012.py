from collections import OrderedDict

def find_cloest(array, k):
    if len(array) == 1:
        return array[0]
    i = len(array)/2
    if array[i] > k:
        sub_closest = find_cloest(array[:i], k)
        return sub_closest if abs(sub_closest - k) <= abs(array[i] - k) else array[i]
    else:
        return find_cloest(array[i:], k)


def get_middles(array):
    db = {}
    for i in xrange(0, len(array)-1):
        d = (array[i+1] - array[i])/2
        b = array[i] + d
        bs = db.get(d, [])
        bs.append(b)
        db[d] = sorted(bs)
    return db


def sherlock_minmax_opt(ar, low_m, high_m):
    sorted_ar = sorted(ar)
    if high_m <= sorted_ar[0]:
        return low_m
    if low_m >= sorted_ar[-1]:
        return high_m
    db = get_middles(sorted_ar)
    sorted_db = OrderedDict(sorted(db.items(), key=lambda t: t[0], reverse=True))
    mid = None
    for e in sorted_db.items():
        distance, possibles = e[0], e[1]
        if mid == None:
            for p in possibles:
                if p >= low_m and p <= high_m:
                    mid = (distance, p)
                    break
    head_dis = abs(low_m - find_cloest(sorted_ar, low_m))
    tail_dis = abs(high_m - find_cloest(sorted_ar, high_m))
    (temp_m, temp_dis) = (low_m, head_dis) if head_dis >= tail_dis else (high_m, tail_dis)
    if temp_dis == mid[0]:
        return temp_m if temp_dis <= mid[1] else mid[1]
    else:
        return temp_m if temp_dis > mid[0] else mid[1]

n = input()
A = map(lambda x: int(x), raw_input().split())
PQ = raw_input().split()
p = int(PQ[0])
q = int(PQ[1])

print sherlock_minmax_opt(A, p, q)