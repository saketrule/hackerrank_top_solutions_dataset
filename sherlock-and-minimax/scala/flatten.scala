object Solution {

    def main(args: Array[String]) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
        val sc = new java.util.Scanner (System.in)
        val n = sc.nextLine().toInt
        val sample = sc.nextLine().split(" ").map(_.toInt).sorted
        val (p::q::_) = sc.nextLine().split(" ").map(_.toInt).toList
            
        //val result =(p to q).map(m => (sample.map(x => Math.abs(x - m)).min,m))
        
        //val sr = result.sortWith{ case ((n1, m1), (n2, m2))  =>
        //    if ( n1 > n2 ) true
        //    else  if ( n1 == n2 && m1 < m2 ) true else false
        //}
       
               
        //println(sr.head._2)
            
         def minMax(m: Int, q: Int, ai: Array[Int], index: Int, max: Int, key: Int): Int = {

    def findMin(m: Int, index: Int): (Int,Int) = {
      def position(start: Int): Int = {
        def isBetween = {
          if (start == ai.length-1)
            true
          else if ((start == 0) && m < ai(start))
            true
          else
            m >= ai(start) && m <= ai(start + 1)
        }
        if (isBetween)
          start
        else
          position(start + 1)
      }
      val start = position(index)
      if (start == ai.length - 1)
        (start,Math.abs(m-ai(start)))
      else
        (start, Math.min(Math.abs(m - ai(start)), Math.abs(ai(start + 1) - m)))
    }

    if (m > q) key
    else {
      val (start, min ) = findMin(m, index)
      if (min > max)
        minMax(m+1, q, ai, start, min, m)
      else
        minMax(m+1,q,ai,start,max,key)
    }
  }
        println(minMax(p, q,sample.toArray, 0, 0, p))
        
    }
}