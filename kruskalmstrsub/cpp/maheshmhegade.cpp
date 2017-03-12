#include <iostream>
#include <map>
#include <vector>
#include <set>
#include <cstring>

#define ii pair<int,int>
using namespace std;

void fillArr(int *id_arr, int *size_arr, int graph_size){
    for(int idx=0; idx<graph_size; idx++){
        size_arr[idx] = 1;
        id_arr[idx] = idx;
    }
}

int getRoot(int *id_arr, int key_val){
    while(key_val != id_arr[key_val]){
        id_arr[key_val] != id_arr[id_arr[key_val]];
        key_val = id_arr[key_val];
    }
    return key_val;
}

void unite(int *id_arr, int *size_arr, int elem_a, int elem_b){
    int root_of_a = getRoot(id_arr, elem_a);
    int root_of_b = getRoot(id_arr, elem_b);
    if(size_arr[root_of_a] < size_arr[root_of_b]){
        id_arr[root_of_a] = root_of_b;
        size_arr[root_of_b] += size_arr[root_of_a];
    }
    else{
        id_arr[root_of_b] = root_of_a;
        size_arr[root_of_a] += size_arr[root_of_b];
    }
}

int updateDistances(map<int, set<ii> > graph, int net_node_count ){

    int net_distance = 0;
    int id_arr[net_node_count], size_arr[net_node_count];
    fillArr(id_arr, size_arr, net_node_count);
    int net_edge_count = 0;
    set<ii> set_iter;
    ii x_y_pair;
    for(map<int, set<ii> >::iterator iter = graph.begin(); iter != graph.end(); iter++){
        set_iter = (set<ii>) iter->second;
        while (net_edge_count != net_node_count - 1 && !set_iter.empty()) {
            x_y_pair = *set_iter.begin();
//            cout << x_y_pair.first << "   " << x_y_pair.second << endl;
            set_iter.erase(x_y_pair);
            if(getRoot(id_arr, x_y_pair.first) != getRoot(id_arr, x_y_pair.second)){
                unite(id_arr, size_arr, x_y_pair.first, x_y_pair.second);
                net_distance += (int) iter->first;
                net_edge_count += 1;
            }
        }
        if(net_edge_count == net_node_count - 1) break;
    }
    return net_distance;
}

int main(int argc, char *argv[])
{
    int  num_of_nodes, num_of_edges, x_node, y_node, weight, source_node;
    map<int, set<ii > > graph;
    set<ii> iter;
    cin >> num_of_nodes >> num_of_edges;

    bool visited_nodes[num_of_nodes];
    memset(visited_nodes, true , sizeof(visited_nodes));
    for(int idx=0; idx<num_of_edges; idx++){
        cin >> x_node >> y_node >> weight;
        x_node--;
        y_node--;
        iter = graph[weight];
        iter.insert(ii(x_node, y_node));
        graph[weight] = iter;
    }
    cin >> source_node;
    cout << updateDistances(graph, num_of_nodes)  << endl;
    return 0;
}
