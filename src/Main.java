import View.MainWindow;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {}

        MainWindow mainFrame = new MainWindow();
        mainFrame.createWindow();

    }
}
