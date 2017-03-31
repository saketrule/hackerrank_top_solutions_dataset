# Enter your code here. Read input from STDIN. Print output to STDOUT

#recursive method that will tear through that garbage
#abuses range sum like a boss
#we'll see how many test cases it passes though :/ optimiiiize
def nikitaMOD(potdict):
    arr = potdict['arr']
    diff = potdict['diff']
    #if there's only one element, we can't split it up 
    if len(arr) == 1:
        return []
    
    #if the last element of rangesum array is odd, then we can't have equals 
    lastnum = arr[-1] - diff
    if lastnum %2 != 0:
        return []
    
    #breaker is the number we'll be looking for 
    breaker = lastnum / 2
    
    magicind = -1
    for ind,num in enumerate(arr):
        if breaker == num - diff:
            magicind = ind + 1
            break
        elif breaker < num - diff:
            return []
        
    #pretty sure I don't have to do this check, but we'll see
    if magicind == -1:
        return []
    
    return [{'arr': arr[:magicind], 'diff': diff}, {'arr': arr[magicind:], 'diff': diff+breaker}]
    #left = nikita(arr[:magicind],diff) + 1
    #right = nikita(arr[magicind:],diff+breaker) + 1
    #return left if left > right else right

cases = input()
for ignore in xrange(cases):
    n = input()
    arr = map(int,raw_input().split())
    cur = 0
    rangesum = []
    for num in arr:
        cur += num
        rangesum.append(cur)
        
    #print nikita(rangesum,0)
    ans = 0
    initial = {'arr': rangesum, 'diff': 0}
    lists = [initial]
    while lists:
        newlists = []
        for pot in lists:
            dank = nikitaMOD(pot)
            for thing in dank:
                newlists.append(thing)
                
        if newlists:
            lists = newlists
            ans += 1
        else:
            break
            
    
    print ans