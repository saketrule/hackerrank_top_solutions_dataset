n = int(raw_input())
a = map(int,raw_input().split(' '))
a = sorted(a)
temp = a[0]
j = 1
print n
while j < n:
    if a[j] == temp:
        j +=1 
    else:
        j+=1
        temp=a[j-1]
        print n-j+1