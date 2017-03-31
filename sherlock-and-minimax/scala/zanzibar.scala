object Solution {

  class Sol(p:Int, q:Int, arr:Array[Int]) {
    def sortAndRemDup(x:Array[Int]):Array[Int] = {
      if(x.length > 1) {
        scala.util.Sorting.quickSort(x)
        var j: Int = 1
        for (i <- 1 until x.length) {
          if (x(i) != x(i - 1)) {
            x(j) = x(i)
            j += 1
          }
        }
        x.take(j)
      } else {
        x
      }
    }
    def bsearch(k:Int):Int = java.util.Arrays.binarySearch(a,k)
    def mean(i:Int):Int = a(i) + midl(i) // (a(i) + a(i+1))/2
    def midl(i:Int):Int = (a(i+1) - a(i)) >> 1
    val a = sortAndRemDup(arr)
    def solve():Int = {
      if(a.isEmpty || p == q)
        return q
      if(q <= a.head)
        return p
      if(a.last <= p)
        return q
      var i = bsearch(p)
      var j = bsearch(q)
      if(i == j) { // since p != q, same index cab only if both values have insertion position
        i = ~i // insertion position between i-1 and i
        val m = mean(i - 1) // find mean value of (a(i-1) + a(i)) / 2
        if(p > m)
          return p
        if(q < m)
          return q
        return m
      }
      var maxmid = 0
      var maxMin = 0
      if(i < 0) {
        i = ~i
        if(i == 0 || p > mean(i - 1)) { // before 1st element or after mean (a(i-1) + a(i)) / 2
          maxmid = a(i) - p
          maxMin = p
        } else {
          i -= 1
        }
      }
      if(j < 0) {
        j = ~j
        if(j == a.length || q < mean(j - 1)) {
          val midHere = q - a(j - 1)
          if(midHere > maxmid) {
            maxmid = midHere
            maxMin = q
          }
          j -= 1
        }
      }
      while(i < j) {
        val midHere = midl(i)
        if(midHere > maxmid || (midHere == maxmid && maxMin == q)) {
          maxmid = midHere
          maxMin = mean(i)
        }
        i += 1
      }
      maxMin
    }
  }
  def test(inp:(Int, Int, Array[Int])) :Unit = {
    val (p, q, a) = inp
    val s = new Sol(p, q, a)
    println(s.solve())
  }
  def getInput():(Int, Int, Array[Int]) = {
    val n = io.StdIn.readInt
    val a = io.StdIn.readLine.split(" ").map(_.toInt)
    val Array(p,q) = io.StdIn.readLine.split(" ").map(_.toInt)
    (p, q, a)
  }
  def main(args: Array[String]) {
    //test(4, 9, Array(8, 14, 5, 5, 5, 5, 8, 14))
    //test(4, 9, Array(5, 8, 14))
    test(getInput())
  }
}