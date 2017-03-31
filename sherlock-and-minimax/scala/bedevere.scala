object Solution {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    val a: List[Long] = List.fill(sc.nextInt())(sc.nextLong()).sorted
    val p = sc.nextLong()
    val q = sc.nextLong()

    val pMin = (a map (ai => Math.abs(ai - p))).min -> p
    val qMin = (a map (ai => Math.abs(ai - q))).min -> q

    val min = a.zip(a.tail) flatMap {case (ai,aj) =>
      val m = (ai+aj)/2
      val d = Math.abs(ai-aj)/2
      if (p <= m && m <= q) Some(d->m)
      else None
    }

    val max = (pMin +: min :+ qMin).max._1

    println((pMin +: min :+ qMin) collectFirst {case (d,m) if d == max => m } get)
  }
    
}