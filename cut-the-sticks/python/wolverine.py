x=raw_input()
sticks=raw_input().split()
sticks=map(int,sticks)

while(len(sticks)+1!=1):
    minz=min(sticks)
    print len(sticks)
    #print sticks
    for i in range(0,len(sticks)):
        sticks[i]=sticks[i]-minz
    while(0 in sticks):   
        xx=sticks.index(0)
        sticks.pop(xx)
    
    
