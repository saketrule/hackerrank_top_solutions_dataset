# Enter your code here. Read input from STDIN. Print output to STDOUT

def choose(n, k):
    """
    A fast way to calculate binomial coefficients by Andrew Dalke (contrib).
    """
    if 0 <= k <= n:
        ntok = 1
        ktok = 1
        for t in xrange(1, min(k, n - k) + 1):
            ntok *= n
            ktok *= t
            n -= 1
        return ntok // ktok
    else:
        return 0

limit = 1000000
a = [True] * limit                          # Initialize the primality list
a[0] = a[1] = False

for (i, isprime) in enumerate(a):
    if isprime:
        for n in range(i*i, limit, i):     # Mark factors non-prime
            a[n] = False
            
primes = []
count = 0
for i in range(limit):
    if a[i] == True:
        count += 1
    primes.append(count)
    
def primecount(total):
    print primes[total]
    
def getM(n):
    # All standing up: 1
    # You can always take away 4 widths
    k = n / 4
    # Number of widths one can replace
    # k - 1 = 
    # n - 1 = j+1
    total = 1
    # This loops from 1 to k-1
    for j in range(1,k+1):
        #print "("
        #print (j+1) + (n - j*4) - 1
        #print (n - j*4)
        val = choose((j+1) + (n - j*4) - 1, (n - j*4))
        #print val
        #print ")"
        total += val
    return total
    
t = input()
for i in range(t):
    n = input()
    total = getM(n)
    primecount(total)