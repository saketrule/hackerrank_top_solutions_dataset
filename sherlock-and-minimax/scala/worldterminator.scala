import java.util.Scanner

object Solution {
	val cin = new Scanner(System.in)

	def main(args: Array[String]) {
		val n = cin.nextInt
		cin.nextLine
		val data = cin.nextLine.trim.split(" ").map(_.toInt).toVector.sorted
		
		val left = cin.nextInt
		val right = cin.nextInt

		var result = -1
		var rvalue = -1

		import math._

		def close(l:Int,r:Int,x:Int) = if (r < x) r else if (l > x) l else x

		if (left < data.head){
			rvalue = data.head - left
			result = left
			//println(s"R1  rvalue:$rvalue   result:$result")
		}

		for { pos <- 0 until (n-1) 
			(x,y) = (data(pos),data(pos+1))
			//overlap range
			if !(right<x || y<left )
		}{
			val ll = max(x,left)
			val rr = min(y,right)
			val mid =  (x+y) / 2
			val mid2 =  (x+y+1) / 2
			val a1 = close(ll,rr,mid)
			val a2 = close(ll,rr,mid2)
			val d1 = min(abs(a1-x),abs(a1 - y))
			val d2 = min(abs(a2-x),abs(a2 - y))

			//println(s" ($x,$y)  ll:$ll rr:$rr mid:$mid a1:$a1 d1: $d1")
				if (d1 > rvalue) {
					result = a1
					rvalue = d1
				}
				if (d2 > rvalue) {
					result = a2
					rvalue = d2
					//println(s"R3  rvalue:$rvalue   result:$result")
				}
		}
		val d = right - data.last
			//println(s"R4 : $d")
		if (data.last < right && d > rvalue){
			result = right
		}

		println(result)

	}
}



