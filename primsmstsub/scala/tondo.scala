object Solution {
    class Path(val point1: Int, val point2: Int, val weight: Int) {
        override def toString(): String = s"Path($point1 <--> $point2, $weight)"
    }
    object Path {
        def apply(line: String): Path = {
            val values = line.split(" ").map(_.toInt)
            new Path(values(0), values(1), values(2))
        }
    }
    
    def findNextPath(paths: List[Path], covered: Set[Int]): Path = {
        if(paths.isEmpty)
            throw new NoSuchElementException()
        val (p1, p2) = (paths.head.point1, paths.head.point2)
        if((covered.contains(p1) && !covered.contains(p2)) || (covered.contains(p2) && !covered.contains(p1)))
            paths.head
        else
            findNextPath(paths.tail, covered)
    }
    
    def main(args: Array[String]) {
        val (n::m::Nil) = scala.io.StdIn.readLine().split(" ").map(_.toInt).toList
        var paths = (for(i <- (1 to m)) yield Path(scala.io.StdIn.readLine())).sortWith(_.weight < _.weight).toList
        var weight = 0
        var visited = Set(1)
        while(visited.size < n) {
            paths = paths.filter(path => !visited.contains(path.point1) || !visited.contains(path.point2))
            val next = findNextPath(paths, visited)
            visited += next.point1
            visited += next.point2
            weight += next.weight
        }
        println(weight)
    }
}