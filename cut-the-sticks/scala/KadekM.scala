object Solution {
    def main(args: Array[String]) {
        val ignore = readLine()
        var sticks = readLine().split(" ").map(_.toInt)
        
        while (sticks exists (_ > 0)) {
            val minn : Int = sticks.filter(_ > 0) min Ordering[Int]  
            val amount = sticks.filter(x => x-minn >= 0) length
            val newSticks = sticks.map(x => x-minn)
            sticks = newSticks
            println(amount)
        }
    }
}