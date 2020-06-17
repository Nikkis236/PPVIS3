package Controllers;


import Model.PointList;
import View.GraphOptionsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphDraw {
    private PointList pointListA;
    private PointList pointListB;

    private GraphOptionsPanel graphOptionsPanel;

    public GraphDraw(JSplitPane splitPaneTableGraph) {
        pointListA = new PointList();
        pointListB = new PointList();
        graphOptionsPanel = new GraphOptionsPanel(this);
        splitPaneTableGraph.setRightComponent(graphOptionsPanel);
    }

    public void addPointToList(Point point, String graphName) {
        if (graphName.equals("A")) pointListA.addPoint(point);
        else pointListB.addPoint(point);
        getGraphOptionsPanel().getGraphDrawPanel().repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clearPointList(String graphName) {
        if (graphName.equals("A")) pointListA.clear();
        else pointListB.clear();

    }

    public List<Point> getPointList(String graphName) {
        if (graphName.equals("A")) return pointListA.getPointList();
        else return pointListB.getPointList();
    }

    public GraphOptionsPanel getGraphOptionsPanel() {
        return graphOptionsPanel;
    }
}


