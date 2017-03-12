#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;


int main() {
    int N; scanf(" %d", &N);
    vector<int> sizes;
    for( int i = 0; i < N; ++i ) {
        int s; scanf(" %d", &s);
        sizes.push_back(s);
    }
    sort(sizes.begin(), sizes.end());
    int s = 0;
    while( s < N ) {
        printf("%d\n", N-s);
        int x = sizes[s];
        while( sizes[s] == x ) ++s;
    }
    return 0;
}
