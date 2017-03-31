def Partition(nums, l, r, total, cur):
  l_total = 0
  r_total = total
  part_point = -1
  for i in xrange(l, r+1):
    l_total += nums[i]
    r_total -= nums[i]
    if l_total == r_total:
      part_point = i
      cur += 1
      break
  if part_point != -1 and r_total != 0:
    return max(Partition(nums, l, part_point, l_total, cur),
               Partition(nums, part_point+1, r, r_total, cur))
  else:
    return cur

T = input()
for _ in xrange(T):
  N = input()
  nums = map(int, raw_input().split())
  total = sum(nums)
  if total == 0:
    print len(nums)-1
  else:
    print Partition(nums, 0, len(nums)-1, total, 0)