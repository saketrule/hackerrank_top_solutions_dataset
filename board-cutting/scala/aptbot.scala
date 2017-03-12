object Solution {

    def main(args: Array[String]) {
        var t = readInt()
        val con  : Long =  math.pow(10,9).toLong + 7
        while (t > 0){
            t -= 1
            val Array(m, n) = readLine().split(" ").map(_.toLong)
            val vCost = readLine().split(" ").map(_.toLong).toList.sortWith((x, y) => x < y )
            val hCost = readLine().split(" ").map(_.toLong).toList.sortWith((x, y) => x < y )
            var vCounter : Long = 1
            var hCounter : Long = 1
            var cost : Long= 0
            var v : scala.collection.mutable.Stack[Long] = new scala.collection.mutable.Stack[Long]()
            v.pushAll(vCost)  
            var h : scala.collection.mutable.Stack[Long] = new scala.collection.mutable.Stack[Long]()
            h.pushAll(hCost)  
            //vCost foreach(i => println(i))
            //for(var x:Int = 0, y:Int=0; x< vCost.length && y<hCost.length;  )
            //intln(v.top)    
            while( !v.isEmpty && !h.isEmpty ){
                if ( v.top < h.top){
                    cost = cost + ( h.pop * vCounter ) % con
                    hCounter += 1
                }
                else{
                    cost = cost + ( v.pop * hCounter ) % con
                    vCounter += 1
                }
                cost = cost % con
            }
            while(!v.isEmpty){
                cost = cost + ( v.pop * hCounter ) % con
                vCounter += 1
            }
            while(!h.isEmpty){
                cost = cost + ( h.pop * vCounter ) % con
                hCounter += 1
            }
            cost = cost % con
            println(cost)
        }
      
     
    }
}