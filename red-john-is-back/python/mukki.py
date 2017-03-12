# Enter your code here. Read input from STDIN. Print output to STDOUT
import math
t=input()
ft=[1]*41
for z in xrange(1,41):
    ft[z]=z*ft[z-1]
for z in xrange(t):
    n=input()
    k=n/4
    M=0
    result=0
    isprime=True
    for x in xrange(k+1):
        M+=(ft[n-3*x])/(ft[n-4*x]*ft[x])
    for i in xrange(2,M+1):
        for j in xrange(2,int(math.sqrt(i))+1):
            if i%j==0:
                isprime=False
                break
            else:
                isprime=True
        if isprime:
            result+=1
    print result
        

        
    