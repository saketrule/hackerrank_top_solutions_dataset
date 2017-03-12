object Solution {

  def solve(sticks : List[BigInt]): Unit = {
    if(!sticks.isEmpty) {
      val min = sticks.min
      val newSticks = sticks map (x => x - min) filter (_ != 0)
      if(!newSticks.isEmpty) {
        println(newSticks.size)
        solve(newSticks)
      }
    }
  }


  def main(args: Array[String]) {
    val baseLenght = Console.readLine
    val sticks = Console.readLine split(' ') map (BigInt(_)) toList

    println(baseLenght)
    solve(sticks)
  }
}