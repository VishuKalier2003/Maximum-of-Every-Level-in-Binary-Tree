/* Given a Binary Tree, generate an array which gives the maximum dat of every level of the Binary Tree... 
 * Eg 1: Tree Nodes = [1, 2, 4, -1, 7, -1, -1, 5, -1, -1, 3, -1, 8, -1, -1]    Output = 1, 3, 8, 7
 * Eg 2: Tree Nodes = [1, 2, 4, -1, -1, 5, -1, -1, 3, 6, -1, -1]               Output = 1, 3, 6
 * Eg 3: Tree Nodes = [1, 2, -1, -1, 4, 3, 5, -1, -1, 7, -1, -1, 0, -1, -1]    Output = 1, 4, 3, 7
*/
import java.util.*;
public class LevelMax      // Superclass for defining the Binary Tree and its Properties...
{
    public class Node     // Creating Node class defining structure of a Node...
    {
        public int data;
        public Node left;
        public Node right;
        public Node(int value){
            this.data = value;
            this.left = null;
            this.right = null;
        }
    }
    public class BinaryTree    // Binary Tree class for defining methods of Binary Tree...
    {
        public static int i = -1;    // static data value...
        public Node InsertNode(int node[])
        {
            i++;
            if(node[i] == -1)    // When -1 passed we backtrack...
                return null;
            Node newNode = new Node(node[i]);
            newNode.left = InsertNode(node);     // We add PreOrder wise...
            newNode.right = InsertNode(node);
            return newNode;
        }
        public void ShowTree(Node root)
        {
            if(root == null)   // If Tree is empty nothing to show
                return;            // Control moves out of the function
            Node temp = root;
            ShowTree(root.left);     // Recursive Call
            System.out.println("Node: ");
            if(root.left != null)    // If left subtree is not empty
                System.out.println("\t"+temp.data+" ---> "+temp.left.data+"\t Left ( Occupied )");
            else      // If left subtree is empty
                System.out.println("\t"+temp.data+"\t\t Left ( Empty )");
            if(root.right != null)   // If right subtree is not empty
                System.out.println("\t"+temp.data+" ---> "+temp.right.data+"\t Right ( Occupied ) ");
            else     // If right subtree is empty
                System.out.println("\t"+temp.data+"\t\t Right ( Empty )");
            ShowTree(root.right);     // Recursive Call
        }
        public void LevelMaximumNode(Node root)
        {
            if(root == null)
                return;
            Queue<Node> queue = new LinkedList<Node>();     // Using Queue to traverse Nodes...
            List<Integer> list = new ArrayList<Integer>();   // Using List to storing the max nodes...
            queue.add(root);
            queue.add(null);         // Null nodes are used to mark the end of the next level...
            int max = root.data, h=1;    // Checking the height of the tree...
            boolean check = true;
            while(!queue.isEmpty())
            {
                Node current = queue.peek();    // Getting the head Node in the Queue...
                if(current != null)
                {
                    System.out.println("Height : "+h+" Node data : "+current.data);
                    if(max < current.data)
                        max = current.data;
                    if(current.left != null)         // If left child is not null node...
                        queue.add(current.left);
                    if(current.right != null)        // If right child is not null node...
                        queue.add(current.right);
                }
                if(current == null)    // When we get the null node...
                {
                    list.add(max);     // Adding the max node and initializing max again to zero...
                    max = 0;
                    h++;
                    queue.add(null);    // We add a null pointer to mark the ned of the next level...
                }
                if(queue.size() == 2)   // If there are only two nodes...
                {
                    for(Node iter: queue)
                    {
                        if(iter != null)     // If one of the node is not null...
                            check = false;
                    }
                    if(check == true)     // If both nodes are null remove one node, the other will be removed when the end point is reached...
                        queue.remove();
                    check = true;
                }
                queue.remove();    // Removing the current or head Node from the queue...
            }
            for(int index = 0; index < list.size(); index++)
                System.out.print(list.get(index)+", ");       // Printing the max nodes...
            System.out.println();
            return;
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int x;
        System.out.print("Enter total number of Nodes (including Null nodes) : ");
        x = sc.nextInt();
        int nodes[] = new int[x];
        for(int i = 0; i < x; i++)
        {
            System.out.print("Enter the "+(i+1)+" th Node : ");
            nodes[i] = sc.nextInt();
        }
        LevelMax maxlevel = new LevelMax();     // Object creation...
        BinaryTree binarytree = maxlevel.new BinaryTree();    // Sub class object creation...
        Node root = binarytree.InsertNode(nodes);
        binarytree.ShowTree(root);
        binarytree.LevelMaximumNode(root);     // function call...
        sc.close();
    }
}

// Time Complexity  - O(n) time...
// Space Complexity - O(log n) space...

/* DEDUCTIONS :- 
 * 1. We use Iterative Deepening Search, such that when a level ends we add a Null node to the queue...
 * 2. We use Queue since we will be searching linearly, level by level...
*/