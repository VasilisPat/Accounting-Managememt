package GUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import main.HandleDatabase;

public class Expenses extends javax.swing.JFrame {
    
    public Expenses() {
        initComponents();
        //Set Application Title
        this.setTitle("Expenses List | Accounting Management");
        //Set Application to Screen Center
        this.setLocationRelativeTo(null);
       //Refresh Tables
        viewTables();
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
                GUI.Expenses.this.dispose();
                System.exit(0);                
           }
        });
    }
    
    private void setTextDefaults(){
        searchDateChooser.setDate(null);
        searchDescriptionField.setText("");
        suppliersIDField.setText("(Supplier ID)");
        expensesDateChooser.setDate(null);
        expensesSumFormattedTextField.setValue(new Float(0));
        descriptionField.setText("");
    }
    
    private void viewTables(){
        //Populate Exepenses Table
        try{
            ResultSet rs = HandleDatabase.getStatement().executeQuery("SELECT * FROM expenses_list"); 
            expensesTable.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Database Not Found (Error Code: 2)","Error",JOptionPane.ERROR_MESSAGE);
        }
        expensesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //Populate Suppliers Table
        try{
            ResultSet rs = HandleDatabase.getStatement().executeQuery("SELECT * FROM suppliers_list"); 
            suppliersTable.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Database Not Found (Error Code: 2)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void viewSearchTable(String query){
        try{
            ResultSet rs = HandleDatabase.getStatement().executeQuery(query); 
            expensesTable.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Action can't be completed (Error Code: 4)","Error",JOptionPane.ERROR_MESSAGE);
        }
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
        totalMonthExpensesField.setEditable(false);
        totalMonthExpensesLabel.setText("Total Income of Month " + month + ":");
        try{
            String query = ("SELECT SUM(Expenses) FROM expenses_list WHERE ExpensesDate BETWEEN CAST('2020-"+sumMonth+"-01' AS DATE) AND CAST('2020-"+sumMonth+"-"+endMonth+"' AS DATE)");
            ResultSet rs = HandleDatabase.getStatement().executeQuery(query); 
            rs.next();
            totalMonthExpensesField.setText(rs.getString(1));
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Database Not Found (Error Code: 2)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void setDateChoosersView(){
        JTextFieldDateEditor chooser = (JTextFieldDateEditor) expensesDateChooser.getDateEditor();
        chooser.setEditable(false);
        expensesDateChooser.setDateFormatString("yyyy/MM/dd");
        JTextFieldDateEditor chooserS = (JTextFieldDateEditor) searchDateChooser.getDateEditor();
        chooserS.setEditable(false);
        searchDateChooser.setDateFormatString("yyyy/MM/dd");
    }
    
    private String getDateChooserValue(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        return date.format(expensesDateChooser.getDate());
    }
    
    private String getSearchDateChooserValue(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        try{
            return date.format(searchDateChooser.getDate());
        }catch(NullPointerException e){
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addEntryButton = new javax.swing.JButton();
        expensesDateLabel = new javax.swing.JLabel();
        expenseSumLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        expensesTable = new javax.swing.JTable();
        returnButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        supplierIdLabel = new javax.swing.JLabel();
        suppliersIDField = new javax.swing.JTextField();
        expensesListLabel = new javax.swing.JLabel();
        suppliersListLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        suppliersTable = new javax.swing.JTable();
        descriptionLabel = new javax.swing.JLabel();
        descriptionField = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();
        totalMonthExpensesLabel = new javax.swing.JLabel();
        totalMonthExpensesField = new javax.swing.JTextField();
        expensesDateChooser = new com.toedter.calendar.JDateChooser();
        expensesSumFormattedTextField = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        searchDateLabel = new javax.swing.JLabel();
        searchDescriptionLabel = new javax.swing.JLabel();
        searchDateChooser = new com.toedter.calendar.JDateChooser();
        searchDescriptionField = new javax.swing.JTextField();
        resetButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        addEntryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add15px.png"))); // NOI18N
        addEntryButton.setText("Add");
        addEntryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEntryButtonActionPerformed(evt);
            }
        });

        expensesDateLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        expensesDateLabel.setText("Expenses Date:");

        expenseSumLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        expenseSumLabel.setText("Expense Sum:");

        expensesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ExpenseID", "SuppliersID", "ExpensesDate", "Expenses", "Description"
            }
        ));
        expensesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                expensesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(expensesTable);
        if (expensesTable.getColumnModel().getColumnCount() > 0) {
            expensesTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

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

        supplierIdLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        supplierIdLabel.setText("Supplier ID:");

        suppliersIDField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        suppliersIDField.setText("Supplier ID");
        suppliersIDField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suppliersIDFieldMouseClicked(evt);
            }
        });

        expensesListLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        expensesListLabel.setText("Expenses List");

        suppliersListLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        suppliersListLabel.setText("Suppliers List");

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
        jScrollPane4.setViewportView(suppliersTable);

        descriptionLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        descriptionLabel.setText("Descrption:");

        descriptionField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descriptionField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descriptionFieldMouseClicked(evt);
            }
        });

        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update15px.png"))); // NOI18N
        updateButton.setText("Update");
        updateButton.setMaximumSize(new java.awt.Dimension(89, 25));
        updateButton.setMinimumSize(new java.awt.Dimension(89, 25));
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        totalMonthExpensesLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        totalMonthExpensesLabel.setText("Total Expenses of Month September:");

        totalMonthExpensesField.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        expensesDateChooser.setDateFormatString("yyyy/MM/dd");
        expensesDateChooser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        expensesSumFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        expensesSumFormattedTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        expensesSumFormattedTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                expensesSumFormattedTextFieldMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        searchDateLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        searchDateLabel.setText("Search by Date:");

        searchDescriptionLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        searchDescriptionLabel.setText("Search by Description:");

        searchDateChooser.setDateFormatString("yyyy/MM/dd");

        resetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reset15px.png"))); // NOI18N
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search15px.png"))); // NOI18N
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(searchDateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(searchDescriptionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchDescriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchDateLabel)
                    .addComponent(searchDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchDescriptionLabel)
                        .addComponent(searchDescriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(supplierIdLabel)
                                    .addComponent(expenseSumLabel))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(suppliersIDField, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                                    .addComponent(expensesSumFormattedTextField))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(expensesDateLabel)
                                    .addComponent(descriptionLabel))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(expensesDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(returnButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addEntryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(totalMonthExpensesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalMonthExpensesField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addComponent(expensesListLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(suppliersListLabel)
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expensesListLabel)
                    .addComponent(suppliersListLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(totalMonthExpensesLabel)
                                    .addComponent(totalMonthExpensesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(supplierIdLabel)
                                .addComponent(suppliersIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(addEntryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(expensesDateLabel))
                            .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(expensesDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionLabel)
                    .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expenseSumLabel)
                    .addComponent(expensesSumFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        int lastExpId;
        if(expensesTable.getRowCount() == 0){
            lastExpId=1;
        }else{
            lastExpId = (Integer) expensesTable.getValueAt(expensesTable.getModel().getRowCount()-1, 0);
            lastExpId++;
        }
        try{
            String expr = String.format("INSERT INTO expenses_list VALUES('"+lastExpId+"','"+Integer.parseInt(suppliersIDField.getText())+"','"+getDateChooserValue()+"','"+expensesSumFormattedTextField.getValue()+"','"+descriptionField.getText()+"')");
            HandleDatabase.getStatement().executeUpdate(expr);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"False Input (Error Code: 3)","Error",JOptionPane.ERROR_MESSAGE);
        }
        //Refresh Tables
        viewTables();
        //Refresh Total Month Income
        totalMonthIncome();
        //Reset Text Fields to Defaults 
        setTextDefaults();
    }//GEN-LAST:event_addEntryButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        try{
            int selectedRow = expensesTable.getSelectedRow();
            String cell = expensesTable.getValueAt(selectedRow,0).toString();
            String del = "DELETE FROM expenses_list WHERE ExpenseID = '" + cell + "'";
            PreparedStatement stmt = HandleDatabase.getConnection().prepareStatement(del);
            stmt.execute();
            stmt.close();
            //Refresh Table
            viewTables();
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

    private void expensesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expensesTableMouseClicked
        int selectedRow = expensesTable.getSelectedRow();
        suppliersIDField.setText(expensesTable.getValueAt(selectedRow, 1).toString());
        expensesDateChooser.setDate((Date)expensesTable.getValueAt(selectedRow,2));
        expensesSumFormattedTextField.setText(expensesTable.getValueAt(selectedRow, 3).toString());
        descriptionField.setText(expensesTable.getValueAt(selectedRow, 4).toString());
    }//GEN-LAST:event_expensesTableMouseClicked

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        int selectedRow = expensesTable.getSelectedRow();
        String upd = "UPDATE expenses_list SET SupplierID = '"+suppliersIDField.getText()+"', ExpensesDate = '"+getDateChooserValue()+"', Expenses = '"+expensesSumFormattedTextField.getValue()+"', Description = '"+descriptionField.getText()+"' WHERE ExpenseID = '"+expensesTable.getValueAt(selectedRow,0).toString()+"'";
        try{
            PreparedStatement stmt = HandleDatabase.getConnection().prepareStatement(upd);
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Action can't be completed (Error Code: 4)","Error",JOptionPane.ERROR_MESSAGE);
        }
        //Refresh Tables
        viewTables();
        //Refresh Total Month Income
        totalMonthIncome();
        //Reset Text Fields to Defaults 
        setTextDefaults();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void suppliersIDFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suppliersIDFieldMouseClicked
        suppliersIDField.setText("");
    }//GEN-LAST:event_suppliersIDFieldMouseClicked

    private void descriptionFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descriptionFieldMouseClicked
        descriptionField.setText("");
    }//GEN-LAST:event_descriptionFieldMouseClicked

    private void expensesSumFormattedTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expensesSumFormattedTextFieldMouseClicked
        expensesSumFormattedTextField.setText("");
    }//GEN-LAST:event_expensesSumFormattedTextFieldMouseClicked

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        searchDateChooser.setDate(null);
        searchDescriptionField.setText("");
        viewTables();
        totalMonthIncome();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String query;
        totalMonthExpensesField.setText("");
        if(getSearchDateChooserValue()==null){
            query = "SELECT * FROM expenses_list WHERE Description LIKE '%"+searchDescriptionField.getText()+"%'";
        }else if(searchDescriptionField.getText().equals(null)){
            query = "SELECT * FROM expenses_list WHERE ExpensesDate = CAST('"+getSearchDateChooserValue()+"' AS DATE)";
        }else
            query = "SELECT * FROM expenses_list WHERE ExpensesDate = CAST('"+getSearchDateChooserValue()+"' AS DATE) AND Description LIKE '%"+searchDescriptionField.getText()+"%'";
        viewSearchTable(query);
    }//GEN-LAST:event_searchButtonActionPerformed
    
    
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Income().setVisible(true);
            }   
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEntryButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField descriptionField;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JLabel expenseSumLabel;
    private com.toedter.calendar.JDateChooser expensesDateChooser;
    private javax.swing.JLabel expensesDateLabel;
    private javax.swing.JLabel expensesListLabel;
    private javax.swing.JFormattedTextField expensesSumFormattedTextField;
    private javax.swing.JTable expensesTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton returnButton;
    private javax.swing.JButton searchButton;
    private com.toedter.calendar.JDateChooser searchDateChooser;
    private javax.swing.JLabel searchDateLabel;
    private javax.swing.JTextField searchDescriptionField;
    private javax.swing.JLabel searchDescriptionLabel;
    private javax.swing.JLabel supplierIdLabel;
    private javax.swing.JTextField suppliersIDField;
    private javax.swing.JLabel suppliersListLabel;
    private javax.swing.JTable suppliersTable;
    private javax.swing.JTextField totalMonthExpensesField;
    private javax.swing.JLabel totalMonthExpensesLabel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
