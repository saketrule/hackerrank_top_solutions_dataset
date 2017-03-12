# Enter your code here. Read input from STDIN. Print output to STDOUT
t = input()
modn = 1000000007

while t > 0:
    mn = [int(i) for i in raw_input().strip().split()]
    m = mn[0]
    n = mn[1]

    y = [int(i) for i in raw_input().strip().split()]
    x = [int(i) for i in raw_input().strip().split()]

    y.sort()
    x.sort()

    cost = 0
    cy = 0
    cx = 0
    while y:
        if not x or y[m-2-cy] >= x[n-2-cx]:
            cost += (y[m-2-cy]*(cx+1))
            cy += 1
            y.pop()
        else:
            cost += (x[n-2-cx]*(cy+1))
            cx += 1
            x.pop()

    while x:
        cost += (x.pop() * (cy+1))

    print (cost%modn)
    t -= 1