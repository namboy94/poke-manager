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

public class EffortValueSpreadTest {

    @Test
    public void testValidRanges() {

        try {
            new EffortValueSpread(1, 1, 1, 1, 1, 1);
            new EffortValueSpread(252, 252, 6, 0, 0, 0);
        } catch (Exception e) {
            //noinspection ConstantConditions
            assertTrue(false);
        }
    }

    @Test
    public void testInvalidRanges() {
        try {
            new EffortValueSpread(0, 0, 7, 252, 252, 0);
            //noinspection ConstantConditions
            assertTrue(false);
        } catch (NumberFormatException ignored) {}

        try {
            new EffortValueSpread(0, 0, 253, 0, 0, 0);
            //noinspection ConstantConditions
            assertTrue(false);
        } catch (NumberFormatException ignored) {}

        try {
            new EffortValueSpread(-3, 9, 100, 252, 252, -100);
            //noinspection ConstantConditions
            assertTrue(false);
        } catch (NumberFormatException ignored) {}
    }

    @Test
    public void testTrueEquality() {
        assertTrue(new EffortValueSpread(1, 2, 3, 4,5, 6)
                .equals(new EffortValueSpread(1, 2, 3, 4,5, 6)));
    }

    @Test
    public void testFalseEquality() {
        EffortValueSpread evSpread = new EffortValueSpread(1, 2, 3, 4, 5, 6);
        assertFalse(evSpread.equals(new EffortValueSpread(1, 2, 3, 4, 5, 0)));
        assertFalse(evSpread.equals(new EffortValueSpread(1, 2, 3, 4, 0, 6)));
        assertFalse(evSpread.equals(new EffortValueSpread(1, 2, 3, 0, 5, 6)));
        assertFalse(evSpread.equals(new EffortValueSpread(1, 2, 0, 4, 5, 6)));
        assertFalse(evSpread.equals(new EffortValueSpread(1, 0, 3, 4, 5, 6)));
        assertFalse(evSpread.equals(new EffortValueSpread(0, 2, 3, 4, 5, 6)));
    }

}


