object Solution {

    def main(args: Array[String]) {
        val sc = new java.util.Scanner (System.in);
        var a0 = sc.nextInt();
        var a1 = sc.nextInt();
        var a2 = sc.nextInt();
        var b0 = sc.nextInt();
        var b1 = sc.nextInt();
        var b2 = sc.nextInt();
      
      def score(a: Int, b: Int) = {
        if (a > b) (1, 0)
        else if (a < b) (0, 1)
        else (0, 0)
      }
      
      val scored = Seq(
        score(a0, b0),
        score(a1, b1),
        score(a2, b2)
      )
        
      val summed = scored.reduce( (x, y) => (x._1 + y._1, x._2+ y._2))
        
        println(summed._1 + " " + summed._2)
    }
}
