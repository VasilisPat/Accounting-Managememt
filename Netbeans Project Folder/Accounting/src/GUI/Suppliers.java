package GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import main.HandleDatabase;

public class Suppliers extends javax.swing.JFrame {
    
    public Suppliers() {
        initComponents();
        //Set Application Title
        this.setTitle("Suppliers List | Accounting Management");
        //Set Application to Screen Center
        this.setLocationRelativeTo(null);
        //Set Default Button for Enter Action
        this.getRootPane().setDefaultButton(addEntryButton);
        //Refresh Table
        viewSuppliersTable();
        //Disable Window Resizing
        this.setResizable(false);
        //Handle App Closing from X Button
        this.addWindowListener(new WindowAdapter(){
           @Override
           public void windowClosing(WindowEvent e){
               HandleDatabase.disconnectFromDatabase();
                GUI.Suppliers.this.dispose();
                System.exit(0);                
           }
        });
    }
    
    private void viewSuppliersTable(){
        try{
            ResultSet rs = HandleDatabase.getStatement().executeQuery("SELECT * FROM suppliers_list"); 
            suppliersTable.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Database Not Found (Error Code: 2)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void viewSuppliersSearchTable(String query){
        try{
            ResultSet rs = HandleDatabase.getStatement().executeQuery(query); 
            suppliersTable.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Action can't be completed (Error Code: 4)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void setTextDefaults(){
        supplierNameField.setText("(Supplier Name)");
        searchSupplierField.setText("");
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addEntryButton = new javax.swing.JButton();
        supplierNamLabel = new javax.swing.JLabel();
        supplierNameField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        suppliersTable = new javax.swing.JTable();
        returnButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        searchSupplierLabel = new javax.swing.JLabel();
        searchSupplierField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        suppliersListLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        addEntryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add15px.png"))); // NOI18N
        addEntryButton.setText("Add");
        addEntryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEntryButtonActionPerformed(evt);
            }
        });

        supplierNamLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        supplierNamLabel.setText("Supplier Name:");

        supplierNameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        supplierNameField.setText("(Supplier Name)");
        supplierNameField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supplierNameFieldMouseClicked(evt);
            }
        });

        suppliersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "SupplierID", "SupplierName"
            }
        ));
        suppliersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suppliersTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(suppliersTable);

        returnButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/return15px.png"))); // NOI18N
        returnButton.setText("Return");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete15px.png"))); // NOI18N
        deleteButton.setText("Delete");
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

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        searchSupplierLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        searchSupplierLabel.setText("Search by Name:");

        searchSupplierField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search15px.png"))); // NOI18N
        searchButton.setText("Search");
        searchButton.setPreferredSize(new java.awt.Dimension(87, 25));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        resetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reset15px.png"))); // NOI18N
        resetButton.setText("Reset");
        resetButton.setMaximumSize(new java.awt.Dimension(79, 30));
        resetButton.setMinimumSize(new java.awt.Dimension(79, 30));
        resetButton.setPreferredSize(new java.awt.Dimension(84, 25));
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(searchSupplierLabel)
                        .addGap(18, 18, 18)
                        .addComponent(searchSupplierField, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchSupplierField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchSupplierLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        suppliersListLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        suppliersListLabel.setText("Suppliers List");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(supplierNamLabel)
                                .addGap(18, 18, 18)
                                .addComponent(supplierNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(updateButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addEntryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(suppliersListLabel)
                .addGap(339, 339, 339))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(suppliersListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addEntryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplierNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplierNamLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void addEntryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEntryButtonActionPerformed
        int lastSupId;
        if(suppliersTable.getRowCount() == 0){
            lastSupId=1;
        }else{
            lastSupId = (Integer) suppliersTable.getValueAt(suppliersTable.getModel().getRowCount()-1, 0);
            lastSupId++;
        }
        try{
            String expr = String.format("INSERT INTO suppliers_list VALUES('"+lastSupId+"','"+supplierNameField.getText()+"')");
            HandleDatabase.getStatement().executeUpdate(expr);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"False Input (Error Code: 3)","Error",JOptionPane.ERROR_MESSAGE);
        }
        //Refresh Table
        viewSuppliersTable();
        //Reset Text Fields to Defaults  
        setTextDefaults();
    }//GEN-LAST:event_addEntryButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        try{
            int selectedRow = suppliersTable.getSelectedRow();
            String cell = suppliersTable.getValueAt(selectedRow,0).toString();
            String del = "DELETE FROM suppliers_list WHERE SupplierID = '" + cell + "'";
            PreparedStatement stmt = HandleDatabase.getConnection().prepareStatement(del);
            stmt.execute();
            stmt.close();
            //Refresh Table
            viewSuppliersTable();
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

    private void suppliersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suppliersTableMouseClicked
        int selectedRow = suppliersTable.getSelectedRow();
        supplierNameField.setText(suppliersTable.getValueAt(selectedRow, 1).toString());
    }//GEN-LAST:event_suppliersTableMouseClicked

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        int selectedRow = suppliersTable.getSelectedRow();
        String upd = "UPDATE suppliers_list SET SupplierName = '"+supplierNameField.getText()+"' WHERE SupplierID = '"+suppliersTable.getValueAt(selectedRow,0).toString()+"'";
        try{
            PreparedStatement stmt = HandleDatabase.getConnection().prepareStatement(upd);
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Action can't be completed (Error Code: 4)","Error",JOptionPane.ERROR_MESSAGE);
        }
        //Refresh Table
        viewSuppliersTable();
        //Reset Text Fields to Defaults 
        setTextDefaults();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void supplierNameFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplierNameFieldMouseClicked
        supplierNameField.setText("");
    }//GEN-LAST:event_supplierNameFieldMouseClicked

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String query = "SELECT * FROM suppliers_list WHERE SupplierName LIKE '%"+searchSupplierField.getText()+"%'";
        viewSuppliersSearchTable(query);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        searchSupplierField.setText("");
        viewSuppliersTable();
    }//GEN-LAST:event_resetButtonActionPerformed
    
    
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
            java.util.logging.Logger.getLogger(Suppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Suppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Suppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Suppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new Suppliers().setVisible(true);
            }   
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEntryButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton returnButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchSupplierField;
    private javax.swing.JLabel searchSupplierLabel;
    private javax.swing.JLabel supplierNamLabel;
    private javax.swing.JTextField supplierNameField;
    private javax.swing.JLabel suppliersListLabel;
    private javax.swing.JTable suppliersTable;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
