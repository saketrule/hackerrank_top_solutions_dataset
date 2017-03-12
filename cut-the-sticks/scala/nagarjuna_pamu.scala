object Solution {

    def main(args: Array[String]) {
        Console.readLine;
        
        val list = Console.readLine.split("\\s+").map(_ toInt);
        
        process(list);
    }
    
    def process(list: Array[Int]): Unit = {
        if(list.length > 0) {
            val min = list.min;
        
            var count = list.length;
        
            val l = list.map(elem => {
                //count = count + 1;
                elem - min
            }).filter(_ != 0);
        
            println(count);
        
            //count = 0;
        
            if(list.length > 0) {
                process(l);
            }   
        }
    }
}