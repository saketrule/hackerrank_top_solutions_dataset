object Solution extends App {
  val lines = io.Source.stdin.getLines().toStream
  val caseCount = lines.head.toInt
  val cases = lines.tail.take(caseCount).toList.map(a => a.toInt)
  val sieve = sieveOfErastothenes((2 to solutionise(40)).toList, Nil)
  def solutionise(remaining: Int): Int = {
    if (remaining < 4) 1 else
     solutionise(remaining - 4) + solutionise(remaining - 1) 
  }
  
  def sieveOfErastothenes(aList : List[Int], acc : List[Int]): List[Int] = {
    if (aList.isEmpty) acc else
      sieveOfErastothenes(aList.filter(a => a % aList.head != 0), aList.head :: acc)
  }
  
  def primeTo(value : Int): Int = {
     sieve.filter(a => a <= value).size
  }
  
  (for {
      a <- cases
    } yield primeTo(solutionise(a))) foreach println
  
}