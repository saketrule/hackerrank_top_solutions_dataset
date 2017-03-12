N=raw_input()
arr=[int(i) for i in raw_input().split()]

while len(arr):
    print len(arr)
    arr=filter(lambda x:x!=min(arr),arr)