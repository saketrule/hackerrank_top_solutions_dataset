def find_steps(array, left, right):
    if left >= right:
        return 0
    
    summ = sum(array[left:right + 1])
    left_sum = 0
    l = left
    while ((l <= right) and ((left_sum * 2) < summ)):
        left_sum = left_sum + array[l];
        l = l + 1
           
    while ((l < right) and (((left_sum + array[l]) * 2) == summ)):
        left_sum = left_sum + array[l]
        l = l + 1  
        
    if ((left_sum * 2) != summ):
        return 0
    
    return max(find_steps(array, left, l - 1) + 1, find_steps(array, l, right) + 1)
        

test_cases = input()

for _ in range(0, test_cases):
    n = input()
    array = map(int, raw_input().split())
    
    if sum(array) == 0:
        print n - 1
    else:
        print find_steps(array, 0, n - 1)
    
    