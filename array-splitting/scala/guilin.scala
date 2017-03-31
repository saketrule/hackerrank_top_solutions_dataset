object Solution {
    
    def score(inputArr:Array[Long],start:Int,end:Int):Int ={
        var sum=0.toLong;
        var half=0.toLong;
        var tag= (-1);
        
        for(i <- start to end){
            sum +=inputArr(i)
        }
       
        var i=start;
        while(i <= end){
            half +=inputArr(i)
            if(half*2.toLong==sum){
                tag = i;
                i=end;
            }
            i+=1;
        }
        
        if(tag==(-1)||start>=end) 0
            else{
            1 + scala.math.max(score(inputArr,start,tag),score(inputArr,tag+1,end))
        }
    }

    def main(args: Array[String]) {
       val lines = scala.io.Source.stdin.getLines();
       lines.next
       while(lines.hasNext){
           lines.next
           val inputArr=lines.next().split(" ").map(_.toLong)
           println(score(inputArr,0,inputArr.length-1))
       }
    }
}