object Solution {

    case class Edge (
        node1 : Int,
        node2 : Int,
        len  : Int
    ) {
        def hasNode(n:Int) = { node1 == n || node2 == n }
        def includedIn(nodes:Set[Int]) = { nodes.contains(node1) && nodes.contains(node2) }
    }
    
    def prims1(start:Int, nb:Int, edges:List[Edge]) = {
        def aux(nodes:Set[Int], res:List[Edge], remain:List[Edge]):List[Edge] = {
            if (nodes.size >= nb || remain.isEmpty) res;
            else {
                val available = nodes.toList.map{ n => remain.filter(_.hasNode(n)) }.flatten;
                if (available.isEmpty) {
                    println("Disjoint graph, cannot solve problem");
                    res
                } else {
                    val best = available.tail.foldLeft(available.head){
                        (smallest, e) => if (e.len < smallest.len) e else smallest
                    };
                    val newNodes = nodes + best.node1 + best.node2;
                    val remEdges = remain.filter{ !_.includedIn(newNodes) };
                    aux(newNodes, best::res, remEdges);
                }
            }
        };
        aux(Set(start),List(), edges);
    }
    
    def prims(start:Int, nb:Int, edges:List[Edge]) = {
        def aux(nodes:Set[Int], res:List[Edge], nexts:List[Edge]):List[Edge] = {
            //println(nodes.toString+" -> "+nexts.toString);
            if (nodes.size >= nb || nexts.isEmpty) res;
            else {
                val best = nexts.tail.foldLeft(nexts.head){
                    (smallest, e) => if (e.len < smallest.len) e else smallest
                };
                val newNode = if (nodes.contains(best.node1)) best.node2 else best.node1;
                val newNodes = nodes + newNode;
                // Remove nodes in neighborhood with the new node
                val newNexts = nexts.filter(!_.hasNode(newNode));
                // Get the neighbors added with new node
                val addedNexts = edges.filter{
                    e => e.hasNode(newNode) && !e.includedIn(newNodes);
                };
                aux(newNodes, best :: res, newNexts ::: addedNexts)
            }
        };
        aux(Set(start), List(), edges.filter(_.hasNode(start)));
    }
    
    def main(args: Array[String]) {
        val fline = readLine().split(" ");
        val numNodes = fline(0).toInt;
        val numEdges = fline(1).toInt;
        def aux(i:Int, res:List[Edge]):List[Edge] = {
            if (i < numEdges) {
                val d = readLine().split(" ").map(_.toInt);
                val next = Edge(d(0), d(1), d(2));
                aux(i+1, next::res);
            } else res
        }
        val edges = aux(0, List());
        val start = readLine().toInt;
        val optimized = prims(start, numNodes, edges);
        println(optimized.foldLeft(0){ (acc,e) => acc+e.len })
    }
}