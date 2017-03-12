object Solution {
    lazy val primes: Stream[Int] = 2 #:: Stream.from(3).filter(n => primes.takeWhile(p => p * p <= n).forall(p => n % p > 0))
    
    def isPrime(n: Int) = primes.takeWhile(_ <= n).contains(n)
    
    def waysToArrange(width: Int): Int = {
        val table = Array.fill(math.max(4, width) + 1) {0}
        for (i <- 1 to 3) {
            table(i) = 1
        }
        table(4) = 2
        for (i <- 5 to width) {
            table(i) = table(i - 1) + table(i - 4)
        }
        table(width)
    }
    
    def main(args: Array[String]) {
        io.Source.stdin.getLines.drop(1).map(n => {
            val numberOfWaysToArrange = waysToArrange(n.toInt)
            primes.takeWhile(_ <= numberOfWaysToArrange).size
        }).foreach(println)
    }
}