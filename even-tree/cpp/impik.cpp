#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int main()
{
	int n, m, i, v, w;
	vector<vector<int>> tree;
	vector<int> vertices_count;
	vector<int> S;
	queue<int> vertices;
	cin >> n >> m;
	tree.resize(n);
	vertices_count.resize(n);
	S.resize(n);
	for(i = 0; i < n; ++i)
	{
		tree[i].resize(n);
	}
	for(i = 0; i < m; ++i)
	{
		cin >> v >> w;
		--v;
		--w;
		tree[v][w] = tree[w][v] = 1;
		++vertices_count[v];
		++vertices_count[w];
	}
	for(i = 0; i < n; ++i)
	{
		if(vertices_count[i] == 1)
			vertices.push(i);
		vertices_count[i] = 0;
	}
	int result = 0, is_list;
	while(!vertices.empty())
	{
		v = vertices.front();
		vertices.pop();
		if(!S[v])
		{
			is_list = 0;
			for(i = 0; i < n; ++i)
			{
				if(tree[v][i] && !S[i])
				{
					++is_list;
					w = i;
				}
			}
			if(is_list == 1)
			{
				++S[v];
				++vertices_count[v];
				vertices.push(w);
				if(vertices_count[v] % 2)	
				{
					vertices_count[w] += vertices_count[v];
				}
				else
				{
					tree[v][w] = tree[w][v] = 0;
					++result;
				}
			}
		}

	}
	cout << result;
	return 0;
}