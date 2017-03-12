import scala.collection.mutable;

object Solution {
  val NMAX = 40

  object PrimeNumberCounter {
    val MAX = 1000000
    val bits = new Array[Boolean](MAX)
    private var next = 2
    private var count = 0
    
    def countUpTo(n: Int): Int = {
      while(n >= next) {
        if(!bits(next)) {
          count += 1
          var ptr = next
          while(ptr < MAX) {
	    bits(ptr) = true
 	    ptr += next
          }
        }
        next += 1
      }
      count
    }
  }

  val cache = Array(new Cache1(),
                    new CacheFull(2),
                    new CacheFull(3),
                    new CacheFull(4),
                    new CacheFull(5),
                    new CacheFull(6),
                    new CacheFull(7),
                    new CacheFull(8),
                    new CacheFull(9),
                    new CacheFull(10))

  abstract class Cache {
    def getOrCalculate(i: Int) : Int
  }

  class Cache1 extends Cache {
    def getOrCalculate(i: Int) : Int = if (i < 4) 0 else 1
  }

  class Cache2 extends Cache {
    def	getOrCalculate(i: Int) : Int = {
      val r = i - 8;
      if(r < 8) {  
      	0
      } else {
        r + 1
      }
    }
  }

  class CacheFull(val level: Int) extends Cache {
    val c = new Array[Int](NMAX + 1)
    val min = level * 4;

    def getOrCalculate(i: Int) : Int = {
      if (i < min) {
        0
      } else {
        var tmp = c(i)
        if(tmp == 0) {
          tmp = calc(i, 0, cache(level-2))
          c(i) = tmp
        }
        tmp
      }
    }

    private def calc(i: Int, sum: Int, c: Cache): Int = {
      val t = c.getOrCalculate(i - 4);
      if (t == 0) {
        sum
      } else {
        calc(i - 1, sum + t, c)
      }
    }
  }

  val pairs = new Array[(Int, Int)](NMAX + 1)
  
  def get(i: Int): (Int, Int) = {
    if (i == 0) {
      (1, PrimeNumberCounter.countUpTo(1))
    } else {
      val value = pairs(i)
      if (value != null) {
        value
      } else {
        val prev = get(i - 1)
        var num = prev._1
        for(c <- cache) {
          num += c.getOrCalculate(i)
	}
        val ret = (num, PrimeNumberCounter.countUpTo(num))
        pairs(i) = ret
        ret
      }
    }
  }

  def main(args: Array[String]) {
    val n = Console.readInt()
    for(i <- 1 to n) {
      val x = Console.readInt()
      println(get(x)._2.toString())
    }
//    for(i <- 1 to 40) {
//      println(i.toString() + ": " + get(i))
//    }
  }
}
