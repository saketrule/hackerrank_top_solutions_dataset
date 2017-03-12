


def num_primes_less(n):
	if n < 2:
		return 0
	if n==2:
		return 1
	last = 3
	primes = [2,3]
	i = 5
	while i <= n:
		prime = True
		for p in primes:
			if i % p == 0:
				prime = False
				break
			if p > i**.5:
				break
		if prime:
			last = i
			primes.append(i)
		i += 2
	return len(primes)



import sys

f = sys.stdin
# f = open('brick.in')

t = int(f.readline())
ns = []
for i in range(t):
	ns.append(int(f.readline()))


OPT = {}
maxn = max(ns)
OPT[0,0] = 1
maxi = (maxn+1)/4
for i in range(maxi+1):
	OPT[i,0] = 0
for n in range(maxn+1):
	OPT[0,n] = 1
for i in range(1,maxi+1):
	for n in range(1,maxn+1):
		OPT[i,n] = OPT[i,n-1] + OPT[i-1,n-1]




for test in range(t):
	n = ns[test]
	max_horiz = int(n / 4)

	count = 0
	for h in range(max_horiz+1):
		count += OPT[h,n-(3*h)]

	print num_primes_less(count)
