object Solution {

    def main(args: Array[String]) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
        val list = io.Source.stdin.getLines.toList.apply(1).split(" ").map(_.toInt).toList
            
        def cut(ls: List[Int]): Unit = if(ls.size > 0) {
            println(ls.size)
            cut(ls.map(_ - ls.min).filter(_ > 0))    
        }
		cut(list)            
    }
}