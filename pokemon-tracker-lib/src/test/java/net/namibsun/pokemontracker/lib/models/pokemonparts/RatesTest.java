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
import static org.junit.Assert.assertEquals;
import net.namibsun.pokemontracker.lib.models.enums.PokemonStatTypes;
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
        assertEquals(1, rates.getEffortValuesGained());
        assertEquals(PokemonStatTypes.SATK, rates.getEffortValueType());
    }

}
