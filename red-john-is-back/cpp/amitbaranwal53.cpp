/*
	amitbaranwal53@gmail.com
	IIT (BHU),VARANASI
*/
#include <set>
#include <map>
#include <queue>
#include <deque>
#include <stack>
#include <ctime>
#include <cmath>
#include <vector>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <climits>
#include <iostream>
#include <algorithm>

using namespace std;

int temp,i,j,k,T;

#define CASE s(T);while(T--)
#define FOR(I,A,B) for(I=A;I<B;++I)
#define REP(i,n) FOR(i,0,n)
#define FORR(I,J,K) for(I=J;I>K;--I)
#define JAM(N) printf("Case #%d: ",N)
#define INPUT(A) freopen(A,"r",stdin);
#define OUTPUT(A) freopen(A,"w",stdout);

#define all(x) x.begin(), x.end()
#define fill(a, val) memset(a, val, sizeof(a))
#define INDEX(a, val) (lower_bound(all(a), val) - a.begin())

#define EXP 1e-10
#define INF (int)1e9

#define F first
#define S second
#define MP make_pair
#define PB push_back

typedef long long LL;
typedef vector<int> VI;
typedef pair<int,int> PII;
typedef pair<LL,LL> PLL;
typedef pair<int,PII> TRI;
typedef unsigned long long ULL;

#define deb(n) cout<<"(<<< DEBUG "<<n<<" >>>)"<<endl;

#define s(n)					scanf("%d",&n)
#define sl(n) 					scanf("%ld",&n)
//#define sll(n) 					scanf("%lld",&n)
#define sf(n) 					scanf("%f",&n)
#define slf(n) 					scanf("%lf",&n)
#define ss(n) 					scanf("%s",n)

LL pow_r(LL d,LL n)
{
	LL x=1ll;
	while(n>0)
	{
		if(n&1)	x=(x*d);
		d=(d*d);
		n>>=1;
	}
	return x;
}
/*
inline int next(){
    char c;int num=0;
    c=getchar_unlocked();
    while(!(c>='0' && c<='9')) c=getchar_unlocked();
    while(c>='0' && c<='9'){
        num=(num<<3)+(num<<1)+c-'0';
        c=getchar_unlocked();
    }
    return num;
}
*/
//main code is here
int a[100];
int prime[300000],ans[300000];
void pre()
{
	a[1]=1;a[2]=1;a[3]=1;a[4]=2;
	FOR(i,5,45)
	a[i]=a[i-1]+a[i-4];
	//deb(a[40]);
	for(i=2;i<300000;++i)
		if(!prime[i])
		{
			for(j=2*i;j<300000;j+=i)
			prime[j]=1;
		}
	ans[2]=1;
	for(i=3;i<300000;++i)
	ans[i]=ans[i-1]+(1-prime[i]);
	//deb(ans[5])
}
int n;
main()
{
	//INPUT("input.txt");
	//OUTPUT("output.txt");
	pre();
	CASE
	{
	cin>>n;
	cout<<ans[a[n]]<<endl;}
	return 0;
}
