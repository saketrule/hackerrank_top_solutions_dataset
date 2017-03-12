object Solution {

    class Node (val index:Int) {
		val neighborIndexArrayBuffer:scala.collection.mutable.ArrayBuffer[Int] = new scala.collection.mutable.ArrayBuffer[Int]();
		var level = -1;
		var parentIndex = -1;
		val childreIndexnArrayBuffer:scala.collection.mutable.ArrayBuffer[Int] = new scala.collection.mutable.ArrayBuffer[Int]();
		var split = false;
	}
	
	def buildTree(nodeArray:Array[Node], nodeIndexQueue:scala.collection.mutable.Queue[Int]):Unit = {
		if (nodeIndexQueue.isEmpty) {
			return;
		}
		else {
			val nodeIndex = nodeIndexQueue.dequeue();
			val unvisitedChildrenArrayBuffer = nodeArray(nodeIndex).neighborIndexArrayBuffer.filter(index => nodeArray(index).level == -1);
			unvisitedChildrenArrayBuffer.foreach(index => {
				// Parent
				nodeArray(nodeIndex).childreIndexnArrayBuffer.+=(index);
				// Children
				nodeArray(index).parentIndex = nodeIndex;
				nodeArray(index).level = nodeArray(nodeIndex).level + 1;
				nodeIndexQueue.enqueue(index)
			})
			return buildTree(nodeArray, nodeIndexQueue);
		}
	}
	
	def findSubTreeNodeAmount(nodeArray:Array[Node], nodeIndexQueue:scala.collection.mutable.Queue[Int], nodeAmount:Int):Int = {
		if (nodeIndexQueue.isEmpty) {
			return nodeAmount;
		}
		else {
			val nodeIndex = nodeIndexQueue.dequeue();
			nodeArray(nodeIndex).childreIndexnArrayBuffer.foreach(index => {
				if(nodeArray(index).split == false) {
					nodeIndexQueue.enqueue(index)
				}
			});
			return findSubTreeNodeAmount(nodeArray, nodeIndexQueue, nodeAmount + 1);
		}
	}
	
	def findSubTreeNodeAmount(nodeArray:Array[Node], nodeIndex:Int):Int = {
		val queue = new scala.collection.mutable.Queue[Int]();
		queue.enqueue(nodeIndex);
		return findSubTreeNodeAmount(nodeArray, queue, 0);
	}
	
	def labelSplitSubTree(nodeArray:Array[Node], nodeIndexQueue:scala.collection.mutable.Queue[Int]):Unit = {
		if (nodeIndexQueue.isEmpty) {
			return;
		}
		else {
			val nodeIndex = nodeIndexQueue.dequeue();
			nodeArray(nodeIndex).split = true;
			nodeArray(nodeIndex).childreIndexnArrayBuffer.foreach(index => nodeIndexQueue.enqueue(index));
			return labelSplitSubTree(nodeArray, nodeIndexQueue);
		}
	}
	
	def labelSplitSubTree(nodeArray:Array[Node], nodeIndex:Int):Unit = {
		val queue = new scala.collection.mutable.Queue[Int]();
		queue.enqueue(nodeIndex);
		return labelSplitSubTree(nodeArray, queue);
	}
	
	def split(nodeArray:Array[Node], nodeIndexList:List[Int], treeAmount:Int):Int = {
		if (nodeIndexList.isEmpty) {
			return treeAmount;
		}
		else {
			val head :: rest = nodeIndexList;
			if (nodeArray(head).split) {
				return split(nodeArray, rest, treeAmount);
			}
			else {
				val subTreeNodeAmount = findSubTreeNodeAmount(nodeArray, head);
				
				if (subTreeNodeAmount % 2 == 0) {
					labelSplitSubTree(nodeArray, head);
					return split(nodeArray, rest, treeAmount + 1);
				}
				else {
					return split(nodeArray, rest, treeAmount);
				}
			}
		}
	}
	
	def main(args: Array[String]) {
		val inputIterator = io.Source.stdin.getLines();
		val nodeAmount :: edgeAmount :: Nil = inputIterator.next().split(" ").toList.map(_.toInt);
		val nodeArray = Array.ofDim[Node](nodeAmount + 1);
		for (i <- 0 to nodeAmount ) {
			nodeArray(i) = new Node(i);
		}
		for (i <- 1 to edgeAmount ) {
			val nodeIndex1 :: nodeIndex2 :: Nil = inputIterator.next().split(" ").toList.map(_.toInt);
			nodeArray(nodeIndex1).neighborIndexArrayBuffer.+=(nodeIndex2);
			nodeArray(nodeIndex2).neighborIndexArrayBuffer.+=(nodeIndex1);
		}
		
		// Select root for tree
		val rootIndex = 2;
		nodeArray(rootIndex).level = 0;
		// Build Tree
		val queue = new scala.collection.mutable.Queue[Int]();
		queue.enqueue(rootIndex);
		buildTree(nodeArray, queue);
		// Split
		val sortedLeafToRootNodeIndexList = nodeArray.toList.drop(1).sortWith((a, b) => a.level > b.level).map(node => node.index);
		println(split(nodeArray, sortedLeafToRootNodeIndexList, 0) - 1);
		
		/*
		// Display Tree
		nodeArray.foreach(node => {
			println("===================")
			println("index: " + node.index);
			println("level: " + node.level);
			println("parent: " + node.parentIndex);
			print("neighbors:")
			node.neighborIndexArrayBuffer.foreach(index => print(" " + index));
			println("");
			print("children:")
			node.childreIndexnArrayBuffer.foreach(index => print(" " + index));
			println("");
		})
		*/
	}
}