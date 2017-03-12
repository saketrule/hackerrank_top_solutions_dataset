object Solution {

    def main(args: Array[String]) {
    val arr = new Array[Int](1000)
    val source = io.Source.stdin.getLines()
    var n = source.next.toInt
    source.next().split(" ").foreach(a => {
      arr(a.toInt) += 1
    })
    arr.foreach(item => {
      if(item>0) {
        println(n)
        n -= item
      }
    })
  }
}