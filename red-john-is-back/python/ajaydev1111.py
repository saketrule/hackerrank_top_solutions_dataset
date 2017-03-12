import sys

def fact(n):
	f=1;
	for i in range(1,n+1):
		f=f*i
	return f
	
	
def isprime(number): 
    if number==2 or number==3:  
        return 1  	
    if number<=1 or number%2==0:  
        return 0  
    check=3  
    maxneeded=number  
    while check<maxneeded+1:  
        maxneeded=number/check  
        if number%check==0:  
            return 0  
        check+=2  
    return 1 
	
	
t=int(raw_input())
for y in range(1,t+1):
	n=int(raw_input())
	if n<4:
		s=1
	if n==4:
		s=2
	if n>4:
		r1=n%4
		r2=(n-r1)/4
		r=r1+r2
		s=1+(fact(r)/(fact(r1)*fact(r2)))
		while r2>=2:
			r2=r2-1
			r1=r1+4
			r=r1+r2
			s=s+(fact(r)/(fact(r1)*fact(r2)))
	x=0;
	for w in range(1,s+1):
		if isprime(w):
			x=x+1
	print x
		
