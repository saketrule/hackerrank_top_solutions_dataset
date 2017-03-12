# Enter your code here. Read input from STDIN. Print output to STDOUT

if __name__=="__main__":
    
    N = int(raw_input())
    
    a = map(int, str(raw_input()).split(" "))
    
    a.sort()
    
    num = len(a)
    
 #   print num
    
#    print num[0]
    
    last = 0
    
    i = 0
    
    while True:
        
        if(i == num):
            break
        
        while (i < num - 1) and (a[i] == a[i + 1]):
            i = i + 1
        
        print N - last
        
        last = i + 1
        
        i = i + 1
    
    
 #   print a