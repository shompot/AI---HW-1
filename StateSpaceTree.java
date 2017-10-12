import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class StateSpaceTree {
    Node root;
    int capacityA = 10;
    int capacityB = 6;
    int level = 3;
    int j = 0;

    StateSpaceTree (){
        this.root = new Node (null, 0, 0);
    }
    StateSpaceTree (Node root){
        this.root = root;
    }

    public void createTree (){
        growTree(this.root);
        j++;
    }

    public void growTree (Node node){
        if (j >= level)
            return;

        j++;
        // try going every possible way
        fillA(node);
        fillB(node);
        pourAtoB(node);
        pourBtoA(node);
        emptyA(node);
        emptyB(node);


    }

    public boolean fillA(Node node){

        //System.out.print("In Fill A\n");

        Node child = new Node (node, capacityA, node.getB());

        if (checkLoop(node, child))     // the node already exists in path from root
            return false;

        node.addChild(child);

        return true;
    }

    public boolean fillB(Node node){

        //System.out.print("In Fill B\n");

        Node child = new Node (node, node.getA(), capacityB);

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

        Node child = new Node (node);

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

        Node child = new Node (node);

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

        Node child = new Node (node, 0, node.getB());

        if (checkLoop(node, child))     // the node already exists in path from root
            return false;

        node.addChild(child);

        return true;
    }

    public boolean emptyB(Node node){

        if (node.getB() == 0)       // can't empty what's already empty
            return false;

        Node child = new Node (node, node.getA(), 0);

        if (checkLoop(node, child))     // the node already exists in path from root
            return false;

        node.addChild(child);

        return true;
    }

    public boolean checkNode (){

        return true;
    }


    public boolean checkLoop (Node node, Node checkedChild){

        return false;

    }

    public void printTree(){
         printHelper (this.root);
    }

    public void printHelper(Node node){
        if (node != null) {
            node.printNode();
            System.out.print("\nIt's Children: \n");
            ArrayList<Node> children = node.getChildren();
            for (int i = 0; i < children.size(); i ++){
                children.get(i).printNode();
                System.out.print(";\t");
            }

            System.out.print("\nDone with children\n\n");
        }
    }



    public static void main(String[] args){
        StateSpaceTree tree = new StateSpaceTree();

        tree.createTree();

        //System.out.print("In main\n");

        tree.printTree();
    }
}
