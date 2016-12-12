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

package net.namibsun.pokemontracker.lib.models.pokemonparts;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;
import net.namibsun.pokemontracker.lib.webscraping.serebii.SerebiiParser;

public class EffortValueYieldTest {

    @SuppressWarnings("Duplicates")
    @Test
    public void testSingleEffortValuePokemon() throws IOException {
        EffortValueYield yield = EffortValueYield.fromWebParser(new SerebiiParser("Bulbasaur"));
        assertEquals(0, yield.getHp());
        assertEquals(0, yield.getAttack());
        assertEquals(0, yield.getDefense());
        assertEquals(1, yield.getSpecialAttack());
        assertEquals(0, yield.getSpecialDefense());
        assertEquals(0, yield.getSpeed());
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testDoubleEffortValuePokemon() throws IOException {
        EffortValueYield yield = EffortValueYield.fromWebParser(new SerebiiParser("Venusaur"));
        assertEquals(0, yield.getHp());
        assertEquals(0, yield.getAttack());
        assertEquals(0, yield.getDefense());
        assertEquals(2, yield.getSpecialAttack());
        assertEquals(1, yield.getSpecialDefense());
        assertEquals(0, yield.getSpeed());
    }

    @Test
    public void testTrueEquality() {
        assertTrue(new EffortValueYield(0, 1, 2, 3, 4, 5).equals( new EffortValueYield(0, 1, 2, 3, 4, 5)));
    }

    @Test
    public void testFalseEquality() {
        assertFalse(new EffortValueYield(0, 1, 2, 3, 4, 5).equals( new EffortValueYield(0, 1, 2, 3, 4, 4)));
    }

}
