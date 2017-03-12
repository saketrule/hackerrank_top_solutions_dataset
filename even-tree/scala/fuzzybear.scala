import scala.annotation.tailrec

object Solution {

    case class SimpleTree(parents: IndexedSeq[Int], size: IndexedSeq[Int]) {

        def split(from: Int, to: Int) = {
            require(from < to)

            val parentsAfterSplit = parents.updated(to, -1)

            // collect parents
            val ancestors: List[Int] = collectAncestors(List(from))
            val sizesAfterSplit = ancestors.takeWhile(_ >= 0).foldLeft(size) { (sizes, i) =>
                sizes.updated(i, sizes(i) - size(to))
            }.toVector
            new SimpleTree(parentsAfterSplit, sizesAfterSplit)
        }

        def merge(from: Int, to: Int): SimpleTree = {
            require(from < to)

            val parentsAfterMerge = parents.updated(to, from)
            val ancestors: List[Int] = collectAncestors(List(from))
            val sizesAfterMerge = ancestors.takeWhile(_ >= 0).foldLeft(size) { (sizes, i) =>
                sizes.updated(i, sizes(i) + size(to))
            }.toVector
            new SimpleTree(parentsAfterMerge, sizesAfterMerge)
        }

        def valid =
            parents zip size filter(_._1 < 0) forall (_._2 % 2 == 0)

        @tailrec
        private def collectAncestors(ancestors:List[Int]): List[Int] =
            ancestors match {
                case head :: tail =>
                    if (head >= 0)
                        collectAncestors(parents(ancestors.head) :: ancestors)
                    else
                        tail
                case Nil => throw new IllegalArgumentException
            }
    }

    def isEven(x: Int) = x % 2 == 0

    def naiveSolution(edges: IndexedSeq[(Int, Int)]) = {
        val p = parents(edges)
        val s = sizes(p)
        val tree = new SimpleTree(p, s)
        val solution = edges.foldLeft(Vector[SimpleTree](tree)) { (trees, edge) =>
            edge match {
                case (from, to) => {
                    // if not empty, throw away the previous results
                    val next = trees.map(_.split(from, to)).filter(_.valid) // try taking from each tree
                    if (next.isEmpty) trees else next
                }
            }
        }.headOption

        solution.fold(0)(_.parents.count(_ < 0) - 1)
    }

    def main(args:Array[String]):Unit = {
        val numEdges = readLine().split(" ").toSeq.map(_.toInt).last
        val raw = for (_ <- 1 to numEdges) yield readLine().split(" ").map(_.toInt - 1)
        val edges = normalizeEdges(raw.map(arr => (arr(0), arr(1))))

        println(naiveSolution(edges))
        
    }

    def normalizeEdges(edges: IndexedSeq[(Int, Int)]) = {
        edges.map(edge => (edge._1.min(edge._2), edge._1.max(edge._2))).sortBy(_._1)
    }

    def parents(edges: IndexedSeq[(Int, Int)]) = {
        -1 +: edges.sortBy(_._2).map(_._1)
    }

    def ancestry(parents: Seq[Int]) = {
        val empty = Set[Int]()
        parents.foldLeft(Vector[Set[Int]]()) { (ancestors, i) =>
            val next = if (i < 0) empty else ancestors(i) + i
            ancestors :+ next
        }
    }

    def sizes(parents: Seq[Int]) = {
        val a = ancestry(parents)
        0.until(parents.size).foldLeft(Vector[Int]()) { (acc, i) =>
            a(i).foldLeft(acc) { (parentSize, i) =>
                val newSize = parentSize(i) + 1
                parentSize.updated(i, newSize)
            } :+ 1
        }
    }

}
