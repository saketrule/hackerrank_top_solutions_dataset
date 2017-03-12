object Solution {

    case class Tree(children: List[Tree], node: Int) {
    val count: Int = 1 + children.foldLeft(0)((x, y) => x + y.count)
    override def toString = node + "(" + (children mkString ",") + ")"
  }
  
  def unitTree(node: Int) = Tree(List(), node)
  
  type TreeMap = Map[Int, List[Int]]
  
  def constructTreeMap(l: List[(Int, Int)]) = {
    def accUpdated(acc: TreeMap, x: Int, y: Int) =
      acc get x match {
        case None => acc updated (x, List(y))
        case Some(z) => acc updated (x, y :: z)
      }
    def ctm(l: List[(Int, Int)], acc: TreeMap): TreeMap = l match {
      case Nil => acc
      case (x, y) :: xs =>
        ctm(xs, accUpdated(accUpdated(acc, y, x), x, y))
    }
    ctm(l, Map())
  }
  
  def constructTree(map: TreeMap) = {
    def ct(root: Int, parent: Int): Tree = {
      map get root match {
        case None => unitTree(root) 
        case Some(children) =>
          val c = children.foldLeft(List[Tree]())((x, child) =>
            if (child != parent) ct(child, root) :: x else x)
          Tree(c, root)
      }
    }
    ct(1, 0)
  }
  
  def removeEdges(tree: Tree) = {
    def re(t: Tree): Int =
      t.children.foldLeft(0)((acc, child) =>
        if (child.count % 2 == 0) acc + 1 + re(child)
        else acc + re(child)
      )
    re(tree)
  }
  
  def main(args: Array[String]): Unit = {
    val i = Console.readLine.split(" ").map(x => x.toInt)
    val N = i(0)
    val M = i(1)
    val L = (for (j <- Range(0, M)) yield Console.readLine.split(" ") match {
      case x => (x.head.toInt, x.last.toInt)
    }).toList
    val t = constructTreeMap(L)
    val t2 = constructTree(t)
    println(removeEdges(t2))
  }
}