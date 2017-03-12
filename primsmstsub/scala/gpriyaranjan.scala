object Solution {

    def find_distances(start:Int, nodes : Array[List[Tuple3[Int,Int,Long]]]) : Long = {
        var done = Set(start)
        var edges: Set[Tuple3[Int,Int,Long]] = nodes(start).toSet
        var total: Long = 0
            
        // println(edges.mkString(" "))
        while(edges.size > 0) {
            var first: Tuple3[Int,Int,Long] = (-1, -1, Long.MaxValue)
            var min_edge: Tuple3[Int,Int,Long] = edges.foldLeft(first)((a,b) => if (a._3 < b._3) a else b)
            // println(min_edge)
            total += min_edge._3
            done = done ++ List(min_edge._2)
            // println(done.mkString(" "))
            // println(edges)
            edges = edges ++ nodes(min_edge._2)
            edges = edges.filter(x => !done.contains(x._2))
            // println(edges.mkString(" "))
        }
        return total
    }
    
    def main(args: Array[String]) {
        /* Enter your code here. 
           Read input from STDIN. 
           Print output to STDOUT. 
           Your class should be named Solution
        */
        var sc = new java.util.Scanner(System.in)
        var n = sc.nextInt()
        var m = sc.nextInt()
        var edges = Range(0, m).map(j => (sc.nextInt()-1, sc.nextInt()-1, sc.nextLong())).sortWith(_._1 < _._1)
        var start = sc.nextInt()-1
        var nodes = Array.ofDim[List[Tuple3[Int,Int,Long]]](n)
        for((i,j,edist) <- edges) {
            nodes(i) = ( if (nodes(i) != null) nodes(i) else List() ) ::: List((i, j, edist))
            nodes(j) = ( if (nodes(j) != null) nodes(j) else List() ) ::: List((j, i, edist))
        }
        var res = find_distances(start, nodes)
        println(res)
    }
}