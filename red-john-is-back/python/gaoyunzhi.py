import sys,math
N=40

def main():
    g=sys.stdin
#    ways=[1]*4
#    for n in xrange(4,N+1):
#        ways.append(ways[-1]+ways[-4])
#    M=ways[N]+10     
#    is_prime=[True]*(M+1)
#    is_prime[0]=False
#    is_prime[1]=False
#    for x in xrange(3, int(math.sqrt(M))+1, 2):
#        if not is_prime[x]: continue
#        for y in xrange(x,M/x+1,2):
#            is_prime[x*y]=False
#    ans=[0]*(N+1)
#    sum=0
#    for x in xrange(1,N+1):
#        for n in xrange(((ways[x-1]+1)/2)*2+1, ways[x]+1, 2):
#            if is_prime[n]:
#                sum+=1
#        if ways[x-1]+1 <= 2 <= ways[x]:
#            sum+=1
#        ans[x]=sum       
#    print ans[:41] 
#    print ways[:41]
#    print range(20)   
  
    ans=[0, 0, 0, 0, 1, 2, 2, 3, 4, 4, 6, 8, 9, 11, 15, 19, 24, 32, 42, 53, 68, 91, 119, 155, 204, 269, 354, 462, 615, 816, 1077, 1432, 1912, 2543, 3385, 4522, 6048, 8078, 10794, 14475, 19385]
    inp=map(int,g.readlines())
    n=inp.pop(0)
    for x in inp[:n]:
        print ans[x]

if __name__=='__main__':
    main()
