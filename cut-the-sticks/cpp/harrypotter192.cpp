#include <iostream>
#include <stdio.h>
#include <cmath>
#include <string>
#include <vector>
#include <stack>
#include <cassert>
#include <algorithm>
#define Pi 3.14159

typedef long long int ll;
using namespace std;

int main ()
{
    int n;
    cin >> n;
    int a[n];
    int b[1001]={0};
    for (int i = 0 ; i<n;i++){cin >>a[i];b[a[i]] ++;}
    cout<<n<<endl;
    for (int i = 1; i <= 1000; i++){n-=b[i];if(b[i] && n)cout<<n<<endl;}
}


