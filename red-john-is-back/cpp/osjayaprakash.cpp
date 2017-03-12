#include <iostream>

using namespace std;

typedef unsigned long long ull;

ull num[41]={1,1,1,1};

bool NotPrime[327286]={0};
ull cprime[327286];

int pre(){
	for(int i=4;i<=40;i++){
		num[i] = num[i-4]  + num[i-1];
		//cout << num[i] << endl; 
	}
	NotPrime[0] = NotPrime[1] = true;
	NotPrime[2] = false;
	for(unsigned i=0;i<327286;i++){
		if(NotPrime[i]==true)
			continue;
		if(i<7286)
			for(unsigned j=i*i; j<327286; j+=i)
				NotPrime[j] = true;
	}
	for(int i=1;i<327286;i++){
		cprime[i] = cprime[i-1] + ((NotPrime[i])?0:1);
		//cout << i << ' ' << cprime[i] << endl;
	}
}

int solve(){
	int N;
	cin >> N;
	//cout << num[N] << endl;
	cout << cprime[ num[N] ] << endl;
}

int main(){
	pre();
	int T;
	cin >> T;
	while(T--){
		solve();		
	}
	return 0;
}