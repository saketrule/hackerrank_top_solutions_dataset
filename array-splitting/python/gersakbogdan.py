# Enter your code here. Read input from STDIN. Print output to STDOUT
class Solution:
    def game(self, score, A):
        sumA = sum(A)
        if len(A) == 1 or sumA % 2 != 0:
            return score
        if sum(A) == 0:
            return score + len(A) - 1
        s = p = 0
        for i in xrange(len(A)):
            s += A[i]
            p = i
            if s == sumA/2:
                break
        
        if p == len(A) - 1:
            return score
        #if p >= len(A) / 2:
        #    return self.game(score+1, A[:p+1])
        #return self.game(score+1, A[p+1:])
        sc1 = self.game(score+1, A[:p+1])
        sc2 = self.game(score+1, A[p+1:])
        if sc1 > sc2:
            return sc1
        return sc2
        
    
if __name__ == '__main__':
    s = Solution()
    n = int(raw_input())
    for i in xrange(n):
        raw_input()
        print s.game(0, map(int, raw_input().strip().split()))
    
