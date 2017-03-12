n = int(raw_input().strip())
sticks = [int(i) for i in raw_input().strip().split()]
sticks.sort()
newItem = sticks[0]
count = len(sticks)
print count
count -= 1
for i in range(1, len(sticks)):
    if sticks[i] != newItem:
        newItem = sticks[i]
        print count
    count -= 1
