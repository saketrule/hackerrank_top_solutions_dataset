object Solution {

  def main(args: Array[String]) {

    val input = io.Source.stdin.getLines.toList
        
    // f(n) = f(n-1) + f(n-4)
    // because: if I have N elements, in how many ways
    // can I solve the problem?
    // a) adding a 1x4 brick -> f(n-1)
    // b) adding a 4x1 brick -> f(n-4)
    def f(n: Int): Int = {
      if(n < 0) 0
      else if (n <= 3) 1
      else f(n-1) + f(n-4)
    }
      
    lazy val primes: Stream[Int] =
      2 #::
      3 #::
      Stream.from(4).filter( e => primes.takeWhile( x => x*x <= e).forall( e % _ != 0))

    input.drop(1).map(_.toInt).map(f).map( x => primes.takeWhile( _ <= x).length).foreach(println)
  }
}