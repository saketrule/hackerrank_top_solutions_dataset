#include <bits/stdc++.h>

using namespace std;

typedef long long LL;
typedef unsigned long long ULL;
typedef pair <int, int> pnt;

#define tup(name, pos) get<(pos)>(name)
#define FI(i,a) for (auto i=0; i<(a); ++i)
#define FOR(i,s,e) for (auto i=(s); i<(e); ++i)
#define MEMS(a,b) memset(a,b,sizeof(a))
#define pb push_back
#define mp make_pair
#define ALL(a) a.begin(),a.end()
#define V(t) vector < t >
#define MAX(a,b) ((a)>(b)?(a):(b))
#define MIN(a,b) ((a)<(b)?(a):(b))
#define ABS(a) ((a)>(0)?(a):(-(a)))
#define ALL(a) a.begin(),a.end()

#define lbl cerr << "debug_label" << endl;
#define dval(x) cerr<<#x<<"="<<(x)<<"\n"

void dout(){cerr << endl;}

template <typename Head, typename... Tail>
void dout(Head H, Tail... T) {
  cerr << H << ' ';
  dout(T...);
}


constexpr double pi  = 3.14159265358979323846264338327950288419716939937511;
constexpr double eps = 1e-9;

//*
char ch_ch_ch[1<<20];
inline string gs() {scanf("%s",ch_ch_ch); return string(ch_ch_ch);}
inline string gl() {gets(ch_ch_ch); return string(ch_ch_ch);}
inline int gi() {int x; scanf("%d",&x); return x;}
//*/

constexpr LL inf = 3ll*1000000000ll*1000000000ll;

// code starts here


V(int) a;

void solution() {
  int n = gi();
  FI(i,n) a.pb(gi());
  sort(ALL(a));
  int p = 0;
  a.pb(a.back());
  while (p < n) {
    printf("%d\n",n-p);
    while (p < n && a[p+1] == a[p]) ++p;
    ++p;
  }



}


// code ends here

int main(int argc, char** argv) {

#ifdef IGEL_ACM
  freopen("in.txt","r",stdin);
  freopen("out.txt", "w", stdout);
  clock_t beg_time = clock();
#else
  //freopen(argv[1],"r",stdin);
  //freopen("path.in", "r", stdin);
  //freopen("path.out", "w", stdout);
#endif

  solution();

#ifdef IGEL_ACM
  fprintf(stderr,"*** Time: %.3lf ***\n",1.0*(clock()-beg_time)/CLOCKS_PER_SEC);
#endif

  return 0;
}


























