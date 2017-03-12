T = int(raw_input())

combin = [[1]]

for i in range(1,50):
	combin.append([1])
	for j in range(1,i):
		combin[i].append(combin[i-1][j-1] + combin[i-1][j])
	combin[i].append(1)

sieve = [1 for i in range(500000)]
sieve[0] = 0
sieve[1] = 0
for i in xrange(2,1000):
	for j in xrange(2*i,500000,i):
		sieve[j] = 0

for i in xrange(1,500000):
	sieve[i] += sieve[i-1]

for i in range(T):
	n = int(raw_input())
	b = n/4
	m = 0
	for bb in range(b+1):
		up = n - bb * 4
		if up > up + bb:
			m += 1
		else:
			m += combin[up + bb][up]
	print sieve[m]
