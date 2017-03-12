object Solution {
  def main(args: Array[String]) {
    val lines = scala.io.Source.stdin.getLines()
    lines.next()
    val sticks = lines.next().split(" ").map(_.toInt)
    Stream.iterate(sticks) { s =>
      val m = s.min
      s.map(_ - m).filter(_ > 0)
    }.takeWhile(_.length > 0).foreach(s => println(s.length))
  }
}