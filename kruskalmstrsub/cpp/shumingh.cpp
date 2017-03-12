#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>
#include <numeric>

using namespace std;
class Edge {
    public:
    int v, w, r;
    Edge(int x, int y,int r):v(x),w(y),r(r){}
    Edge(const Edge & e){
        w = e.w;
        v = e.v;
        r = e.r;
    }
};

class UF {
    int N;
    vector<int> id;
    vector<int> size;
    int root(int i){
        while(id[i]!= i)
           i = id[i];
        return i;
    }
    public:
    UF(int N) {
        this->N = N;
        id.resize(N);
        iota(id.begin(), id.end(), 0);
        size.resize(N,1);
    }
    
    int connected(int i, int j){
        return root(i)==root(j);
    }
    void connect(int i, int j){
      int ri = root(i);
      int rj = root(j);
      if(ri == rj) return;
      int ra, rb;
      if(size[ri] < size[rj]){
          ra = ri;
          rb = rj;
      } else {
          ra = rj;
          rb = ri;
      }
      id[ra] = rb;
      size[rb] += size[ra];
    }
};


int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        int N, M;
        cin >> N >> M;
        UF uf(N);
        vector<Edge> edges;
        for(int m=0; m < M; m++){
            int x,y,r;
            cin >> x >> y >> r;
            Edge e(x-1,y-1,r);
            edges.push_back(e);
        }
        int S;
        cin >> S;
        int mst_weight=0;
        int mst_size=0;
        auto comp = [](Edge const & e, Edge const & f){
            if(e.r != f.r) 
                return e.r > f.r;
            else 
                return e.w + e.v > f.w + f.v;
        };
        priority_queue<Edge, vector<Edge>, decltype(comp)> pq(comp, edges);

        while(mst_size<N-1 && !pq.empty()) {
            Edge ne(pq.top());
            pq.pop();
            if(uf.connected(ne.v, ne.w)) 
                continue;
            uf.connect(ne.v, ne.w);
            mst_weight += ne.r;
            mst_size++;
        }
    cout << mst_weight << endl;
    
    return 0;
}

