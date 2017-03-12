object Solution {

    val MAX_VAL: Int = 220000
    val arr = Array.fill[Boolean](MAX_VAL)(true)

    def evalPrimeArray() = {
        arr(0) = false
        arr(1) = false
        for(i <- 2 until Math.sqrt(MAX_VAL).toInt) {
            if(arr(i) == true) {
                for (j <- i*i until MAX_VAL by i)
                    arr(j) = false
                }
            }
        }


    def findAllPrimes(N: Int):Int = {
        var sum = 0
        for (i <- 0 to N){
            if (arr(i) == true)
                sum += 1
        }
        return sum
    }

    def main(args: Array[String]) {
        evalPrimeArray()
        val T = io.StdIn.readLine().toInt
        for (i <- 0 until T){
            val N = io.StdIn.readLine().toInt
            val x = evalDP(N)
            println(findAllPrimes(x))
        }
    }

    def evalDP(N: Int):Int = {
        val f = Array.fill(N+1)(1)

        for (i <- 4 to N){
            f(i) = f(i-1) + f(i-4)
        }
        return f(N)
    }
}