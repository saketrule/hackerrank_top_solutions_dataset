object Solution {

    def main(args: Array[String]) {
        def loop (cx: Long, cy: Long, x: List[Long], y: List[Long]): Long = (x,y) match {
            case (xi::xs, yi::ys) if xi > yi => (xi * cx + loop(cx, cy + 1L, xs, y)) % 1000000007
            case (xi::xs, yi::ys) => (yi * cy + loop(cx + 1L, cy, x, ys)) % 1000000007
            case (Nil, yi::ys) => (yi * cy + loop(cx, cy, Nil, ys)) % 1000000007
            case (xi::xs, Nil) => (xi * cx + loop(cx, cy, xs, Nil)) % 1000000007
            case (Nil, Nil) => 0L
        }
                                                  
        (1 to readInt).foreach(t => {
            val mn = readLine.split(" ").map(_.toInt)
            val y = readLine.split(" ").map(_.toLong).sortWith(_>_)
            val x = readLine.split(" ").map(_.toLong).sortWith(_>_)
            val c = loop(1L, 1L, x.toList, y.toList)
            println(c)
        })
    }
}
