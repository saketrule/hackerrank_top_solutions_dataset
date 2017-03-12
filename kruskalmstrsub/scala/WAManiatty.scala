import java.util.Scanner;
import scala.collection.immutable.TreeMap;

object Solution {
    private val scanner = new Scanner(System.in);
    
    def findSet(node : Int, nodeToParent : Map[Int,(Int, Int)]) : (Int, Map[Int, (Int, Int)]) = {
        var parent = nodeToParent.getOrElse(node, (-1, -1))._1;
        val result = if (node != parent){ 
            findSet(parent, nodeToParent);
        } else {(parent, nodeToParent)};
        return result;
    }
    
    def link(nodeToParent : Map[Int, (Int, Int)], x : Int, y : Int) : Map[Int, (Int, Int)] = {
        val (parentX, rankX) = nodeToParent.getOrElse(x, (-1, -1));
        val (parentY, rankY) = nodeToParent.getOrElse(y, (-1, -1));
        val result = if (rankX > rankY){
                nodeToParent + (y -> (parentX, rankY))
            } else if (rankX == rankY){
                nodeToParent + (x -> (parentY, rankY + 1))
            } else {
                nodeToParent + (x -> (parentY, rankX)) + (y -> (parentY, rankY + 1));
            }
        return result;
    }
                                                          
    def union(nodeToParent : Map[Int, (Int, Int)], x : Int, y : Int) : Map[Int, (Int, Int)] = {
        val (xParent, xNodeToParent) = findSet(x, nodeToParent);
        val (yParent, yNodeToParent) = findSet(y, xNodeToParent);
        return link(yNodeToParent, xParent, yParent);
    }
    
    def main(args: Array[String]) {
        val n = scanner.nextInt(); // number of nodes,N
        val m = scanner.nextInt(); // number of edges,m
        var edgeToWeight = Map[(Int, Int), Long]();
        (1 to m).foreach(i => {
            val x = scanner.nextInt();
            val y = scanner.nextInt();
            val w = scanner.nextLong();
            val edge = (Math.min(x, y), Math.max(x,y));
            val oldWeight = edgeToWeight.getOrElse(edge, Long.MaxValue);
            if (w < oldWeight){
                edgeToWeight = edgeToWeight + (edge -> w);
            }
        })
        var weightToEdges = TreeMap[Long, Set[(Int,Int)]]();
        edgeToWeight.foreach(kv => {
            val (edge, weight) = kv;
            val edgeSet = weightToEdges.getOrElse(weight, Set[(Int, Int)]()) + edge;
            weightToEdges = weightToEdges + (weight -> edgeSet)
        });
        var result = Map[(Int, Int), Long]();
        var nodeToParent = (1 to n).map(node => (node -> (node, 0))).toMap; // first element is node, second is parent MakeSet
        weightToEdges.foreach(kv => {
            val (weight, edgeSet) = kv;
            //System.out.println(s"Visiting weight = ${weight}");
            edgeSet.foreach(e => {
                val (x, y) = e;
                val (xParent, xNodeToParent) = findSet(x, nodeToParent);
                val (yParent, yNodeToParent) = findSet(y, xNodeToParent);
                nodeToParent = yNodeToParent;
                //System.out.println(s"""visiting edge e=${e}, x = ${x}, xParent = ${xParent}, y = ${y}, yParent =${yParent}, result = ${result}, nodeToParent=${nodeToParent}.""")
                if (xParent != yParent){
                    result = result + (e -> weight);
                    nodeToParent = union(nodeToParent, x, y);
                    //System.out.println(s"""Added edge e = ${e}, nodeToParent = ${nodeToParent}.""")
                }
            });
        });
        val sum = result.values.toVector.foldLeft(0L)(_+_);
        //System.out.println(s"""${sum}, result=${result}""");
        System.out.println(s"""${sum}""");
    }
}