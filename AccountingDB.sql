DROP DATABASE IF EXISTS accounting;
CREATE DATABASE accounting;
USE accounting;

CREATE TABLE suppliers_list(SupplierID INT NOT NULL, SupplierName VARCHAR(50) NOT NULL, PRIMARY KEY(SupplierID));
CREATE TABLE expenses_list(ExpenseID INT NOT NULL, SupplierID INT NOT NULL, ExpensesDate DATE NOT NULL, Expenses FLOAT(8,2) NOT NULL,Description VARCHAR(30),PRIMARY KEY(ExpenseID), FOREIGN KEY(SupplierID) REFERENCES suppliers_list(SupplierID));
CREATE TABLE income_list(IncomeID INT NOT NULL, IncomeDate DATE NOT NULL, Income FLOAT(8,2) NOT NULL, PRIMARY KEY(IncomeID));

SHOW TABLES;
SELECT * FROM income_list;
SELECT * FROM expenses_list;
SELECT * FROM suppliers_list;