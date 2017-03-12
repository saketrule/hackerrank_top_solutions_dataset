object Solution {

    def cut(l: List[Int]): Unit = {

        val sorted = l.sortWith((x, y) => x < y)
        def cutIter(ls: List[Int]): Unit = ls match {
            case Nil => return
            case _ => {
            println(ls.length)
            val minv = ls.head
            cutIter(ls.map(x => x - minv).filter(x => x > 0))
        }
    }
    
    cutIter(sorted)
  }
    
    
    def main(args: Array[String]) {
        
        val num = readLine()
        val sticks = readLine()
        val numbers = sticks.split(" ").map(x => x.toInt).toList
        cut(numbers)
    }
}