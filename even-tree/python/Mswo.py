class Node():
    def __init__(self):     
        self.father = -1
        self.weightEdgeToFather = -1
        self.nbDescendants = 0
        
class Arbre():
    def __init__(self,root):
        self.root = root
        self.voisins = []
        self.noeuds = []
        self.nbSommets = 0
        self.nbVertices = 0
        self.nbVerticesToDestroy = 0
        
    def analyse_input(self):
        self.nbSommets,self.nbVertices = map(int,raw_input().split(' '))
        for i in xrange(self.nbSommets+1):
            self.voisins.append([])
            self.noeuds.append([])
        for i in xrange(self.nbSommets-1):
            self.addArrete(raw_input())
        for i in xrange(self.nbSommets+1):
            self.voisins[i].sort()
    
    def addArrete(self,arrete):
        x,y = map(int,arrete.split(' '))
        self.voisins[x].append(y)
        self.voisins[y].append(x)
    
    def construireArbre(self):
        for i in xrange(self.nbSommets+1):
            self.noeuds[i] = Node()
        self.construire(self.root,-1)
        
    def construire(self,indice,father):
        self.noeuds[indice].father = father
        for v in self.voisins[indice]:
            if v != father:
                self.construire(v,indice)
                self.noeuds[indice].nbDescendants += self.noeuds[v].nbDescendants+1
        if ((self.noeuds[indice].nbDescendants % 2) ==1):
              self.nbVerticesToDestroy += 1     
        
    def parcoursEnProfondeur(self,indice):
        toDisplay = "Noeud "+str(indice)
        toDisplay += ", Nb de Fils " + str(self.noeuds[indice].nbDescendants)
        print toDisplay
        for v in self.voisins[indice]:
            if v != self.noeuds[indice].father:
                self.parcoursEnProfondeur(v)
                
monArbre = Arbre(1)
monArbre.analyse_input()
monArbre.construireArbre()
#monArbre.parcoursEnProfondeur(1)
print monArbre.nbVerticesToDestroy-1