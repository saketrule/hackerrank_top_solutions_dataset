N = int(raw_input())
v = sorted(map(int, raw_input().split(' ')))

while len(v):
	print len(v)
	minimum = v[0]
	v = [i-minimum for i in v if i > minimum]