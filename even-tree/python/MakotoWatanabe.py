#-------------------------------------------------------------------------------
# Name:        Even Tree
# Purpose:
#
# Author:      udonko
#
# Created:     29/07/2012
# Copyright:   (c) udonko 2012
# Licence:     <your licence>
#-------------------------------------------------------------------------------
#!/usr/bin/env python
import sys
class cvertex:
    def __init__(self, no):
        self.children = []
        self.parent = None
        self.no=no
        self.count = 1
    def calccount(self):
        self.count = 1
        for child in self.children:
            child.calccount()
            self.count += child.count
def main():
    temp = sys.stdin.readline()
    temps = temp.split()
    n = int(temps[0])
    m = int(temps[1])
    array = [[ False for i in range(n)] for j in range(n)]
    vertexes =[ cvertex(i+1) for i in range(n)]
    for i in range(m):
        temp = sys.stdin.readline()
        temps = temp.split()
        vertexes[int(temps[0])-1].parent = vertexes[int(temps[1])-1]
        vertexes[int(temps[1])-1].children.append(vertexes[int(temps[0])-1])

    vertexes[0].calccount()

    count = 0
    for vertex in vertexes:
        if vertex.count %2 == 0 and vertex.parent != None:
            count += 1

    sys.stdout.write(str(count))

if __name__ == '__main__':
    main()
