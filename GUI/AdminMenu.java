package GUI;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import main.HandleDatabase;
import net.proteanit.sql.DbUtils;

public class AdminMenu extends javax.swing.JFrame {

    public AdminMenu() {
        initComponents();
        //Set Application Title
        this.setTitle("Admin Menu | Accounting Management");
        //Set Application to Screen Center
        this.setLocationRelativeTo(null);
        //Refresh Table
        viewUsersTable();
        //Disable Window Resizing
        this.setResizable(false);
        //Handle App Closing from X Button
        this.addWindowListener(new WindowAdapter(){
           @Override
           public void windowClosing(WindowEvent e){
               HandleDatabase.disconnectFromDatabase();
                GUI.AdminMenu.this.dispose();
                System.exit(0);                
           }
        });
    }
    
    private void viewUsersTable(){
        try{
            ResultSet rs = HandleDatabase.getStatement().executeQuery("SELECT user, host FROM mysql.user;"); 
            usersTable.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void setTextDefaults(){
        userNameTextField.setText("");
        passwordTextField.setText("");
        hostTextField.setText("localhost");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        addUserButton = new javax.swing.JButton();
        deleteUserButton = new javax.swing.JButton();
        returnButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        userNameTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JTextField();
        hostLabel = new javax.swing.JLabel();
        hostTextField = new javax.swing.JTextField();
        createCheckBox = new javax.swing.JCheckBox();
        selectCheckBox = new javax.swing.JCheckBox();
        dropCheckBox = new javax.swing.JCheckBox();
        insertCheckBox = new javax.swing.JCheckBox();
        deleteCheckBox = new javax.swing.JCheckBox();
        updateCheckBox = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        grantsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Administrator Menu");

        usersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Users", "Host"
            }
        ));
        jScrollPane1.setViewportView(usersTable);

        addUserButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add15px.png"))); // NOI18N
        addUserButton.setText("Add");
        addUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserButtonActionPerformed(evt);
            }
        });

        deleteUserButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete15px.png"))); // NOI18N
        deleteUserButton.setText("Delete");
        deleteUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserButtonActionPerformed(evt);
            }
        });

        returnButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/return15px.png"))); // NOI18N
        returnButton.setText("Return");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel2.setText("User Name:");

        userNameTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel3.setText("Password:");

        passwordTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        hostLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        hostLabel.setText("Host:");

        hostTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        hostTextField.setText("localhost");

        createCheckBox.setBackground(new java.awt.Color(204, 204, 255));
        createCheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        createCheckBox.setText("Create");

        selectCheckBox.setBackground(new java.awt.Color(204, 204, 255));
        selectCheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectCheckBox.setText("Select");

        dropCheckBox.setBackground(new java.awt.Color(204, 204, 255));
        dropCheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dropCheckBox.setText("Drop");

        insertCheckBox.setBackground(new java.awt.Color(204, 204, 255));
        insertCheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        insertCheckBox.setText("Insert");

        deleteCheckBox.setBackground(new java.awt.Color(204, 204, 255));
        deleteCheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deleteCheckBox.setText("Delete");

        updateCheckBox.setBackground(new java.awt.Color(204, 204, 255));
        updateCheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        updateCheckBox.setText("Update");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel4.setText("Privileges:");

        grantsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/grants15px.png"))); // NOI18N
        grantsButton.setText("Grants");
        grantsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grantsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hostLabel)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(deleteUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(grantsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(12, 12, 12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(deleteCheckBox)
                                    .addComponent(insertCheckBox))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(returnButton, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(addUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createCheckBox)
                            .addComponent(dropCheckBox))
                        .addGap(124, 124, 124)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectCheckBox)
                            .addComponent(updateCheckBox))))
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(261, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(227, 227, 227))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hostLabel)
                            .addComponent(hostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(createCheckBox)
                            .addComponent(insertCheckBox)
                            .addComponent(selectCheckBox))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateCheckBox)
                            .addComponent(deleteCheckBox)
                            .addComponent(dropCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addUserButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(deleteUserButton, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(returnButton)
                            .addComponent(grantsButton))))
                .addContainerGap(16, Short.MAX_VALUE))
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

    private void addUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserButtonActionPerformed
        String privileges = "";
        StringBuilder tempPrivileges = new StringBuilder();
        if(createCheckBox.isSelected()){
            tempPrivileges.append("CREATE,");
        }
        if(dropCheckBox.isSelected()){
            tempPrivileges.append("DROP,");
        }
        if(insertCheckBox.isSelected()){
            tempPrivileges.append("INSERT,");
        }
        if(deleteCheckBox.isSelected()){
            tempPrivileges.append("DELETE,");
        }
        if(selectCheckBox.isSelected()){
            tempPrivileges.append("SELECT,");
        }
        if(updateCheckBox.isSelected()){
            tempPrivileges.append("UPDATE,");
        }
        if(",".equals(tempPrivileges.substring(tempPrivileges.length() - 1))){
            privileges = tempPrivileges.substring(0,tempPrivileges.length() - 1);
        }
        if(!userNameTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty() && !hostTextField.getText().isEmpty() && !privileges.isEmpty()){
            try{
                String expr = "CREATE USER '"+userNameTextField.getText()+"'@'"+hostTextField.getText()+"' IDENTIFIED BY '"+passwordTextField.getText()+"';";
                HandleDatabase.getStatement().executeUpdate(expr);
                expr = "GRANT "+privileges+" ON accounting.* TO '"+userNameTextField.getText()+"'@'"+hostTextField.getText()+"';";
                System.out.println(expr);
                HandleDatabase.getStatement().executeUpdate(expr);
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
            }
            //Refresh Table
            viewUsersTable();
            //Reset Text Fields to Defaults  
            setTextDefaults();
        }else{
            if(userNameTextField.getText().isEmpty()){
                userNameTextField.setBorder(new LineBorder(Color.RED, 1));

            }
            if(passwordTextField.getText().isEmpty()){
                passwordTextField.setBorder(new LineBorder(Color.RED, 1));

            }
            if(hostTextField.getText().isEmpty()){
                hostTextField.setBorder(new LineBorder(Color.RED, 1));

            }
            if(privileges.isEmpty()){
                JOptionPane.showMessageDialog(null,"User needs to have at least one privilege!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_addUserButtonActionPerformed

    private void deleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserButtonActionPerformed
        try{
            int selectedRow = usersTable.getSelectedRow();
            String userName = usersTable.getValueAt(selectedRow,0).toString();
            String host = usersTable.getValueAt(selectedRow,1).toString();
            String expr = String.format("DROP USER '"+userName+"'@'"+host+"'");
            HandleDatabase.getStatement().executeUpdate(expr);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        //Refresh Table
        viewUsersTable();
        //Reset Text Fields to Defaults  
        setTextDefaults();
    }//GEN-LAST:event_deleteUserButtonActionPerformed

    private void grantsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grantsButtonActionPerformed
        StringBuilder resGrant = new StringBuilder();
        try{
            int selectedRow = usersTable.getSelectedRow();
            String userName = usersTable.getValueAt(selectedRow,0).toString();
            String host = usersTable.getValueAt(selectedRow,1).toString();
            ResultSet rs = HandleDatabase.getStatement().executeQuery("SHOW GRANTS FOR '"+userName+"'@'"+host+"'");
            while(rs.next()){
                resGrant.append(rs.getString(1)).append("\n");
            }
            if(!resGrant.toString().isEmpty()){
                JOptionPane.showMessageDialog(null,resGrant,"Grants of User: " + userName ,JOptionPane.INFORMATION_MESSAGE);
            }
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_grantsButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AdminMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addUserButton;
    private javax.swing.JCheckBox createCheckBox;
    private javax.swing.JCheckBox deleteCheckBox;
    private javax.swing.JButton deleteUserButton;
    private javax.swing.JCheckBox dropCheckBox;
    private javax.swing.JButton grantsButton;
    private javax.swing.JLabel hostLabel;
    private javax.swing.JTextField hostTextField;
    private javax.swing.JCheckBox insertCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JButton returnButton;
    private javax.swing.JCheckBox selectCheckBox;
    private javax.swing.JCheckBox updateCheckBox;
    private javax.swing.JTextField userNameTextField;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables
}
