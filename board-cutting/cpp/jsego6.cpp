#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

long long modu = 1000000007;

int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */ 
    int T; cin>>T;
    while(T--){
        int M,N; cin>>M>>N;
        vector<pair<long long,int> > v;
        long long cur;
        for(int i=0;i+1<M;i++){ cin>>cur; v.push_back(make_pair(cur,0)); }
        for(int i=0;i+1<N;i++){ cin>>cur; v.push_back(make_pair(cur,1)); }
        sort(v.rbegin(),v.rend());
        long long yc = 1,xc = 1,res=0;
        for(int i=0;i<int(v.size());i++){
            if(v[i].second == 0){
                res = (res + xc * v[i].first)%modu;
                yc++;
            }
            else{
                res = (res + yc * v[i].first)%modu;
                xc++;
            }
        }
        cout<<res<<endl;
    }
    return 0;
}
