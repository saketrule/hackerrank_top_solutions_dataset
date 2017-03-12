#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <map>
#include <set>
#include <tuple>
using namespace std;


int main() {
    long x,y,m,n,r,i, s, nST=1, tcost=0, minx,miny, minr=1000000000;

    
    cin >> n >> m;
    vector<bool> insubtree(n+1, false);
    vector<map<long, long>> edges(n+1);
    map<long, long>::iterator it;
    set<tuple<long,long,long,long>> availSTedges;  // r, u+v+r, node in ST, node out of ST
    set<tuple<long,long,long,long>>::iterator itCheap;
    
    for (i=0 ; i<m ; i++) {
        cin >> x >> y >> r;
        
        if (edges[x].count(y)) {
            edges[y][x] = edges[x][y] = min(edges[x][y],r);
        } else {
            edges[y][x] = edges[x][y] = r;
        }
        
        if (r<minr) {
            minx = x;
            miny = y;
        }
    }
    
    cin >> s;
    insubtree[s] = true;
    
    for (it=edges[s].begin() ; it!=edges[s].end() ; ++it) {
        availSTedges.insert(make_tuple(it->second, s + it->first + it->second, s, it->first));
    }
    
    while (nST<n) {
        itCheap = availSTedges.begin();
        tcost += get<0>(*itCheap);
        long STnode = get<2>(*itCheap);
        long newnode = get<3>(*itCheap);
        insubtree[newnode] = true;
        for (it=edges[newnode].begin() ; it!=edges[newnode].end() ; ++it) {
            if (!insubtree[it->first]) {
                availSTedges.insert(make_tuple(it->second, it->second + newnode + it->first, newnode, it->first));
            } else {
                availSTedges.erase(make_tuple(it->second, it->second + newnode + it->first, it->first, newnode));
            }
        }
        nST++;
    }
    
    cout << tcost << endl;
    return 0;
}

