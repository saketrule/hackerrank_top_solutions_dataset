#include <iostream>
#include <stack>

using namespace std;

int main()
{
	int N, M;
	cin >> N >> M;

	bool adj[100][100];

	// O(N^2)
	for (int x = 0; x < N; x++)
		for (int y = 0; y < N; y++)
			adj[x][y] = false;

	for (int x = 0; x < M; x++)
	{
		int a, b;
		cin >> a >> b;
		adj[--a][--b] = true;
		adj[b][a] = true;
	}

	// 0 root
	int parent[100];
	int weights[100];
	int children[100];
	for (int x = 0; x < N; x++) // init
	{
		parent[x] = -1; // unassigned parent
		weights[x] = 1;
		children[x] = 0;
	}

	stack<int> loners; // stack with indices of vertices with weight of 1 (ie, no children)
	stack<int> s;
	s.push(0);
	parent[0] = -2; // no parent

	// O(N^2)
	while (!s.empty())
	{
		int n = s.top();
		s.pop();

		for (int x = 0; x < N; x++)
		{
			if (adj[n][x] && parent[x] == -1)
			{
				parent[x] = n;
				s.push(x);
				children[n]++;
			}
		}

		if (children[n] == 0)
			loners.push(n);
	}


	int ans = 0;
	// clear stuff up from the bottom
	while (!loners.empty())
	{
		int n = loners.top();
		loners.pop();

		if (n == 0)
			break;

		//cout << n << ": " << weights[n] << endl;
		if (weights[n] % 2 == 0)
		{
			ans++;
		}
		else
		{
			weights[parent[n]]+=weights[n];
		}
		if (--children[parent[n]] == 0)
			loners.push(parent[n]);
		

	}

	cout << ans << endl;

	return 0;
}