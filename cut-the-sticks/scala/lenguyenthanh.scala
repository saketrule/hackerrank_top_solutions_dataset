
object Solution {
    def main(args: Array[String]) {
    	val n = readLine()
        val list = readLine().trim().split(" ").map(_.toInt).sorted.toList
        val result = getStick(list)
        result foreach println
    }

    def getStick(list: List[Int]): List[Int] = {
    	def iter(l: List[Int], result: List[Int]): List[Int] = l match {    	
	    	case x::xs => {
	    		val temp = l.map(_ - x).filter(_ != 0)
	    		if(temp.length > 0) iter(temp, result:+temp.length)
	    		else result
    		}
    		case _ => result
		}    			
    	iter(list, List(list.length))
    }

}