#include <iostream>
#include <cstdio>
#include <algorithm>
#include <cstdlib>
#include <cstring>
#include <string>
#include <vector>
#include <queue>
#include <deque>
#include <stack>
#include <fstream>
#include <set>
#include <map>
#include <cmath>
#pragma comment(linker,"/STACK:16777216")
#define inf 1000000000
#define MP make_pair
#define PI acos(-1.0)
#define eps 1e-9
#define pii pair<int,int>
#define FT first
#define SD second
#define MAXN 300000
using namespace std;
typedef long long i64;
typedef unsigned long long u64;
bool comp[MAXN+1];
int sump[MAXN+1];

int main()
{
    for (int i=2; i*i <= MAXN; ++i)
        if (comp[i]==false)
        {
            for (int j=i*2; j<=MAXN; j+=i)
                comp[j]=true;
        }
    comp[0]=comp[1]=true;
    for (int i=2; i<= MAXN; ++i)
        sump[i]=sump[i-1]+(!comp[i]);
    
    int T, N;
    cin >> T;
   
    
    while (T--)
    {
        cin >> N;
        int dp[50]={};
        for (int i=0;i<=3;++i)
            dp[i]=1;
        dp[4]=2;
        for (int i=5;i<=N; ++i)
        {
            for (int j=i;j>=4;--j)
                dp[i]+=dp[j-4];
            dp[i]+=1;
        }
        
        cout << sump[dp[N]] << endl;
    }
   
    return 0;
}