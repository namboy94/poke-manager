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

import net.namibsun.pokemontracker.lib.database.dbinterface.QueryResult;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that wraps around an SQL ResultSet to give a uniform acces to its results
 */
public class SQLiteQueryResult extends QueryResult {

    /**
     * The ResultSet provided by the SQLite Database
     */
    private ResultSet queryResult;

    /**
     * Initializes the SQLite query result
     * @param queryResult: The query result to wrap around
     */
    public SQLiteQueryResult(ResultSet queryResult) {
        super(queryResult);
    }

    /**
     * @return The amount of rows in the query result
     */
    public int getQueryLength() {
        try {
            if (this.queryResult.isBeforeFirst()) {
                return 0;
            }
            else {
                this.queryResult.last();
                return this.queryResult.getRow() + 1;
            }
        } catch (SQLException | NullPointerException e) {
            return 0;
        }
    }

    /**
     * Fetches a String value from a specified column and row
     * @param column_name: The column's name
     * @param rowNumber:   The row's index
     * @return             The String value in that location
     */
    public String getString(String column_name, int rowNumber) throws SQLException {
        this.queryResult.relative(rowNumber);
        return this.queryResult.getString(column_name);
    }

    /**
     * Fetches a String value from a specified column and row
     * @param column_number: The column's index
     * @param rowNumber:     The row's index
     * @return               The String value in that location
     */
    public String getString(int column_number, int rowNumber) throws SQLException {
        this.queryResult.relative(rowNumber);
        return this.queryResult.getString(column_number);
    }

    /**
     * Fetches an Integer value from a specified column and row
     * @param column_name: The column's name
     * @param rowNumber:   The row's index
     * @return             The int value in that location
     */
    public int getInt(String column_name, int rowNumber) throws SQLException {
        this.queryResult.relative(rowNumber);
        return this.queryResult.getInt(column_name);
    }

    /**
     * Fetches an Integer value from a specified column and row
     * @param column_number: The column's index
     * @param rowNumber:     The row's index
     * @return               The int value in that location
     */
    public int getInt(int column_number, int rowNumber) throws SQLException {
        this.queryResult.relative(rowNumber);
        return this.queryResult.getInt(column_number);
    }

    /**
     * Fetches an Double value from a specified column and row
     * @param column_name: The column's name
     * @param rowNumber:   The row's index
     * @return             The double value in that location
     */
    public double getDouble(String column_name, int rowNumber) throws SQLException {
        this.queryResult.relative(rowNumber);
        return this.queryResult.getDouble(column_name);
    }

    /**
     * Fetches an Double value from a specified column and row
     * @param column_number: The column's index
     * @param rowNumber:     The row's index
     * @return               The double value in that location
     */
    public double getDouble(int column_number, int rowNumber) throws SQLException {
        this.queryResult.relative(rowNumber);
        return this.queryResult.getDouble(column_number);
    }

    /**
     * Fetches an Boolean value from a specified column and row
     * @param column_name: The column's name
     * @param rowNumber:   The row's index
     * @return             The boolean value in that location
     */
    public boolean getBoolean(String column_name, int rowNumber) throws SQLException {
        this.queryResult.relative(rowNumber);
        return this.queryResult.getBoolean(column_name);
    }

    /**
     * Fetches an Boolean value from a specified column and row
     * @param column_number: The column's index
     * @param rowNumber:     The row's index
     * @return               The boolean value in that location
     */
    public boolean getBoolean(int column_number, int rowNumber) throws SQLException {
        this.queryResult.relative(rowNumber);
        return this.queryResult.getBoolean(column_number);
    }
}
