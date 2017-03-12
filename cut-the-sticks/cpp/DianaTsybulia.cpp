#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int a[100010];
int b[100010];

int main() {
    int n;
    cin >> n;
    for(int i = 0 ; i < n ; ++i)
        cin >> a[i];
    while(1)
    {
        cout << n << "\n";
        sort(a, a+n);
        int nn = 0;
        
        
        for(int i = 0 ; i < n ; ++i)
        if(a[i]-a[0] > 0)
            b[nn++] = a[i]-a[0];
        n = nn;
        for(int i = 0 ; i < n ; ++i)
        	a[i] = b[i];
     	if(n == 0)
            break;
    }
    return 0;
}
