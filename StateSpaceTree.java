import java.util.ArrayList;
import java.util.LinkedList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class StateSpaceTree {
    private Node root;
    private int capacityA = 10;
    private int capacityB = 6;


    StateSpaceTree (){
        this.root = new Node (null, 0, 0);
    }
    StateSpaceTree (Node root){
        this.root = root;
        root.printNode();
    }

    public Node getRoot (){
        return root;
    }

    public void createTree (){
        createChildren(this.root);

        growTree(root);
    }

    public void growTree (Node node){

        ArrayList<Node> children = node.getChildren();
        for (int i = 0; i < children.size(); i ++){
               if (!checkNode(children.get(i)))
                   createChildren(children.get(i));
        }
        for (int i = 0; i < children.size(); i ++){
            if (!checkNode(children.get(i)))
                growTree(children.get(i));
        }
    }

    public void createChildren (Node node){

        // try going every possible way
        fillA(node);
        fillB(node);
        pourAtoB(node);
        pourBtoA(node);
        emptyA(node);
        emptyB(node);

    }

    public boolean fillA(Node node){


        if (node.getA() == capacityA)       // can't fill what's already full
            return false;

        Node child = new Node (capacityA, node.getB());

        if (checkLoop(node, child))     // the node already exists in path from root
            return false;

        node.addChild(child);

        return true;
    }

    public boolean fillB(Node node){

        if (node.getB() == capacityB)       // can't fill what's already full
            return false;

        Node child = new Node (node.getA(), capacityB);

        if (checkLoop(node, child))     // the node already exists in path from root
            return false;

        node.addChild(child);

        return true;
    }

    public boolean pourAtoB(Node node){

        if (node.getA() == 0)       // can't pour from empty
            return false;

        if (node.getB() == capacityB)   // can't pour into full
            return false;

        Node child = new Node ();

        child.setA( max ( 0, node.getA() + node.getB() - capacityB ) );
        child.setB( min ( capacityB, node.getA() + node.getB() ) );

        if (checkLoop(node, child))     // the node already exists in path from root
            return false;

        node.addChild(child);

        return true;
    }

    public boolean pourBtoA(Node node){

        if (node.getB() == 0)       // can't pour from empty
            return false;

        if (node.getA() == capacityA)   // can't pour into full
            return false;

        Node child = new Node ();

        child.setA( min ( capacityA, node.getA() + node.getB() ) );
        child.setB( max ( 0, node.getA() + node.getB() - capacityA ) );

        if (checkLoop(node, child))     // the node already exists in path from root
            return false;

        node.addChild(child);

        return true;
    }

    public boolean emptyA(Node node){

        if (node.getA() == 0)       // can't empty what's already empty
            return false;

        Node child = new Node (0, node.getB());

        if (checkLoop(node, child))     // the node already exists in path from root
            return false;

        node.addChild(child);

        return true;
    }

    public boolean emptyB(Node node){

        if (node.getB() == 0)       // can't empty what's already empty
            return false;

        Node child = new Node (node.getA(), 0);

        if (checkLoop(node, child))     // the node already exists in path from root
            return false;

        node.addChild(child);

        return true;
    }

    public boolean checkNode (Node node){
        if ( node.getA() == 8 )
            return true;
        return false;
    }


    public boolean checkLoop (Node node, Node checkedChild){

        LinkedList<Node> list = BFT (root);

        for (int i = 0; i < list.size(); i ++) {
            if (list.get(i).isEqual(checkedChild)) {
                return true;
            }
        }
        return false;
    }

    public void print(){
        LinkedList<Node> nodes = BFT (root);

        nodes.forEach(Node::printNode);
    }

    public LinkedList<Node> BFT (Node node) {

        LinkedList<Node> result = new LinkedList<Node>();
        LinkedList<Node> queue = new LinkedList<Node>();

        queue.add(node);

        while (!queue.isEmpty())
        {
            result.add(queue.get(0));

            ArrayList<Node> children = queue.get(0).getChildren();

            for (int i=0; i < children.size(); i++)
                queue.add(children.get(i));

            queue.remove(0);
        }
        return result;
    }

    public LinkedList <Node> search (Node node){

        LinkedList<Node> path = new LinkedList<Node>();
        LinkedList<Node> queue = BFT (node);
        Node result = null;

        for (int i=0; i < queue.size(); i++){
            if (queue.get(i).getA()==8){
                result = queue.get(i);
                break;
            }
        }

        if (result == null)
            return null;
        while (result!=null){
            path.addFirst(result);
            result = result.getParent();

        }
        return path;

    }


    public static void main(String[] args){
        StateSpaceTree tree = new StateSpaceTree();

        tree.createTree();

        System.out.print("The composed tree is:\n");
        tree.print();

        LinkedList<Node> path = tree.search (tree.getRoot());

        System.out.print("The path from the first result is:\n");

        for (int i=0; i < path.size(); i++)
            path.get(i).printNode();
    }
}
