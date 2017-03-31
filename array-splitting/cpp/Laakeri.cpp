#include <bits/stdc++.h>
#define F first
#define S second
using namespace std;
typedef long long ll;

ll a[101010];

int get(int l, int r){
	if (l>=r) return 0;
	ll su=0;
	for (int i=l;i<=r;i++){
		su+=a[i];
	}
	ll ss=0;
	for (int i=l;i<=r;i++){
		ss+=a[i];
		if (ss*2==su){
			return 1+max(get(l, i), get(i+1, r));
		}
	}
	return 0;
}

int main(){
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	int t;
	cin>>t;
	for (int tc=0;tc<t;tc++){
		int n;
		cin>>n;
		int f=0;
		for (int i=0;i<n;i++){
			cin>>a[i];
			if (a[i]>0) f=1;
		}
		if (f==0){
			cout<<n-1<<endl;
		}
		else{
			cout<<get(0, n-1)<<endl;
		}
	}
}