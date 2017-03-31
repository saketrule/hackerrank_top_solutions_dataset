#!/usr/bin/env python
#coding: utf
'''
    Nikita just came up with a new array game. The rules are as follows:
    Initially, there is an array A, containing N integers.
    In each move, Nikita must partition the array into 2 non-empty parts such that
    the sum of the elements in the left partition is equal to the sum of the elements in the right partition.
    If Nikita can make such a move, she gets 1 point; otherwise, the game ends.
    After each successful move, Nikita discards either the left partition or the right partition
    and continues playing by using the remaining partition as array A.

    Given A, finds and prints the maximum number of points that can be scored.
'''
def nikita(A):
    N = len(A)
    if N <= 1:
        return 0
    # All zeros?
    zeros = A.count(0)
    if zeros == N:
        return zeros - 1
    S = sum(A)
    s = 0
    for i, a in enumerate(A[:-1], 1):
        s += a
        if s == S-s:
            return 1 + max(nikita(A[:i]), nikita(A[i:]))
    return 0

#from sys import stderr
if __name__ == '__main__':
    '''
        Input Format:
        First line of the input has an integer T. T cases follow. 
        Each test case begins with an integer N.
        In the next line, N integers follow representing the elements of array .
    '''
    T = int(raw_input().strip())
    assert 1 <= T <= 10

    for t in range(T):
#        print >>stderr, 'Test:', t+1 
        # Read number of nodes and number of edges
        N = int(raw_input().strip())
        assert 1 <= N <= 2**14
        A = map(int, raw_input().strip().split())
        assert N == len(A)
        for a in A:
            assert 0 <= a <= 1e9
        '''
            Output Format:
            For each test case, print Nikita's maximum possible score on a new line.
        '''
        print nikita(A)