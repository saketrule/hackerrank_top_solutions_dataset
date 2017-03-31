import sys

def solve(f):
	N = int(f.readline())
	tokens = f.readline().split()
	A = [int(x) for x in tokens]
	tokens = f.readline().split()
	P = int(tokens[0])
	Q = int(tokens[1])

	A.sort()
	best = 0
	ind = -1
#	print A,P,Q
	if P<A[0]:
		B = A[:]
		B.reverse()
		B.append(A[0]-2*(A[0]-P))
		B.reverse()
		A = B[:]
	if Q>A[-1]:
		A.append(A[-1]+2*(Q-A[-1]))
#	print A,P,Q
	for i in range(0,len(A)-1):
		mid = (A[i]+A[i+1])/2
#		print mid
		if mid<=Q and mid>=P:
#			print A[i+1],A[i]
			if best<A[i+1]-mid and A[i+1]-mid<=mid-A[i]:
				best = A[i+1]-mid
				ind = mid
			elif best<mid-A[i] and mid-A[i]<=A[i+1]-mid:
				best = mid-A[i]
				ind = mid
		else:
			if mid>Q:
				if A[i]<=Q and Q-A[i]>best and Q-A[i]<=A[i+1]-Q:
					best = Q-A[i]
					ind = Q
			else:
				if A[i+1]>=P and A[i+1]-P>best and A[i+1]-P<=P-A[i]:
					best - A[i+1]-P
					ind = P
#		print best,ind
	print ind
					
				
if __name__=="__main__":

	solve(sys.stdin)

