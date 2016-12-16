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

import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.species.GenderRatio;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import net.namibsun.pokemontracker.lib.webscraping.serebii.SerebiiParser;

public class GenderRatioTest {

    @Test
    public void testBulbasaurRatio() throws IOException {
        GenderRatio ratio = GenderRatio.fromWebParser(new SerebiiParser("Bulbasaur"));
        assertEquals(12.5, ratio.getFemaleRatio(), 0.0);
        assertEquals(87.5, ratio.getMaleRatio(), 0.0);
        assertFalse(ratio.isNeutralGendered());
    }

    @Test
    public void testMagnemiteRatio() throws IOException {
        GenderRatio ratio = GenderRatio.fromWebParser(new SerebiiParser("Magnemite"));
        assertEquals(0.0, ratio.getFemaleRatio(), 0.0);
        assertEquals(0.0, ratio.getMaleRatio(), 0.0);
        assertTrue(ratio.isNeutralGendered());
    }

    @Test
    public void testTrueEquality() {
        assertTrue(new GenderRatio(0.0, 0.0).equals(new GenderRatio(0.0, 0.0)));
    }

    @Test
    public void testFalseEquality() {
        assertFalse(new GenderRatio(0.0, 0.0).equals(new GenderRatio(0.0, 0.1)));
        assertFalse(new GenderRatio(0.0, 0.0).equals(new GenderRatio(0.1, 0.0)));
    }

}
