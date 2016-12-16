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

import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.species.Name;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;
import net.namibsun.pokemontracker.lib.pokemon.enums.species.Languages;
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

        assertEquals("Bulbasaur", name.getName(Languages.ENGLISH));
        assertEquals("Bisasam", name.getName(Languages.GERMAN));
        assertEquals("Bulbizarre", name.getName(Languages.FRENCH));
        assertEquals("Fushigidane フシギダネ", name.getName(Languages.JAPANESE));
        assertEquals("이상해씨", name.getName(Languages.KOREAN));

    }

    @Test
    public void testMultiLanguageConstructor() {

        Name name = new Name("A", "B", "C", "D", "E");

        assertEquals("A", name.getName(Languages.ENGLISH));
        assertEquals("B", name.getName(Languages.FRENCH));
        assertEquals("C", name.getName(Languages.GERMAN));
        assertEquals("D", name.getName(Languages.JAPANESE));
        assertEquals("E", name.getName(Languages.KOREAN));

    }

    @Test
    public void testGettingInvalidLanguage(){

        Name name = new Name("TestName", "TestLanguage");
        assertEquals(name.getName("NotExistingLanguage"), "Missingno.");

    }

    @Test
    public void testTrueEquality() {
        assertTrue(new Name("A", "B").equals(new Name("A", "B")));
    }

    @Test
    public void testFalseEquality() {
        assertFalse(new Name("A", "B").equals(new Name("A", "C")));
    }

}
