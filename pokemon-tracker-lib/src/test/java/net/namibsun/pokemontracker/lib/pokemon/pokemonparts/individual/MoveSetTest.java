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

import net.namibsun.pokemontracker.lib.pokemon.enums.DamageCategories;
import net.namibsun.pokemontracker.lib.pokemon.enums.PokemonTypes;
import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.Move;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MoveSetTest {

    private Move one;
    private Move two;
    private Move three;
    private Move four;

    @Before
    public void setup() {
        this.one = new Move("A", "B", DamageCategories.PHYSICAL, PokemonTypes.BUG);
        this.two = new Move("C", "D", DamageCategories.PHYSICAL, PokemonTypes.FAIRY);
        this.three = new Move("E", "F", DamageCategories.SPECIAL, PokemonTypes.DARK);
        this.four = new Move("G", "H", DamageCategories.STATUS, PokemonTypes.DRAGON);
    }

    @Test
    public void testVariableParameterConstructors() {
        assertTrue(new MoveSet(this.one).equals(new MoveSet(this.one, null)));
        assertTrue(new MoveSet(this.one).equals(new MoveSet(this.one, null, null)));
        assertTrue(new MoveSet(this.one).equals(new MoveSet(this.one, null, null, null)));
        assertTrue(new MoveSet(this.one, null).equals(new MoveSet(this.one)));
        assertTrue(new MoveSet(this.one, null, null).equals(new MoveSet(this.one)));
        assertTrue(new MoveSet(this.one, null, null, null).equals(new MoveSet(this.one)));
    }

    @Test
    public void testWrongOrder() {
        try {
            new MoveSet(this.one, null, this.two);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Moves must be in order");
        }
        try {
            new MoveSet(this.one, null, null, this.two);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Moves must be in order");
        }
        try {
            new MoveSet(this.one, this.two, null, this.three);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Moves must be in order");
        }
        try {
            new MoveSet(null, this.one);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Moves must be in order");
        }
        try {
            new MoveSet(null, null, this.one);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Moves must be in order");
        }
        try {
            new MoveSet(null, null, null, this.one);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Moves must be in order");
        }
    }

    @Test
    public void testUniqueness() {
        try {
            new MoveSet(this.one, this.one);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Moves must be unique");
        }
        try {
            new MoveSet(this.one, this.two, this.one);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Moves must be unique");
        }
        try {
            new MoveSet(this.one, this.two, this.three, this.one);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Moves must be unique");
        }
        try {
            new MoveSet(this.one, this.two, this.two);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Moves must be unique");
        }
        try {
            new MoveSet(this.one, this.two, this.three, this.two);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Moves must be unique");
        }
        try {
            new MoveSet(this.one, this.two, this.three, this.three);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Moves must be unique");
        }
    }

    @Test
    public void testAmount() {
        try {
            //noinspection ConstantConditions
            new MoveSet(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "MoveSet must contain at least one move");
        }
    }

    @Test
    public void testEquality() {
        MoveSet tester = new MoveSet(this.one, this.two, this.three, this.four);
        assertTrue(tester.equals(new MoveSet(this.one, this.two, this.three, this.four)));
        assertFalse(tester.equals(new MoveSet(this.four, this.one, this.two, this.three)));
        assertFalse(tester.equals(new MoveSet(this.one, this.four, this.three, this.two)));
        assertFalse(tester.equals(new MoveSet(this.one, this.two, this.four, this.three)));
        assertFalse(tester.equals(new MoveSet(this.one, this.two, this.three, null)));
    }

}
