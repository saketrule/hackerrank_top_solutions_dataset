n = int(raw_input())
a = map(int,raw_input().split())
p,q = map(int,raw_input().split())

a.sort()

mx = max(a)
mn = min(a)

mx = 0
imx = 0

for i in range(n-1):
    if (a[i+1]-a[i])/2 > mx and a[i]+(a[i+1]-a[i])/2 >= p and a[i]+(a[i+1]-a[i])/2 <= q:
        mx = (a[i+1]-a[i])/2
        imx = a[i] + mx
        

mn = 9999999999999999

for i in a:
    mn = min(mn,abs(i-p))
    
if mn >= mx:
    imx = p
    
mn = 9999999999999999

for i in a:
    mn = min(mn,abs(i-q))
    
if mn > mx:
    imx = q
    
print imx