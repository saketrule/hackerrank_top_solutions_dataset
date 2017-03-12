object Solution {

  def main(args: Array[String]): Unit = {

    var n = readInt
    var arr = List(readLine.split(" ").map(a => Integer.parseInt(a))).flatten.sorted
    arr = arr.reverse
    while (arr.length >0) {
      
      println(arr.length)
      arr = arr.map(f => f - arr.min)
      arr = arr.take(arr.indexOf(0))
      
    }
    

  }

}