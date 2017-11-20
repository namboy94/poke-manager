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

/**
 * Models a Database column as a Tuple-like object consisting of the Type and the column header
 */
public class DatabaseColumn {

    /**
     * The column header name
     */
    private String name;

    /**
     * The data type
     */
    private String type;

    /**
     * Creates a new Database Column
     * @param name: The name of the column header
     * @param type: The type of the data represented in that column
     */
    public DatabaseColumn(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * @return The Column header name followed by the type, separated by a space
     */
    public String getColumn() {
        return this.name + " " + this.type;
    }

    /**
     * @param end: A String to be appended to the column expression
     * @return     The Column header name followed by the type, separated by a space.
     */
    public String getColumn(String end) {
        return this.getColumn() + end;
    }

    /**
     * @return The header name of the column
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param end: A string to be appended to the header name
     * @return     The header name of the column
     */
    public String getName(String end) {
        return this.getName() + end;
    }

    /**
     * @return The data type of the column
     */
    public String getType() {
        return this.type;
    }

}
