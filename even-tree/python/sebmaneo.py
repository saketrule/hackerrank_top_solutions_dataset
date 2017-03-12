# Enter your code here. Read input from STDIN. Print output to STDOUT
# Enter your code here. Read input from STDIN. Print output to STDOUT
import sys

def tree_prune(A,degree_set,cum_vertices,edge_count):
    ### Given a tree (adjacency matrix) and
    ### a degree set, remove all nodes that
    ### have a degree of one and return updated
    ### adjacency matrix
    mac_prune = [i for i,x in enumerate(degree_set) if x==1]
    
    ### Remove only one edge at a time:
    mac_prune = mac_prune[0]

    ### This is a single degree vertex; to which vertex is it connected:
    temp_index = A[mac_prune].index(1)
    
    if cum_vertices[mac_prune]%2 == 0:
        edge_count+=1
        degree_set[temp_index]-=1
        #degree_set[mac_prune]=[]
        
    else:    
    
      cum_vertices[temp_index]+=cum_vertices[mac_prune]
      degree_set[temp_index]-=1
    
    del degree_set[mac_prune]
    del cum_vertices[mac_prune] 
   
    ### Update adjacency matrix, weight set and machine_set
    remove_col_and_row(A,mac_prune)        

    return A,degree_set,cum_vertices,edge_count

def remove_col_and_row(A,i):

    for j in range(len(A)):
        del A[j][i]
    del A[i] 
    return A

def get_degree_set(A):
    ### Given an adjacency matrix, return the
    ### degree set of the graph
    
    degree_set=[0]*len(A)
    for i in range(len(A)):
        for j in range(len(A)):
           degree_set[i]+=A[i][j]*A[j][i]
    return degree_set

t = sys.stdin.readline()
t_split = t.split()
t = map(int,t_split)

N = t[0]
m = t[1]

A = []
for i in range(N):
    temp_zero = [0]*N
    A.append(temp_zero)
    
for i in range(m):
    num_points = sys.stdin.readline()

    num_points_split = num_points.split()
    num_points = map(int,num_points_split) 
    A[num_points[0]-1][num_points[1]-1]=1
    A[num_points[1]-1][num_points[0]-1]=1
    
cum_vertices = [1]*N       
degree_set = get_degree_set(A)
edge_count=0
    
while True:
   [A,degree_set,cum_vertices,edge_count]=tree_prune(A,degree_set,cum_vertices,edge_count)
   if len(A)==1:
      break
 
   #break
print edge_count        
