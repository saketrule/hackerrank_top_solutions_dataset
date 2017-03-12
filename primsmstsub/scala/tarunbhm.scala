object Solution {
  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)

    val n = sc.nextInt()
    val m = sc.nextInt()
    val vertices = new Array[scala.collection.mutable.Map[Int, Int]](n+1)
    val read = Array.ofDim[Boolean](n+1, n+1)
    val weights = new Array[Long](n+1)
    val maxWeight = Integer.MAX_VALUE
    for(i <- 0 to n){
      vertices(i) = scala.collection.mutable.Map.empty[Int, Int]
      weights(i) = maxWeight
    }

    for(i <- 1 to m){
      val s = sc.nextInt()
      val d = sc.nextInt()
      val w = sc.nextInt()
      if(read(s)(d)){
        // Have read both source and destination already so need to update previous edges
        if(w < vertices(s)(d)){
          vertices(s).update(d, w)
          vertices(d).update(s, w)
        }
      } else {
        vertices(s) += (d -> w)
        vertices(d) += (s -> w)
        read(s)(d) = true
        read(d)(s) = true
      }
    }

    val source = sc.nextInt()
    val queue = scala.collection.mutable.Map.empty[Int, Long]
    weights(source) = 0
    for(i <- 1 to n){
      queue += (i -> weights(i))
    }

    object MinOrder extends math.Ordering[(Int, Long)] {
      override def compare(x: (Int, Long), y: (Int, Long)): Int = x._2 compare y._2
    }

    var minWeight = 0L
    
    while(queue.nonEmpty){
      val (current, currentWeight) = queue.min(MinOrder)
      minWeight += currentWeight
      queue -= current
      vertices(current).foreach{v =>
        val (neighbor, weight) = v
        if(weight < weights(neighbor)){
          weights(neighbor) = weight
          if(queue.get(neighbor).isDefined){
            queue.update(neighbor, weight)
          }
        }
      }
    }

    println(minWeight)
  }
}