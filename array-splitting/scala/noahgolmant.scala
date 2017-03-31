object Solution {
    
    def partition(score: Int, A: Seq[Int]): Int = {
        if (A.size <= 1) return score;
        if (A.size == 2) {
            if (A(0) == A(1)) return score + 1 else return score;
        }
        
        //if (A.forall(p => p == 0)) return A.size - 1
        
        var leftI = 0;
        var rightI = A.size - 1;
        
        var last = -1;
        //println("score: " + score);
        //println("A: " + A.mkString(" "));

        var i = (A.size - 1) / 2;
        //println("i: " + i);
        //println("L: " + leftI + ", R: " + rightI);
        if (i == last) {
            return score;
        }
        last = i;
        val (left, right) = A.splitAt(i);
        //println("A: " + left.mkString(" ") + ", B: " + right.mkString(" "));
        var leftSum: BigInt = left.foldLeft(BigInt(0))(_ + _);
        var rightSum: BigInt = right.foldLeft(BigInt(0))(_ + _);
        //println("A sum: " + leftSum + ", Bsum: " + rightSum);

        if (rightSum + leftSum == 0) return A.size - 1;
        //if (leftSum == 0 || rightSum == 0) return score;

        while (leftSum < rightSum && i < A.size) {
            leftSum += A(i);
            rightSum -= A(i);
            i += 1;
        }

        while (rightSum < leftSum && i >= 0) {
            i -= 1;
            rightSum += A(i);
            leftSum -= A(i);
        }

        if (leftSum == rightSum) {
            val (leftR, rightR) = A.splitAt(i);
            return math.max(partition(score + 1, leftR), partition(score + 1, rightR));
        }
        
        return score;
    }

    def main(args: Array[String]) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
        val T = readInt();
        for (_ <- 1 to T) {
            val N = readInt();
            val A = readLine().split(" ").map(_.toInt);
            println(partition(0, A));
        }
    }
}