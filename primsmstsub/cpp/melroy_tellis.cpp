#include <iostream>
#include <list>
#include <climits>

using namespace std;

struct edge
{
    int to;
    int weight;
};
class graph
{
    private:
        unsigned int number_of_nodes;
        list<edge> *edges;

        int find_smallest_weight_node(int* weights, bool* included)
        {
            int min = INT_MAX, min_node = number_of_nodes;
        
            for ( int i = 0 ; i < number_of_nodes ; ++ i )
            {   
                if ( ! included [i] && weights [i] < min )
                {
                    min = weights [i];
                    min_node = i;
                }
            }
            return min_node;
        }
    
        int helper_min_spanning_tree_weight( int* weights, bool* included, int acc = 0 )
        {   
            int m = find_smallest_weight_node(weights, included);
            
            if ( m == number_of_nodes ) return acc;
           
            included[m] = true;
            
            for ( auto it = edges[m].begin() ; it != edges[m].end() ; ++ it )
            {
               edge e = *it;
               if ( ! included [e.to] && e.weight < weights[e.to] )
               {
                    weights[e.to] = e.weight;
               }
            }
            return helper_min_spanning_tree_weight(weights, included, acc + weights[m]);
        }
       
    public:
        graph (unsigned int number_of_nodes) : number_of_nodes (number_of_nodes)
        {
            edges = new list<edge>[number_of_nodes];
        }
        void add_edge (unsigned int from, unsigned int to, unsigned int weight)
        {
            edge e;
            e.to = to;
            e.weight = weight;
            edges[from].push_back(e);
        }
        int min_spanning_tree_weight(int start)
        {
            int* weights = new int[number_of_nodes];
            for(int i = 0 ; i < number_of_nodes ; ++ i)
            {
                weights[i] = INT_MAX;
            }
            weights[start] = 0;  
            
            bool *included = new bool[number_of_nodes]{false}; 
            
            int ans = helper_min_spanning_tree_weight(weights, included);
            
            delete[] included;
            delete[] weights;
            
            return ans;
        }

        ~graph()
        {
            delete[] edges;
        }

};

int main()
{
    unsigned int n, m, x, y, r, s;
    cin >> n >> m;
    graph g(n);
    while ( m -- )
    {
        cin >> x >> y >> r;
        -- x, -- y;
        g.add_edge(x, y, r);
        g.add_edge(y, x, r);
    }
    cin >> s;
    -- s;
    cout << g.min_spanning_tree_weight(s) << endl;
    
    return 0;
}
