object Solution extends App {
  readLine()
  var data = readLine().split(" ").map(_.toInt).toList.sorted
  var result = List(data.size)
  while (!data.isEmpty) {
    data = data.collect{case (el) if el - data.head > 0 => el - data.head}
    if (data.size > 0)
      result ::= data.size
  }
  println(result.reverse.mkString("\n"))
}