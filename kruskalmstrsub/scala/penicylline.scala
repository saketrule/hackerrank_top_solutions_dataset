object Solution {

    def main(args: Array[String]) {
        val sc = new java.util.Scanner (System.in);
        val nodes = sc.nextInt;
        val edges = sc.nextInt;
        val arr = Array.ofDim[Int](nodes, nodes);
        for (i <- 0 to edges -1) {
            val r = sc.nextInt - 1;
            val c = sc.nextInt - 1;
            val w = sc.nextInt + 1;
            arr(r)(c) = w;
            arr(c)(r) = w;
        }
        var s:Int = sc.nextInt - 1;
        val ns = new Array[Boolean](nodes);
        var nCount = 1;
        var sum = 0;
        ns(s) = true;
        while (nCount < nodes) {
            var minWeight = 100001;
            var minNode = -1;
            for (i <- 0 to nodes - 1) {
                for (j <- 0 to nodes - 1) {
                    if (i != j && ns(i) && !ns(j) && arr(i)(j) > 0) {
                        if (arr(i)(j) < minWeight) {
                            minWeight = arr(i)(j) - 1;
                            minNode = j;
                        }
                    }
                }
            }
            if (minNode >= 0) {
                ns(minNode) = true;
                sum += minWeight;
                nCount += 1;
                s = minNode;
            } else {
                println(-1);
                return;
            }
        }
        println(sum);
    }
}