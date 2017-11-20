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

package net.namibsun.pokemontracker.lib.pokemon.pokemonparts.species;

import org.junit.Test;
import static org.junit.Assert.*;
public class StatSpreadTest {

    @Test
    public void testTrueEquality() {
        assertTrue(new BaseStats(1, 1, 1, 1, 1, 1).equals(new BaseStats(1, 1, 1, 1, 1, 1)));
    }

    @Test
    public void testFalseEquality() {
        assertFalse(new BaseStats(1, 1, 1, 1, 1, 1).equals(new BaseStats(5, 1, 1, 1, 1, 1)));
        assertFalse(new BaseStats(1, 1, 1, 1, 1, 1).equals(new BaseStats(1, 5, 1, 1, 1, 1)));
        assertFalse(new BaseStats(1, 1, 1, 1, 1, 1).equals(new BaseStats(1, 1, 5, 1, 1, 1)));
        assertFalse(new BaseStats(1, 1, 1, 1, 1, 1).equals(new BaseStats(1, 1, 1, 5, 1, 1)));
        assertFalse(new BaseStats(1, 1, 1, 1, 1, 1).equals(new BaseStats(1, 1, 1, 1, 5, 1)));
        assertFalse(new BaseStats(1, 1, 1, 1, 1, 1).equals(new BaseStats(1, 1, 1, 1, 1, 5)));
        assertFalse(new BaseStats(5, 1, 1, 1, 1, 1).equals(new BaseStats(1, 1, 1, 1, 1, 1)));
        assertFalse(new BaseStats(1, 5, 1, 1, 1, 1).equals(new BaseStats(1, 1, 1, 1, 1, 1)));
        assertFalse(new BaseStats(1, 1, 5, 1, 1, 1).equals(new BaseStats(1, 1, 1, 1, 1, 1)));
        assertFalse(new BaseStats(1, 1, 1, 5, 1, 1).equals(new BaseStats(1, 1, 1, 1, 1, 1)));
        assertFalse(new BaseStats(1, 1, 1, 1, 5, 1).equals(new BaseStats(1, 1, 1, 1, 1, 1)));
        assertFalse(new BaseStats(1, 1, 1, 1, 1, 5).equals(new BaseStats(1, 1, 1, 1, 1, 1)));
    }
}
