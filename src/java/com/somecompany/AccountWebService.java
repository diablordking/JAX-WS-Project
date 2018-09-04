package com.somecompany;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Clock;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "AccountWebService")
public class AccountWebService {

    static ResultSet resultSet = null;
    dbConnection dbConnect = new dbConnection();
    Account account = new Account();

    @WebMethod(operationName = "getAccountDetails")
    public Account getAccountDetails(@WebParam(name = "name") String customerName) throws SQLException {
        account = new Account();
        dbConnect.getConnection();
        String selectSQL = "SELECT * FROM webservice.account WHERE customername=?";
        PreparedStatement preparedStatement = dbConnect.preStatement(selectSQL);
        preparedStatement.setString(1, customerName);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int accountnumber = resultSet.getInt("accountnumber");
            int balance = resultSet.getInt("balance");
            int return1 = resultSet.getInt("return1");
            String customername = resultSet.getString("customername");
            account.setAccountNumber(accountnumber);
            account.setBalance(balance);
            account.setReturns(return1);
            account.setCustomerName(customerName);
        }
        dbConnect.closeConnection();
        return account;
    }

    @WebMethod(operationName = "addAccountDetails")
    public Account addAccountDetails(@WebParam(name = "accountnumber") int accountnumber1, @WebParam(name = "balance") int balance1, @WebParam(name = "return11") int return11, @WebParam(name = "customername1") String customername1) throws SQLException, error {

        account = new Account();
        dbConnect.getConnection();
        String selectSQL = "INSERT INTO account (accountnumber, balance, return1,customername)\n"
                + "VALUES (?, ?,?, ?);";
        PreparedStatement preparedStatement = dbConnect.preStatement(selectSQL);
        preparedStatement.setInt(1, accountnumber1);
        preparedStatement.setInt(2, balance1);
        preparedStatement.setInt(3, return11);
        preparedStatement.setString(4, customername1);
        try {
        int a = preparedStatement.executeUpdate();     
        } catch (Exception e) {
              throw new error();
        }
        
        account.setBalance(balance1);
        account.setReturns(return11);
        account.setCustomerName(customername1);
        dbConnect.closeConnection();
        return account;

    }

}
