object Solution {

    def main(args: Array[String]) {
        val sc = new java.util.Scanner (System.in);
        var a0 = sc.nextInt();
        var a1 = sc.nextInt();
        var a2 = sc.nextInt();
        var b0 = sc.nextInt();
        var b1 = sc.nextInt();
        var b2 = sc.nextInt();
        
        var a, b = 0
        if (a0 > b0) {
            a += 1
        } else if (a0 < b0) {
            b += 1
        }
        if (a1 > b1) {
            a += 1
        } else if (a1 < b1) {
            b += 1
        }
        if (a2 > b2) {
            a += 1
        } else if (a2 < b2) {
            b += 1
        }
        printf("%d %d\n", a, b)
    }
}
