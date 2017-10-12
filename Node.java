import java.util.ArrayList;

public class Node {
    private int jugA;
    private int jugB;

    private Node parent;
    private ArrayList<Node> children;
    private int childrenSize = 0;

    Node (){
        this.jugA = 0;
        this.jugB = 0;
        this.parent = null;
        children = new ArrayList<Node>();
    }

    Node (Node parent, int jugA, int jugB){
        this.jugA = jugA;
        this.jugB = jugB;
        this.parent = parent;
        children = new ArrayList<Node>();
    }

    Node (int jugA, int jugB){

        this.jugA = jugA;
        this.jugB = jugB;
        this.parent = null;
        children = new ArrayList<Node>();
    }

    public void addChild (Node child) {
        children.add(child);
        child.setParent(this);
        childrenSize ++ ;
    }

    public void setParent (Node node){
        this.parent = node;
    }

    public void setA (int a){
        this.jugA = a;
    }

    public void setB (int b){
        this.jugB = b;
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

    public int getChildrenSize (){
        return childrenSize;
    }

    public void printNode (){
        System.out.print("A is " + this.getA() + "\t B is " + this.getB() + ";\n");
    }

    public boolean isEqual(Node node){
        if (this.getA() == node.getA() && this.getB() == node.getB())
            return true;
        return false;
    }

}
