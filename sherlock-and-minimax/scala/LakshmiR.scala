import java.util.Scanner
object Solution {
  def minmax(arr2: Array[Int], p: Int, q: Int, N: Int): Int = {
    val sortedArr = arr2.sorted.zipWithIndex
    val startIndex = sortedArr.collectFirst({case (elem, ind) if elem > p => ind}).getOrElse(N)
    val endIndex = sortedArr.collectFirst({case (elem, ind) if elem > q => ind}).getOrElse(N)

    var arr = sortedArr.map(_._1)
    if (endIndex == 0) return p
    if (startIndex == N) return q

    var M = p
    var maxDiffValue = if(startIndex == 0) math.abs(arr(startIndex)-p) else {
      val diff = (arr(startIndex)-arr(startIndex-1))/2
      if(p < diff+arr(startIndex-1)){
        M = diff + arr(startIndex-1)
        diff
      }
      else math.abs(arr(startIndex)-p)
    }

    for( i <- startIndex until endIndex-1){
      val curDiff = (arr(i+1) - arr(i))/2
      if(curDiff > maxDiffValue){
        maxDiffValue = curDiff
        M =  arr(i)+curDiff
      }
    }

    var endM = q
    val endMaxDiffValue = if(endIndex == N) math.abs(q-arr(endIndex-1)) else {
      val diff = (arr(endIndex)-arr(endIndex-1))/2
      if(arr(endIndex-1) + diff < q){
        endM = diff + arr(endIndex-1)
        diff
      }
      else math.abs(q-arr(endIndex-1))
    }

    if(endMaxDiffValue > maxDiffValue){
      M = endM
    }

    M
  }
  def main(args: Array[String]): Unit ={
    val sc = new Scanner(System.in)
    val N = sc.nextLine().trim.toInt
    val arr = sc.nextLine().trim.split(" ").map(_.toInt)
    val splits  = sc.nextLine().trim.split(" ")
    val p = splits(0).toInt
    val q= splits(1).toInt

    println(minmax(arr, p, q, N))
  }

}
