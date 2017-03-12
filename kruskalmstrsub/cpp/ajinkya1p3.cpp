#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int parent[3003], size[3003];
vector< pair<int, pair<int, int> > > X;

bool lowest_sum(pair<int, pair<int, int> >i, pair<int, pair<int, int> >j)
{
    return (i.first<j.first)||((i.first==j.first && i.second.first+i.second.second==j.second.first+j.second.second));
}

int find_parent(int i)
{
    if(parent[i]==parent[parent[i]])
        return parent[i];
    
    parent[i] = find_parent(parent[i]);
    
    return parent[i];
}

void unite(int A, int B)
{
    A = find_parent(A);
    B = find_parent(B);
    
    if(size[A]<size[B])
    {
        size[B]+= size[A];
        parent[A] = B;
    }
    else
    {
        size[A]+= size[B];
        parent[B] = A;
    }
}

int main() {
    
    //int T;
    //scanf("%d", &T);
    
    //while(T--)
    //{
        int N, M;
        scanf("%d %d", &N, &M);
        
        for(int i=1; i<=N; i++)
        {
            parent[i] = i;
            size[i] = 1;
        }
        
        X.clear();
        
        for(int i=0; i<M; i++)
        {
            int A, B, C;
            scanf("%d %d %d", &A, &B, &C);
            
            X.push_back(make_pair(C, make_pair(A, B)));
        }
        
        sort(X.begin(), X.end(), lowest_sum);
        
        int ans = 0;
        
        for(int i=0; i<M; i++)
        {
            if(find_parent(X[i].second.first)==find_parent(X[i].second.second))
                continue;
            
            unite(X[i].second.first, X[i].second.second);
            ans+= X[i].first;
        }
        
        printf("%d\n", ans);
    //}
    
    return 0;
}
