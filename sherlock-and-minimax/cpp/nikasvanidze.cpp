#include<math.h>
#include<algorithm>
#include<cstdlib>
#include<iostream>
#include<stdio.h>
#include<map>
#include<ext/hash_map>
#include<ext/hash_set>
#include<set>
#include<string>
#include<assert.h>
#include<vector>
#include<time.h>
#include<queue>
#include<deque>
#include<sstream>
#include<stack>
#include<sstream>
#define MA(a,b) ((a)>(b)?(a):(b))
#define MI(a,b) ((a)<(b)?(a):(b))
#define AB(a) (-(a)<(a)?(a):-(a))
#define X first
#define Y second
#define mp make_pair
#define pb push_back
#define pob pop_back
#define ep 0.0000000001
#define pi 3.1415926535897932384626433832795

using namespace std;
using namespace __gnu_cxx;
const int N=1000111;
const int INF=1000000008;
int n,m,i,j,k,l,r,p;
int a[1000],b[1000];
void sol(int x)
{
    if (l<=x && x<=r){
    int kk=INF;
    for (j=1;j<=n;j++)
        kk=MI(kk,AB(x-a[j]));
    if (kk>k || (kk==k && x<m))
    {
        k=kk;
        m=x;
    }}
}
int main()
{
    int t;
        cin>>n;
     for (i=1;i<=n;i++)
        cin>>a[i];
        sort(a+1,a+n+1);
     cin>>l>>r;
     m=l;
     k=0;
     sol(l);
     sol(r);
     for (i=1;i<n;i++)
        sol((a[i]+a[i+1])/2);
cout<<m<<endl;
    return 0;
}
