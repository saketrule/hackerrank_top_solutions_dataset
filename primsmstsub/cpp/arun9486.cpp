#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <bits/stdc++.h>
using namespace std;

int GetIndex(int *arr, int size, int val){
    for(int i=0;i<size;i++){
        if(arr[i] == val)
            return i;
    }
    
    return -1;
}

int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */   
    
    int N,M;cin>>N>>M;
    int key[N+3] = {INT_MAX};
    vector<pair<int, int>> v[N+3];
    vector<int> mstset;
    int ans=0;
    
    for(int i=0;i<N+3;i++)
        key[i] = INT_MAX;
    
    for(int i=0;i<M;i++){
        int x,y,r;cin>>x>>y>>r;

        pair<int,int> p =make_pair(y,r);
        v[x].push_back(p);
        p = make_pair(x,r);
        v[y].push_back(p);
    }

    key[1] = 0;
    while( mstset.size() < N){
        int m = *min_element( key, key+N+3);
        int T = GetIndex(key, N+3, m);
        
        if( T == -1){
            cout<<ans;
            return 0;
        }else{
            ans += key[T];
            mstset.push_back(T);
            key[T] = INT_MAX;
        }

        for(int i=0;i<v[T].size();i++){
            pair<int,int> p = v[T][i];
            int u = p.first;
            int l = p.second;
            
            if( find(mstset.begin(), mstset.end(), u) == mstset.end()){
                if( key[u] > l){
                    key[u] = l;
                }
            }
        }
    }
    
    cout<<ans;
    return 0;
}
