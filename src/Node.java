import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton implements ActionListener {

    private Node parent;

    private int col;

    private int row;

    private int gCost;

    private int hCost;

    private int fCost;

    private boolean start;

    private boolean goal;

    private boolean solid;

    private boolean open;

    private boolean checked;

    public Node(int col, int row) {
        this.col = col;
        this.row = row;

        setBackground(Color.white);
        setForeground(Color.black);
        addActionListener(this);
    }

    public void setAsStart() {
        setBackground(Color.blue);
        setForeground(Color.white);
        setText("Start");
        start = true;
    }

    public void setAsGoal() {
        setBackground(Color.yellow);
        setForeground(Color.black);
        setText("Goal");
        goal = true;
    }

    public void setAsSolid() {
        setBackground(Color.black);
        setForeground(Color.black);
        solid = true;
    }

    public void setAsOpen() {
        open = true;
    }

    public void setAsChecked() {
        if(!start && !goal) {
            setBackground(Color.orange);
            setForeground(Color.black);
        }
        checked = true;
    }

    public void setAsPath() {
        setBackground(Color.green);
        setForeground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setBackground(Color.orange);
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean isSolid() {
        return solid;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getGCost() {
        return gCost;
    }

    public void setGCost(int gCost) {
        this.gCost = gCost;
    }

    public int getHCost() {
        return hCost;
    }

    public void setHCost(int hCost) {
        this.hCost = hCost;
    }

    public int getFCost() {
        return fCost;
    }

    public void setFCost(int fCost) {
        this.fCost = fCost;
    }


    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParentNode() {
        return this.parent;
    }
}
