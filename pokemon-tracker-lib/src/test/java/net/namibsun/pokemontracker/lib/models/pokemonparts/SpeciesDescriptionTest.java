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

public class SpeciesDescriptionTest {

    @Test
    public void testGeneratingFromSerebii() throws IOException {

        SerebiiParser parser = new SerebiiParser("Bulbasaur");
        SpeciesDescription description = SpeciesDescription.fromWebParser(parser);

        assertEquals(15.2, description.getImperialWeight(), 0.0);
        assertEquals(6.9, description.getMetricWeight(), 0.0);
        assertEquals(2.04, description.getImperialHeight(), 0.0);
        assertEquals(0.7, description.getMetricHeight(), 0.0);
        assertEquals("Seed Pokémon", description.getClassification());

    }

    @Test
    public void testTrueEquality() {
        assertTrue(new SpeciesDescription(0.0, 0.0, 0.0, 0.0, "")
                .equals(new SpeciesDescription(0.0, 0.0, 0.0, 0.0, "")));
    }

    @Test
    public void testFalseEquality() {
        assertFalse(new SpeciesDescription(0.0, 0.0, 0.0, 0.0, "")
                .equals(new SpeciesDescription(0.0, 0.0, 0.0, 0.1, "")));
        assertFalse(new SpeciesDescription(0.0, 0.0, 0.0, 0.0, "")
                .equals(new SpeciesDescription(0.0, 0.0, 0.1, 0.0, "")));
        assertFalse(new SpeciesDescription(0.0, 0.0, 0.0, 0.0, "")
                .equals(new SpeciesDescription(0.0, 0.1, 0.0, 0.0, "")));
        assertFalse(new SpeciesDescription(0.0, 0.0, 0.0, 0.0, "")
                .equals(new SpeciesDescription(0.1, 0.0, 0.0, 0.0, "")));
        assertFalse(new SpeciesDescription(0.0, 0.0, 0.0, 0.0, "")
                .equals(new SpeciesDescription(0.0, 0.0, 0.0, 0.0, "a")));
    }

}
