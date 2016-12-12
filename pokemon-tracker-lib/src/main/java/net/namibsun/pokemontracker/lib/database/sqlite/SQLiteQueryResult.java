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
import net.namibsun.pokemontracker.lib.database.pokedex.PokedexColumns;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that wraps around an SQL ResultSet to give a uniform acces to its results
 */
public class SQLiteQueryResult extends QueryResult {

    private Connection database;
    private String query;

    public SQLiteQueryResult(Connection database, String query) throws SQLException {
        this.database = database;
        this.query = query;
    }

    /**
     * @return The amount of rows in the query result
     */
    public int getQueryLength() throws SQLException {

        int length = 0;
        ResultSet set = this.database.createStatement().executeQuery(this.query);
        while(set.next()) {
            length++;
        }

        return length;
    }

    private ResultSet getRowResultSet(int index) throws SQLException {

        ResultSet set = this.database.createStatement().executeQuery(this.query);
        for (int i = 0; i < index; i++) {
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
        return this.getRowResultSet(rowNumber).getString(column_number);
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
        return this.getRowResultSet(rowNumber).getInt(column_number);
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
        return this.getRowResultSet(rowNumber).getDouble(column_number);
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
        return this.getRowResultSet(rowNumber).getBoolean(column_number);
    }
}
