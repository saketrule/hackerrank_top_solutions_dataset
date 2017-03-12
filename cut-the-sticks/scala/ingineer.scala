object Solution {
    
    def cut(sticks: List[Int]): Unit = if (sticks.length != 0){
        println(sticks.length)
        val min = sticks.min
        val newSticks = sticks.filter(_ > min)
        cut(newSticks)
    }

    def main(args: Array[String]) {
        val numSticks = readInt()
        val sticks = readLine.split(" ").map(_.toInt)
        
        cut(sticks.toList)
    }
}