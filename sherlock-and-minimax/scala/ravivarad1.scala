import scala.util.control._
    
object Solution {
    
    def getIntArr():Array[Int] = {
        Console.readLine.split(" ") map { e => e.trim.toInt }
    }
        
    def main(args: Array[String]) {
        val nInt = Console.readInt
        val inpArr = getIntArr.sorted
        val lowHighArr = getIntArr
        val low = lowHighArr(0)
        val high = lowHighArr(1)
        var maxMinPt:Int = -1
        var maxMinDist:Int = -1
        if (inpArr(0) >= low) {
            maxMinPt = low
            maxMinDist = inpArr(0) - low
        }
        val loop = new Breaks
        loop.breakable {
            for (i <- 0 until nInt -1) {
               //println("A(i)="+inpArr(i)+",A(i+1)="+inpArr(i+1))
               //println("maxMinPt = " + maxMinPt)
               //println("maxMinDist = " + maxMinDist)
               val midPt = Math.floor((inpArr(i)+inpArr(i+1))/2).toInt
               val midPtDist = Math.min(midPt-low, low-midPt)
               var potentialPt:Int = -1
               if (inpArr(i) >= high) {
                   // low < high <= inpArr[i] < inpArr[i+1]
                   if (i == 0) {
                       maxMinPt = low
                       maxMinDist = inpArr(i)-low
                   } 
                   loop.break
               } else if (inpArr(i) >= low) {
                   // low <= inpArr[i] < high
                   if (inpArr(i+1) <= high) {
                       // low <= inpArr[i] < inpArr[i+1] <= high -- midpt of interval is a potential solution
                       potentialPt = midPt  
                   } else {
                       // low <= inpArr[i] < high < inpArr[i+1] 
                       if (midPt < high) {
                           potentialPt = midPt
                       } else {
                           potentialPt = high 
                       }
                   }
               } else if (inpArr(i+1) >= high) {
                   // inpArr[i] < low < high <= inpArr[i+1]
                   if (midPt >= low && midPt <= high) {
                      potentialPt = midPt 
                   } else if (Math.min(low - inpArr(i), inpArr(i+1) - low)  >= 
                              Math.min(high - inpArr(i), inpArr(i+1) - high)) {
                          potentialPt = low
                       } else {
                          potentialPt = high
                      }            
               } else if (inpArr(i+1) > low) {
                   // inpArr[i] < low < inpArr[i+1] < high
                   if (midPt >= low) {
                        potentialPt = midPt
                   } else {
                        potentialPt = low
                   }
               }
               if (potentialPt >= 0) {
                   val potentialPtMinDist = Math.min(potentialPt - inpArr(i), inpArr(i+1) - potentialPt)
                   //println("potentialPt = " + potentialPt)
                   //println("potentialPtMinDist = " + potentialPtMinDist)
                   if (potentialPtMinDist > maxMinDist) {
                        maxMinPt = potentialPt
                        maxMinDist = potentialPtMinDist
                   }
               }
            } 
        }
        if (high >= inpArr(nInt-1) && (high - inpArr(nInt-1) > maxMinDist)) {
            maxMinPt = high
            maxMinDist = high - inpArr(nInt-1)
        }
        println(maxMinPt)
        //println(maxMinDist)
    }
}