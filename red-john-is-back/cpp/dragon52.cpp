#include <iostream>
#include <cstdio>

using namespace std;

int f[45];

bool prime[300000];
int cumalative[300000];

void sieve(){
	for(int i =0 ; i < 300000 ; i++){
		prime[i] = true;
	}
	prime[0] = prime[1] = false;
	for(int i = 2; i*i<300000;i++){
		if(prime[i]){
			for(int j = i*i;j<300000;j+=i)
				prime[j] = false;
		}
	}
	for(int i = 1; i < 300000;i++)
		cumalative[i] = cumalative[i-1] + (prime[i]);
	//for(int i =0 ; i < 100 ;i++) if(prime[i]) cout <<i << " ";
}

int main(){
	sieve();
	
	f[0] = 1;
	for(int i = 1; i <=40 ; i++){
		if(i-4>=0) f[i]+=f[i-4];
		f[i]+=f[i-1];
	}
	//cout << cumalative[10] <<" " <<cumalative[11] <<" " << endl;
	int t,N;
	cin >> t;
	while(t--){
		cin >> N;
		cout << cumalative[f[N]] << endl;
	}

}