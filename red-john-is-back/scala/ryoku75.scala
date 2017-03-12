import java.util.Scanner

object Solution extends App {

  val in: Scanner = new Scanner(System.in)
  val numLines = in.nextLine.toInt

  val testCases = for (i <- 1 to numLines) yield in.nextLine.toInt

  testCases map computeWalls foreach println

  def computeWalls(n: Int): Int = {

    @annotation.tailrec
    def computeWallsRec(ints: List[Int], max: Int, cur: Int): Int = {
      if (cur == max) ints(0) + ints(3)
      else computeWallsRec(ints.tail :+ ints(0) + ints(3), max, cur + 1)
    }

    if (n < 4) 0
    else computePrime(computeWallsRec(List(1, 1, 1, 1), n, 4))

  }

  def computePrime(n: Int): Int = {

    @annotation.tailrec
    def computePrimesRec(i: Int, s: Stream[Int]): Int = {
      if (s.head > n) i
      else computePrimesRec(i + 1, s.tail)
    }

    computePrimesRec(0, primes)
  }

  def primes(): Stream[Int] = {
    def merge(a: Stream[Int], b: Stream[Int]): Stream[Int] = {
      def next = a.head min b.head
      Stream.cons(next, merge(if (a.head == next) a.tail else a,
        if (b.head == next) b.tail else b))
    }
    def test(n: Int, q: Int,
             compositeStream: Stream[Int],
             primesStream: Stream[Int]): Stream[Int] = {
      if (n == q) test(n + 2, primesStream.tail.head * primesStream.tail.head,
        merge(compositeStream,
          Stream.from(q, 2 * primesStream.head).tail),
        primesStream.tail)
      else if (n == compositeStream.head) test(n + 2, q, compositeStream.tail,
        primesStream)
      else Stream.cons(n, test(n + 2, q, compositeStream, primesStream))
    }
    Stream.cons(2, Stream.cons(3, Stream.cons(5,
      test(7, 25, Stream.from(9, 6), primes().tail.tail))))
  }

}