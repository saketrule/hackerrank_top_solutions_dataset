# Enter your code here. Read input from STDIN. Print output to STDOUT

import os

mod = 1000000007

def solve(M, N, x, y):
    y.sort(reverse=True)
    x.sort(reverse=True)
    
    ans = 0
    nx = 1
    ny = 1
    
    length = M + N - 2
    
    xi = 0;
    yi = 0;
    
    for i in xrange(length):
#        print "xi = ", xi, " yi = ", yi
        if (yi != M - 1 and (xi == N - 1 or y[yi] > x[xi])):
            ans = (ans + y[yi] * nx) % mod
            ny += 1
#            print y[yi]
            yi = yi + 1
            
        else:
            ans = (ans + x[xi] * ny) % mod
            nx += 1
#            print x[xi]
            xi = xi + 1
    
    
    print (ans % mod)

if __name__=="__main__":
    
    T = int(raw_input())
    
    for i in xrange(T):
        line = str(raw_input())
        M = int(line.split()[0])
        N = int(line.split()[1])
        line = str(raw_input())
        y = map(int, line.split())
        line = str(raw_input())
        x = map(int, line.split())
        solve(M, N, x, y)