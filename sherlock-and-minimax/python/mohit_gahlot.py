
class qObj:
	def __init__(self, P, Q, data):
		self.P = P
		self.Q = Q
		self.data = data
def isValid(val, obj):
	return val >= obj.P and val <= obj.Q

def getInput():
	N = int(raw_input())
	ar = []
	for x in raw_input().strip().split(' '):
		ar.append(int(x))
	ar.sort()
	P,Q = raw_input().strip().split(' ')
	P = int(P)
	Q = int(Q)

	return qObj(P, Q, ar)

if __name__ == '__main__':
	ipt = getInput()
	i = 0
	glmx = 0
	save = -1
	pmn = -1
	qmn = -1
	for x in ipt.data:
		if i != 0:
			mid = int(ipt.data[i-1]) + int(x - ipt.data[i-1]) / 2	
			mx = min(abs(x-mid), abs(ipt.data[i-1]-mid))
			#print("mid=",mid)
			if mx > glmx and isValid(mid, ipt):
				glmx = mx
				save = mid
		if abs(x - ipt.P) < pmn or pmn < 0:
			pmn = abs(x - ipt.P)
			sP = ipt.P
		if abs(x - ipt.Q) < qmn or qmn < 0:
			qmn = abs(x - ipt.Q)
			sQ = ipt.Q
		i = i+1
	#print(save,abs(ipt.data[0]-ipt.P)," ", glmx, " ", ipt.data[0], " ", ipt.P)

	if ipt.P <= ipt.data[0] and abs(ipt.data[0]-ipt.P) >= glmx:
		glmx = abs(ipt.data[0] - ipt.P)
		save = ipt.P
	if ipt.Q >= ipt.data[-1] and abs(ipt.data[-1] - ipt.Q) > glmx:
		glmx = abs(ipt.data[-1] - ipt.Q)
		save = ipt.Q
	if pmn > glmx:
		glmx = pmn
		save = sP
	if qmn > glmx:
		save = sQ
	print(save)