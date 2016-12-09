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
import net.namibsun.pokemontracker.lib.webscraping.PokemonConstants;
import net.namibsun.pokemontracker.lib.webscraping.serebii.SerebiiParser;

public class NameTest{

    @Test
    public void testCreatingNameAndGettingNameValue() {

        Name name = new Name("TestName", "TestLanguage");
        assertEquals("TestName", name.getName("TestLanguage"));

    }

    @Test
    public void testGettingPokemonNamesFromSerebii() throws IOException {
        SerebiiParser parser = new SerebiiParser("Bulbasaur");
        Name name = Name.fromWebParser(parser);

        assertEquals("Bulbasaur", name.getName(PokemonConstants.ENGLISH_KEY));
        assertEquals("Bisasam", name.getName(PokemonConstants.GERMAN_KEY));
        assertEquals("Bulbizarre", name.getName(PokemonConstants.FRENCH_KEY));
        assertEquals("Fushigidane フシギダネ", name.getName(PokemonConstants.JAPANESE_KEY));
        assertEquals("이상해씨", name.getName(PokemonConstants.KOREAN_KEY));

    }

    @Test
    public void testMultiLanguageConstructor() {

        Name name = new Name("A", "B", "C", "D", "E");

        assertEquals("A", name.getName(PokemonConstants.ENGLISH_KEY));
        assertEquals("B", name.getName(PokemonConstants.FRENCH_KEY));
        assertEquals("C", name.getName(PokemonConstants.GERMAN_KEY));
        assertEquals("D", name.getName(PokemonConstants.JAPANESE_KEY));
        assertEquals("E", name.getName(PokemonConstants.KOREAN_KEY));

    }

    @Test
    public void testGettingInvalidLanguage(){

        Name name = new Name("TestName", "TestLanguage");
        assertEquals(name.getName("NotExistingLanguage"), "Missingno.");

    }
}
