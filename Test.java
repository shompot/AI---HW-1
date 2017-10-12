import java.util.ArrayList;
import java.util.LinkedList;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

import javax.swing.*;

public class Test {

    public static void main(String[] args){

        // Instead of below, get Cholpon's tree and bfs it.
        Node root = new Node(null, 0, 0);
        Node node1 = new Node(null, 1, 1);
        Node node2 = new Node(null, 2, 2);
        Node node3 = new Node(null, 3, 3);
        Node node4 = new Node(null, 4, 4);
        Node node5 = new Node(null, 5, 5);
        root.addChild(node1);
        root.addChild(node2);
        root.addChild(node3);
        node2.addChild(node4);
        node4.addChild(node5);

        bfs(root);
    }

    public static void bfs(Node root) {
        LinkedList<Node> queue = new LinkedList();

        queue.add(root);
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            // Process node right here as you will see each and every one of them in breadth-first fashion, right here.
            // Wait a little bit so that its incremental.
            try
            {
                Thread.sleep(2000); // 1000 millis = 1 seconds.
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            node.printNode();
            // Right here, open a node editing session, de-highlight the node before, and highlight the current one.
            ArrayList<Node> children = node.getChildren();
            for (Node child : children) {
                queue.add(child);
            }
        }
    }
}