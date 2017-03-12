# Enter your code here. Read input from STDIN. Print output to STDOUT
n = input()
sticks = map(int, raw_input().split())
sticks.sort()
curr_cut = sticks[0]
cut_length = 1
cut_num = n
for i in xrange(1, n):
    if sticks[i] != curr_cut:
        curr_cut = sticks[i]
        print cut_num
        cut_num -= cut_length
        cut_length = 1
    else:
        cut_length += 1
print cut_num
    