package main;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class DateConversion {
    
  private static SimpleDateFormat inSDF = new SimpleDateFormat("yyyy-mm-dd");
  private static SimpleDateFormat outSDF = new SimpleDateFormat("yyyy/mm/dd");

  public static String formatDate(String inDate) {
    String outDate = "";
    if(inDate != null) {
        try{
            Date date = inSDF.parse(inDate);
            outDate = outSDF.format(date);
        }catch (ParseException ex){
           JOptionPane.showMessageDialog(null,"Database Not Found (Error Code: 4)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    try{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");
        Date retDate = formatter.parse(outDate);
    }catch(ParseException ex){
        JOptionPane.showMessageDialog(null,"Database Not Found (Error Code: 4)","Error",JOptionPane.ERROR_MESSAGE);
    }
    return outDate;
  }
   
}
