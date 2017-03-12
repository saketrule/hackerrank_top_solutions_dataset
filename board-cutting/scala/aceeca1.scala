object Solution {
    import io.StdIn._

    def main(args:Array[String]) {
        for (i <- 1 to readInt) {
            readLine
            val a = readLine.split(' ').map{x => (x.toLong, 0)}
            val b = readLine.split(' ').map{x => (x.toLong, 1)}
            val c = a.toBuffer.++=(b).sorted
            println(c.foldRight((Array(1L, 1L), 0L)){
                case ((n, t), (x, s)) => {
                    x(t) = x(t) + 1L
                    (x, s + n * x(1 - t))
                }
            }._2 % 1000000007)
        }
    }
}
