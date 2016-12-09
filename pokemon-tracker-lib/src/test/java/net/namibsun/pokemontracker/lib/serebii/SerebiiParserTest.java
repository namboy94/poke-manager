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

package net.namibsun.pokemontracker.lib.serebii;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import net.namibsun.pokemontracker.lib.serebii.SerebiiParser;
import net.namibsun.pokemontracker.lib.serebii.SerebiiConstants;

public class SerebiiParserTest {

    @Test
    public void testParserInitialization() throws IOException {
        assertEquals(
                new SerebiiParser(1).getUrl(),
                new SerebiiParser("Bulbasaur").getUrl()
        );
    }

    @Test
    public void testParsingInvalidPokedexNumber() {
        try {
            new SerebiiParser(SerebiiConstants.MAX_POKEMON_NUMBER + 1);
            //noinspection ConstantConditions
            assertTrue(false);
        } catch (IOException e) {
            assertEquals("Maximum number of Pokemon exceeded", e.getMessage());
        }
    }

    @Test
    public void testParsingInvalidPokemonName() {
        try {
            new SerebiiParser("BulbasaurTwo");
            //noinspection ConstantConditions
            assertTrue(false);
        } catch (IOException e) {
            assertEquals("Pokemon not found", e.getMessage());
        }
    }

}
