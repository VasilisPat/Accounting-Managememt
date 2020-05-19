package main;

import java.lang.Thread;
import QueriesGUI.LoadingScreen;
import javax.swing.JOptionPane;


public class Loading {
    
    public void callLoadingScreen(){
        int i;
        LoadingScreen load = new LoadingScreen();
        for(i=0; i<=100; i++){
            try {
                Thread.sleep(15);
                load.setVisible(true);
                load.loadingLabel.setText("Loading..."+i+"%");
                load.progressBar.setValue(i);
            }catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null,"Driver Not Loaded (Error -1)");
            System.exit(-1);
            }
        }
        load.dispose();
    }
}
