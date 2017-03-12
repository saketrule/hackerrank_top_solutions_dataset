import sys

def get_cost(y,x):
  cost=0
  y_count = 1
  x_count = 1
  while 1:
    if x and y:
      if y[0]> x[0]:
        cost += y[0]*x_count
        y_count +=1
        y.remove(y[0])
      else:
        cost += x[0]*y_count
        x_count +=1
        x.remove(x[0])
    elif not y:
      for el in x:
        cost += el*y_count
        x_count +=1
      break
    elif not x:
      for el in y:
        cost += el*x_count
        y_count +=1
      break
  return cost % 1000000007 
    
t= int (raw_input())
for i in range(t):
  
  m,n = map (int, raw_input().split())
  y = map (int, raw_input().split())
  x = map (int, raw_input().split())

  y= sorted(y, reverse=True)
  x= sorted(x, reverse=True)
  
  cost  = get_cost(y,x)
  print cost


