import scala.io.StdIn.{readInt}
object Solution {

    private def isPrime(n : Int) : Boolean = 
        primes.takeWhile(p => p*p <= n).forall(n % _ != 0)
    
    private val primes = 2 #:: Stream.from(3,2).filter(isPrime)
    private def primesBelow(n : Long) = primes.takeWhile(_ <= n)
    
    private def tileWall {
        val n = readInt;
        val tilings = new Array[Long](n+1);
        tilings(0) = 1L;
        if(n >= 1) tilings(1) = 1L;
        if(n >= 2) tilings(2) = 1L;
        if(n >= 3) tilings(3) = 1L;
        
        for(i <- 4 to n)
            tilings(i) = tilings(i-1) + tilings(i-4);

        println(primesBelow(tilings(n)).length)
    }
    
    def main(args: Array[String]) {
        val t = readInt; for(i <- 0 until t) tileWall
    }
}