// olimpoUS
#include <iostream>
#include <cstdio>
#include <vector>
#include <map>
#include <queue>
#include <deque>
#include <stack>
#include <set>
#include <bitset>
#include <cmath>
#include <complex>
#include <algorithm>
#include <cstring>
#include <cstdlib>
#include <stdlib.h>
#include <utility>
#include <ctime>
#include <limits.h>
using namespace std;

#define BIT(x) __builtin_popcount(x)
#define REP(i,n) for(int i=0; i<n;++i)
#define MAX 105
#define MOD 1000000007
vector<int>T[MAX];
int N[MAX];
bool S[MAX];
bool Is[MAX];
int n,m,x,y;
void DFS(int x , int p){
     N[x] = 1;
     Is[x] = 1;
     REP(i , T[x].size() )
     if(T[x][i]!=p){
       DFS(T[x][i],x);
       N[x] += N[ T[x][i] ];
     }     
}
int main(){
    cin >> n >> m;
    while(m--){
       cin >> x >> y;
       x--;y--;
       T[x].push_back(y);
       T[y].push_back(x);           
    }
    int r = 0;
    REP(i,n){
       if(Is[i]){
         if( (N[i] != 0) && (N[i]%2==0) ) r++;          
       }else{
             DFS(i,-1);
       }               
    }
    cout << r << endl;
  return 0;    
}
