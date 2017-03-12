object Solution {
    class Graph {
        var vertices = Set[Int]()
        var edges = List[Edge]()
        var vertexMap = Map[Int,List[Edge]]()
        def revEdge(e:Edge)=new Edge(e.node2,e.node1,e.weight)
        def addVertex(v: Int) = vertices = vertices + v
        def addEdge(e: Edge) = {
            edges = e :: edges
            vertexMap += e.node1 -> (e::vertexMap.getOrElse(e.node1, Nil))
            vertexMap += e.node2 -> (revEdge(e)::vertexMap.getOrElse(e.node2, Nil))
        }
        override def toString = vertexMap.toString
    }
    case class Edge(node1: Int, node2: Int, weight: Int = 0)
        
    def main(args: Array[String]) {
        var g=new Graph();
        val sc = new java.util.Scanner (System.in)
        var n = sc.nextInt()
        var m = sc.nextInt()
        var visited=Set[Int]()
        var toVisit=(1 to n).toSet
        var path=0
        for(i<-1 to m) {
            g.addVertex(i+1)
            var x=sc.nextInt()
            var y=sc.nextInt()
            var r=sc.nextInt()
            g.addVertex(x)
            g.addVertex(y)                
            g.addEdge(new Edge(x,y,r))
        }
        var s=sc.nextInt()
        visited+=s
        toVisit-=s
//        println(g.toString)
        while(toVisit.size>0) {
            var e=visited.flatMap(x=>g.vertexMap(x)).filterNot(visited contains _.node2).min(Ordering.by((x:Edge)=>(x.weight)))
            visited+=e.node2
            toVisit-=e.node2
            path+=e.weight
        }
        println(path)
        
    }
}