object Solution {

	def buildPrimes(upto: Int):Seq[Int] = {
		val primes = collection.mutable.Seq.fill(upto+1)(true)
		primes.update(0, false)
		primes.update(1, false)

		for(n <- 2 to upto) {
			if(primes.apply(n)) {
				var i = n*2
				while(i <= upto) {
					primes.update(i, false)

					i+=n
				}
			}
		}

		primes.zipWithIndex.filter(_._1).map(_._2)
	}

	def main(args: Array[String]) {
		val lines = io.Source.stdin.getLines()
		val T = lines.next().toInt

		val A = Array.fill(41)(1)

		for(i <- 4 to 40) {
			A(i) = A(i-1)+A(i-4)
		}

		val testCases = lines.take(T).map(_.toInt).toSeq
		val primes = buildPrimes(A(testCases.max)+1)

		testCases.foreach {
			N =>
				val numOfConfigs = A(N)
				println(primes.count(_<=numOfConfigs))
		}
	}
}