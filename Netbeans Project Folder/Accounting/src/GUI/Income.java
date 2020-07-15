package GUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import main.HandleDatabase;

public class Income extends javax.swing.JFrame {
    
    
    public Income() {
        initComponents();
        //Set Application Title
        this.setTitle("Income List | Accounting Management");
        //Set Application to Screen Center
        this.setLocationRelativeTo(null);
        //Set Default Button for Enter Action
        this.getRootPane().setDefaultButton(addEntryButton);
        //Refresh Table
        viewIncomeTable();
        //Refresh Total Month Income
        totalMonthIncome();
        //Set Date Choosers Apperance
        setDateChoosersView();
        //Set Text Fields to Defaults
        setTextDefaults();
        //Disable Window Resizing
        this.setResizable(false);
        //Handle App Closing from X Button
        this.addWindowListener(new WindowAdapter(){
           @Override
           public void windowClosing(WindowEvent e){
               HandleDatabase.disconnectFromDatabase();
                GUI.Income.this.dispose();
                System.exit(0);                
           }
        });
    }
      
    private void viewIncomeTable(){
        try{
            ResultSet rs = HandleDatabase.getStatement().executeQuery("SELECT * FROM income_list"); 
            incomeTable.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Database Not Found (Error Code: 2)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void viewIncomeSearchTable(String query){
        try{
            ResultSet rs = HandleDatabase.getStatement().executeQuery(query); 
            incomeTable.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Action can't be completed (Error Code: 4)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setTextDefaults(){
        incomeDateChooser.setDate(null);
        searchDateChooser.setDate(null);
        incomeFormattedTextField.setValue(new Float(0));
    }
    
    private void totalMonthIncome(){
        String[] monthName = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        Calendar cal = Calendar.getInstance();
        String month = monthName[cal.get(Calendar.MONTH)];
        Integer sumMonth = (cal.get(Calendar.MONTH)+1);
        Integer endMonth = 30;
        if(month.equals("January") || month.equals("March") || month.equals("May") || month.equals("July") || month.equals("August") || month.equals("October") || month.equals("December")){
            endMonth = 31;
        }else if(month.equals("February")){
            endMonth = 28;
        }
        totalMonthIncomeField.setEditable(false);
        totalMonthIncomeLabel.setText("Total Income of Month " + month + ":");
        try{
            String query = ("SELECT SUM(Income) FROM income_list WHERE IncomeDate BETWEEN CAST('2020-"+sumMonth+"-01' AS DATE) AND CAST('2020-"+sumMonth+"-"+endMonth+"' AS DATE)");
            ResultSet rs = HandleDatabase.getStatement().executeQuery(query); 
            rs.next();
            totalMonthIncomeField.setText(rs.getString(1));
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Database Not Found (Error Code: 2)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void setDateChoosersView(){
        JTextFieldDateEditor chooser = (JTextFieldDateEditor) incomeDateChooser.getDateEditor();
        chooser.setEditable(false);
        incomeDateChooser.setDateFormatString("yyyy/MM/dd");
        JTextFieldDateEditor chooserS = (JTextFieldDateEditor) searchDateChooser.getDateEditor();
        chooserS.setEditable(false);
        searchDateChooser.setDateFormatString("yyyy/MM/dd");
    }
    
    private String getDateChooserValue(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        return date.format(incomeDateChooser.getDate());
    }
    
    private String getSearchDateChooserValue(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        return date.format(searchDateChooser.getDate());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addEntryButton = new javax.swing.JButton();
        incomeDateLabel = new javax.swing.JLabel();
        dayIncomeLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        incomeTable = new javax.swing.JTable();
        returnButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        totalMonthIncomeLabel = new javax.swing.JLabel();
        totalMonthIncomeField = new javax.swing.JTextField();
        incomeDateChooser = new com.toedter.calendar.JDateChooser();
        incomeFormattedTextField = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        searchDateLabel = new javax.swing.JLabel();
        resetButton = new javax.swing.JButton();
        searchButyon = new javax.swing.JButton();
        searchDateChooser = new com.toedter.calendar.JDateChooser();
        incomeListLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        addEntryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add15px.png"))); // NOI18N
        addEntryButton.setText("Add");
        addEntryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEntryButtonActionPerformed(evt);
            }
        });

        incomeDateLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        incomeDateLabel.setText("Income Date:");

        dayIncomeLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        dayIncomeLabel.setText("Day's Income:");

        incomeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Income ID", "Income Date", "Total Income"
            }
        ));
        incomeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                incomeTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(incomeTable);

        returnButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/return15px.png"))); // NOI18N
        returnButton.setText("Return");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete15px.png"))); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.setMaximumSize(new java.awt.Dimension(87, 30));
        deleteButton.setMinimumSize(new java.awt.Dimension(87, 30));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update15px.png"))); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        totalMonthIncomeLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        totalMonthIncomeLabel.setText("Total Income of Month September:");

        totalMonthIncomeField.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        incomeDateChooser.setDateFormatString("yyyy/MM/dd");
        incomeDateChooser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        incomeFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        incomeFormattedTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        incomeFormattedTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                incomeFormattedTextFieldMouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        searchDateLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        searchDateLabel.setText("Search by Date:");

        resetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reset15px.png"))); // NOI18N
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        searchButyon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search15px.png"))); // NOI18N
        searchButyon.setText("Search");
        searchButyon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButyonActionPerformed(evt);
            }
        });

        searchDateChooser.setDateFormatString("yyyy/MM/dd");
        searchDateChooser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(searchDateLabel)
                        .addGap(18, 18, 18)
                        .addComponent(searchDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchButyon, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchDateLabel)
                    .addComponent(searchDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchButyon))
                .addContainerGap())
        );

        incomeListLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        incomeListLabel.setText("Income List");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(totalMonthIncomeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalMonthIncomeField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dayIncomeLabel)
                                    .addComponent(incomeDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(incomeFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(incomeDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(returnButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(addEntryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(incomeListLabel)
                .addGap(336, 336, 336))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(incomeListLabel)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalMonthIncomeLabel)
                            .addComponent(totalMonthIncomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(addEntryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(incomeDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(incomeDateLabel))
                                .addGap(15, 15, 15)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dayIncomeLabel)
                            .addComponent(incomeFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addEntryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEntryButtonActionPerformed
        int lastIncId;
        if(incomeTable.getRowCount() == 0){
            lastIncId=1;
        }else{
            lastIncId = (Integer) incomeTable.getValueAt(incomeTable.getModel().getRowCount()-1, 0);
            lastIncId++;
        }
        try{
            String expr = String.format("INSERT INTO income_list VALUES('"+lastIncId+"','"+getDateChooserValue()+"','"+incomeFormattedTextField.getValue()+"')");
            HandleDatabase.getStatement().executeUpdate(expr);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"False Input (Error Code: 3)","Error",JOptionPane.ERROR_MESSAGE);
        }
        //Refresh Table
        viewIncomeTable();
        //Refresh Total Month Income
        totalMonthIncome();
        //Reset Text Fields to Defaults 
        setTextDefaults();
    }//GEN-LAST:event_addEntryButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        try{
            int selectedRow = incomeTable.getSelectedRow();
            String cell = incomeTable.getValueAt(selectedRow,0).toString();
            String del = "DELETE FROM income_list WHERE IncomeID = '" + cell + "'";
            PreparedStatement stmt = HandleDatabase.getConnection().prepareStatement(del);
            stmt.execute();
            //Refresh table;
            viewIncomeTable();
            //Refresh Total Month Income
            totalMonthIncome();
            //Reset Text Fields to Defaults 
            setTextDefaults();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Action can't be completed (Error Code: 4)","Error",JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Action can't be completed (Error Code: 4)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        this.dispose();
        new GUI.MainMenu().setVisible(true);
    }//GEN-LAST:event_returnButtonActionPerformed

    private void incomeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_incomeTableMouseClicked
        int selectedRow = incomeTable.getSelectedRow();
        incomeDateChooser.setDate((Date)incomeTable.getValueAt(selectedRow,1));
        incomeFormattedTextField.setText(incomeTable.getValueAt(selectedRow, 2).toString());
    }//GEN-LAST:event_incomeTableMouseClicked

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        int selectedRow = incomeTable.getSelectedRow();
        String upd = "UPDATE income_list SET IncomeDate = '"+getDateChooserValue()+"', Income = '"+incomeFormattedTextField.getValue()+"' WHERE IncomeID = '"+incomeTable.getValueAt(selectedRow,0).toString()+"'";
        try{
            PreparedStatement stmt = HandleDatabase.getConnection().prepareStatement(upd);
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Action can't be completed (Error Code: 4)","Error",JOptionPane.ERROR_MESSAGE);
        }
        //Refresh Table
        viewIncomeTable();
        //Refresh Total Month Income
        totalMonthIncome();
        //Reset Text Fields to Defaults 
        setTextDefaults();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void incomeFormattedTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_incomeFormattedTextFieldMouseClicked
        incomeFormattedTextField.setText("");
    }//GEN-LAST:event_incomeFormattedTextFieldMouseClicked

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        searchDateChooser.setDate(null);
        viewIncomeTable();
        totalMonthIncome();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void searchButyonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButyonActionPerformed
        totalMonthIncomeField.setText("");
        String query ="SELECT * FROM income_list WHERE IncomeDate = CAST('"+getSearchDateChooserValue()+"' AS DATE)";
        viewIncomeSearchTable(query);
    }//GEN-LAST:event_searchButyonActionPerformed
    
    
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
            java.util.logging.Logger.getLogger(Income.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Income.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Income.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Income.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Income().setVisible(true);
            }   
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEntryButton;
    private javax.swing.JLabel dayIncomeLabel;
    private javax.swing.JButton deleteButton;
    private com.toedter.calendar.JDateChooser incomeDateChooser;
    private javax.swing.JLabel incomeDateLabel;
    private javax.swing.JFormattedTextField incomeFormattedTextField;
    private javax.swing.JLabel incomeListLabel;
    private javax.swing.JTable incomeTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton returnButton;
    private javax.swing.JButton searchButyon;
    private com.toedter.calendar.JDateChooser searchDateChooser;
    private javax.swing.JLabel searchDateLabel;
    private javax.swing.JTextField totalMonthIncomeField;
    private javax.swing.JLabel totalMonthIncomeLabel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
