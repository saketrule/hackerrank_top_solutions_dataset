object Solution {

    def main(args: Array[String]) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
    val sc = new java.util.Scanner (System.in);
        var n = sc.nextInt();
        var arr = new Array[Int](n);
        for(arr_i <- 0 to n-1) {
           arr(arr_i) = sc.nextInt();
        }
        var p = sc.nextInt();
        var q = sc.nextInt();
        
        arr = arr.sortWith( _ < _ );
        println(findMinDiffMax(arr, n, p, q));
    }
    
    def findMinDiffMax(arr:Array[Int], n:Int, p:Int, q:Int) : Int = {
        
        var maxMinDiff = -1;
        var result = -1;
        arr.indices.foreach {
            i => {
                if( i == 0 && p <= arr(i) ) {
                    maxMinDiff = Math.abs(arr(i) - p);
                    result = p;
                }
                if( i + 1 < n && p < arr(i+1) && q > arr(i) ) {
                    var midValue = ( arr(i+1) - arr(i) ) / 2 + arr(i);
                    if( p <= midValue && q >= midValue && Math.abs(arr(i) - midValue) > maxMinDiff ) {
                        maxMinDiff = Math.abs(arr(i) - midValue);
                        result = midValue;
                    }else if( q < midValue && q >= arr(i) && Math.abs(arr(i) - q) > maxMinDiff ) {
                        maxMinDiff = Math.abs(arr(i) - q);
                        result = q;
                    }else if( p > midValue && p <= arr(i+1) && Math.abs(arr(i+1) - p) > maxMinDiff ) {
                        maxMinDiff = Math.abs(arr(i+1) - p);
                        result = p;
                    }
                }
                if( i == n-1 && q >= arr(n-1) ) {
                    if( Math.abs(arr(i) - q) > maxMinDiff ) {
                        maxMinDiff = Math.abs(arr(i) - q);
                        result = q;
                    }
                }
            }
        };
        
        return result;
        
    }
}