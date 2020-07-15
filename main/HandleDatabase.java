package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import org.jasypt.util.text.StrongTextEncryptor;


public class HandleDatabase {
    
    private static Connection Conn = null;
    private static Statement Statem = null;
    private static String passKey = "itsfossvp";
    private static String connStr = "jdbc:mysql://localhost:3306/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
    
    public static Connection getConnection(){ return Conn; }
    
    public static Statement getStatement(){ return Statem; }
    
    public static int loadDriver(){
        String pathToConnector = "com.mysql.cj.jdbc.Driver";
        try{
            Class.forName(pathToConnector);
            return 0;
        } catch (ClassNotFoundException ex){
            return -1;
        }
    }
        
    public static int connectToDatabase(String user, String pass) {
       StrongTextEncryptor loginDecryptor = new StrongTextEncryptor();
       loginDecryptor.setPassword(passKey);
       try{
           Conn = DriverManager.getConnection(connStr, loginDecryptor.decrypt(user), loginDecryptor.decrypt(pass));
           Statem = Conn.createStatement();
        }catch(SQLException ex){}
        if(Conn != null){
            checkForDatabase();
            return 0;
        }else{
            return 1;
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
                }
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Database Not Found (Error Code: 2)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void createDatabase(){
        try{
            //Database Creation
            String initDbStr = "CREATE DATABASE accounting";
            getStatement().executeUpdate(initDbStr);
            //Set Database in Use
            initDbStr = "USE accounting";
            getStatement().executeUpdate(initDbStr);
            //Create Suppliers Table
            initDbStr = "CREATE TABLE suppliers_list " + "(SupplierID INT NOT NULL, " + " SupplierName VARCHAR(50) NOT NULL, " +  "PRIMARY KEY(SupplierID))";
            getStatement().executeUpdate(initDbStr);
            //Create Income Table
            initDbStr = "CREATE TABLE income_list " + "(IncomeID INT NOT NULL," + "IncomeDate DATE NOT NULL, " + " Income FLOAT(8,2) NOT NULL, " +  "PRIMARY KEY(IncomeID))";
            getStatement().executeUpdate(initDbStr);
            //Create Expenses Table
            initDbStr = "CREATE TABLE expenses_list " + "(ExpenseID INT NOT NULL, " + " SupplierID INT NOT NULL, " + " ExpensesDate DATE NOT NULL, " + "Expenses FLOAT(8,2) NOT NULL," +
                    "Description VARCHAR(30)," + "PRIMARY KEY(ExpenseID),"+ "FOREIGN KEY(SupplierID) REFERENCES suppliers_list(SupplierID))";
            getStatement().executeUpdate(initDbStr);
        }catch(SQLException ex){
            //JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    public static void disconnectFromDatabase(){
        if(Statem != null){
            try{
                Statem.close();
            }catch(SQLException ex){System.out.println(ex);}
        }
        if(Conn != null){
            try{
                Conn.close();
            }catch(SQLException ex){System.out.println(ex);}
        }
    }
}
