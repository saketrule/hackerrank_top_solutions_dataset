#include <iostream>
#define FOR(i,a)    for(int i = 0;i < (a);i++)
#define REP(i,a,b)  for(int i = (a);i < (b);i++)
#define SZ(a)   ((signed)a.size())
#define PB(a)   push_back(a)
#include <string>
#include <algorithm>
#include <vector>
#include <map>
#include <set>
#include <cstdio>

using namespace std;

int l[1000001],k = 0;
bool prime[1000001] = {0};

void preprocess(){
    for(long long i = 2;i < 1000001;i++){
        if(!prime[i]){
            l[k++] = i;
            for(long long j = i*i ; j <= 1000000;j += i)    prime[j] = 1;
        }
    }
}

void solve(){
    long long arr[101];
    arr[1] = 1,arr[2] = 1,arr[3] = 1,arr[4] = 2;
    arr[0] = 1;
    REP(i,5,41)    arr[i] = (arr[i - 1] + arr[i - 4]);
    int c = 0;
    int v;
    cin>>v;
    FOR(i,k){
        if(l[i] <= arr[v])  c++;
        else    break;
    }
    cout<<c<<"\n";
}

int main(){
    //freopen("input.txt","r",stdin);
    //freopen("output.txt","w",stdout);
    int t;
    preprocess();
    cin >> t;
    FOR(i,t)    solve();
    return 0;
}