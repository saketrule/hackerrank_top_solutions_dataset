object Solution{
    def main(args: Array[String]){
        val n = readInt()
    var l = readLine().split(" ").toList map(x => x.toInt)
        while(l != Nil){
          println(l.length)
          val min = l.min
          l = l.map(x => x-min).filter(x => x != 0)
        }
    }
}