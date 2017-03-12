object Solution {

   def main(args: Array[String]) {
    var i = readLine().split(" ")
    var tree = Array.ofDim[Int](i(0).toInt, i(0).toInt)
    var counter = 0;
    var temp = 0;

    for (a <- 1 to i(1).toInt) {
      var j = readLine().split(" ")
      var k = j(0).toInt
      var l = j(1).toInt
      tree(k - 1)(l - 1) = 1
      tree(l - 1)(k - 1) = 1

    }
    for (a <- 0 to i(1).toInt) {

      for (b <- a + 1 to i(1).toInt) {
        temp = 0
        if (tree(a)(b) == 1) {
          temp += check(tree, b, i(1).toInt)

        }
        if (temp % 2 != 0) {

          counter = counter + 1

          tree(b)(a) = 0
          tree(a)(b) = 0

        }

      }

    }
    println(i(1).toInt-counter)
  }
  def check(ar: Array[Array[Int]], b: Int, c: Int): Int = {
    var te = 0;
    var flag = 0;
    for (i <- b + 1 to c) {
      if (ar(b)(i) == 1) {
        flag = 1
        te += check(ar, i, c)
      }

    }
    if (flag == 0) {

      return 1
    }
    return te + flag
  }
}