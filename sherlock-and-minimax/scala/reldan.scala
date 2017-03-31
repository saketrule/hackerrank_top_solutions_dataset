object Solution {

  def main(args: Array[String]) {
    readInt()
    var d = readLine().split(' ').map(_.toInt).toList.distinct.sorted.toArray
    val Array(p, q) = readLine().split(' ').map(_.toInt).toArray
    var i = 0


    // 0 -> 0
    // 1 -> 1
    // 2 -> 1
    // 3 -> 2
    // 4 -> 2
    // 5 -> 3
    // 6 -> 3
    // 7 -> 4
    // 8 -> 4

    var maxI = -1
    var maxDiff = - 1

    def s(el: Int) = {
      val t = el - 1
      t / 2 + t % 2
    }

    val nearestP = d.foldLeft(Int.MaxValue) {
      case(acc, el) if (acc > (Math.abs(el - p))) => (Math.abs(el - p))
      case(acc, _) => acc
    }


    val nearestQ = d.foldLeft(Int.MaxValue) {
      case(acc, el) if (acc > (Math.abs(el - q))) => (Math.abs(el - q))
      case(acc, _) => acc
    }

    while (i < d.size - 1) {
      val diff = s(d(i + 1) - d(i))
      if (diff > maxDiff && d(i) + diff >= p && d(i) + diff <= q) {
        maxDiff = diff
        maxI = i
      }
      i += 1
    }
    
    var e = if (nearestQ != Int.MaxValue)
      List((nearestQ, q)) else Nil

    if (maxDiff > 0)
      e ::= ((maxDiff, d(maxI) + maxDiff))


    if (nearestP != Int.MaxValue)
      e ::= ((nearestP, p))

    
    
    println(e.maxBy(_._1)._2)

  }
}