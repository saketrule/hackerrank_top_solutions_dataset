
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/**
 * Interview Street's Even Tree Challenge
 *
 * You are given a tree (a simple connected graph with no cycles).You have to
 * remove as many edges from the tree as possible to obtain a forest with the
 * condition that : Each connected component of the forest contains even number
 * of vertices
 *
 * Your task is to calculate the number of removed edges in such a forest.
 *
 * Input: The first line of input contains two integers N and M. N is the number
 * of vertices and M is the number of edges. N between 2 and 100. Next M lines
 * contains two integers ui and vi which specifies an edge of the tree. (1-based
 * index)
 *
 * Output: Print a single integer which is the answer
 *
 * Sample Input 10 9 2 1 3 1 4 3 5 2 6 1 7 2 8 6 9 8 10 8
 *
 * Sample Output : 2
 *
 * Explanation : On removing the edges (1, 3) and (1, 6), we can get the desired
 * result.
 *
 * @author Pooya
 */
public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
//        Solution problem = new Solution(new FileInputStream("input.txt"));
        Solution problem = new Solution(System.in);            
        System.out.println(problem.solve());
    }


    public Solution(InputStream input) {
        Scanner scanner = new Scanner(input);

        numOfVertices = scanner.nextInt();
        numOfEdges = scanner.nextInt();

        leaves = new HashSet<Integer>();
        tree = new TreeNode[numOfVertices + 1];
        for (int i = 1; i <= numOfVertices; i++) {
            leaves.add(i);
            tree[i] = new TreeNode(i);
        }
        
        for (int i = 0; i < numOfEdges; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            tree[from].setParent(to);
            tree[to].addChild(from);
        }

        calcSubtreeSize(1);
    }


    public final int calcSubtreeSize(int nodeId) {
        if(tree[nodeId].getChildren().isEmpty())
            return tree[nodeId].getSubtreeSize();
        
        for(Integer i: tree[nodeId].getChildren()){
            tree[nodeId].incSubtreeSize(calcSubtreeSize(i));
        }
        
        return tree[nodeId].getSubtreeSize();
    }

    public int solve() {
        int bridges = 0;

        for (Integer i : leaves) {
            TreeNode curNode = tree[i];
            while (curNode.hasParent() && !curNode.isVisited()) {
                curNode.visit();

                if (curNode.getSubtreeSize() % 2 == 0) {
                    if ((numOfVertices - curNode.getSubtreeSize()) % 2 == 0) {
                        bridges++;
                    }
                }

                curNode = tree[curNode.getParent()];
            }
        }

        return bridges;
    }
    private int numOfVertices;
    private int numOfEdges;
    private TreeNode[] tree;
    private Set<Integer> leaves;

    private static class TreeNode {

        public TreeNode(int id) {
            this.id = id;
            this.parent = id;
            this.subtreeSize = 1;
            this.visited = false;
            this.children = new LinkedList<Integer>();
        }
        public void addChild(int childId){
            children.add(childId);
        }

        public List<Integer> getChildren(){
            return children;
        }
        public int getSubtreeSize() {
            return subtreeSize;
        }

        public void setSubtreeSize(int subtreeSize) {
            this.subtreeSize = subtreeSize;
        }

        public void incSubtreeSize(int by) {
            this.subtreeSize += by;
        }

        public int getParent() {
            return parent;
        }

        public boolean hasParent() {
            return parent != id;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public boolean isVisited() {
            return visited;
        }

        public void visit() {
            this.visited = true;
        }
        
        //id of the current node
        private int id;
        //Parent of the current node in the array
        private int parent;
        private List<Integer> children;
        //Size of the subtree rooted at the current node
        private int subtreeSize;
        //True if the node has been visited through one of its descendants
        private boolean visited;
    }
}
