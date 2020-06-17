package View;

import Controllers.GraphDraw;
import Controllers.MainWindowController;
import javax.swing.*;
import java.awt.*;

public class MainWindow {
    MainWindowController mainWindowController = new MainWindowController();
    MainWindowHandler mainWindowHandler = new MainWindowHandler();
    private JFrame mainFrame = new JFrame("Functions");
    private JPanel backgroundPanel = new JPanel();
    private JPanel infoPanel = new JPanel();
    private JSplitPane splitPaneH;

    private JSplitPane splitPaneTableGraph = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    private GraphDraw controller = new GraphDraw(splitPaneTableGraph);


    public void createWindow() {
        backgroundPanel.setLayout(new BorderLayout());

        infoPanel = mainWindowController.createInfoPanel();
        splitPaneH = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        backgroundPanel.add(splitPaneH, BorderLayout.CENTER);
        splitPaneH.setLeftComponent(infoPanel);
        splitPaneH.setRightComponent(splitPaneTableGraph);

        mainFrame.add(backgroundPanel);
        mainFrame.setSize(1366, 700);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindowHandler.handler(mainFrame, splitPaneTableGraph, controller, mainWindowController);
        mainFrame.setVisible(true);
    }


}
