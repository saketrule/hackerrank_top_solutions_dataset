object Solution {

    def main(args: Array[String]) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
        val numbers = io.Source.stdin.getLines.toList.drop(1).map(_.trim).map(_.toInt)
        
        for(a<- numbers){            
            var counter = 0
            for (b <- combination(a) to 0 by -1 if(isPrime(b))){            
                counter = counter+1
            }
            println(counter)
        }
    }  
   
     def combination(n: Int): Int =
     {
         val wall = 4* n
         var combinations = 0.0
         var bigBrick = wall/16
         var smallBrick = 0        
             
         if(wall<16){
             bigBrick = 0
             smallBrick = wall%16/4
         }else if(wall%16 ==0){
             bigBrick = wall/16
         }else
             smallBrick = wall%16/4
             
        /* println("B "+bigBrick)
         println("S "+smallBrick)*/
             
         for(i<- bigBrick to 0 by -1){
            var parts = repeatablePermmitation(bigBrick, smallBrick)
             //println(i + " " + parts)
             combinations = combinations + parts
             bigBrick = bigBrick-1
             smallBrick = smallBrick + 4    
         }
         if(n < 4){
             return 0
         }
         return combinations.toInt
     }
    
    def repeatablePermmitation(x: Int, y: Int): Double = {
        /*println("C "+factorial(x+y))
            println("C "+factorial(x))
            println("C "+factorial(y))*/
        var x_y = (factorial(x.toDouble) * factorial(y.toDouble))
        return factorial(x.toDouble+y.toDouble)/x_y
    }
    
    def factorial(n: Double): Double = n match {
        case 0 => 1
        case _ => n * factorial(n-1)
    }
    
    def isPrime(n: Int): Boolean =
    {        
        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        var i = 3;
        while(i*i<=n) {
            if(n%i==0)
                return false;
            i = i+2;
        }
        return true;
    }
}