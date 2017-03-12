import sys

N = int(sys.stdin.readline())
A = map(int, sys.stdin.readline().split(' '))
A = sorted(A)
last = -1
print N
for i in range(1, len(A)):
    if A[i] != A[i - 1]:
        print (N - i)