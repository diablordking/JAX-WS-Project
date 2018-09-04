/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.somecompany;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbConnection {

	public String message = "";
	public Connection connection;
	private java.sql.Statement statement;
	public PreparedStatement pstatement;
	public ResultSet rset;
	public CallableStatement cstmt;

	public Statement statCommitsiz;

	public dbConnection() {
	}

	public String getMessage() {
		return message;
	}

	public Statement getStatement() {
		return statement;
	}

	public java.sql.PreparedStatement preStatement(String sql) {
		try {
			pstatement = connection.prepareStatement(sql);
			return pstatement;
		} catch (Exception e) {
			return pstatement;
		}
	}

	public java.sql.Statement getStatCommitsiz() {
		try {
			statCommitsiz = connection.createStatement();
			return statCommitsiz;
		} catch (Exception e) {
			return statCommitsiz;
		}
	}

	public boolean getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			connection = DriverManager.getConnection("jdbc:mysql://localhost/webservice?user=root&password=toor&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"); 
			statement = connection.createStatement();

			return true;

		} catch (Exception e) {
			message = e.toString();
			System.err.println("JDBC error >> " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public void closeConnection() {
		try {
			if (rset != null)
				try {
					rset.close();
				} catch (Exception e) {
					System.err.println("__CONNECTION RESULTSET KAPATILAMADI: " + e.getMessage());
				}
			if (cstmt != null)
				try {
					cstmt.close();
				} catch (Exception e) {
					System.err.println("__CONNECTION CALLABLE STATEMENT KAPATILAMADI: " + e.getMessage());
				}
			if (statement != null)
				try {
					statement.close();
				} catch (Exception e) {
					System.err.println("__CONNECTION STATEMENT KAPATILAMADI: " + e.getMessage());
				}
			if (statCommitsiz != null)
				try {
					statCommitsiz.close();
				} catch (Exception e) {
					System.err.println("__CONNECTION COMMITSIZ STATEMENT KAPATILAMADI: " + e.getMessage());
				}
			if (pstatement != null)
				try {
					pstatement.close();
				} catch (Exception e) {
					System.err.println("__CONNECTION PREPARED STATEMENT KAPATILAMADI: " + e.getMessage());
				}
			if (connection != null)
				try {
					connection.close();
				} catch (Exception e) {
					System.err.println("__CONNECTION KAPATILAMADI: " + e.getMessage());
				}
		} catch (Exception e) {
			message = e.toString();
		}

	}

	@Override
	protected void finalize() throws Throwable {
		this.closeConnection();
	}

	public boolean setAutoCommit(boolean deger) {
		try {
			connection.setAutoCommit(deger);
			return true;

		} catch (Exception e) {
			message = e.toString();
			return false;

		}
	}

	public boolean commit() {
		try {
			connection.commit();
			return true;

		} catch (Exception e) {
			message = e.toString();
			return false;

		}
	}

	public boolean rollback() {
		try {
			connection.rollback();
			return true;

		} catch (Exception e) {
			message = e.toString();
			return false;

		}
	}
}