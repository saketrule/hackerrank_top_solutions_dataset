# Enter your code here. Read input from STDIN. Print output to STDOUT
def find(s,su,f,k):
    if(su not in s or f%2 !=0):
        if(k==1):
            return 1
        elif(len(s)>1 and f in s):
            return 1
        else:
            if(f==1 and len(s)!=0 and len(s)!=1):
                return 1
            else:           
                return 0
    else:
        oo=f/2
        p=s.index(su)
        #print s[0:(p+1)],su-oo,oo
        #print s[(p+1):],su+oo,oo 
        r=find(s[0:(p+1)],su-oo,oo,2)+1
        e=find(s[(p+1):],su+oo,oo,2)+1
        return max(r,e)
    
    
for i in range(int(raw_input())):
    l=int(raw_input())
    a=map(int,raw_input().split())
    #print a
    for j in range(1,l,1):
        a[j]=a[j-1]+a[j]
    su=a[-1]
    #print 'sum',su
    if(su%2==0 and su!=0 and su/2 in a ):
        print find(a,su/2,su/2,1)
    else:
        if(su==0):
            print l-1
        else:
            print 0