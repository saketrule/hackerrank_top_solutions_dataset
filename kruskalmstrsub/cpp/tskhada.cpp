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

struct edge{
	int node1;
	int node2;
	ll len;
};
bool operator >(const edge& a, const edge& b){
	if(a.len==b.len) return a.node1+a.node2<b.node1+b.node2;
	return a.len<b.len;
}
bool operator <(const edge& a, const edge& b){
	if(a.len==b.len) return a.node1+a.node2>b.node1+b.node2;
	return a.len>b.len;
}
ll inf=1000000000000;
vector<edge> ed;
int p[3002];
int r[3002];
int find(int x){
	if(p[x]==x) return x;
	p[x]=find(p[x]);
	return p[x];
}
void uni(int x, int y){
	int px=find(x);
	int py=find(y);
	if(r[px]>r[py]) p[py]=px;
	else if(r[px]<r[py]) p[px]=py;
	else{
		p[px]=py;
		r[py]++;
	}
}
int main(){
	
		int n,m;
		cin>>n>>m;
		for(int i=1; i<=n; i++){
			p[i]=i;
			r[i]=0;
		}
		pq<edge> pq;
		for(int i=0; i<m; i++){
			int a,b;
			ll c;
			cin>>a>>b>>c;
			edge e;
			e.node1=a;
			e.node2=b;
			e.len=c;
			ed.push_back(e);
		}
		int s;
		cin>>s;
		ll total=0;
		for(int i=0; i<m; i++) pq.enq(ed[i]);
		while(!pq.empty()){
			edge cur=pq.deq();
			if(find(cur.node1)!=find(cur.node2)){
				uni(cur.node1,cur.node2);
				total+=cur.len;
			}
		}
		cout<<total<<endl;
	
	return 0;
}