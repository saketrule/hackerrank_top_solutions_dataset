#include <bits/stdc++.h>
#define SZ(X) ((int)(X).size())
#define ALL(X) (X).begin(), (X).end()
#define REP(I, N) for (int I = 0; I < (N); ++I)
#define REPP(I, A, B) for (int I = (A); I < (B); ++I)
#define RI(X) scanf("%d", &(X))
#define RII(X, Y) scanf("%d%d", &(X), &(Y))
#define RIII(X, Y, Z) scanf("%d%d%d", &(X), &(Y), &(Z))
#define DRI(X) int (X); scanf("%d", &X)
#define DRII(X, Y) int X, Y; scanf("%d%d", &X, &Y)
#define DRIII(X, Y, Z) int X, Y, Z; scanf("%d%d%d", &X, &Y, &Z)
#define RS(X) scanf("%s", (X))
#define CASET int ___T, case_n = 1; scanf("%d ", &___T); while (___T-- > 0)
#define MP make_pair
#define PB push_back
#define MS0(X) memset((X), 0, sizeof((X)))
#define MS1(X) memset((X), -1, sizeof((X)))
#define LEN(X) strlen(X)
#define F first
#define S second
typedef long long LL;
using namespace std;
const int SIZE = 111;
int a[SIZE];
const int INF = 2e9;
int test(int x,int n){
    int mi=INF;
    REP(i,n){
        mi=min(mi,abs(a[i]-x));
    }
    return mi;
}
int main(){
    DRI(N);
    REP(i,N)RI(a[i]);
    sort(a,a+N);
    DRII(P,Q);
    int an,ma=-1;
    if(test(P,N)>ma){
        ma=test(P,N);
        an=P;
    }
    if(test(Q,N)>ma){
        ma=test(Q,N);
        an=Q;
    }
    REPP(i,1,N){
        if((a[i]+a[i-1])/2>=P&&(a[i]+a[i-1])/2<=Q)
            if(test((a[i]+a[i-1])>>1,N)>ma){
                ma=test((a[i]+a[i-1])>>1,N);
                an=(a[i]+a[i-1])>>1;
            }
    }
    cout<<an<<endl;

    return 0;
}
