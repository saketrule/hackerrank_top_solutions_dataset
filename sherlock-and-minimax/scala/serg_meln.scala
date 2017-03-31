object Solution {
    import scala.io.StdIn
    
    val distance: (Int, Int) => Int = (a: Int, b: Int) => math.abs(a-b)

    def mind(a: Array[Int], m: Int, d: (Int, Int) => Int): Int = {
	val n = a.size
	if (m <= a(0)) a(0)-m 
	else if (m >= a(n-1)) m-a(n-1)
	else {
		var r = n-1
		var l = 0
		var ar = 0
		var al = 0
		var dr = 0
		var dl = 0
		while (r - l >= 2) {
			//println(l + " " + r)
			al = a(l) - m
			ar = a(r) - m
			val mid = l + (r-l)/2
			val am = a(mid) - m
			if (am > 0) {
				if (al < 0) r = mid
				else if (al > 0) l = mid
			} else if (am < 0) {
				if (al < 0) l = mid
				else if (al > 0) r = mid
			} else { // am == 0, minimun is found
				r = mid
				l = mid	
			}
		}
		//println(l + " " + r + " (2)")
		al = a(l)
		ar = a(r)
		dl = d(al, m)
		dr = d(ar, m)
		dl min dr
	}
    }
	
    def main(args: Array[String]) {        
        val n = StdIn.readInt
        val a = StdIn.readLine.split(" ").toArray.map(_.toInt).sorted
        val List(p,q) = StdIn.readLine.split(" ").toList.map(_.toInt)
        
//	println(q)
//	println(p)

//	println(a(0))
//	println(a(n-1))

	val as = a.toList
	val midas = (as.tail.foldLeft((as.head, List.empty[Int]))((acc, x) => {
		val prev = acc._1
		val list = acc._2
		val next = prev + (x - prev) / 2
		(x, next :: list)
	})._2 ++ List(p,q)).sorted.filter(x => (x >= p) && (x <= q))
	val result = midas.map(x => (x, mind(a, x, distance))).maxBy(_._2)._1
	println(result)


/*
	var iter = 0
	var m = p
	var maxd = -1
	var target = 0
	val step = 1 
	while (m <= q) {
		val d = mind(a, m, distance)
		if (d > maxd) { 
			target = m
			maxd = d
		}
		m += step
		iter += 1
		//if (iter % 1000 == 0) println(d)
	}
	println(target)
*/

/*
	val t = a.map(ai => mind2(ai, p, q, distance)).max
	println(t)
*/

/*
	val ar = Array(2,3,4,6,7,8,9,10,10,10,10,10,10,10,11,12)
	println(ar.mkString(" "))
	val md = mind(ar, 5, distance)
	println(md)
*/
	
/*
	println()
	println("448101966: " + mind(a, 448101966, distance))
	println("493216533: " + mind(a, 493216533, distance))
	println("789945206: " + mind(a, 789945206, distance))
*/
    }    
}
