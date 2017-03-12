//tonynater - HackerRank 2014

#include <algorithm>
#include <bitset>
#include <cassert>
#include <cmath>
#include <ctime>
#include <fstream>
#include <iomanip>
#include <iostream>
#include <list>
#include <map>
#include <queue>
#include <set>
#include <sstream>
#include <stack>
#include <string>
#include <vector>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

using namespace std;

#define sz(x) ((int) x.size())

typedef long double ld;
typedef long long ll;
typedef pair<int, int> pii;

const double pi = acos(-1);
const double tau = 2*pi;
const double epsilon = 1e-6;

const int MAX_N = 1100;

int N;

int A[MAX_N];

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    
    cin >> N;
    
    for(int i = 0; i < N; i++)
        cin >> A[i];
    
    sort(A, A+N);
    
    cout << N << '\n';
    for(int i = 0; i < N; i++)
    {
        while(i < N-1 && A[i] == A[i+1]) ++i;
        if(N-i-1 > 0) cout << N-i-1 << '\n';
    }
    
    return 0;
}
