import scala.collection.mutable.Buffer
import scala.collection.mutable.Queue
import java.util.Scanner

object Solution {
  val white = 0
  val gray = 1
  val black = 2
  
  class Vertex(val num: Int, var color: Int, var parent: Vertex, var childNum: Int) {
    def incChildNum() {
      var cur = this
      while(cur != null) {
        cur.childNum += 1
        cur = cur.parent
      }
    }
  }
  
  class Graph(v: Int) {
    val vertexes = new Array[Vertex](v + 1)         //1 based index, so first slot is not used
    for(i <- 1 to v) {
      (vertexes(i) = new Vertex(i, white, null, 0))
    }
    val adjs = new Array[Buffer[Int]](v + 1)        //1 based index, so first slot is not used
    for(i <- 1 to v) {
      (adjs(i) = Buffer.empty[Int])
    }
    
    def addEdge(u: Int, v: Int) {
      adjs(u) += v
      adjs(v) += u
    }
    
    def adjacents(v: Int): Seq[Int] = adjs(v)
    
    def countChilds() {
      //count childs by bfs
      val s = vertexes(1)
      
      s.color = gray
      
      val q = Queue.empty[Vertex]
      q.enqueue(s)
      while(!q.isEmpty) {
        val u = q.dequeue
        for(v <- adjacents(u.num)) {
          val vtx = vertexes(v)
          if(vtx.color == white) {
            vtx.color = gray
            vtx.parent = u
            u.incChildNum()
            
            q.enqueue(vtx)
          }
        }
        
        u.color = black
      }
    }
    
    def countEven(): Int = {
      var count = 0
      
      val sorted = vertexes.slice(1, vertexes.length).sortBy(_.childNum)
      for(v <- sorted) {
        v.color = white
      }
      
      for(v <- sorted) {
        var cur = v
        var found = false
        while(!found && cur.parent != null && cur.color == white) {
          if((cur.childNum + 1) % 2 == 0) {
            found = true
            count += 1
            
            cur.parent.childNum -= cur.childNum - 1
            cur.parent = null
          }
          
          cur.color = gray
          cur = cur.parent
        }
      }
      
      count
    }
  }
  
  def main(args: Array[String]) {
    val s = new Scanner(System.in)
    val n = s.nextInt()
    val m = s.nextInt()
    
    val g = new Graph(n)
    for(i <- 1 to m) {
      g.addEdge(s.nextInt(), s.nextInt())
    }
    
    g.countChilds()
    val ret = g.countEven()
    println(ret)
  }
}