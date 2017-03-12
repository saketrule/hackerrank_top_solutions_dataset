#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;


int main() {
    vector < int > values ( 1001, 0 );
   	int N;
    cin >> N;
    	
    for (int i = 0; i < N; i++){
    	int next;
        cin >> next;
        	
        values[next]++;
    }
    
    for (int i = 0; i < values.size(); i++){
    	if ( values[i] ){
            cout << N << endl;
            N = N - values[i];
        }
    }
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */   
    return 0;
}
