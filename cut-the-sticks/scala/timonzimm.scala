object Solution {

  def main(args: Array[String]) {
    val sticks = io.Source.stdin.getLines().filter(_.length > 0).drop(1).take(1).toList(0).split(" ").toList.map(_.toInt)
    println(sticks.size)
    sticks.distinct.sorted.dropRight(1).foreach(i => println(sticks.map(x => x-i).filter(_ > 0).size))
  }
}