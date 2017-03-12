/* Enter your code here. Read input from STDIN. Print output to STDOUT */
#include <iostream>
#include <vector>
#include <cstring>
using namespace std;

int n;
int m;
int u[111];
int v[111];
vector<int> e[111];
int degree[111];
int weight[111];
bool visited[111];
int ans = 0;

int main(void) 
{
	cin >> n >> m;
	for (int i=0; i<m; i++)
	{
		cin >> u[i] >> v[i];
		e[u[i]].push_back(v[i]);
		e[v[i]].push_back(u[i]);
		degree[u[i]]++;
		degree[v[i]]++;
	}
	for (int i=1; i<=n; i++)
		weight[i] = 1;
	for (int cb=1; cb<=n; cb++)
	{
		for (int i=1; i<=n; i++)
			if (degree[i] == 1 && !visited[i]) 
			{
				if (weight[i] % 2 == 0) ans++;

				for (int j=0; j<(int)e[i].size(); j++) 
				{
					if (!visited[e[i][j]]) 
					{
						degree[e[i][j]]--;
						weight[e[i][j]] += weight[i];
						break;
					}
				}

				degree[i] = 0;
				visited[i] = true;
			}
	}
	
	cout << ans << "\n";

	return 0;
}
