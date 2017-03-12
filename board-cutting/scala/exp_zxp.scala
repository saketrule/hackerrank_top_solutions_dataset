import java.util.Scanner

object Solution {

    def main(args: Array[String]) {

        val scn = new Scanner(System.in)
        val cases = scn.nextLine().toInt
        for (_ <- 1 to cases) {

            val mn = scn.nextLine().split(" ").map(_.toInt)
            val ys = scn.nextLine().split(" ").map(x => ('y',x.toLong))
            val xs = scn.nextLine().split(" ").map(x => ('x',x.toLong))
            val cuts:List[(Char,Long)] = (ys ++ xs).sortBy(_._2).reverse.toList
            var vPieces, hPieces = 1
            val costs = cuts.foldLeft(0:Long){(cost, cut) =>
                if (cut._1 == 'y') {
                    hPieces += 1
                    cost + (cut._2 * vPieces)
                } else{
                    vPieces += 1
                    cost + (cut._2 * hPieces)
                }   
            }
        println(costs % (Math.pow(10,9)+7).toLong)
        }
    }
}