object Solution extends App {
    val Array(n, m) = readLine().split(" ").map(_.toInt)
    val res = Array.fill(n + 1){0}
    var sum = Array.fill(n + 1){1}
    sum(0) = 0
        
    Range(1, m + 1).foreach{ _ =>
        val Array(x, y) = readLine().split(" ").map(_.toInt)
        res(x) = y
    }
        
    def updateSum() = {
      sum = Array.fill(n + 1){1}
      Range(m + 1, 2, -1).foreach { i =>
        sum(res(i)) += sum(i)
      }
    }
    
    var stop = false 
    var rrr = 0
    var in = 100
    updateSum()
        
    while (!stop) {
    var r = Range(2, res.size).count(el => sum(el) == in)
    if (r == 0) {
      if (in == 0)
        stop = true
    }
    else {
      Range(2, res.size).foreach {
        i => if (sum(i) == in) res(i) = 0
      }
      updateSum()
    }
    in -= 2
  }

  var contain = Range(2, res.size).count{el => (res(el) == 0 && sum(el) % 2 == 1)}
  var s = Range(2, res.size).count{el => (res(el) == 0 && sum(el) % 2 == 0)}
    
  println(s)
}