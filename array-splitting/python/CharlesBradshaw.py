# Enter your code here. Read input from STDIN. Print output to STDOUT
T = int(raw_input())
for test in range(T):
    N = int(raw_input())
    A = [int(i) for i in raw_input().split() if i!='0']
    if len(A)==0:
        print N-1
    else:
        N=len(A)
        cumSum = [0]*(N+1)
        for i in range(0,N):
            cumSum[i+1] = cumSum[i]+A[i]
        possibles = [[0,N]]
        moves = 0
        best = 0;
        #print A,cumSum
        while len(possibles)>0:
            #print possibles
            nextPossibles = []
            for pos in possibles:
                suma =cumSum[pos[1]]-cumSum[pos[0]]
               
                #print pos, suma
                if suma%2==0 and (suma/2+cumSum[pos[0]]) in cumSum:
                        dingus=cumSum.index(suma/2+cumSum[pos[0]])
                    #if not [cumSum.index(suma/2+cumSum[pos[0]]),pos[1]] in nextPossibles:
                        nextPossibles.append([dingus,pos[1]])
                        #print 'ha',[cumSum.index(suma/2+cumSum[pos[0]]),pos[1]]
                    #if not [pos[0],cumSum.index(suma/2+cumSum[pos[0]])] in nextPossibles:
                        nextPossibles.append([pos[0],dingus])
                        #print 'hmmm',[pos[0],cumSum.index(suma/2+cumSum[pos[0]])]
            moves+=1
            possibles=nextPossibles
        print moves-1
