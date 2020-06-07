package Controllers;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class MainWindowController {
    private boolean stop = false;
    private JButton graphABt = new JButton("Draw a graph A (4х-1)");
    private JButton graphBBt = new JButton("Draw a graph B (Aho-Corasick)");
    private JButton stopAllBt = new JButton("Stop drawing");
    private JButton readBtn = new JButton("Select file");
    private JButton clearABtn = new JButton("Clear A graph");
    private JButton clearBBtn = new JButton("Clear B graph");


    public JPanel createInfoPanel() {
        JPanel panel = new JPanel(new GridLayout(2,1));
        JPanel btnBox = new JPanel(new GridLayout(1, 5, 2, 2));
        JPanel clearBtBox = new JPanel(new GridLayout(2, 1));

        for (JComponent r : Arrays.asList(clearABtn,clearBBtn)) {
            clearBtBox.add(r);
        }
        for (JComponent r : Arrays.asList(graphABt, graphBBt,readBtn, stopAllBt,clearBtBox)) {
            btnBox.add(r);
        }
        panel.add(btnBox);
        return panel;
    }

    public JPanel createTableBPanel(JScrollPane scrollPane) {
        JPanel panel = new JPanel();
        scrollPane.setMinimumSize(new Dimension(100, 180));
        panel.setLayout(new BorderLayout());

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }


    public JButton getGraphABt() {
        return graphABt;
    }
    public JButton getGraphBBt() {
        return graphBBt;
    }
    public JButton getStopAllBt() {
        return stopAllBt;
    }
    public JButton getReadBtn() {
        return readBtn;
    }
    public JButton getClearABtn() { return clearABtn; }
    public JButton getClearBBtn() { return clearBBtn; }

}