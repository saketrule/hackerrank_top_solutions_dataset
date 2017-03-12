object Solution {

    def find(i:Int, parent: Array[Int]) : Int = {
        var j = i
        while(parent(j) != j)
            j = parent(j)
        return j
    }
    
    def union( i:Int, j:Int, parent:Array[Int] ) : Unit = {
        parent(i) = j
    } 
    
    def find_distances(start:Int, n: Int, edges:List[Tuple3[Int,Int,Long]]) : Long = {
        var total : Long = 0
        var parent = Range(0, n).toArray
        // var done = Set[Int]()
        var sorted = edges.sortWith(_._3 < _._3)
        // println(sorted.mkString(" "))
        for((i, j, edist) <- sorted) {
            if (find(i, parent) != find(j, parent) ) {
                // done = done ++ Set(i, j)
                // println("" + (i, j) + (find(i, parent), find(j, parent)))
                union(find(i, parent), find(j, parent), parent)
                total += edist
                // println(total)
                // println(parent.mkString(" "))
            } // else {
            //     println("Excluded " + (i, j) + (find(i, parent), find(j, parent)))
            // }
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
        var edges = Range(0, m).map(j => (sc.nextInt()-1, sc.nextInt()-1, sc.nextLong())).sortWith(_._1 < _._1).toList
        var start = sc.nextInt()-1

        // var all_edges = edges ::: edges.map(t => (t._2, t._1, t._3))
        var all_edges = edges
        var res = find_distances(start, n, all_edges)
        println(res)
    }
}