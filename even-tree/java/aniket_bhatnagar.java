/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;


public class Solution {
    
    private Map<Integer, Node> nodes = new HashMap<Integer, Node>(100);
    
    private Node root;
    
    public static void main(String[] args) throws FileNotFoundException {
        Solution solution = new Solution();
        solution.readInput();
        //solution.printTree(solution.root);
        System.out.println(solution.calcVerticesToRemove(solution.root, solution.root));
    }
    
    private int calcVerticesToRemove(Node node, Node root) {
        if (node == null) {
            return 0;
        }
        List<Node> children = node.getChildren();
        if (children == null) {
            return 0;
        }
        // Copy children as children can be modified during calc
        children = new LinkedList<Node>(children);
        int verticesToDel = 0;
        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);
            if (child.getSubtreeNodes() % 2 == 0) {
                child.disconnect();
                verticesToDel++;
            }
            verticesToDel += calcVerticesToRemove(child, root);
        }
        return verticesToDel;
    }
    
    private Node findBestNodeToDc(Node node) {
        /*List<Node> children = node.getChildren();
        if (children == null) {
            return null;
        }
        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);
            // If child has odd children, it's best to delete this
            if (child.getChildren() != null && child.getChildren().size() % 2 == 1) {
                return child;
            }
        }
        //Can't find a best match. Return first
        return node.getChildren().get(0);*/
        return node;
    }

    private void printTree(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);
        Node currentParent = null;
        while (!queue.isEmpty()) {
            node = queue.remove();
            List<Node> children = node.getChildren();
            if (children != null) {
                for (Node child : children) {
                    queue.add(child);
                }
            }
            if (node.getParent() != currentParent) {
                System.out.println("");
            }
            currentParent = node.getParent();
            System.out.print(node.getData() + " ");
        }  
        System.out.println("");
        //System.out.println(this.nodes.get(4));
    }

    private void readInput() throws FileNotFoundException {
        //Scanner scanner = new Scanner(new File("c:\\temp\\interviewstreet\\eventree\\input01.txt"));
        Scanner scanner = new Scanner(System.in);
        int numNodes = scanner.nextInt();
        for (int i = 0; i < numNodes; i++) {
            int data = i + 1;
            Node node = new Node(data);
            nodes.put(data, node);
        }
        int numVertices = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numVertices; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            Node uNode = nodes.get(u);
            Node vNode = nodes.get(v);
            vNode.addChild(uNode);
        }
        this.root = nodes.entrySet().iterator().next().getValue();
        while (this.root.getParent() != null) {
            this.root = this.root.getParent();
        }
    }
    
}

class Node {
    private int data;
    private int subtreeNodes;
    private Node parent;
    private List<Node> children;
    
    public Node(int data) {
        this.data = data;
        this.subtreeNodes = 1;
    }
    
    public void addChild(Node node) {
        if (this.children == null) {
            this.children = new LinkedList<Node>();
        }
        this.subtreeNodes += node.subtreeNodes;
        node.parent = this;
        this.children.add(node);
        Node parent = this.parent;
        while (parent != null) {
            parent.subtreeNodes += node.subtreeNodes;
            parent = parent.parent;
        }
    }
    
    public void disconnect() {
        Node parent = this.parent;
        if (parent == null) {
            throw new IllegalStateException("Node " + this + " is already disconnected");
        }
        while (parent != null) {
            parent.subtreeNodes -= this.subtreeNodes;
            parent = parent.parent;
        }
        this.parent.children.remove(this);
        this.parent = null;
        //System.out.println("dc : " + this);
    }
    
    public int getData() {
        return this.data;
    }
    
    public Node getParent() {
        return this.parent;
    }
    
    public List<Node> getChildren() {
        return this.children;
    }
    
    public int getSubtreeNodes() {
        return this.subtreeNodes;
    }

    /**
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Node [data=" + data + ", subtreeNodes=" + subtreeNodes + ", children=" + children + "]";
    }
}
