// *********** EVEN TREE *********//
#include<stdio.h>
#include<stdlib.h>
#include<stdio.h>
#include<stdlib.h>
struct node;
struct graph_vertex;
struct node
{	struct graph_vertex *vertex_ptr;
	struct node *next;
};
struct graph_vertex
{	int name,data,s,f,d, count;
	char color, update_flag;
	struct graph_vertex *parent;
	struct node *adj_list_start;	
};
typedef struct graph_vertex vertex;

typedef struct graph_edge
{	vertex *node_1;
	vertex *node_2;	
	char read_flag;
	char include_flag;
	struct graph_edge *next;
}graph_edge;

typedef struct node graph_node;
typedef struct node adj_list_node;
typedef struct my_graph
{	graph_node *start_graph;  //this pointer if for a link list that stores all vertices in a graph
	graph_edge *start_graph_edge;	// this pointer is for a link list that stores all edges in a graph
	int vertices;
	int edges;
}graph;

void initialise_new_vertex(vertex *ptr, int name, vertex *parent);
vertex* add_new_vertex_to_graph(graph *G);
vertex* add_vertex(graph *G, int name, vertex *parent);
graph* create_graph(char *input_file_name);
vertex *node_exists(graph *G, int input_name);
void add_edge(graph *G, vertex *main_node_ptr,vertex * ngbr_node_ptr);
void add_edge_to_graph(graph *G, vertex *main_node_ptr,vertex *ngbr_node_ptr);
void add_to_adj_list(graph *G, vertex *target_node, vertex *ngbr);

int display_graph_wrt_edges(graph *G);
void display_graph(graph *G);
int display_adj_lists(graph *G);
int display_names(graph *G);

int dfs(graph *G);
int initialise_dfs(graph *G);
int dfs_visit(graph *G, vertex *v);

int global_count = 0;

int initialise_dfs(graph *G)
{	graph_node *tra = G->start_graph;
	if(tra == NULL)
	{	////printf("graph empty..nothing to initialise\n");
		return 0;
	}
	vertex *v;
	while(1)
	{	v = tra->vertex_ptr;
		v->color = 'w';
			if(tra->next == NULL) 
			break;
		tra = tra->next;
	}
}

void initialise_new_vertex(vertex *ptr, int name, vertex *parent)
{
	ptr->name=name;
	ptr->color='w';
	ptr->data=0;
	ptr->parent=parent;
	ptr->adj_list_start=NULL;
	ptr->s=0;
	ptr->f=0;
	ptr->d=0;
	ptr->count=0;
	ptr->update_flag = 'n';
	
}

vertex* add_new_vertex_to_graph(graph *G)
{	vertex *new_vertex_ptr = malloc(sizeof(vertex));
	graph_node *new_graph_ptr = malloc(sizeof(graph_node));
	new_graph_ptr->next = G->start_graph;
	new_graph_ptr->vertex_ptr = new_vertex_ptr;
	G->start_graph = new_graph_ptr;
	return new_vertex_ptr;
}

vertex* add_vertex(graph *G,int name, vertex *parent)
{
	vertex *new_vertex_ptr = add_new_vertex_to_graph(G);
	initialise_new_vertex(new_vertex_ptr, name, parent);
	return new_vertex_ptr;	
}


graph* create_graph(char *input_file_name)
{
	graph *G = malloc(sizeof(graph));
	FILE *fp;
	fp=fopen(input_file_name,"r");
	int main_node,ngbr_node;
	vertex *main_node_ptr, *ngbr_node_ptr;
	int i;
	int num_vertices=0, num_edges = 0;
	//fscanf(fp,"%d %d", &num_vertices, &num_edges);	
	scanf("%d %d", &num_vertices, &num_edges);
	//////printf("vertices: %d, edges: %d\n", num_vertices, num_edges);
	G->vertices = num_vertices;
	G->edges = num_edges;	
	for(i=0;i<num_edges;i++)
	{	//fscanf(fp,"%d %d", &main_node, &ngbr_node);	
		scanf("%d %d", &main_node, &ngbr_node);
		ngbr_node_ptr=node_exists(G, ngbr_node);
		if(ngbr_node_ptr == NULL)
		{	ngbr_node_ptr = add_vertex(G, ngbr_node, NULL);
		}
		main_node_ptr=node_exists(G, main_node);
		if(main_node_ptr == NULL)
		{	main_node_ptr = add_vertex(G, main_node, ngbr_node_ptr);
		}
		
		add_edge(G, main_node_ptr, ngbr_node_ptr);
	}	
	return G;	
}

vertex *node_exists(graph *G,int input_name)
{	if(G->start_graph == NULL)
		return NULL;
	//////printf("node exixts begins\n");	
	graph_node *graph_tra=G->start_graph;
	while(1)
	{	if(graph_tra->vertex_ptr->name == input_name)
		{	return graph_tra->vertex_ptr;}
		if(graph_tra->next == NULL)
			return NULL;
		graph_tra = graph_tra->next;
	}
	//////printf("node exixts ends\n");
}

void add_edge(graph *G, vertex *main_node_ptr,vertex * ngbr_node_ptr)
{	
	add_edge_to_graph(G, main_node_ptr, ngbr_node_ptr);
	add_to_adj_list(G, main_node_ptr, ngbr_node_ptr);
	add_to_adj_list(G, ngbr_node_ptr, main_node_ptr);
}

void add_edge_to_graph(graph *G, vertex *main_node_ptr,vertex *ngbr_node_ptr)
{	
	graph_edge *new_graph_edge = malloc(sizeof(graph_edge));
	new_graph_edge->next = G->start_graph_edge;
	new_graph_edge->node_1 = main_node_ptr;
	new_graph_edge->node_2 = ngbr_node_ptr;
	new_graph_edge->read_flag = 'n';
	new_graph_edge->include_flag = 'y';
	G->start_graph_edge = new_graph_edge;
}


void add_to_adj_list(graph *G, vertex *target_node, vertex *ngbr)
{	
	adj_list_node *new_adj_list_node = malloc(sizeof(adj_list_node));
	new_adj_list_node->next = target_node->adj_list_start;
	new_adj_list_node->vertex_ptr = ngbr;
	target_node->adj_list_start = new_adj_list_node;
}	

int display_graph_wrt_edges(graph *G)
{	int n1,n2;
	if(G->start_graph_edge == NULL)
		return 0;
	graph_edge *edge_tra = G->start_graph_edge;
	while(1)
	{	n1 = edge_tra->node_1->name;
		n2 = edge_tra->node_2->name;
		//////printf("%d %d\n",n1,n2);

		if(edge_tra->next == NULL)
			return 0;
		
		edge_tra = edge_tra->next;
	}
}

void display_graph(graph *G)
{	display_graph_wrt_edges(G);
}

int display_names(graph *G)
{	graph_node *tra = G->start_graph;
	if(tra == NULL)
	{	//////printf("graph empty\n");
		return 0;
	}
	while(1)
	{	//////printf("%d %d\n",tra->vertex_ptr->name,tra->vertex_ptr->d);
		if(tra->next == NULL)
		{	break;}
		tra=tra->next;
	}
}

int display_adj_lists(graph *G)
{	graph_node *tra = G->start_graph;
	vertex *vertex_tra, *ngbr_tra;
	adj_list_node *adj_tra;
	if(tra == NULL)
	{	//////printf("graph empty\n");
		return 0;
	}
	while(1)
	{	
		vertex_tra = tra->vertex_ptr;
		adj_tra = vertex_tra->adj_list_start;
	//	////printf("node %d\n",vertex_tra->name);
		if(adj_tra == NULL)
		{	////printf("no adj nodes\n"); 
			int p=0;	
		}
		else
		{	
			while(1)
			{	ngbr_tra = adj_tra->vertex_ptr;		
				//////printf("%d ",ngbr_tra->name);
				if(adj_tra->next == NULL)
					break;
				adj_tra = adj_tra->next;
			}
			//////printf("\n");
		}
		if(tra->next == NULL )
		{	break;}
		tra=tra->next;
	}
}

vertex *find_root(graph *G)
{	graph_node *v_ptr = G->start_graph;
	//////printf("Inside finding root\n");	
	if(v_ptr==NULL)
	{	//////printf("No vertices in the graph...returning\n");	
		return NULL; 
	}
	while(1)
	{	if(v_ptr->vertex_ptr->parent == NULL)
		{	////printf("Found root node: %d\n", v_ptr->vertex_ptr->name);
			return v_ptr->vertex_ptr;
		}
		if(v_ptr->next == NULL)
		{	//////printf("Could not find node. somthing wrong \n"); 
			return NULL;
		}
		v_ptr = v_ptr->next;
	}
}

int enable_read_flag(graph *G, vertex *main_vertex)
{	if(main_vertex->parent == NULL)
	{	////printf("Reached the root..no need to enable the read_flag\n");
		return 0;
	}
	vertex *par = main_vertex->parent;
	graph_edge *edge_tmp = G->start_graph_edge;
	int found = 0;
	while(edge_tmp != NULL)
	{	if((edge_tmp->node_1==main_vertex && edge_tmp->node_2==par)||(edge_tmp->node_2==main_vertex && edge_tmp->node_1==par))
		{	////printf("Enabling the read_flag between node %d and %d\n", main_vertex->name, par->name);
			edge_tmp->read_flag = 'y';
				break;
		}
		if(edge_tmp->next == NULL)
		{	////printf("ERROR: should have never reached here..inside divide forest Check1..breaking from enable_read_flag\n");
			break;
		}
		else 
		{ edge_tmp = edge_tmp->next; }
	}
}

int disable_include_flag(graph *G, vertex *main_vertex)
{	if(main_vertex->parent == NULL)
	{	////printf("Reached the root...no need to disable include flag\n");
		return 0;
	}
	vertex *par = main_vertex->parent;
	graph_edge *edge_tmp = G->start_graph_edge;
	int found = 0;
	while(edge_tmp != NULL)
	{	if((edge_tmp->node_1==main_vertex && edge_tmp->node_2==par)||(edge_tmp->node_2==main_vertex && edge_tmp->node_1==par))
		{	edge_tmp->include_flag = 'n';
			////printf("Disabling the include flag betweeen %d and %d\n", edge_tmp->node_1->name, edge_tmp->node_2->name);
				break;
		}
		if(edge_tmp->next == NULL)
		{	////printf("ERROR: should have never reached here..inside divide forest Check1..breaking from disable_include_flag\n");
			break;
		}
		else 
		{ edge_tmp = edge_tmp->next; }
	}
}

int update_counter_for_vertex(graph *G, vertex *main_vertex)
{	if(main_vertex->update_flag == 'y')
	{	////printf("Update flag already set for vertex: %d ....ERROR wrong section reached\n", main_vertex->name); 
		return 0;	
	}
	main_vertex->update_flag = 'y';
	int local_count = 0;
	adj_list_node *v_tmp = main_vertex->adj_list_start;
	while(v_tmp != NULL)
	{	if(v_tmp->vertex_ptr == main_vertex->parent)
		{	if(v_tmp->next == NULL)
				break;
			else	
				v_tmp = v_tmp->next;
		}
		graph_edge *edge_tmp = G->start_graph_edge;
		int found = 0;
		while(edge_tmp != NULL)
		{	////printf("looking for edge: %d %d, current edges: %d %d\n",main_vertex->name, v_tmp->vertex_ptr->name, edge_tmp->node_1->name, edge_tmp->node_2->name);	
			if((edge_tmp->node_1==main_vertex && edge_tmp->node_2==v_tmp->vertex_ptr)||(edge_tmp->node_2==main_vertex && edge_tmp->node_1==v_tmp->vertex_ptr))
			{	if(edge_tmp->node_1->name == 6 && edge_tmp->node_2->name == 1 && main_vertex->name == 1 && v_tmp->vertex_ptr->name == 6)
				{	////printf("Value of edge_tmp->include_flag: %c\n", edge_tmp->include_flag);
				}
				if(edge_tmp->include_flag == 'y')
				{	main_vertex->count = main_vertex->count + v_tmp->vertex_ptr->count;
					break;
				}		
				else
				{	////printf("Breaking for edge\n");
					break; 
				}
			}
			if(edge_tmp->next == NULL)
			{	////printf("ERROR: should have never reached here..inside divide forest Check1..breaking update flag\n");
				break;
			}
			edge_tmp = edge_tmp->next;
		}
		if(v_tmp->next == NULL)
		{	////printf("breaking...\n"); break; 
		}
		v_tmp = v_tmp->next;
	}
	main_vertex->count = main_vertex->count + 1;
	////printf("Count for vertex %d: %d\n", main_vertex->name, main_vertex->count);
	local_count = main_vertex->count;
	vertex *root = find_root(G);
	if((local_count)%2 == 0 && main_vertex != root)
	{	disable_include_flag(G, main_vertex);	
		global_count++;
	}
	enable_read_flag(G, main_vertex);
	////printf("Value of global_count till now: %d\n", global_count);
	return 0;
}

int dfs_visit(graph *G, vertex *main_vertex)
{	////printf("inside dfs_visit, current_node: %d\n", main_vertex->name);	
	main_vertex->color = 'g';
	adj_list_node *v_tmp = main_vertex->adj_list_start;
	vertex *u;
	if(v_tmp != NULL)
	{	while(1)
		{	
			u = v_tmp->vertex_ptr;
			if(u->color == 'w')
			{	dfs_visit(G,u);
			}
			if(v_tmp->next == NULL)
				break;
			v_tmp = v_tmp->next;
		}
	}
	////printf("After processing child nodes, main_node: %d\n", main_vertex->name);
	update_counter_for_vertex(G, main_vertex);
	main_vertex->color = 'b';
}

int dfs(graph *G)
{
	initialise_dfs(G);
	vertex *root = find_root(G);
	if(root != NULL)
	{	dfs_visit(G,root);
	}
	else
	{	return 0;
	}
}

int main_logic(graph *G)
{	dfs(G);
}

int main()
{	char *input_file_name; 	
	//input_file_name = "/home/pragya/Dropbox/codechef/graph_new1/graph_files/graph_file3.txt";		
	graph *G = create_graph(input_file_name);	
	main_logic(G);	
	//display_graph(G);	
	printf("%d\n", global_count);
}
	

	