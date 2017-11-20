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

package net.namibsun.pokemontracker.lib.pokemon.pokemonparts.individual;

import org.junit.Test;
import static org.junit.Assert.*;

public class LevelTest {

    @Test
    public void testLevelCreation() {
        new Level(1);
        try {
            new Level(0);
            fail();
        } catch (IllegalArgumentException ignored) {}
        try {
            new Level(101);
            fail();
        } catch (IllegalArgumentException ignored) {}
        try {
            new Level(-1);
            fail();
        } catch (IllegalArgumentException ignored) {}
    }

    @Test
    public void testEquality() {
        assertTrue(new Level(1).equals(new Level(1)));
        assertFalse(new Level(1).equals(new Level(2)));
        assertFalse(new Level(2).equals(new Level(1)));
    }

}