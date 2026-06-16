# JDBC Money Transfer System using Oracle Database

## Overview

The JDBC Money Transfer System is a banking transaction application developed using Java, JDBC, and Oracle Database. This project demonstrates how to perform secure money transfers between bank accounts while maintaining data consistency using transaction management.

The application validates sender and receiver accounts, verifies account balance, authenticates the user through a PIN, and performs debit and credit operations within a single database transaction.

---

## Features

* Account validation
* Sender account verification
* Receiver account verification
* Sufficient balance check
* PIN authentication
* Debit operation from sender account
* Credit operation to receiver account
* Transaction management using Commit and Rollback
* Exception handling
* Oracle Database integration using JDBC
* Secure database operations using PreparedStatement

---

## Technologies Used

* Java
* JDBC
* Oracle Database 11g
* Eclipse IDE

---

## Database Schema

```sql
CREATE TABLE account (
    acc_no NUMBER PRIMARY KEY,
    acc_holdername VARCHAR2(50),
    balance NUMBER
);
```

### Sample Data

```sql
INSERT INTO account VALUES (12345, 'Tim', 7000);
INSERT INTO account VALUES (23456, 'Steve', 1000);
```

---

## Project Workflow

1. Load Oracle JDBC Driver
2. Establish Database Connection
3. Disable Auto Commit
4. Read User Input
5. Validate Transfer Amount
6. Verify Sender Account
7. Verify Receiver Account
8. Check Available Balance
9. Verify PIN
10. Debit Amount from Sender Account
11. Credit Amount to Receiver Account
12. Commit Transaction
13. Rollback Transaction on Failure
14. Close JDBC Resources

---

## Sample Execution

```text
Driver Loaded
Connection Established

Enter sender acc_no :
12345

Enter receiver acc_no :
23456

Enter amount :
500

Enter Pin :
123123

Money Transferred Successfully
```

---

## JDBC Concepts Demonstrated

* Driver Loading
* Database Connectivity
* PreparedStatement
* ResultSet
* Transactions
* Commit
* Rollback
* Exception Handling
* Resource Management

---

## Learning Outcomes

Through this project, I learned:

* JDBC Architecture
* Oracle Database Connectivity
* Transaction Management
* Banking Transaction Logic
* SQL Query Execution
* Database Security using PreparedStatement
* Real-world Database Operations

---

## Future Enhancements

* User Registration Module
* Account Creation
* Mini Statement Generation
* Transaction History
* Login Authentication
* GUI using Java Swing
* Web Version using Servlets and JSP

---

## Author

**ChinnaReddaiah**

 JDBC | Oracle SQL | Core Java

GitHub: https://github.com/chinnareddaiah22

