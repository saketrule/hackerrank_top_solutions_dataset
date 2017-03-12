# Enter your code here. Read input from STDIN. Print output to STDOUT


ans=0
class TreeNode:
    def __init__(self,x):
        self.n_leaf=0
        self.links=[x]
  
dic=dict()


N,M=list(map(int,raw_input().split(' ')))
for x in range(M):
    a,b=list(map(int,raw_input().split(' ')))
    if a in dic:
        dic[a].links.append(b)
    else:
        dic[a]=TreeNode(b)
    if b in dic:
        dic[b].links.append(a)
    else:
        dic[b]=TreeNode(a)

        
def treat_leaf():
    global ans
    li_del=[]
    for x in dic:
        if len(dic[x].links)==1:
            dic[dic[x].links[0]].links.remove(x)
            if dic[x].n_leaf&1:#cut this node
                ans+=1
            else:#reduce to link node.
                dic[dic[x].links[0]].n_leaf+=dic[x].n_leaf+1
            li_del.append(x)
    for x in li_del:
        del dic[x]

while len(dic)>1:
    treat_leaf()
print(ans)

