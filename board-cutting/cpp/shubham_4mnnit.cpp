#include<cstdio>
#include<iostream>
#include<string>
#include<vector>
#include<algorithm>
#include<cmath>
#include<climits>
#define LL long long
#define MIN(a,b) (a<b?a:b)
#define MAX(a,b) (a>b?a:b)
#define getcx getchar_unlocked
#define LOOP(i,a,b,k) for(int i=a;i<b;i+=k)
#define DOWNTO(i,a,b,k) for(int i=a;i>b;i-=k)

#define MOD 1000000007

using namespace std;

/*inline void inp( int &n )//fast input function
{
   n=0;
   int ch=getcx();int sign=1;
   while( ch < '0' || ch > '9' ){if(ch=='-')sign=-1; ch=getcx();}

   while(  ch >= '0' && ch <= '9' )
           n = (n<<3)+(n<<1) + ch-'0', ch=getcx();
   n=n*sign;
}

int gcd(int a,int b){
    if(b==0)
        return a;
    return gcd(b,a%b);
}*/



int main()
{
    int t;
    cin>>t;
    while(t--){
        vector< pair<LL,int> > v;
        LL m,n;
        LL sum=0,r=1,c=1;
        cin>>m>>n;
        LOOP(i,1,m,1){
            int t;
            cin>>t;
            v.push_back(make_pair(t,0));
        }

        LOOP(i,1,n,1){
            int t;
            cin>>t;
            v.push_back(make_pair(t,1));
        }
        sort(v.begin(),v.end());
        reverse(v.begin(),v.end());
        for(vector< pair<LL,int> >::iterator it=v.begin();it!=v.end();it++){
            if((*it).second==0){
                sum+=((*it).first)*c;
                r++;
            }
            else{
                sum+=((*it).first)*r;
                c++;
            }
            if(sum>=MOD)
                sum%=MOD;
        }
        cout<<sum<<"\n";
    }
	return 0;
}
