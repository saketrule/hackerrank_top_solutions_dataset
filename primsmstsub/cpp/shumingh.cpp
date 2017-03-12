#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>

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


int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        int N, M;
        cin >> N >> M;
        vector<vector<Edge>> adj_edges(N);
        for(int m=0; m < M; m++){
            int x,y,r;
            cin >> x >> y >> r;
            Edge e(x-1,y-1,r);
            adj_edges[x-1].push_back(e);
            adj_edges[y-1].push_back(e);
        }
        int S;
        cin >> S;
        int mst_weight=0;
        int mst_size=0;
        auto comp = [](Edge const & e, Edge const & f){
            return e.r > f.r;
        };
        priority_queue<Edge, vector<Edge>, decltype(comp)> pq(comp);
        auto queue_adj_edges = [&](int v){
            for(auto e: adj_edges[v]) {
                pq.push(e) ;     
            }
        };
        vector<int> visited(N, 0);
        queue_adj_edges(S-1);
        visited[S-1] = 1;
        while(mst_size<N-1 && !pq.empty()) {
            Edge ne(pq.top());
            pq.pop();
            if(visited[ne.v] && visited[ne.w]) 
                continue;
            mst_weight += ne.r;
            mst_size++;
            int new_v = visited[ne.v]? ne.w:ne.v;
            queue_adj_edges(new_v);
            visited[new_v] = 1;
        }
    cout << mst_weight << endl;
    
    return 0;
}
