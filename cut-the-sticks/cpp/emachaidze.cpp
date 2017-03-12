#include<iostream>
#include<cstring>
#include<stdio.h>
#include<algorithm>
#include<cmath>
#include<vector>
#include<queue>
#include<stack>
#include<map>
#include<set>
#define mp make_pair
#define pb push_back
#define MAX(a,b) (a>b?a:b)
#define MIN(a,b) (a<b?a:b)
#define F first
#define S second
#define ll long long
#define pp pair<int,int>
#define P 1000000007ll
using namespace std;
const int n_max=300005;
int n,m,i,j,x,k,ans,cur;
vector<int> v;



main()
{scanf("%d",&n);
 for(i=1;i<=n;i++){
    scanf("%d",&x);
    v.pb(x);
 }
 sort(v.begin(),v.end());

 k=0;
 while(k<v.size()){
    ans=(int)v.size()-k;
    cur+=(v[k]-cur);


    while(k<v.size() && v[k]-cur<=0)k++;

    printf("%d\n",ans);
   // system("pause");
 }
}

