#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;
#define rep(i,n) for( int i=0; i<n; ++i )

int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */   
    int n,a[1005];
    cin>>n;
    rep(i,n)
        cin>>a[i];
    sort(a,a+n);
    cout << n<<"\n";
    rep(i,n-1){
        if( a[i]!=a[i+1] )
            cout<< n-i-1 <<"\n";
    }
    
    
    return 0;
}
