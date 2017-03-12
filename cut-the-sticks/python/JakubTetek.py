n = raw_input()
sticks = map(int, raw_input().split(" "))

while sticks:
    print(len(sticks))
    minimum = min(sticks)
    sticks = [x-minimum for x in sticks if x-minimum]
    