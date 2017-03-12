#!/usr/bin/python
import sys, math, heapq
class Node:
	def __init__(self, id = None):
		self.id = id
		self.edges = []
		self.attribs = Attribs()
	
class Attribs:
	#Additional attributes for MST
	def __init__(self):
		self.color = 0
		
class HeapObj:
	#For comparing node priorities in the Dijkstras heap
	def __init__(self, node = None, priority = -1):
		self.node = node
		self.priority = priority
	
	def __cmp__(self, other):
		return cmp(self.priority, other.priority)


def main():
	
	for t in range(0,1):
		try:
			[N,M] = map(int, raw_input().split())

			nodelist = [None] * (N + 1)
			for i in range(1,N+1):
				nodelist[i] = Node(i)

			for i in range(0,M):
				[n1,n2,w] = map(int, raw_input().strip().split())
				nodelist[n1].edges.append([nodelist[n2],w])
				nodelist[n2].edges.append([nodelist[n1],w])

			S = int(raw_input().strip())

		except ValueError, e:
			print e
			sys.exit()
	
		startnode = nodelist[S]
		MST = 0
		heap = []
		heapq.heappush(heap, HeapObj(startnode, 0))
		
		while len(heap) != 0:
			heapobj = heapq.heappop(heap)
			node = heapobj.node
			if node.attribs.color == 0:
				node.attribs.color = 1
				MST = MST + heapobj.priority
				for [nearbynode, edgeweight] in node.edges:
					if nearbynode.attribs.color == 0:
						heapq.heappush(heap, HeapObj(nearbynode, edgeweight))
							
		print MST
		
		
if __name__ == '__main__':
	main()