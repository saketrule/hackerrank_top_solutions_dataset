N=input()
arr=[int(i) for i in raw_input().split()]
raw=raw_input().split()
P=int(raw[0])
Q=int(raw[1])
arr.sort()
maxValue=-1
M=P

for i in xrange(N):
    if i == 0:
        if P <= arr[i]:
            M=P
            maxValue=arr[i]-P
    else:
        mid=arr[i-1] + (arr[i]-arr[i-1])/2
        if mid >= P and mid <= Q:
            candidate = (arr[i]-arr[i-1])/2
            if candidate > maxValue:
                maxValue=candidate
                M=mid
        elif mid > Q and arr[i-1] < Q:
            candidate=Q-arr[i-1]
            if candidate > maxValue:
                maxValue=candidate
                M=Q
        elif mid < P and arr[i] > P:
            candidate=arr[i]-P
            if candidate > maxValue:
                maxValue=candidate
                M=P
    if i == N-1 and Q >= arr[i] and Q-arr[i] > maxValue:
        M=Q
        maxValue=Q-arr[i]
print M
    