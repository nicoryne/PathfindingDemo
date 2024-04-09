import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DemoPanel extends JPanel {

    private static final int MAX_COL = 15;

    private static final int MAX_ROW = 10;

    private static final int NODE_SIZE = 70;

    private static final int SCREEN_WIDTH = NODE_SIZE * MAX_COL;

    private static final int SCREEN_HEIGHT = NODE_SIZE * MAX_ROW;

    private final Node[][] node = new Node[MAX_COL][MAX_ROW];

    private Node startNode;

    private Node goalNode;

    private Node currentNode;

    private boolean goalReached;

    private int step = 0;

    private final ArrayList<Node> openList = new ArrayList<>();

    private final ArrayList<Node> checkedList = new ArrayList<>();
    public DemoPanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(MAX_ROW, MAX_COL));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);

        int col = 0;
        int row = 0;

        while(col < MAX_COL && row < MAX_ROW) {
            node[col][row] = new Node(col, row);
            this.add(node[col][row]);

            col++;
            if(col == MAX_COL) {
                col = 0;
                row++;
            }
        }

        setStartNode(3, 6);
        setGoalNode(11,3);

        setSolidNode(10, 2);
        setSolidNode(10, 3);
        setSolidNode(10, 4);
        setSolidNode(10, 5);
        setSolidNode(10, 6);
        setSolidNode(10, 7);
        setSolidNode(6, 2);
        setSolidNode(7, 2);
        setSolidNode(8, 2);
        setSolidNode(9, 2);
        setSolidNode(11, 7);
        setSolidNode(12, 7);
        setSolidNode(6, 1);

        setCostOnNodes();
    }

    private void setCostOnNodes() {
        int col = 0;
        int row = 0;

        while(col < MAX_COL && row < MAX_ROW) {
            getCost(node[col][row]);
            col++;
            if(col == MAX_COL) {
                col = 0;
                row++;
            }
        }
    }

    public void search() {
        if(!goalReached && step < 300) {
            int col = currentNode.getCol();
            int row = currentNode.getRow();

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            if(row-1 >= 0) {
                openNode(node[col][row-1]);
            }

            if(col-1 >= 0) {
                openNode(node[col-1][row]);
            }

            if(row+1 < MAX_ROW) {
                openNode(node[col][row+1]);
            }

            if(col+1 < MAX_COL) {
                openNode(node[col+1][row]);
            }

            int bestNodeIndex = 0;
            int bestNodeFCost = 999;

            for(int i = 0; i < openList.size(); i++) {
                if(openList.get(i).getFCost() < bestNodeFCost) {
                    bestNodeIndex = i;
                    bestNodeFCost = openList.get(i).getFCost();
                } else if(openList.get(i).getFCost() == bestNodeFCost) {
                    if(openList.get(i).getGCost() < openList.get(bestNodeIndex).getGCost()) {
                        bestNodeIndex = i;
                    }
                }
            }

            currentNode = openList.get(bestNodeIndex);

            if(currentNode == goalNode) {
                goalReached = true;
                trackPath();
            }
        }
        step++;
    }

    public void autoSearch() {
        while (!goalReached) {
            int col = currentNode.getCol();
            int row = currentNode.getRow();

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            if(row-1 >= 0) {
                openNode(node[col][row-1]);
            }

            if(col-1 >= 0) {
                openNode(node[col-1][row]);
            }

            if(row+1 < MAX_ROW) {
                openNode(node[col][row+1]);
            }

            if(col+1 < MAX_COL) {
                openNode(node[col+1][row]);
            }

            int bestNodeIndex = 0;
            int bestNodeFCost = 999;

            for(int i = 0; i < openList.size(); i++) {
                if(openList.get(i).getFCost() < bestNodeFCost) {
                    bestNodeIndex = i;
                    bestNodeFCost = openList.get(i).getFCost();
                } else if(openList.get(i).getFCost() == bestNodeFCost) {
                    if(openList.get(i).getGCost() < openList.get(bestNodeIndex).getGCost()) {
                        bestNodeIndex = i;
                    }
                }
            }

            currentNode = openList.get(bestNodeIndex);

            if(currentNode == goalNode) {
                goalReached = true;
                trackPath();
            }
        }
    }

    private void openNode(Node node) {
        if(!node.isOpen() && !node.isChecked() && !node.isSolid()) {
            node.setAsOpen();
            node.setParent(currentNode);
            openList.add(node);
        }
    }

    private void trackPath() {
        Node current = goalNode;

        while(current != startNode) {
            current = current.getParentNode();

            if(current != startNode) {
                current.setAsPath();
            }
        }
    }

    private void setStartNode(int col, int row) {
        node[col][row].setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }

    private void setGoalNode(int col, int row) {
        node[col][row].setAsGoal();
        goalNode = node[col][row];
    }

    private void setSolidNode(int col, int row) {
        node[col][row].setAsSolid();
    }

    private void getCost(Node node) {
        int xDistance = Math.abs(node.getCol() - startNode.getCol());
        int yDistance = Math.abs(node.getRow() - startNode.getRow());
        node.setGCost(xDistance + yDistance);

        xDistance = Math.abs(node.getCol() - goalNode.getCol());
        yDistance = Math.abs(node.getRow() - goalNode.getRow());
        node.setHCost(xDistance + yDistance);

        node.setFCost(node.getGCost() + node.getHCost());

        if(node != startNode && node != goalNode) {
            node.setText("<html>F: " + node.getFCost() + "<br>G:" + node.getGCost() + "</html>");
        }
    }
}
