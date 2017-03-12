#include <iostream>
#include <string.h>
#include <vector>
#include <algorithm>
#include <map>
#include <cmath>
#include <set>
#include <fstream>
#include <cstdio>
#define ll long long
using namespace std;

template <class T>
class pq{
public:
	pq();
	~pq();
	bool empty();
	void enq(T n);
	T deq();
private:
	vector<T> v;
};
template <class T>
pq<T>::pq()
{
    T filler;
    v.push_back(filler);
}

template <class T>
pq<T>::~pq()
{
}
template <class T>
bool pq<T>::empty()
{
    return v.size()==1;  
}
template <class T>
void pq<T>::enq(T newValue)
{
    v.push_back(newValue);                                      
    int i=v.size()-1;
    while(v[i]>v[i/2]&&i>1){
        T a=v[i];
        v[i]=v[i/2];
        v[i/2]=a;
        i/=2;
    }
}
template <class T>
T pq<T>::deq()
{
    T value=v[1];
    T last=v[v.size()-1];
    v.pop_back();
    if(v.size()>1) v[1]=last;
    int i=1;
    while(true){
        if(2*i<v.size()){
            if(2*i+1<v.size()){
                if(v[i]<v[2*i]||v[i]<v[2*i+1]){
                    if(v[2*i]<v[2*i+1]){
                        T a=v[i];
                        v[i]=v[2*i+1];
                        v[2*i+1]=a;
                        i=2*i+1;
                    } else{
                        T a=v[i];
                        v[i]=v[2*i];
                        v[2*i]=a;
                        i=2*i;
                    }
                } else break;
            } else if(v[i]<v[2*i]){
                T a=v[i];
                v[i]=v[2*i];
                v[2*i]=a;
                i=2*i;
            } else break;
        } else break;
    }
    return value;
}

struct node{
	int name;
	ll dist;
	bool vis;
};
bool operator >(const node& a, const node& b){
	return a.dist<b.dist;
}
bool operator <(const node& a, const node& b){
	return a.dist>b.dist;
}
ll inf=1000000000000;
node nd[3001];
int main(){
		int n,m;
		cin>>n>>m;
		for(int i=1; i<=n; i++){
			nd[i].name=i;
			nd[i].dist=inf;
			nd[i].vis=false;
		}
		vector<pair<int,ll> > g[3001];
		pq<node> pq;
		for(int i=0; i<m; i++){
			int a,b;
			ll c;
			cin>>a>>b>>c;
			g[a].push_back(make_pair(b,c));
			g[b].push_back(make_pair(a,c));
		}
		int s;
		cin>>s;
		nd[s].dist=0;
		pq.enq(nd[s]);
		while(!pq.empty()){
			node cur=pq.deq();
			if(!cur.vis){
				nd[cur.name].vis=true;
				for(int i=0; i<g[cur.name].size(); i++){
					if(!nd[g[cur.name][i].first].vis){
						ll alt=g[cur.name][i].second;
						if(alt<=nd[g[cur.name][i].first].dist){
							nd[g[cur.name][i].first].dist=alt;
							pq.enq(nd[g[cur.name][i].first]);
						}
					}
				}
			}
		}
		ll total=0;
		for(int i=1; i<=n; i++){
			total+=nd[i].dist;
		}
		cout<<total<<endl;
	return 0;
}