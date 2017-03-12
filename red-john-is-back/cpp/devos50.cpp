#include <iostream>

using namespace std;

int n;

bool is_prime(int n)
{
	if(n == 0 || n == 1) return false;
	if(n == 2) return true;
	if(n % 2 == 0) return false;
	
	for(int i = 3; i * i <= n; i += 2)
		if(n % i == 0) return false;
	return true;
}

int ways(int cur)
{
	if(cur == n) return 1;
	else if(cur > n) return 0;
	
	return ways(cur + 1)  + ways(cur + 4);
}

void solve()
{
	cin >> n;
	int w = ways(0);
	
	int ans = 0;
	for(int i = 2; i <= w; i++)
		if(is_prime(i)) { ans++; } 
	cout << ans << endl;
}

int main(int argc, char *argv[]) 
{
	int t; cin >> t;
	while(t--) solve();
}