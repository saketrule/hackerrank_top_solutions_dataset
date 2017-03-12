import java.util.Scanner;
import scala.collection.immutable.TreeMap;

class MyNodePriorityQueue(priorityToValuesIn : TreeMap[Long, Set[Int]], valueToPriorityIn : Map[Int, Long]){
    private var priorityToValues = priorityToValuesIn;
    private var valueToPriority = valueToPriorityIn;
    
    def this() = {this(TreeMap[Long, Set[Int]](), Map[Int, Long]());}
    
    def insert(priority : Long, value : Int) : Unit = {
        val values = priorityToValues.getOrElse(priority, Set[Int]()) + value;
        priorityToValues = priorityToValues + (priority -> values);
        valueToPriority = valueToPriority + (value -> priority);
    }
    
    def dequeue() : (Long, Int) = {
        val (priority, values) = priorityToValues.head;
        val value = values.head;
        val newValues = values - value;
        priorityToValues = if (newValues.isEmpty){priorityToValues - priority} else {priorityToValues + (priority -> newValues)}
        valueToPriority = valueToPriority - value;
        return (priority, value);
    }
    
    def changePriority(priority : Long, value : Int) : Unit = {
        val oldPriority = valueToPriority.getOrElse(value, Long.MaxValue);
        valueToPriority = valueToPriority + (value -> priority);
        val oldValues = priorityToValues.getOrElse(oldPriority, Set[Int]()) - value;
        priorityToValues = if (oldValues.isEmpty){priorityToValues - oldPriority} else {priorityToValues + (oldPriority -> oldValues)}
        val values = priorityToValues.getOrElse(priority, Set[Int]()) + value;
        priorityToValues = priorityToValues + (priority -> values);
    }
    
    def isEmpty() : Boolean = { valueToPriority.isEmpty }
    
    def contains(value : Int) : Boolean = { valueToPriority.contains(value) }
    
    def getPriority(value : Int) : Long = { valueToPriority.getOrElse(value, Long.MaxValue); }
    
    override def toString() : String = s"""(priorityToValues = ${priorityToValues}, valueToPriority = ${valueToPriority})""" 
}



object Solution {    
    private val scanner = new Scanner(System.in);
    
    def prim(edgeToWeight : Map[(Int, Int), Long], n : Int, root : Int) : Map[(Int, Int), Long] = {
        val pq = new MyNodePriorityQueue();
        (1 to n).foreach(i => {
            val priority = if (i == root){0L}else{Long.MaxValue};
            pq.insert(priority, i);
        });
        var parent = Map[Int, Int]();
        var neighbors = Map[Int, Set[Int]]();
        edgeToWeight.keys.foreach(edge => {
            val (x, y) = edge;
            val xNeighbors = neighbors.getOrElse(x, Set[Int]()) + y;
            val yNeighbors = neighbors.getOrElse(y, Set[Int]()) + x;
            neighbors = neighbors + (x -> xNeighbors) + (y -> yNeighbors);
        });
        // println(s"""prim(edgeToWeight = ${edgeToWeight}, n = ${n}, root = ${root}) parent = ${parent}, $neighbors = ${neighbors}, """)
        while (!pq.isEmpty){
            val (nodeWeight, node) = pq.dequeue();
            //println(s"""(nodeWeight = ${nodeWeight}, node = ${node}) after pq.dequeue, pq = ${pq}""")
            neighbors.getOrElse(node, Set[Int]()).foreach(neighbor => {
                val edge = (Math.min(node, neighbor), Math.max(node, neighbor));
                val edgeWeight = edgeToWeight.getOrElse(edge, Long.MaxValue);
                val neighborNodeWeight = pq.getPriority(neighbor);
                // println(s"""neigbhor = ${neighbor}, edge = ${edge}, edgeWeight = ${edgeWeight}, nodeWeight = ${nodeWeight}, neighborNodeWeight = ${neighborNodeWeight}""")
                if (pq.contains(neighbor) && edgeWeight < neighborNodeWeight){
                    parent = parent + (neighbor -> node);
                    pq.changePriority(edgeWeight, neighbor);
                    // println(s"""relaxed node, parent = ${parent}, pq = ${pq}""") 
                }
            })
        }
        var result = Map[(Int, Int), Long]();
        parent.foreach(kv => {
            val (child, father) = kv;
            val edge = (Math.min(child, father), Math.max(child, father));
            val weight = edgeToWeight.getOrElse(edge, Long.MaxValue);
            result = result + (edge -> weight)
        })
        return result;
    }
    
    def main(args: Array[String]) {
        val n = scanner.nextInt();
        val m = scanner.nextInt();
        var edgeToWeight = Map[(Int, Int), Long]();
        (1 to m).foreach(i => {
            val x = scanner.nextInt();
            val y = scanner.nextInt();
            val edge = (Math.min(x,y), Math.max(x,y));
            val weight = scanner.nextInt();
            val oldWeight = edgeToWeight.getOrElse(edge, Long.MaxValue);
            if (oldWeight > weight){
                edgeToWeight = edgeToWeight + (edge -> weight);
            }
        });
        val s = scanner.nextInt();
        val mst = prim(edgeToWeight, n, s);
        val result = mst.values.foldLeft(0L)(_+_);
        println(s"""${result}""")
    }
}