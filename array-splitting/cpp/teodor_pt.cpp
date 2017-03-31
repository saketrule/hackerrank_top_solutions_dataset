#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int n;
int a[17000];
long long sum = 0;

int compute_score(int s, int f, long long sum) {
    long long sum2 = 0;
    int i;
    
    if (s==f) return 0;
    
    for (i=s;i<=f && sum-sum2 > sum2;i++) {
        sum2+=a[i];
        if (sum-sum2 == sum2) {
            break;
        }
    }
    
    if (sum-sum2 == sum2) {
        return (max(compute_score(s,i,sum2),compute_score(i+1,f,sum-sum2)))+1;
    }
    
    return 0;
}

int main() {
    int t;
    cin >> t;
    
    for (int tt=0;tt<t;tt++) {
        sum = 0;
        cin >> n;

        for (int i=0;i<n;i++) {
            cin >> a[i];
            sum += a[i];
        }

        cout << compute_score(0,n-1,sum) << "\n";
    }
    return 0;
    
}
