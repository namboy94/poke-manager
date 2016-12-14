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

package net.namibsun.pokemontracker.lib.species.pokemonparts;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;
import net.namibsun.pokemontracker.lib.species.enums.PokemonTypes;
import net.namibsun.pokemontracker.lib.webscraping.serebii.SerebiiParser;

public class TypeTest {

    @Test
    public void generateMonotypePokemonFromSerebii() throws IOException {
        Type type = Type.fromWebParser(new SerebiiParser("Charmander"));
        assertEquals(PokemonTypes.FIRE, type.getPrimaryType());
        assertEquals("Fire", type.getPrimaryTypeAsString());
        assertEquals(null, type.getSecondaryType());
        assertEquals(null, type.getSecondaryTypeAsString());
        assertFalse(type.isDualTyped());
    }

    @Test
    public void generateMultitypePokemonFromSerebii() throws IOException {
        Type type = Type.fromWebParser(new SerebiiParser("Bulbasaur"));
        assertEquals(PokemonTypes.GRASS, type.getPrimaryType());
        assertEquals("Grass", type.getPrimaryTypeAsString());
        assertEquals(PokemonTypes.POISON, type.getSecondaryType());
        assertEquals("Poison", type.getSecondaryTypeAsString());
        assertTrue(type.isDualTyped());
    }

    @Test
    public void testTrueEquality() {
        assertTrue(new Type(PokemonTypes.BUG).equals(new Type(PokemonTypes.BUG)));
        assertTrue(new Type(PokemonTypes.BUG).equals(new Type("Bug")));
        assertTrue(new Type(PokemonTypes.BUG, PokemonTypes.POISON).equals(
                new Type(PokemonTypes.BUG, PokemonTypes.POISON)));
        assertTrue(new Type(PokemonTypes.BUG, PokemonTypes.POISON).equals(
                new Type("Bug", "poison")));
    }

    @Test
    public void testFalseEquality() {
        assertFalse(new Type(PokemonTypes.BUG).equals(new Type(PokemonTypes.GRASS)));
        assertFalse(new Type(PokemonTypes.BUG).equals(new Type(PokemonTypes.BUG, PokemonTypes.POISON)));
    }

}
