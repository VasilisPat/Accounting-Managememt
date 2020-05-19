package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jasypt.util.text.StrongTextEncryptor;


public class HandleSQLDatabase {
    
    private static String passKey = "itsfossvp";
    private static Connection Conn = null;
    private static Statement Statem = null; 
    private static String connStr = "jdbc:mysql://localhost:3306/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
    
    public static Connection getConnection(){
        return Conn;
    }
    
    public static Statement getStatement(){
        return Statem;
    }
    
    public static int loadDriver(){
        
        String pathToConnector = "com.mysql.cj.jdbc.Driver";
        
        try{
            Class.forName(pathToConnector);
            return 0;
        } catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"JDBC driver not loaded (Error -1)");
            return -1;
        }
    }
    
        
    public static int connectToDatabase(String user, String pass) {
       StrongTextEncryptor loginDecryptor = new StrongTextEncryptor();
       loginDecryptor.setPassword(passKey);
       try{
           Conn = DriverManager.getConnection(connStr, loginDecryptor.decrypt(user), loginDecryptor.decrypt(pass));
           Statem = Conn.createStatement();
        }catch(SQLException exsql){
           //JOptionPane.showMessageDialog(null, "Connection to database NOT successfull! (Error 1)");
        }
        if(Conn != null){
            checkForDatabase();
            return 0;
        }else{
            JOptionPane.showMessageDialog(null, "Connection to database NOT successfull! application is exiting (Error 1)");
            return 1;
        }
    }
    
    private static void createDatabase(){
        try{
            String sql = "CREATE DATABASE accounting";
            getStatement().executeUpdate(sql);
        }catch(SQLException exsql){
            //JOptionPane.showMessageDialog(null,exsql);
        }
        try{
            String sql1 = "USE accounting";
            getStatement().executeUpdate(sql1);
        }catch(SQLException exsql){
            //JOptionPane.showMessageDialog(null,exsql);
        }
        try{
            String sql2 = "CREATE TABLE suppliers_list " + "(SupplierID INT NOT NULL, " + " SupplierName VARCHAR(50) NOT NULL, " +  "PRIMARY KEY(SupplierID))";
            getStatement().executeUpdate(sql2);
        }catch(SQLException exsql){
            //JOptionPane.showMessageDialog(null,exsql);
        }
        try{
            String sql3 = "CREATE TABLE income_list " + "(IncomeID INT NOT NULL," + "IncomeDate DATE NOT NULL, " + " Income FLOAT(8,2) NOT NULL, " +  "PRIMARY KEY(IncomeID))";
            getStatement().executeUpdate(sql3);
        }catch(SQLException exsql){
            //JOptionPane.showMessageDialog(null,exsql);
        }
        try{
            String sql4 = "CREATE TABLE expenses_list " + "(ExpenseID INT NOT NULL, " + " SupplierID INT NOT NULL, " + " ExpensesDate DATE NOT NULL, " + "Expenses FLOAT(8,2) NOT NULL," +
                    "Description VARCHAR(30)," + "PRIMARY KEY(ExpenseID),"+ "FOREIGN KEY(SupplierID) REFERENCES suppliers_list(SupplierID))";
            getStatement().executeUpdate(sql4);
        }catch(SQLException exsql){
            //JOptionPane.showMessageDialog(null,exsql);
        }       
    }
    
    
    private static void checkForDatabase(){
        
        try{
            String dbName = "accounting";
            ResultSet rs = Conn.getMetaData().getCatalogs();
            while(rs.next()){
                if(dbName.equals(rs.getString(1))){
                    String sql = "USE accounting";
                    Statem.executeUpdate(sql);
                    rs.close();
                    break;
                }else{
                    createDatabase();
                    //JOptionPane.showMessageDialog(null,"Database Not Found! Application is exiting (Error 2)");
                    //System.exit(0);
                }
            }
        }catch(SQLException exsql){
            JOptionPane.showMessageDialog(null,"Database Not Found (Error 2)");
        }
    }
    
    public static void disconnectFromDatabase(){
        
        if(Statem != null){
            try{
                Statem.close();
            }catch(SQLException ex3){
                //System.out.println(ex3);
            }
        }
        if(Conn != null){
            try{
                Conn.close();
            }catch(SQLException ex3){
                //System.out.println(ex3);
            }
        }
    }
}
