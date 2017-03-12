
N = int(raw_input())
A = [int(i) for i in raw_input().split()]
A.sort()
count = N
while len(A)!=0:
    i = A[0]
    j = 0
    print count
    while i in A:
        if A[j] == i:
            A.remove(i)
	count = len(A)
