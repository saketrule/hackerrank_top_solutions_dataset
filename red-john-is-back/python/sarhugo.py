# Enter your code here. Read input from STDIN. Print output to STDOUT
import math

def primes(n):
    sieve = [True] * n
    for i in xrange(3,int(n**0.5)+1,2):
        if sieve[i]:
            sieve[i*i::2*i]=[False]*((n-i*i-1)/(2*i)+1)
    return [2] + [i for i in xrange(3,n,2) if sieve[i]]

T = int(raw_input())
for i in xrange(0, T):
   N = int(raw_input())
   Ntimes = N / 4
   s = 0
   for j in xrange(0, Ntimes + 1):
        bricks4 = j
        bricks1 = (N - j * 4)
        s += math.factorial(bricks4 + bricks1) / (math.factorial(bricks4) * math.factorial(bricks1))
   if s < 4:
        print s - 1
   else:
        print len(primes(s + 1))