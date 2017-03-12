import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution
{
   public static void main(String[] args)
   {
      Scanner scanner = new Scanner(System.in);

      int n = scanner.nextInt();
      int m = scanner.nextInt();

      EvenTree tree = new EvenTree(n);

      for (int i = 0; i < m; i++)
      {
         int node1 = scanner.nextInt();
         int node2 = scanner.nextInt();

         tree.addEdge(node1, node2);
      }

      int solution = tree.countEvensAndOdds();

      System.out.println(solution);
   }

   public static class EvenTree
   {
      private final EvenTreeNode[] nodes;
      private int evenCount = 0;

      public EvenTree(int numberOfNodes)
      {
         nodes = new EvenTreeNode[numberOfNodes];

         for (int i = 0; i < numberOfNodes; i++)
         {
            nodes[i] = new EvenTreeNode(i + 1);
         }
      }

      public void addEdge(int identifier1, int identifier2)
      {
         EvenTreeNode node1 = getNode(identifier1);
         EvenTreeNode node2 = getNode(identifier2);

         EvenTreeEdge edge = new EvenTreeEdge(node1, node2);
         node1.addEdge(edge);
         node2.addEdge(edge);

      }

      public EvenTreeNode getNode(int identifier)
      {
         return nodes[identifier - 1];
      }

      public Collection<EvenTreeNode> getLeaves()
      {
         ArrayList<EvenTreeNode> ret = new ArrayList<EvenTreeNode>();

         for (EvenTreeNode node : nodes)
         {
            if (node.isLeaf())
            {
               ret.add(node);
            }
         }

         return ret;
      }

      public int countEvensAndOdds()
      {
         LinkedList<EvenTreeNode> queue = new LinkedList<EvenTreeNode>();
         Collection<EvenTreeNode> leaves = this.getLeaves();

         for (EvenTreeNode leaf : leaves)
         {
            queue.add(leaf);
         }

         while (!queue.isEmpty())
         {
            EvenTreeNode node = queue.pop();
            if (!node.isLeaf())
               throw new RuntimeException();

            HashSet<EvenTreeEdge> edges = node.getEdges();
            if (edges.size() == 0)
               continue;
            if (edges.size() != 1)
               throw new RuntimeException();

            EvenTreeEdge[] edgeArray = new EvenTreeEdge[1];
            edges.toArray(edgeArray);
            EvenTreeEdge edge = edgeArray[0];

            EvenTreeNode otherNode = edge.getOtherNode(node);

            if (node.lastEdgeWillBeOdd())
            {
               otherNode.flipLastEdgeWillBe();
            }
            else
            {
               incrementEvenCount();
            }

            this.removeEdge(edge);
            if (otherNode.isLeaf())
            {
               queue.add(otherNode);
            }
         }

         return getEvenCount();
      }

      private void removeEdge(EvenTreeEdge edge)
      {
         EvenTreeNode nodeOne = edge.getNodeOne();
         EvenTreeNode nodeTwo = edge.getNodeTwo();

         nodeOne.removeEdge(edge);
         nodeTwo.removeEdge(edge);
      }

      private int getEvenCount()
      {
         return evenCount;
      }

      private void incrementEvenCount()
      {
         evenCount++;
      }

   }

   public static class EvenTreeNode
   {
      private final int identifier;
      private final HashSet<EvenTreeEdge> edges = new HashSet<EvenTreeEdge>();
      private boolean lastEdgeWillBeOdd = true;

      public EvenTreeNode(int identifier)
      {
         this.identifier = identifier;
      }

      public int getIdentifier()
      {
         return identifier;
      }

      public void addEdge(EvenTreeEdge edge)
      {
         this.edges.add(edge);
      }

      public HashSet<EvenTreeEdge> getEdges()
      {
         return edges;
      }

      public boolean isLeaf()
      {
         return ((edges.size() == 1) || (edges.size() == 0));
      }

      public boolean lastEdgeWillBeOdd()
      {
         return lastEdgeWillBeOdd;
      }

      public void flipLastEdgeWillBe()
      {
         lastEdgeWillBeOdd = !lastEdgeWillBeOdd;
      }

      public void removeEdge(EvenTreeEdge edge)
      {
         boolean removedEdge = edges.remove(edge);
         if (!removedEdge)
            throw new RuntimeException();
      }

   }

   public static class EvenTreeEdge
   {
      private final EvenTreeNode node1, node2;

      public EvenTreeEdge(EvenTreeNode node1, EvenTreeNode node2)
      {
         if (node1 == node2)
            throw new RuntimeException();

         this.node1 = node1;
         this.node2 = node2;
      }

      public EvenTreeNode getNodeOne()
      {
         return node1;
      }

      public EvenTreeNode getNodeTwo()
      {
         return node2;
      }

      public EvenTreeNode getOtherNode(EvenTreeNode node)
      {
         if ((node1 != node) && (node2 != node))
            throw new RuntimeException();

         if (node1 == node)
            return node2;
         return node1;
      }

   }

}
