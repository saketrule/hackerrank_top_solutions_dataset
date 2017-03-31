#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

long long A[(1<<14)+5];

int solve(int left, int right)
{
    int ans = 0;
    long long sum = A[right] - A[left-1];
    
    if(sum%2 || left==right)
        return 0;
    
    for(int i=left; i<right; i++)
    {
        if(A[i]-A[left-1]>sum/2)
            return 0;
        
        if(A[i]-A[left-1]==sum/2)
            return 1 + max(solve(left, i), solve(i+1, right));
    }
    
    return 0;
}

int main() {
    
    int T;
    scanf("%d", &T);
    
    while(T--)
    {
        int N;
        scanf("%d", &N);
        
        for(int i=1; i<=N; i++)
            scanf("%lld", &A[i]);
        
        for(int i=1; i<=N; i++)
            A[i]+= A[i-1];
        
        cout << solve(1, N) << endl;
    }
    
    return 0;
}
