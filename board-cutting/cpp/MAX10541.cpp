#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

const long modu=1000000007;

int main()
{
     long t;
    cin>>t;
    while(t--)
    {
        long m,n;
        cin>>m>>n;
        long long x[m-1],y[n-1];
        for(long i=0 ; i<m-1 ; i++)
            cin>>x[i];
        for(long i=0 ; i<n-1 ; i++)
            cin>>y[i];
        sort(x,x+m-1);
        sort(y,y+n-1);
        long tx=1,ty=1;
        long long res=0;
        while((tx<m)&&(ty<n))
        {
            if (x[m-1-tx] >= y[n-1-ty])
            {
                res=(res+ty*x[m-1-tx])%modu;
                tx++;
            }
            else
            {
                res=(res+tx*y[n-1-ty])%modu;
                ty++;
            }
        }
        while (tx<m)
        {
            res=(res+ty*x[m-1-tx])%modu;
            tx++;
        }
        while (ty<n)
        {
            res=(res+tx*y[n-1-ty])%modu;
            ty++;
        }
        cout<<res<<endl;
    }
    return 0;
}
