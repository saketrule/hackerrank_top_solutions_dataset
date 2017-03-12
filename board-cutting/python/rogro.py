import sys

with sys.stdin as fs:
    T = int(fs.readline())
    for _ in range(T):
        x_length = y_length = 1
        points = 0
        M, N = map(int, fs.readline().split())
        y = sorted(map(int, fs.readline().split()))
        x = sorted(map(int, fs.readline().split()))
        
        while y or x:
            x_max = x[-1] if x else -1
            y_max = y[-1] if y else -1
            
            if y_max >= x_max:
                points += y_length * y.pop()
                x_length += 1
            else:
                points += x_length * x.pop()
                y_length += 1
        
        print (points % (10 ** 9 + 7))