object Solution {

    def main(args: Array[String]) {
        val n = readInt;
        val l = readLine.split(" ").map(_.toInt).sorted;
        val Array(p, q) = readLine.split(" ").map(_.toInt);
        
        var k = l.sliding(2).map({ case Array(a, b) =>
            val o = Math.abs(a - b) / 2;
            if (a >= p) {
                if (a + o <= q)
                    (o, a);
                else
                    (q - a, a);
            } else if (p > a && p < b) {
                 if (a + o >= p)
                     (o, a);
                 else
                     (b - p, p);
            } else
                (Int.MinValue, Int.MinValue);
            }).foldLeft(Int.MinValue -> 0) { (acc, elt) => if (elt._1 > acc._1) elt else acc};
        
        if (p < l(0) && l(0) - p > k._1) {
            k = (l(0) - p, l(0));
        }
        if (q > l.last && q - l.last > k._1) {
            k = (q - l.last, l.last);
        }
        //println(k);
        val m1 = Math.abs(k._2 - k._1);
        val m2 = k._1 + k._2;
        def getmin(m: Int) : Int = {
            l.map(x => Math.abs(x - m)).min
        }
        if (getmin(m1) >= getmin(m2) && m1 >= p)
            println(m1);
        else
            println(m2);
        //val m = l.map(x => Math.abs(x - k)).max;
        //println(m);
    }
}