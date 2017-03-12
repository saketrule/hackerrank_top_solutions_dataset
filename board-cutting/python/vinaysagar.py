# Enter your code here. Read input from STDIN. Print output to STDOUT

def minCost(x, y, M, N):
    x.sort()
    y.sort()

    d = 10**9 + 7
    total = 0

    xi = 0
    yi = 0
    while True:
        if xi >= len(x) and yi >= len(y):
            break
        
        if xi < len(x) and yi < len(y) and x[xi] < y[yi]:
            total += (x[xi]*M % d)
            N -= 1
            xi += 1
        elif xi < len(x) and yi < len(y) and x[xi] >= y[yi]:
            total += (y[yi]*N % d)
            M -= 1
            yi += 1
        elif xi < len(x):
            total += (x[xi]*M % d)
            N -= 1
            xi += 1
        elif yi < len(y):
            total += (y[yi]*N %d)
            M -= 1
            yi += 1

        
        total %= d

    return total

T = input()
for i in range(T):
    M, N = map(int, raw_input().split())
    y = map(int, raw_input().split())
    x = map(int, raw_input().split())
    print minCost(x, y, M, N)