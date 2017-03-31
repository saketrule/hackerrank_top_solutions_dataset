tc = int(raw_input())
arr = map(int, raw_input().split())
arr.sort()
low,up = map(int, raw_input().split())

best = (0,0) #(value,-number)
if (low<=arr[0]): best = (arr[0]-low,-low)
if (up>=arr[-1]): best = max(best, (up-arr[-1],-up))

for i in xrange(tc-1):
    avg = int((arr[i] + arr[i+1])/2)
    dif = int((arr[i+1] - arr[i])/2)
    if (avg > up and arr[i] <= up):
        avg = up
        dif = up - arr[i]
    elif (avg < low and arr[i+1] >= low):
        avg = low
        dif = arr[i+1] - low
    if(up>=avg>=low):best = max(best, (dif, -avg))

print -best[1]
    