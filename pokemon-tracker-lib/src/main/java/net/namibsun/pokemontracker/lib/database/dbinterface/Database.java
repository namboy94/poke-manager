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

package net.namibsun.pokemontracker.lib.database.dbinterface;

import java.sql.SQLException;

/**
 * Interface that provides an abstraction layer to use different Database engines
 */
public interface Database {

    /**
     * Executes an SQL statement
     * @param sqlStatement: The statement to execute
     * @param arguments:    Arguments that will be inserted into '?' characters in the statement.
     *                      This reduces the risk of SQL injection
     * @throws SQLException if an SQL Error occurred
     */
    void executeSql(String sqlStatement, Object[] arguments) throws SQLException;

    /**
     * Creates a new table in the database
     * @param tableName:    The name of the table
     * @param columns:      The columns of the table
     * @throws SQLException if an SQL Error occurred
     */
    void createTable(String tableName, DatabaseColumn[] columns) throws SQLException;

    /**
     * Inserts an element into the database
     * @param tableName: The table to insert the element into.
     * @param data:      An array of data for the new database entry
     * @throws SQLException if an SQL Error occurred
     */
    void insert(String tableName, Object[] data) throws SQLException;

    /**
     * Inserts an element into the database
     * @param tableName: The table to insert the element into.
     * @param order:     An array of columns, defining the order in which to insert the data elements
     * @param data:      An array of data for the new database entry
     * @throws SQLException if an SQL Error occurred
     */
    void insert(String tableName, DatabaseColumn[] order, Object[] data) throws SQLException;

    /**
     * Executes an SQL query and returns the result as a QueryResult object
     * @param sqlStatement: The query to execute
     * @param arguments:    Arguments that will be inserted into '?' characters in the statement.
     *                      This reduces the risk of SQL injection
     * @throws SQLException if an SQL Error occurred
     */
    QueryResult query(String sqlStatement, Object[] arguments) throws SQLException;

    /**
     * Commits the changes to the database
     * @throws SQLException if an SQL Error occurred
     */
    void commitChanges() throws SQLException;

    /**
     * Closes the database connection
     * @throws SQLException if an SQL Error occurred
     */
    void close() throws SQLException;

}
