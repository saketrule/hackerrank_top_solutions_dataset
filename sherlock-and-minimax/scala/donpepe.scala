object Solution extends App {

  override def main(args: Array[String]) {

    val raw = io.Source.stdin.getLines().toList
    val N = raw.head.toInt
    val input = raw.tail.head.split(" ").toList.map(_.toInt).sortWith(_ < _)
    val range = raw.last.split(" ").toList.map(_.toInt)
    
    val (low, upp) = (range.head, range.last)
    
	def allPairs(l: List[Int]): List[(Int, Int)] = l match {
		case Nil => Nil
		case head::tail => tail.map((head, _)) ::: allPairs(tail)
	}
    
    def min(m: Int) = input.map(x => math.abs(x-m)).min   
    
    val allpairs1 = allPairs(input).map(x => (x._1 + x._2)/2).sortWith(_<_).filterNot((x) => (x < low) || (x > upp))
    val allpairs = low :: allpairs1 ::: List(upp)
    val pairsWithM = allpairs.map(x => (x, min(x)))
    println(pairsWithM.maxBy(_._2)._1)

  }
}