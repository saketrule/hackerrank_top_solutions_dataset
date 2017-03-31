# Enter your code here. Read input from STDIN. Print output to STDOUT
T=int(raw_input())

for i in range(T):
    N = int(raw_input())
    A = map(int, raw_input().split())
    points = 0
    queue=[]
    queue.append((0, len(A)))
    sum_val = reduce(lambda x,y:x+y, A)
    if sum_val%2==1:
        print 0
        continue
    while len(queue)>0:
        new_queue = []
        for B in queue:
            len_B = B[1]-B[0]
            if len_B<=1:
                continue
            new_sum = 0
            for i in range(len_B):
                new_sum+=A[B[0]+i]
                if new_sum*2==sum_val:
                    new_queue.append((B[0], B[0]+i+1))
                    new_queue.append((B[0]+i+1, B[1]))
                    break
                elif new_sum*2>sum_val:
                    break
        if len(new_queue)>0:
            points +=1
        sum_val/=2
        if sum_val%2==1:
            break
        queue = new_queue
    print points      