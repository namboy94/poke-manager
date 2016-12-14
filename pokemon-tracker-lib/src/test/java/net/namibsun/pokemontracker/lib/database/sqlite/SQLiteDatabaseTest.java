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

import java.io.File;

import net.namibsun.pokemontracker.lib.database.dbinterface.DatabaseColumn;
import org.junit.*;

import java.sql.SQLException;
import static org.junit.Assert.*;
import net.namibsun.pokemontracker.lib.database.dbinterface.QueryResult;

public class SQLiteDatabaseTest {

    private SQLiteDatabase db;
    private DatabaseColumn[] standardColumns;

    @Before
    public void setup() throws SQLException {
        this.db = new SQLiteDatabase("test.db");

        this.standardColumns = new DatabaseColumn[] {
                new DatabaseColumn("one", "Integer"),
                new DatabaseColumn("two", "Text"),
                new DatabaseColumn("three", "Double"),
                new DatabaseColumn("four", "Boolean")
        };

        this.db.createTable("test_table", standardColumns);
    }

    @After
    public void tearDown() throws SQLException {
        this.db.close();
        File dbFile = new File("test.db");
        //noinspection ResultOfMethodCallIgnored
        dbFile.delete();
    }

    @Test
    public void testCreatingDatabaseFile() throws SQLException {
        File dbFile = new File("test.db");
        assertTrue(dbFile.isFile());
    }

    @Test
    public void testCreatingAndInsertingData() throws SQLException {

        this.db.insert("test_table", this.standardColumns, new Object[] {1, "A", 1.0, 1});
        assertEquals(this.db.query("SELECT * FROM test_table", null).getQueryLength(), 1);
        this.db.insert("test_table", new Object[] {2, "B", 2.0, 0});
        assertEquals(this.db.query("SELECT * FROM test_table", null).getQueryLength(), 2);
    }

    @Test
    public void testRetrievingData() throws SQLException {
        this.testCreatingAndInsertingData();

        QueryResult result = this.db.query("SELECT * FROM test_table", null);
        assertEquals(result.getQueryLength(), 2);

        assertEquals(result.getInt("one", 0), 1);
        assertEquals(result.getInt(0, 1), 2);
        assertEquals(result.getString("two", 0), "A");
        assertEquals(result.getString(1, 1), "B");
        assertEquals(result.getDouble("three", 0), 1.0, 0.0);
        assertEquals(result.getDouble(2, 1), 2.0, 0.0);
        assertEquals(result.getBoolean("four", 0), true);
        assertEquals(result.getBoolean(3, 1), false);
    }

    @Test
    public void testInconsistentColumnCountInsert() {
        try {
            this.db.insert("test_table",
                    new DatabaseColumn[]{new DatabaseColumn("one", "Integer")},
                    new Object[]{1, 2});
        } catch (SQLException e) {
            assertEquals(e.getMessage(), "Inconsistent amount of columns and arguments");
        }
    }

    @Test
    public void testInsertingAndRetrivingNullValues() throws SQLException {
        this.db.insert("test_table", new Object[] {null, null, null, null});
        QueryResult result = this.db.query("SELECT * FROM test_table WHERE two IS ?", new Object[] {null});

        assertTrue(0 == result.getInt(0, 0));
        assertTrue(null == result.getString(1, 0));
        assertTrue(0.0 == result.getDouble(2, 0));
        assertFalse(result.getBoolean(3, 0));
    }
}
