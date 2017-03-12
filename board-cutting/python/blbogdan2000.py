#!/usr/bin/env python

P = 1000000007

T = int(raw_input().strip().split()[0])
for _ in xrange(T):
    M, N = [int(i) for i in raw_input().strip().split()]
    y = [int(i) for i in raw_input().strip().split()]
    x = [int(i) for i in raw_input().strip().split()]

    cost = 0
    nx = 1
    ix = 0
    ny = 1
    iy = 0

    x.sort(reverse=True)
    y.sort(reverse=True)

    while ix < len(x) and iy < len(y):
        if y[iy] > x[ix]:
            cost = (cost + ((nx * y[iy]) % P)) % P
            ny = ny + 1
            iy = iy + 1
        else:
            cost = (cost + ((ny * x[ix]) % P)) % P
            nx = nx + 1
            ix = ix + 1

    while ix < len(x):
            cost = (cost + ((ny * x[ix]) % P)) % P
            ix = ix + 1

    while iy < len(y):
            cost = (cost + ((nx * y[iy]) % P)) % P
            iy = iy + 1

    print cost % P

