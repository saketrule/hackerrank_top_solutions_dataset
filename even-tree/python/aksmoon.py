# Enter your code here. Read input from STDIN. Print output to STDOUT
# Enter your code here. Read input from STDIN. Print output to STDOUT
line1 = raw_input()
n,k = line1.split(' ') 
edge_dict = {}
edges = {}
scores = {}
for i in range(int(n)):
    edge_dict[str(i+1)] = []
    edges[str(i+1)] = 0
    scores[str(i+1)] = 1
for i in range(int(n)-1):
    line = raw_input()
    a,b = line.split(' ')
    edges[a] = edges[a]+1
    edges[b] = edges[b]+1
    edge_dict[a].append(b)
    edge_dict[b].append(a)
#print edge_dict
count = 0
#print scores
#print edges
to_remove = []
while len(edge_dict) > 1:
   # print "here\n"
   # print count
   # print edge_dict
    to_update = 1
    for key in edges:
       # print "key ="
      #  print key , edges[key]
        if edges[key] != 1:
      #      print "more\n"
            continue
        if (scores[key] % 2 == 0):
            count = count + 1
         #   print "count incresed" 
            to_update = 0
        for node in edge_dict[key]:
            if to_update:
                scores[node] = scores[node] + scores[key]
          #      print node,key,scores[node]
            edges[node] = edges[node] - 1
            edge_dict[node].remove(key)
       # print "removing %s"%key
        del edge_dict[key]
        to_remove.append(key)
        to_update = 1
    for elem in to_remove:
        del edges[elem]
        del scores[elem]
    to_remove = []
      #  edges[key] = 0
      #  print edges
  #  print scores
print count