import sys
def find_in_arr(arr):
    rs = []
    for i in xrange(1,len(arr)):
        x = (arr[i]-arr[i-1])/2
        y = arr[i-1]+x
        rs.append((x,y))
    return rs
def find_with_m(arr,m):
    min = -1
    for i in arr:
        if min == -1 or min > abs(i-m):
            min = abs(i-m)
    return min
def solve(arr,K,Q):
    arr = sorted(arr)
    x = find_in_arr(arr)
    #print x
    min_K = find_with_m(arr,K)
    min_Q = find_with_m(arr,Q)
    x.append((min_K,K))
    x.append((min_Q,Q))
    x = sorted(x)
    max = -1
    for i in xrange(len(x)-1,-1,-1):
        if x[i][1] <= Q and x[i][1] >= K:
            if max == -1 or max < x[i][0]:
                max = x[i][0]
    min = -1
    for i in xrange(len(x)-1,-1,-1):
        if x[i][0] == max:
            if (min == -1 or min > x[i][1]) and x[i][1] <= Q and x[i][1] >= K:
                min = x[i][1]         
    return min        
        
n = int(sys.stdin.readline())
arr = map(int,sys.stdin.readline().strip().split())
K,Q = map(int,sys.stdin.readline().strip().split())

print solve(arr,K,Q)