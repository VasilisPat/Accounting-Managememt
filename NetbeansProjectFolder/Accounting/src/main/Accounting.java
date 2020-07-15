package main;

import GUI.Login;

public class Accounting {
    
    public static void main(String[] args){
        new main.LoadingProcess().callLoadingScreen();
        new GUI.Login().setVisible(true);
        
    }
}
