
import fileinput 
from math import sqrt

C = 4 # first composite number


def primes_lt( N):
	primes = []
	n=2    
	while n <= N:
		is_prime = True
		for p in primes:
			if n % p == 0 :
				is_prime = False
				break
			if p > sqrt(n):
				break
		if is_prime:
			primes.append(n)  
		n = n+1
	return len(primes)
	

def choose(n,k):
    k = min(n-k, k)
    if (k>n or k<0):
        return 0
    if k == 0:
        return 1
    ch = 1.0
    for i in range(1,k+1):
        ch = ch * (n-k + i) / i
    ch = int(ch +0.5)
    return ch
    
    
def getM(N):
    max_blocks = int(N/C)
    M = 0L
    for Cblocks in range(max_blocks+1):
        ones = N - C*Cblocks
        allblocks = ones + Cblocks
        M_k = choose(allblocks, ones)
        M = M + M_k
    return M
    
        

def main():
    input = fileinput.input()
    testcases = int(next(input))
    for i  in range(testcases):
        line = next(input)
        line = line.strip()
        if line != "":
            N = int(line)
            M = getM(N)
            P = primes_lt(M)
            print P
            
            
		
			
	
	
if __name__ == "__main__":	
	main()