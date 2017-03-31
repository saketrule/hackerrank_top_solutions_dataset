def neighbors(arr, v):
    low = 0
    high = len(arr)-1
    while (low <= high):
        mid = (low+high)/2
        if arr[mid] == v:
            return (mid,mid)
        elif arr[mid] < v:
            low = mid+1
        else:
            high = mid-1
    return (high,low)

def min_distance(arr, v, l, r):
    if l >= 0 and r < len(arr):
        return min(abs(arr[l]-v), abs(arr[r]-v))
    elif l < 0:
        return abs(arr[r]-v)
    else:
        return abs(arr[l]-v)

def find_m(arr, p, q):
    arr.sort()
    l, r = neighbors(arr, p)
    dst_p = min_distance(arr, p, l, r)
    l, r = neighbors(arr, q)
    dst_q = min_distance(arr, q, l, r)
    res = p
    maxdst = dst_p
    if dst_q > dst_p:
        maxdst = dst_q
        res = q
    for i in xrange(len(arr)-1):
        v = (arr[i]+arr[i+1])/2
        if v >= p and v <= q:
            dst = min(abs(arr[i]-v), abs(arr[i+1]-v))
            if dst > maxdst:
                maxdst = dst
                res = v
            elif dst == maxdst and v < res:
                res = v
    return res
        

m = input()
arr = [int(n) for n in raw_input().split(' ')]
p, q = (int(n) for n in raw_input().split(' '))
print find_m(arr, p, q)
