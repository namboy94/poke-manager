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
     * Executes an SQL stement
     * @param sqlStatement: The statement to execute
     * @throws SQLException if an SQL Error occured
     */
    void executeSql(String sqlStatement) throws SQLException;

    /**
     * Executes an SQL query and returns the result as a QueryResult object
     * @param sqlStatement: The query to execute
     * @throws SQLException if an SQL Error occured
     */
    QueryResult query(String sqlStatement) throws SQLException;

    /**
     * Commits the changes to the database
     * @throws SQLException if an SQL Error occured
     */
    void commitChanges() throws SQLException;

    /**
     * Closes the database connection
     * @throws SQLException if an SQL Error occured
     */
    void close() throws SQLException;

}
