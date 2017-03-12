x1=0
x2=0
import math
def fact(n):
    if n<=0:
        return 1
    elif n==1:
        return 1
    return n*fact(n-1)
def comb(x,y):
    return fact(x)/(fact(x-y)*fact(y))

sum=0
cnt=0
xcnt=1
primes=[1]*300000
Result=[0]*300000
def prime(n):
    xcnt=0
    for i in range(2,int(math.sqrt(n))+1):
        if primes[i]:
            for j in range(i*i,n,i):
                primes[j]=0
    for i in range(2,300000):
        if primes[i]:
            xcnt+=1
            Result[i]=xcnt
        else:
            Result[i]=xcnt
t=int(raw_input())
prime(300000)
while 1:
    sum=0
    if t==0:
        break
    t-=1
    n=int(raw_input())
    x1=n/4
    x2=n%4
    while 1:
        if x1<0:
            break
        sum+=comb(x1+x2,x1)
        x1-=1
        x2+=4
    print Result[sum]