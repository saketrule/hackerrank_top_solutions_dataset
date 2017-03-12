/*
	Aditya Sharma
	IIIT Allahabad
*/
#include <bits/stdc++.h>
#define si(n) scanf("%d",&n)
#define sl(n) scanf("%lld",&n)
#define ss(n) scanf("%s",n)
#define fr(i, n) for(int i = 0; i < n; i++)
#define REP(i, a, b) for (int i = int(a); i <= int(b); i++)
#define REN(i, a, b) for (int i = int(a); i >= int(b); i--)
#define ms(i, n) memset(i, n, sizeof(i))
#define INF 1002000000
#define MOD 1000000007
typedef long long LL;
using namespace std;
int main ()
{
	//freopen ("input.txt","r",stdin);
	//freopen ("output.txt","w",stdout);
	int n, m;
	vector<vector<pair<int, int> > > vec;
    si(n);si(m);
    vec.resize(n);
    int a, b, c;
    fr(i, m) {
        si(a);
        si(b);si(c);
        --a;
        --b;
        vec[a].push_back(make_pair(b, c));
        vec[b].push_back(make_pair(a, c));
    }
    int st;
    si(st);
    st--;
    priority_queue<pair<int, int>, vector<pair<int, int> > , greater<pair<int, int> > > q;
    q.push(make_pair(0, st));
    vector<int> d, visited;
    visited.resize(n);
    d.resize(n);
    fr(i, n)
        d[i] = INF, visited[i] = 0;
    d[st] = 0;
    visited[st] = 1;
    int ans = 0;
    while(!q.empty()) {
        pair<int, int> curr = q.top();
        int f = curr.first, s = curr.second;
        q.pop();
        if(d[s] < f)
            continue;
        ans += f;
        visited[s] = 1;
        //cout<<s<<endl;
        fr(i, vec[s].size()) {
            if(visited[vec[s][i].first] == 0 && vec[s][i].second < d[vec[s][i].first]) {
                d[vec[s][i].first] = vec[s][i].second;
                q.push(make_pair(d[vec[s][i].first], vec[s][i].first));
            }
        }
    }
    printf("%d", ans);
	return 0;
}
