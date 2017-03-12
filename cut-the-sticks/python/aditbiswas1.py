n = int(raw_input())
sticks = map(int, raw_input().split())


while(len(sticks)> 0):
	smallest = min(sticks)
	l = len(sticks)
	sticks = filter(lambda x: x > smallest, sticks)
	sticks = map(lambda x: x-smallest, sticks)
	print l
	