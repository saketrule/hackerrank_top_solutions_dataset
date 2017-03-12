#include <bits/stdc++.h>
#define _ ios_base::sync_with_stdio(false);cin.tie(0);
using namespace std;
#define pb push_back
#define pob pop_back
#define pf push_front
#define pof pop_front
#define mp make_pair
#define all(a) a.begin(),a.end()
#define bitcnt(x) __builtin_popcountll(x) 
#define MOD 5000000007
#define total 500005
#define M 1000000007
typedef unsigned long long int uint64;
typedef long long int int64;
/*
inline void fast(int &x) {
    register int64 c = getchar_unlocked();
    x = 0;
    int neg = 0;
    for(; ((c<48 || c>57) && c != '-'); c = getchar_unlocked());
    if(c=='-') {
        neg = 1;
        c = getchar_unlocked();
    }
    for(; c>47 && c<58 ; c = getchar_unlocked()) {
        x = (x<<1) + (x<<3) + c - 48;
    }
    if(neg)
        x = -x;
}
*/
int a[1005];
priority_queue<int,vector<int>,greater<int> >p,q;
int main()
{
	int n,i,j;
	cin>>n;
	for(i=0;i<n;i++){
		cin>>j;
		p.push(j);
	}
	int cnt=0;
	while(1){
		if(cnt%2==0){
			if(p.empty())
			return 0;
			cout<<p.size()<<endl;
			int z=p.top();
			while(!p.empty()){
				int tmp=p.top()-z;
				p.pop();
				if(tmp!=0)
				q.push(tmp);
			}
			cnt++;
			continue;
		}
		else{
			if(q.empty())
			return 0;
			cout<<q.size()<<endl;
			int z=q.top();
			while(!q.empty()){
				int tmp=q.top()-z;
				q.pop();
				if(tmp!=0)
				p.push(tmp);
			}
			cnt++;
			continue;
		}
	}
	
	return 0;
}