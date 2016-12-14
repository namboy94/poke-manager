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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import net.namibsun.pokemontracker.lib.database.dbinterface.QueryResult;

/**
 * Class that wraps around an SQL ResultSet to supply an interface that is interchangeable with other
 * Database Implementations
 */
public class SQLiteQueryResult extends QueryResult {

    /**
     * The SQL statement that created this Query Result
     */
    private PreparedStatement statement;

    /**
     * Creates a new SQLiteQueryResult object
     * Stores the database and query as private variables
     * @param database:     The database to use
     * @param query:        The SQL query to execute
     * @throws SQLException If an SQL Error occurs
     */
    public SQLiteQueryResult(Connection database, String query, String[] arguments) throws SQLException {

        this.statement = database.prepareStatement(query);
        for (int i = 0; i < arguments.length; i++) {
            this.statement.setString(i + 1, arguments[i]);
        }
    }

    /**
     * @return The amount of rows in the query result
     */
    public int getQueryLength() throws SQLException {

        int length = 0;
        ResultSet set = this.statement.executeQuery();
        while(set.next()) {
            length++;
        }
        return length;
    }

    /**
     * Moves the cursor of the result set to a specified position and returns it
     * This is done like this because JDBC in conjunction with SQLite currently does
     * not support traversing a ResultSet bidirectionally
     * @param index:        The row index to move to.
     * @return              The ResultSet moved to the specified index
     * @throws SQLException if an SQL error occurred
     */
    private ResultSet getRowResultSet(int index) throws SQLException {

        ResultSet set = this.statement.executeQuery();
        for (int i = 0; i <= index; i++) {
            set.next();
        }
        return set;
    }

    /**
     * Fetches a String value from a specified column and row
     * @param column_name: The column's name
     * @param rowNumber:   The row's index
     * @return             The String value in that location
     */
    public String getString(String column_name, int rowNumber) throws SQLException {
        return this.getRowResultSet(rowNumber).getString(column_name);
    }

    /**
     * Fetches a String value from a specified column and row
     * @param column_number: The column's index
     * @param rowNumber:     The row's index
     * @return               The String value in that location
     */
    public String getString(int column_number, int rowNumber) throws SQLException {
        return this.getRowResultSet(rowNumber).getString(column_number + 1);
    }

    /**
     * Fetches an Integer value from a specified column and row
     * @param column_name: The column's name
     * @param rowNumber:   The row's index
     * @return             The int value in that location
     */
    public int getInt(String column_name, int rowNumber) throws SQLException {
        return this.getRowResultSet(rowNumber).getInt(column_name);
    }

    /**
     * Fetches an Integer value from a specified column and row
     * @param column_number: The column's index
     * @param rowNumber:     The row's index
     * @return               The int value in that location
     */
    public int getInt(int column_number, int rowNumber) throws SQLException {
        return this.getRowResultSet(rowNumber).getInt(column_number + 1);
    }

    /**
     * Fetches an Double value from a specified column and row
     * @param column_name: The column's name
     * @param rowNumber:   The row's index
     * @return             The double value in that location
     */
    public double getDouble(String column_name, int rowNumber) throws SQLException {
        return this.getRowResultSet(rowNumber).getDouble(column_name);
    }

    /**
     * Fetches an Double value from a specified column and row
     * @param column_number: The column's index
     * @param rowNumber:     The row's index
     * @return               The double value in that location
     */
    public double getDouble(int column_number, int rowNumber) throws SQLException {
        return this.getRowResultSet(rowNumber).getDouble(column_number + 1);
    }

    /**
     * Fetches an Boolean value from a specified column and row
     * @param column_name: The column's name
     * @param rowNumber:   The row's index
     * @return             The boolean value in that location
     */
    public boolean getBoolean(String column_name, int rowNumber) throws SQLException {
        return this.getRowResultSet(rowNumber).getBoolean(column_name);
    }

    /**
     * Fetches an Boolean value from a specified column and row
     * @param column_number: The column's index
     * @param rowNumber:     The row's index
     * @return               The boolean value in that location
     */
    public boolean getBoolean(int column_number, int rowNumber) throws SQLException {
        return this.getRowResultSet(rowNumber).getBoolean(column_number + 1);
    }
}
