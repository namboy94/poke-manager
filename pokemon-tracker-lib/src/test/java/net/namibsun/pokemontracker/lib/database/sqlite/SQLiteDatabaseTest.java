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
import org.junit.Test;
import java.sql.SQLException;
import static org.junit.Assert.*;
import net.namibsun.pokemontracker.lib.database.dbinterface.QueryResult;

public class SQLiteDatabaseTest {

    @Test
    public void testCreatingDatabase() throws SQLException {
        new SQLiteDatabase("test.db").close();
        File dbFile = new File("test.db");
        assertTrue(dbFile.isFile());
        //noinspection ResultOfMethodCallIgnored
        dbFile.delete();
    }

    @Test
    public void testStoringAndGettingValues() throws SQLException {
        SQLiteDatabase db = new SQLiteDatabase("test.db");
        db.executeSql("CREATE TABLE test (one Text, two Integer, three Double, four Boolean);");
        db.executeSql("INSERT INTO test (one, two, three, four) VALUES (\"A\", 1, 1.0, 0)");
        db.executeSql("INSERT INTO test (one, two, three, four) VALUES (\"B\", 2, 2.0, 1)");
        db.commitChanges();

        QueryResult result = db.query("SELECT * FROM test");
        assertEquals(result.getQueryLength(), 2);
        assertEquals(result.getString("one", 0), "A");
        assertEquals(result.getString(0, 1), "B");
        assertEquals(result.getInt("two", 0), 1);
        assertEquals(result.getInt(1, 1), 2);
        assertEquals(result.getDouble("three", 0), 1.0, 0.0);
        assertEquals(result.getDouble(2, 1), 2.0, 0.0);
        assertEquals(result.getBoolean("four", 0), false);
        assertEquals(result.getBoolean(3, 1), true);

        db.close();
        //noinspection ResultOfMethodCallIgnored
        new File("test.db").delete();

    }

}
