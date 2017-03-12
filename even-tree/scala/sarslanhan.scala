object Solution {
	
	class Node(_value:Int) {
		val value = _value
		var numOfNodes = 0
		var visited = false
		var adjacentNodes = Seq[Node]()

		def dfs():Int = {
			if(!visited) {
				visited = true

				numOfNodes = 1 + adjacentNodes.foldLeft(0)((count, child) => count + child.dfs())
				numOfNodes
			} else {
				0
			}
		}
	}

	def main(args: Array[String]) {
		val lines = io.Source.stdin.getLines()

		val a = lines.next().split(" ")

		val N = a(0).toInt
		
		val nodes = 1 to N map ((n) => new Node(n))
		
		val M = a(1).toInt

		val edges = lines.take(M).toSeq
									.map((l) => l.split(" "))
									.map((a) => (a(0).toInt, a(1).toInt))

		(edges ++ edges.map(_.swap)) foreach { edge =>
			nodes(edge._1-1).adjacentNodes = nodes(edge._1-1).adjacentNodes :+ nodes(edge._2-1)
		}
		nodes(0).dfs()
		println(nodes.count(_.numOfNodes % 2 == 0)-1)
	}
}