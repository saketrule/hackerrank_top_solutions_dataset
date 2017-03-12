# Enter your code here. Read input from STDIN. Print output to STDOUT
import math
T = int(raw_input())
def primes_sieve(limit):
	limitn = limit+1
	not_prime = [False] * limitn
	primes = []
	for i in range(2, limitn):
		if not_prime[i]:
			continue
		for f in xrange(i*2, limitn, i):
			not_prime[f] = True
		primes.append(i)
	return primes
def ans(a):
    if a<4:
        return 1
    else:
        n=a/4
        r=a%4
        s=0
        for i in range(n+1):
            s+=math.factorial(a-(3*i))/(math.factorial(i)*(math.factorial(a-(4*i))))
        return s
p = primes_sieve(1000000)
i = 0
for i in range(T):
    N = int(raw_input())
    M = ans(N)
    P = sum([1 for i in p if i <= M])
    print P
