'''
from memoize import *
@memoize
def g(n): return 0 if n < 0 else 1 if n == 0 else g(n-1) + g(n-4)
L = g(40)
pi = [0,0] + [1]*(L-1)
for i in xrange(2,L+1):
	if pi[i]:
		for j in xrange(i<<1,L+1,i):
			pi[j] = 0
	pi[i] += pi[i-1]

for n in xrange(41):
	print "%s," % (pi[g(n)]),
'''
from __future__ import print_function
ans = [0, 0, 0, 0, 1, 2, 2, 3, 4, 4, 6, 8, 9, 11, 15, 19, 24, 32, 42, 53, 68, 91, 119, 155, 204, 269, 354, 462, 615, 816, 1077, 1432, 1912, 2543, 3385, 4522, 6048, 8078, 10794, 14475, 19385]

[print(ans[input()]) for v in xrange(input())]
