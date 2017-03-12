#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <cstdio>
#include <cmath>
#include <deque>

using namespace std;

#define MAXN 1024*1024
#define x first
#define y second
#define mp make_pair
#define pb push_back
#define sz(a) (int)(a.size())
#define all(a) a.begin(), a.end()
#define R(a) ((a)%M)

typedef long long ll;
typedef vector<int> VI;
typedef vector<VI> VVI;
typedef pair<int,int> PI;
typedef vector<PI> VPI;
typedef vector<VPI> VVPI;
typedef vector<VVPI> VVVPI;
typedef vector<VVI> VVVI;

int N, T, f[50], a[300000];
VI P;
int cnt;

int main ()
{
    f[0] = 1;
    f[1] = 1;
    f[2] = 1;
    f[3] = 1;
    for (int n = 4; n <= 40; n += 1)
        f[n] = f[n-1]+f[n-4];
    
    for (int n = 2; n <= f[40]; n += 1)
    {
        P.pb(n);
        for (int i = 0; i < sz(P) && P[i]*P[i] <= n; i += 1)
        {
            if(n%P[i] == 0)
            {
                P.pop_back();
                break;
            }
        }
        a[n] = sz(P);
    }
    
    cin >> T;
    for (int cs = 0; cs < T; cs += 1)
    {
        cin >> N;
        cout << a[f[N]] << '\n';
    }
    return 0;
}








