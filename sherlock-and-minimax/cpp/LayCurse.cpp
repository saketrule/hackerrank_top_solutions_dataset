#include<bits/stdc++.h>
using namespace std;

#define REP(i,a,b) for(i=a;i<b;i++)
#define rep(i,n) REP(i,0,n)

#define mygc(c) (c)=getchar_unlocked()
#define mypc(c) putchar_unlocked(c)

void reader(int *x){int k,m=0;*x=0;for(;;){mygc(k);if(k=='-'){m=1;break;}if('0'<=k&&k<='9'){*x=k-'0';break;}}for(;;){mygc(k);if(k<'0'||k>'9')break;*x=(*x)*10+k-'0';}if(m)(*x)=-(*x);}
void reader(int *x, int *y){reader(x);reader(y);}
void writer(int x, char c){int i,sz=0,m=0;char buf[10];if(x<0)m=1,x=-x;while(x)buf[sz++]=x%10,x/=10;if(!sz)buf[sz++]=0;if(m)mypc('-');while(sz--)mypc(buf[sz]+'0');mypc(c);}

int N, A[1000], P, Q;

int test[100000], test_size;

int check(int k){
  int i;
  int res = 2000000000;
  rep(i,N) res = min(res, abs(A[i]-k));
  return res;
}

int main(){
  int i, j, k;
  int res, mx;

  reader(&N);
  rep(i,N) reader(A+i);
  reader(&P,&Q);

  sort(A,A+N);

  mx = -1;
  test_size = 0;
  test[test_size++] = P;
  test[test_size++] = Q;
  REP(i,1,N) {
    k = (A[i] + A[i-1])/2;
    if(P<=k && k<=Q) test[test_size++] = k;
    k = (A[i] + A[i-1])/2 + 1;
    if(P<=k && k<=Q) test[test_size++] = k;
  }
  sort(test,test+test_size);

  mx = -1;
  rep(i,test_size){
    k = check(test[i]);
    if(k > mx) mx = k, res = test[i];
  }
  writer(res, '\n');

  return 0;
}
