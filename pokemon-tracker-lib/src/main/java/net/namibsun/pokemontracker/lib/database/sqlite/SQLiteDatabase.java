/*
This file is part of pokemon-tracker.

    pokemon-tracker is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    pokemon-tracker is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with pokemon-tracker.  If not, see <http://www.gnu.org/licenses/>.
*/

package net.namibsun.pokemontracker.lib.database.sqlite;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import net.namibsun.pokemontracker.lib.database.dbinterface.Database;
import net.namibsun.pokemontracker.lib.database.dbinterface.QueryResult;
import net.namibsun.pokemontracker.lib.database.dbinterface.DatabaseColumn;

/**
 * Class that implements the SQLite database.
 */
public class SQLiteDatabase implements Database {

    /**
     * The SQLite database connection
     */
    Connection connection;

    /**
     * Creates a new SQLiteDataBase object
     * @param databaseFile: The location of the database file
     * @throws SQLException if an SQL Error occurred
     */
    public SQLiteDatabase(String databaseFile) throws SQLException {

        String url = "jdbc:sqlite:" + databaseFile;
        this.connection = DriverManager.getConnection(url);
        this.connection.setAutoCommit(false);

    }

    /**
     * Executes an SQL statement
     * @param sqlStatement: The statement to execute
     * @param arguments:    Arguments that will be inserted into '?' characters in the statement.
     *                      This reduces the risk of SQL injection
     * @throws SQLException if an SQL Error occurred
     */
    public void executeSql(String sqlStatement, Object[] arguments) throws SQLException {

        if (arguments == null) {
            arguments = new String[] {};
        }

        PreparedStatement statement = this.connection.prepareStatement(sqlStatement);
        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i] != null) {
                statement.setString(i + 1, "" + arguments[i]);
            }
            else {
                statement.setString(i + 1, null);
            }
        }
        statement.execute();
    }

    /**
     * Creates a new table in the database
     * @param tableName:    The name of the table
     * @param columns:      The columns of the table
     * @throws SQLException if an SQL Error occurred
     */
    public void createTable(String tableName, DatabaseColumn[] columns) throws SQLException {

        String sql = "CREATE TABLE " + tableName + " (";
        for (int i = 0; i < columns.length; i++) {
            if (i != columns.length - 1) {
                sql += columns[i].getColumn(",");
            }
            else {
                sql += columns[i].getColumn(")");
            }
        }

        this.executeSql(sql, null);
    }

    /**
     * Inserts an element into the database
     * @param tableName: The table to insert the element into.
     * @param data:      An array of data for the new database entry
     * @throws SQLException if an SQL Error occurred
     */
    public void insert(String tableName, Object[] data) throws SQLException {

        String sql = "INSERT INTO " + tableName + " VALUES " + this.parameterize(data.length);
        this.executeSql(sql, data);
    }

    /**
     * Inserts an element into the database
     * @param tableName: The table to insert the element into.
     * @param order:     An array of columns, defining the order in which to insert the data elements
     * @param data:      An array of data for the new database entry
     * @throws SQLException if an SQL Error occurred
     */
    public void insert(String tableName, DatabaseColumn[] order, Object[] data) throws SQLException {

        if (order.length != data.length) {
            throw new SQLException("Inconsistent amount of columns and arguments");
        }

        String sql = "INSERT INTO " + tableName + " (";
        for (int i = 0; i < order.length; i++) {
            if (i != order.length - 1) {
                sql += order[i].getName(",");
            }
            else {
                sql += order[i].getName(") VALUES " + this.parameterize(data.length));
            }
        }

        this.executeSql(sql, data);

    }

    /**
     * Creates a Parameter string for insert statements
     * @param count: The amount of objects to parameterize
     * @return       The parameter string (?, ? ..., ?)
     */
    private String parameterize(int count) {

        String parameterized = "(";
        for (int i = 0; i < count; i++) {
            if (i != count - 1) {
                parameterized += "?,";
            }
            else {
                parameterized += "?)";
            }
        }
        return parameterized;
    }

    /**
     * Executes an SQL query and returns the result as a QueryResult object
     * @param sqlStatement: The query to execute
     * @param arguments:    Arguments that will be inserted into '?' characters in the statement.
     *                      This reduces the risk of SQL injection
     * @throws SQLException if an SQL Error occurred
     */
    @Override
    public QueryResult query(String sqlStatement, Object[] arguments) throws SQLException {

        if (arguments == null) {
            arguments = new String[] {};
        }

        return new SQLiteQueryResult(this.connection, sqlStatement, arguments);
    }

    /**
     * Commits the changes to the database
     * @throws SQLException if an SQL Error occurred
     */
    @Override
    public void commitChanges() throws SQLException {
        this.connection.commit();
    }

    /**
     * Closes the Database Connection
     * @throws SQLException if an SQL Error occurred
     */
    @Override
    public void close() throws SQLException {
        this.connection.close();
    }
}
