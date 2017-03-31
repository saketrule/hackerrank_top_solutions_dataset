#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>

using namespace std;

int n;
int a[100010];

const int ddd = 10000;

int p, q;

int f(int d)
{
    if(d < p || d > q)
        return -10;
    
    int tmp = abs(a[0]-d);
    for(int i = 1 ; i < n ; ++i)
        tmp = min(tmp, abs(a[i]-d));
    return tmp;
}

int main()
{
    ios::sync_with_stdio();
    cin >> n;
    for(int i = 0 ; i < n ; ++i)
        cin >> a[i];
    sort(a, a+n);
    
    cin >> p >> q;
    
    int ans = -1, dd = -1;
    for(int d = 0 ; d < ddd ; ++d)
    {
        int k = f(p+d);

        if(ans < k)
            ans = k, dd = p+d;
        else if(ans == k)
            dd = min(dd, p+d);
        
        k = f(q-d);
        
        if(ans < k)
            ans = k, dd = q-d;
        else if(ans == k)
            dd = min(dd, q-d);
    }
    
    for(int i = 0 ; i+1 < n ; ++i)
    {
        int md = (a[i]+a[i+1])/2;
        for(int d = -ddd ; d <= ddd ; ++d)
        {
            int k = f(md+d);
            if(ans < k)
                ans = k, dd = md+d;
            else if(ans == k)
                dd = min(dd, md+d);
        }
    }
    
    for(int i = 0 ; i < n ; ++i)
    {
        int md = a[i];
        for(int d = -ddd ; d <= ddd ; ++d)
        {
            int k = f(md+d);
            if(ans < k)
                ans = k, dd = md+d;
            else if(ans == k)
                dd = min(dd, md+d);
        }
    }
    
    cout << dd << "\n";
    return 0;
}