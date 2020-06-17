package View;

import Controllers.GraphDraw;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class GraphOptionsPanel extends JPanel {
    private GraphDrawPanel graphDrawPanel;
    private JSlider slider;
    private JLabel graphALabel = new JLabel("Function A  ");
    private JLabel graphBLabel = new JLabel("Function B  ");
    private JLabel lineALabel = new JLabel("---");
    private JLabel lineBLabel = new JLabel("---");
    private JLabel hatchLabelX = new JLabel("Unit interval X = 0.01");
    private JLabel hatchLabelY = new JLabel("Unit interval Y = 0");
    private JButton hatchUpdateBtn = new JButton("Click to update");
    private JScrollPane scrollPanel;

    public GraphOptionsPanel(GraphDraw controller) {
        super();
        this.setLayout(new BorderLayout());
        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);
        slider = new JSlider(JSlider.HORIZONTAL, 50, 150, 100);
        slider.setEnabled(false);
        graphDrawPanel = new GraphDrawPanel(controller);
        toolBar.setFloatable(false);
        toolBar.setLayout(new FlowLayout());
        scrollPanel = new JScrollPane(graphDrawPanel);
        scrollPanel.setAutoscrolls(true);
        graphOptionsHandler();

        scrollPanel.setPreferredSize(new Dimension(800, 600));
        scrollPanel.setVisible(true);

        graphALabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        graphBLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lineALabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
        lineBLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));

        lineALabel.setForeground(Color.MAGENTA);
        lineBLabel.setForeground(Color.orange);
        JPanel box = new JPanel(new GridLayout(2,2));
        JPanel hatchBox = new JPanel(new GridLayout(3,1));

        for (JComponent r : Arrays.asList(graphALabel, lineALabel,graphBLabel,lineBLabel,hatchLabelX)) {
            box.add(r);
        }
        for (JComponent r : Arrays.asList(hatchLabelX, hatchUpdateBtn , hatchLabelY)) {
            hatchBox.add(r);
        }
        for (JComponent r : Arrays.asList(box, slider, hatchBox)) {
            toolBar.add(r);
        }

        this.add(scrollPanel, BorderLayout.WEST);
        this.add(toolBar, BorderLayout.SOUTH);
        this.setVisible(true);

    }

    private void graphOptionsHandler(){

        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point origin;

            @Override
            public void mousePressed(MouseEvent e) {
                origin = e.getPoint();

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (origin != null) {
                    JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, graphDrawPanel);
                    if (viewPort != null) {
                        int deltaX = origin.x - e.getX();
                        int deltaY = origin.y - e.getY();

                        Rectangle view = viewPort.getViewRect();
                        view.x += deltaX * 0.3;
                        view.y += deltaY * 0.2;
                        graphDrawPanel.scrollRectToVisible(view);
                    }
                }
            }
        };

        graphDrawPanel.addMouseListener(mouseAdapter);
        graphDrawPanel.addMouseMotionListener(mouseAdapter);
        scrollPanel.addMouseWheelListener(e -> {
            if (e.isControlDown()) {
                if (e.getWheelRotation() < 0) {
                    graphDrawPanel.setZoom(graphDrawPanel.getZoom() + 0.1);

                } else {
                    graphDrawPanel.setZoom(graphDrawPanel.getZoom() - 0.1);
                }
                slider.setValue((int) (graphDrawPanel.getZoom() * 100));
                graphDrawPanel.revalidate();
            }
        });



        hatchUpdateBtn.addActionListener(e->{
            hatchLabelX.setText("Unit interval X = " + String.valueOf(graphDrawPanel.getHatchX()));
            hatchLabelY.setText("Unit interval Y = " + String.valueOf(graphDrawPanel.getHatchY()));

        });

    }


    public GraphDrawPanel getGraphDrawPanel() {
        return graphDrawPanel;
    }

}
