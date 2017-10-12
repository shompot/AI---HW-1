import java.util.ArrayList;

public class Node {
    private int jugA;
    private int jugB;

    private Node parent;
    private ArrayList<Node> children;

    Node (Node parent, int jugA, int jugB){
        this.jugA = jugA;
        this.jugB = jugB;
        this.parent = parent;
        children = new ArrayList<Node>();
    }

    Node (Node parent){
        this.parent = parent;
        children = new ArrayList<Node>();
    }

    public void addChild (Node child) {
        children.add(child);
    }

    public void setA (int a){
        jugA = a;
    }

    public void setB (int b){
        jugA = b;
    }

    public ArrayList<Node> getChildren (){
        return children;
    }

    public int getA (){
        return jugA;
    }

    public int getB () {
        return jugB;
    }

    public Node getParent (){
        return parent;
    }

    public void printNode (){
        System.out.print("A is " + this.getA() + "\t B is " + this.getB());
    }

}
