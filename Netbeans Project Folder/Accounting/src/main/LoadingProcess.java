package main;

import java.lang.Thread;
import GUI.LoadingScreen;
import javax.swing.JOptionPane;


public class LoadingProcess {
    
    public void callLoadingScreen(){
        LoadingScreen load = new LoadingScreen();
        for(int i=0; i<=100; i++){
            try {
                Thread.sleep(15);
                load.setVisible(true);
                load.loadingLabel.setText("Loading..."+i+"%");
                load.progressBar.setValue(i);
            }catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(null,"Driver Not Loaded (Error Code: -1)","Error",JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }
        }
        load.dispose();
    }
}
