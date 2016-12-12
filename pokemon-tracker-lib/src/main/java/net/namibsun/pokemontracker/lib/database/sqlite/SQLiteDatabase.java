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

import net.namibsun.pokemontracker.lib.database.dbinterface.Database;
import net.namibsun.pokemontracker.lib.database.dbinterface.QueryResult;
import net.namibsun.pokemontracker.lib.database.pokedex.PokedexColumns;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
     * @throws SQLException If an SQL error occured
     */
    public SQLiteDatabase(String databaseFile) throws SQLException {

        String url = "jdbc:sqlite:" + databaseFile;
        this.connection = DriverManager.getConnection(url);
        this.connection.setAutoCommit(false);

    }

    /**
     * Executes an SQL stement
     * @param sqlStatement: The statement to execute
     * @throws SQLException if an SQL Error occured
     */
    @Override
    public void executeSql(String sqlStatement) throws SQLException {
        this.connection.createStatement().execute(sqlStatement);
    }

    /**
     * Executes an SQL query and returns the result as a QueryResult object
     * @param sqlStatement: The query to execute
     * @throws SQLException if an SQL Error occured
     */
    @Override
    public QueryResult query(String sqlStatement) throws SQLException {
        return new SQLiteQueryResult(this.connection, sqlStatement);
    }

    /**
     * Commits the changes to the database
     * @throws SQLException if an SQL Error occured
     */
    @Override
    public void commitChanges() throws SQLException {
        this.connection.commit();
    }

    /**
     * Closes the Database Connection
     * @throws SQLException if an SQL Error occured
     */
    @Override
    public void close() throws SQLException {
        this.connection.close();
    }
}
