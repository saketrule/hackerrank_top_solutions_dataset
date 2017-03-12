__author__ = 'digao'
__url__ = 'https://www.hackerrank.com/contests/feb14/challenges/sherlock-and-watson'

import sys,StringIO

mod = 10**9+7

def main(input):
    T = int(input.readline())

    for i in xrange(T):
        M,N = map(int,input.readline().split())
        y = map(int,input.readline().split())
        y.sort()
        x = map(int,input.readline().split())
        x.sort()
        multx = 1
        multy = 1
        count = 0
        while y or x:
            a,b =(0,0)
            if len(x): a = x[-1]
            if len(y): b = y[-1]
            isA = len(x) >= len(y)
            if a>b:
                isA = True
            elif a<b:
                isA = False

            if isA:
                multy+=1
                count = (count + multx*a)%mod
                x.pop()
            else:
                multx+=1
                count = (count + multy*b)%mod
                y.pop()
        print count







teste = """1
6 4
2 1 3 1 4
4 1 2"""

#main(StringIO.StringIO(teste))
main(sys.stdin)