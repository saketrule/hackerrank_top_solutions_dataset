#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>
#include <functional>
using namespace std;

bool done[3003];

vector< vector< pair<int, int> > > X(3003);
priority_queue<pair<int, int>, vector< pair<int, int> >, greater< pair<int, int> > > Q;

int main() {
    
    int N, M;
    scanf("%d %d", &N, &M);
    
    while(M--)
    {
        int A, B, C;
        scanf("%d %d %d", &A, &B, &C);
        
        X[A].push_back(make_pair(B, C));
        X[B].push_back(make_pair(A, C));
    }
    
    done[1] = 1;
    
    for(vector< pair<int, int> >::iterator it=X[1].begin(); it!=X[1].end(); it++)
        Q.push(pair<int, int>(it->second, it->first));
    
    int ans = 0;
    
    while(!Q.empty())
    {
        pair<int, int> x = Q.top();
        Q.pop();
        
        int indx = x.second, wt = x.first;
        
        if(!done[indx])
        {
            done[indx] = 1;
            ans+= wt;
            
            for(vector< pair<int, int> >::iterator it=X[indx].begin(); it!=X[indx].end(); it++)
                Q.push(pair<int, int>(it->second, it->first));
        }
    }
    
    printf("%d\n", ans);
    
    return 0;
}
