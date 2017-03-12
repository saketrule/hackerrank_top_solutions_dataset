object Solution {

    def main(args: Array[String]) {
        val iter = io.Source.stdin.getLines
        val n = iter.next().toInt
        iter.take(n).foreach(s => {
            val x = Prime(Combination(s.toInt))
            println(x)
        })
    }
}

object Combination {
    private val map = collection.mutable.Map.empty[Int, Int]
        
    def apply(n: Int): Int = {
        map.getOrElseUpdate(n, cal(n))
    }
    
    def cal(n: Int): Int = {
        if (n < 4) {
            1
        } else {
            apply(n - 4) + apply(n - 1)
        }
    }
}

object Prime {
    private val buffer = collection.mutable.Buffer(2, 3, 5, 7, 11)
    
    def apply(n: Int): Int = {
        for (i <- (buffer.last + 1) to n) {
            if (buffer.forall(i % _ != 0)) {
                buffer += i
            }
        }
        
        buffer.takeWhile(_ <= n).count(_ => true)
    }
}