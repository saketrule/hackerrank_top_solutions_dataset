object Solution {

    def main(args: Array[String]) {
        val input = io.Source.stdin.getLines().toList
        val sticks = input(0).toInt
        var stickLengths = input(1).split(" +").map(_.toInt)

        while(!stickLengths.isEmpty)
        {
            println(stickLengths.length)
            stickLengths = stickLengths.map(x => x - (stickLengths.min)).filter(y => y > 0)
        }
    }
}