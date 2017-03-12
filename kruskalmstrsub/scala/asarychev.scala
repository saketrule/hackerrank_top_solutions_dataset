  object Solution {

    import scala.collection.mutable

    def main(args: Array[String]) {
      val sc = new java.util.Scanner(System.in)
      val N = sc.nextInt
      val M = sc.nextInt

      case class Node(ch: mutable.ListBuffer[(Int, Int)] = mutable.ListBuffer(), var d: Int = Int.MaxValue)

      val G = Vector.fill(N)(Node())

      for (j <- 1 to M) {
        val x = sc.nextInt - 1
        val y = sc.nextInt - 1
        val r = sc.nextInt
        G(y).ch.append((r, x))
        G(x).ch.append((r, y))
      }

      val S = sc.nextInt - 1
      val X = mutable.Set(S)

      //        val Q = mutable.PriorityQueue[(Int, Int)]((0, S))(Ordering[Int].reverse.on(_._1))
      val Q = mutable.PriorityQueue[(Int, Int)]()(Ordering.by(-_._1))

      Q ++= G(S).ch

      var res = 0

      while (Q.nonEmpty) {
        val (d, i) = Q.dequeue()
        if (!X.contains(i)) {
          X += i
          Q ++= G(i).ch
          res += d
        }
      }

      println(res)
    }
  }
