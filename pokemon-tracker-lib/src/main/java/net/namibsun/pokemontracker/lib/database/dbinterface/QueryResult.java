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
 * Interface that wraps around the various implementations of SQL query results and provides a unified way to
 * access the results
 */
public abstract class QueryResult {

    /**
     * @return The amount of rows in the query result
     * @throws SQLException if an SQL Error occurred
     */
    public abstract int getQueryLength() throws SQLException ;

    /**
     * Fetches a String value from a specified column and row
     * @param column_name: The column's name
     * @param rowNumber:   The row's index
     * @return             The String value in that location
     * @throws SQLException if an SQL Error occurred
     */
    public abstract String getString(String column_name, int rowNumber) throws SQLException;

    /**
     * Fetches a String value from a specified column and row
     * @param column_number: The column's index
     * @param rowNumber:     The row's index
     * @return               The String value in that location
     * @throws SQLException if an SQL Error occurred
     */
    public abstract String getString(int column_number, int rowNumber) throws SQLException;

    /**
     * Fetches an Integer value from a specified column and row
     * @param column_name: The column's name
     * @param rowNumber:   The row's index
     * @return             The int value in that location
     * @throws SQLException if an SQL Error occurred
     */
    public abstract int getInt(String column_name, int rowNumber) throws SQLException;

    /**
     * Fetches an Integer value from a specified column and row
     * @param column_number: The column's index
     * @param rowNumber:     The row's index
     * @return               The int value in that location
     * @throws SQLException if an SQL Error occurred
     */
    public abstract int getInt(int column_number, int rowNumber) throws SQLException;

    /**
     * Fetches an Double value from a specified column and row
     * @param column_name: The column's name
     * @param rowNumber:   The row's index
     * @return             The double value in that location
     * @throws SQLException if an SQL Error occurred
     */

    public abstract double getDouble(String column_name, int rowNumber) throws SQLException;

    /**
     * Fetches an Double value from a specified column and row
     * @param column_number: The column's index
     * @param rowNumber:     The row's index
     * @return               The double value in that location
     * @throws SQLException if an SQL Error occurred
     */
    public abstract double getDouble(int column_number, int rowNumber) throws SQLException;

    /**
     * Fetches an Boolean value from a specified column and row
     * @param column_name: The column's name
     * @param rowNumber:   The row's index
     * @return             The boolean value in that location
     * @throws SQLException if an SQL Error occurred
     */
    public abstract boolean getBoolean(String column_name, int rowNumber) throws SQLException;

    /**
     * Fetches an Boolean value from a specified column and row
     * @param column_number: The column's index
     * @param rowNumber:     The row's index
     * @return               The boolean value in that location
     * @throws SQLException if an SQL Error occurred
     */
    public abstract boolean getBoolean(int column_number, int rowNumber) throws SQLException;

}
