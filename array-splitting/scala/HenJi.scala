object Solution {

    def splitArray(data:Array[Int], left:Int, right:Int) = {
        def add(i:Int, res:Long):Long = {
            if (i == right) res else add(i+1, res+data(i));
        }
        val sum = add(left, 0L);
        def halfIdx(i:Int, res:Long):Int = {
            if (res+res == sum) i;
            else if (res+res > sum) -1;
                else halfIdx(i+1, res+data(i));
        }
        halfIdx(left, 0L);
    }
    
    def findBestScore(data:Array[Int]) {
                def isSame(i:Int, hd:Int):Int = {
            if (i > 0) {
                if (data(i) == hd) isSame(i-1, hd);
                else -1
            } else hd;
        }
        val sameCheck = isSame(data.length-1, data.head);
        if (sameCheck > 0) {
            def aux(i:Int, res:Int):Int = {
                if (i%2 == 0) aux(i/2, res+1);
                else res;
            }
            println(aux(data.length, 0));
        } else if (sameCheck == 0) {
            println(data.length-1);
        } else {
            def aux(left:Int, right:Int, score:Int):Int = {
                val cut = splitArray(data, left, right);
                if (cut < 0) score;
                else {
                    val l = aux(left, cut, score+1);
                    val r = aux(cut, right, score+1);
                    Math.max(l,r);
                }
            }
            val highest = aux(0, data.length, 0);
            println(highest);
        }
    }
    
    def main(args: Array[String]) {
        val tests = readLine().toInt;
        def aux(i:Int) {
            if (i < tests) {
                val nb = readLine();
                val data = readLine().split(" ").map(_.toInt);
                findBestScore(data);
                aux(i+1);
            }
        }
        aux(0);
    }
}