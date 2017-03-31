#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

long getMax(vector<long> &v,vector<long> &SDP,int s,int e) {
    if(s>=e)
        return 0;
    long shalf = (SDP[e+1]-SDP[s])/2;
    //int i = lower_bound(SDP.begin()+s,SDP.end(),shalf)-SDP.begin()
    for(int i=s;i<e;i++) {
        long S = SDP[i+1]-SDP[s];
        if(S==shalf) {
            return 1+max(getMax(v,SDP,s,i),getMax(v,SDP,i+1,e));
        }
    }
    return 0;
}

void solve() {
    int N;
    cin>>N;
    vector<long> v(N);
    vector<long> SDP(N+1,0);
    
    for(int i=0;i<N;i++) {
        cin>>v[i];
        v[i]*=2;//twice all
        SDP[i+1]=SDP[i]+v[i];
    }
    cout<<getMax(v,SDP,0,N-1)<<endl;
}
int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */ 
    int T;
    cin>>T;
    while(T--)
        solve();
    return 0;
}
