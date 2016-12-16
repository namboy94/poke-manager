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

package net.namibsun.pokemontracker.lib.pokemon.pokemonparts;

import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.species.Rates;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;
import net.namibsun.pokemontracker.lib.webscraping.serebii.SerebiiParser;

public class RatesTest {

    @Test
    public void testGeneratingRatesFromWebParser() throws IOException {
        Rates rates = Rates.fromWebParser(new SerebiiParser("Bulbasaur"));
        assertEquals(45, rates.getCaptureRate());
        assertEquals(5120, rates.getBaseEggSteps());
        assertEquals(70, rates.getBaseHappiness());
        assertEquals(1059860, rates.getExperienceGrowthPoints());
        assertEquals("Medium Slow", rates.getExperienceGrowthDescription());
    }

    @Test
    public void testTrueEquality() {
        assertTrue(new Rates(0, 0, 0, 0, "").equals(new Rates(0, 0, 0, 0, "")));
    }

    @Test
    public void testFalseEquality() {
        assertFalse(new Rates(0, 0, 0, 0, "").equals(new Rates(0, 0, 0, 0, "A")));
        assertFalse(new Rates(0, 0, 0, 0, "").equals(new Rates(0, 0, 0, 1, "")));
        assertFalse(new Rates(0, 0, 0, 0, "").equals(new Rates(0, 0, 1, 0, "")));
        assertFalse(new Rates(0, 0, 0, 0, "").equals(new Rates(0, 1, 0, 0, "")));
        assertFalse(new Rates(0, 0, 0, 0, "").equals(new Rates(1, 0, 0, 0, "")));
    }

}
