object Solution {

    def main(args: Array[String]) {
        val sc = new java.util.Scanner (System.in)
        var a: (Int, Int, Int) = (sc.nextInt(), sc.nextInt(), sc.nextInt())
        var b: (Int, Int, Int) = (sc.nextInt(), sc.nextInt(), sc.nextInt())
        
        var aScore: Int = 0
        var bScore: Int = 0
        
        if (a._1 > b._1) aScore += 1 else if (b._1 > a._1) bScore += 1 
        if (a._2 > b._2) aScore += 1 else if (b._2 > a._2) bScore += 1 
        if (a._3 > b._3) aScore += 1 else if (b._3 > a._3) bScore += 1 
        
        println(s"$aScore $bScore")
    }
}
