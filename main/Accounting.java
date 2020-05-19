package main;

import main.Loading;
import QueriesGUI.LoginForm;


public class Accounting {
    
    public static void main(String[] args){
        new main.Loading().callLoadingScreen();
        new QueriesGUI.LoginForm().setVisible(true);
        
    }
}
