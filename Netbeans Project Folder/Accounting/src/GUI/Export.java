package GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import main.HandleDatabase;


public class Export extends javax.swing.JFrame {

    public Export() {
        initComponents();
        //Set Application Title
        this.setTitle("Export to CSV | Accounting Management");
        //Set Application to Screen Center
        this.setLocationRelativeTo(null);
        //Disable Window Resizing
        this.setResizable(false);
        //Handle App Closing from X Button
        this.addWindowListener(new WindowAdapter(){
           @Override
           public void windowClosing(WindowEvent e){
               HandleDatabase.disconnectFromDatabase();
                GUI.Export.this.dispose();
                System.exit(0);                
           }
        });
    }
    
    private String chooseDirectory(){
        JFileChooser chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.showOpenDialog(null);
        return chooser.getSelectedFile().toString();
    }
    
    private String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    private String convocateName(String dir, String list, String date){
       return new StringBuilder().append(dir).append("\\").append(list).append("(").append(date).append(").csv").toString();
    }
    
    private void printSuppliers(){
        try {
            File expFile = new File(convocateName(chooseDirectory(),"Suppliers List", getDate()));
            PrintWriter printWri= new PrintWriter(expFile);
            StringBuilder strBld = new StringBuilder();
            String exp="SELECT * FROM suppliers_list";
            PreparedStatement stmt = HandleDatabase.getConnection().prepareStatement(exp);
            ResultSet rs = stmt.executeQuery();
            strBld.append("sep=,\r\n");
            strBld.append("Suppplier Id,Suppplier Name\r\n\n");
            while(rs.next()){
                strBld.append(rs.getString("SupplierId"));
                strBld.append(","); 
                strBld.append(rs.getString("SupplierName"));
                strBld.append("\r\n");
            }
            strBld.append("\n, ,powered by ©Accounting Management\r\n");
            stmt.close();
            rs.close();
            printWri.write(strBld.toString());
            printWri.close();
        }catch(IOException | SQLException e){
            JOptionPane.showMessageDialog(null,"Action can't be completed (Error Code: 4)","Error",JOptionPane.ERROR_MESSAGE);
        }catch(NullPointerException e){}
    }
    
    private void printIncome(){
        try {
            File expFile = new File(convocateName(chooseDirectory(),"Income List", getDate()));
            PrintWriter printWri= new PrintWriter(expFile);
            StringBuilder strBld = new StringBuilder();
            String exp="SELECT * FROM income_list";
            PreparedStatement stmt = HandleDatabase.getConnection().prepareStatement(exp);
            ResultSet rs = stmt.executeQuery();
            strBld.append("sep=,\r\n");
            strBld.append("Income Id,Income Date, Total Income\r\n\n");
            while(rs.next()){
                strBld.append(rs.getString("IncomeId"));
                strBld.append(","); 
                strBld.append(rs.getString("IncomeDate"));
                strBld.append(","); 
                strBld.append(rs.getString("Income"));
                strBld.append("\r\n");
            }
            strBld.append("\n, , ,powered by ©Accounting Management\r\n");
            stmt.close();
            rs.close();
            printWri.write(strBld.toString());
            printWri.close();
        }catch(IOException | SQLException e){
            JOptionPane.showMessageDialog(null,"Action can't be completed (Error Code: 4)","Error",JOptionPane.ERROR_MESSAGE);
        }catch(NullPointerException e){}
    }
    
    private void printExpenses(){
        try {
            File expFile = new File(convocateName(chooseDirectory(),"Expenses List", getDate()));
            PrintWriter printWri= new PrintWriter(expFile);
            StringBuilder strBld = new StringBuilder();
            String expe="SELECT * FROM expenses_list";
            String exps="SELECT * FROM suppliers_list";
            PreparedStatement stmte = HandleDatabase.getConnection().prepareStatement(expe);
            PreparedStatement stmts = HandleDatabase.getConnection().prepareStatement(exps);
            ResultSet rse = stmte.executeQuery();
            ResultSet rss = stmts.executeQuery();
            strBld.append("sep=,\r\n");
            strBld.append("Expense Id, Supplier ID, Expenses Date, Expenses, Description, , Supplier ID, Supplier Name\r\n\n");
            while(rse.next()){
                strBld.append(rse.getString("ExpenseId"));
                strBld.append(","); 
                strBld.append(rse.getString("SupplierId"));
                strBld.append(","); 
                strBld.append(rse.getString("ExpensesDate"));
                strBld.append(","); 
                strBld.append(rse.getString("Expenses"));
                strBld.append(","); 
                strBld.append(rse.getString("Description"));
                while(rss.next()){
                    strBld.append(", ,"); 
                    strBld.append(rss.getString("SupplierId"));
                    strBld.append(","); 
                    strBld.append(rss.getString("SupplierName"));
                    break;
                }
                strBld.append("\r\n");
            }
            strBld.append("\n, , , , , , , ,powered by ©Accounting Management\r\n");
            stmte.close();
            stmts.close();
            rse.close();
            rss.close();
            printWri.write(strBld.toString());
            printWri.close();
        }catch(IOException | SQLException e){
            JOptionPane.showMessageDialog(null,"Action can't be completed (Error Code: 4)","Error",JOptionPane.ERROR_MESSAGE);
        }catch(NullPointerException e){}
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        exportComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        returnButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        exportComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        exportComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a list...", "Suppliers List", "Income List", "Expenses List" }));
        exportComboBox.setAutoscrolls(true);
        exportComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Select list to be exported to CSV file:");

        returnButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/return15px.png"))); // NOI18N
        returnButton.setText("Return");
        returnButton.setToolTipText("");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exportComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(exportComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(returnButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        this.dispose();
        new GUI.MainMenu().setVisible(true);
    }//GEN-LAST:event_returnButtonActionPerformed

    private void exportComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportComboBoxActionPerformed
        JComboBox comboBox = (JComboBox) evt.getSource();
        Object selected = comboBox.getSelectedItem();
        if(selected.toString().equals("Suppliers List")){
            printSuppliers();
        }else if(selected.toString().equals("Income List")){
            printIncome();
        }else if(selected.toString().equals("Expenses List")){
            printExpenses();
        }
    }//GEN-LAST:event_exportComboBoxActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Export.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Export.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Export.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Export.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Export().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> exportComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton returnButton;
    // End of variables declaration//GEN-END:variables

}
