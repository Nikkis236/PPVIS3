package View;

import Controllers.GraphDraw;
import Controllers.MainWindowController;
import Controllers.RandomStringGenerator;
import Controllers.ValueTableController;
import Model.AhoCorasick;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;


public class MainWindowHandler {
    ValueTableController tableController = new ValueTableController();
    private boolean stop = false;
    private Thread threadB = new Thread();
    private Thread threadA = new Thread();
    private JPanel tableBPanel;
    static JPanel functionAPanel;
    private JTable funcBTable;
    private JScrollPane scrollPane;
    public List<String> randomStrings = new ArrayList<>();
    private File readFile;



    public void handler(JFrame mainFrame, JSplitPane splitPaneTableGraph, GraphDraw controller, MainWindowController mainWindowController) {
        mainWindowController.getGraphABt().addActionListener(e -> {

            stop = false;
            if (threadA.isAlive()) {
                threadA.stop();

            }
            controller.clearPointList("A");
            threadA = new Thread(new Runnable() {
                @Override
                public void run() {
                    int x = 1;
                    while (x <= 25 && !stop) {
                        int F = functionA(x);
                        Point point = new Point(x * 100, F * 10000);
                        controller.addPointToList(point, "A");
                        x += 1;
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException en) {
                            JOptionPane.showMessageDialog(mainFrame, "Ошибка");
                        }
                    }
                }

                int functionA(int x) {
                    return (x+7);
                }
            });

            threadA.start();
            splitPaneTableGraph.setLeftComponent(functionAPanel);


        });

        mainWindowController.getGraphBBt().addActionListener(e -> {
            if (splitPaneTableGraph.getLeftComponent() != null) {
                tableBPanel.remove(scrollPane);
            }
            if (readFile!=null) {


                funcBTable = tableController.createTable();
                scrollPane = new JScrollPane(funcBTable);
                tableBPanel = mainWindowController.createTableBPanel(scrollPane);
                splitPaneTableGraph.setLeftComponent(tableBPanel);
                stop = false;
                if (threadB.isAlive()) {
                    threadB.stop();
                }
                controller.clearPointList("B");

                //List<String> randomStrings = new ArrayList<>();
                RandomStringGenerator rand = new RandomStringGenerator();
                try {
                    rand.setSource(readFile.getAbsolutePath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                //System.out.println(rand.getSource());
                randomStrings = rand.getRandomStrings();


                threadB = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        double x = 1;
                        while (x <= 25 && !stop) {
                            double F = functionB(x);

                            Point point = new Point((int) x * 100, (int) (F * 10000));
                            controller.addPointToList(point, "B");
                            funcBTable = tableController.addRow(funcBTable, String.format("%.0f", (x)), String.format("%.0f", (F)));
                            x += 1;
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException en) {
                                JOptionPane.showMessageDialog(mainFrame, "Ошибка");
                            }
                        }
                    }

                    private double functionB(double x) {
                        double F = 0;
                        AhoCorasick.TrieNode trie = new AhoCorasick.TrieNode();
                        trie.addWord(randomStrings.get((int) x - 1));
                        trie.constructFallLinks();
                        long time = System.currentTimeMillis();
                        trie.search(rand.getText());
                        F = System.currentTimeMillis() - time;
                        System.out.println(System.currentTimeMillis() - time);
                        //System.out.println(rand.getSource());

                        return abs(F);
                    }

                });

                threadB.start();

            } else {JOptionPane.showMessageDialog(mainFrame, "Pls choose source file");}
        });

        mainWindowController.getStopAllBt().addActionListener(e -> stop = !stop);

        mainWindowController.getReadBtn().addActionListener(e->{
            try {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("txt","txt");
                chooser.setFileFilter(filter);
                int ret = chooser.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    readFile = chooser.getSelectedFile();

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        mainWindowController.getClearABtn().addActionListener(e->{

            stop = false;
            if (threadA.isAlive()) {
                threadA.stop();

            }
            controller.clearPointList("A");
            threadA = new Thread(new Runnable() {
                @Override
                public void run() {
                    Point point = new Point(0, 0);
                    controller.addPointToList(point, "A");
                }
            });
            threadA.start();
        });

        mainWindowController.getClearBBtn().addActionListener(e->{

            stop = false;
            if (threadB.isAlive()) {
                threadB.stop();
            }
            controller.clearPointList("B");
            threadB = new Thread(new Runnable() {
                @Override
                public void run() {
                    Point point = new Point(0, 0);
                    controller.addPointToList(point, "B");
                }

            });
            threadB.start();
            tableBPanel.remove(scrollPane);
        });

    }

}
