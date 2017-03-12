p=1000000007
T=input()
for _ in range(T):
    M,N = (int(i) for i in raw_input().split())
    yarr=[int(i) for i in raw_input().split()]
    xarr=[int(i) for i in raw_input().split()]
    yarr.sort()
    xarr.sort()

    total=0
    ymul=N
    xmul=M
    while yarr or xarr:
        if len(xarr)==0:
            total=(total+yarr.pop(0)*ymul)%p
            xmul-=1
        elif len(yarr)==0:
            total=(total+xarr.pop(0)*xmul)%p
            ymul-=1
        elif yarr[0]<xarr[0]:
            total=(total+yarr.pop(0)*ymul)%p
            xmul-=1
        else:
            total=(total+xarr.pop(0)*xmul)%p
            ymul-=1
    print total  %p      