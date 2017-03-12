# Enter your code here. Read input from STDIN. Print output to STDOUT

N = input()
a = map(int, raw_input().split())

aFreq = [0] * 1001
for i in a:
    aFreq[i] += 1
    
for i in xrange(1,1001):
    aFreq[i] += aFreq[i-1]

for i in xrange(1,1001):
    if aFreq[i] == aFreq[i-1]: continue
        
    print (N - aFreq[i-1])
    
    