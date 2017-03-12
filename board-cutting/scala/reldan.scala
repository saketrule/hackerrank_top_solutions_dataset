object Solution {

    def main(args: Array[String]) {
        Range(0, readInt()).foreach{ _ =>
          readLine()
          var h = readLine().split(' ').map(_.toLong).sorted.reverse.toArray
          var v = readLine().split(' ').map(_.toLong).sorted.reverse.toArray
          
          var hm = 1
          var vm = 1
          var res = 0l
          val split = 1000000007
          var vi = 0
          var hi = 0
          
          while (hi < h.size && vi < v.size) {
             if (v(vi) > h(hi)) {
               res += v(vi) * vm % split
               vi += 1
               hm += 1
             } else {
               res += h(hi) * hm % split
               hi += 1
               vm += 1
             }
          }
          println((res + (h.drop(hi).sum % split * hm) % split + (v.drop(vi).sum % split * vm) % split) % split)
          
        }
    }
}