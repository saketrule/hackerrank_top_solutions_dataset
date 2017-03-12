import scala.collection.mutable.ListBuffer

object Solution {
  def main(args:Array[String]): Unit = {
    val N = readLine().toInt
    val arr = readLine().split(" ").map(a => a.toInt)
    val sorted = arr.sortBy(x => x)
    val initialState = (1, 0, sorted.length)
    val deduped = ListBuffer[Int]()

    deduped += sorted.length

    val (count, lv, rem) = sorted.foldLeft(initialState)((s, i) => {
      val (lastCount, lastValue, left) = s
      if (lastValue == i) (lastCount + 1, lastValue, left - 1)
      else if (lastValue > 0) {
        deduped += left
        (1, i, left - 1)
      } else (1, i, left - 1)
    })

    //deduped += count

    deduped.foreach(x => println(x))
  }
}
