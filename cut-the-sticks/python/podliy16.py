# Enter your code here. Read input from STDIN. Print output to STD
N = int( raw_input() )
a = map(int, raw_input().split(" ") )
res = list()

exit = False
while exit == False:
    exit = True
    small = 1001
    for el in a:
        if el<small and el>0:
            small = el
    count = 0
    for index, el in enumerate(a):
        a[index] -= small
        if a[index]>0:
            count += 1
            exit = False
    if count != 0:
        res.append(count)
    
print N
for el in res:
    print el