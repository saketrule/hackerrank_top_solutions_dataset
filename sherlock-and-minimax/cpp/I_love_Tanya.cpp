/*
*/

//#pragma comment(linker, "/STACK:16777216")
#include <fstream>
#include <iostream>
#include <string>
#include <complex>
#include <math.h>
#include <set>
#include <vector>
#include <map>
#include <queue>
#include <stdio.h>
#include <stack>
#include <algorithm>
#include <list>
#include <ctime>
#include <memory.h>
 
#define y0 sdkfaslhagaklsldk
#define y1 aasdfasdfasdf
#define yn askfhwqriuperikldjk
#define j1 assdgsdgasghsf
#define tm sdfjahlfasfh
#define lr asgasgash
 
#define eps 1e-11
//#define M_PI 3.141592653589793
#define bs 1000000007
#define bsize 256

using namespace std;

long n,l,r,ar[200000];
long tl,tr;
long p,q;

long solve(long x)
{
 tl=p;
 tr=q;
 for (int i=0;i<n;i++)
 {
  if (ar[i]-x<tl){tl=max(tl,ar[i]+x);}
 }
 if (tl>tr)return -1;
 return tl;
}

int main(){
//freopen("dagger.in","r",stdin);
//freopen("dagger.out","w",stdout);
//freopen("C:/input.txt","r",stdin);
//freopen("C:/output.txt","w",stdout);
ios_base::sync_with_stdio(0);
//cin.tie(0);

cin>>n;
for (int i=0;i<n;i++)
 cin>>ar[i];
 sort(ar,ar+n);

cin>>p>>q;

l=0;
r=1e9;
while (l<r)
{
 long mid=l+r+1;
 mid/=2;
 if (solve(mid)!=-1)
 l=mid;
 else r=mid-1;
}

cout<<solve(l)<<endl;

cin.get();cin.get();
return 0;}

