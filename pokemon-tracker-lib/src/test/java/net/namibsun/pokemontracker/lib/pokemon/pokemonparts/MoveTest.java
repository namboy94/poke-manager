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

import net.namibsun.pokemontracker.lib.pokemon.enums.DamageCategories;
import net.namibsun.pokemontracker.lib.pokemon.enums.PokemonTypes;
import org.junit.Test;
import static org.junit.Assert.*;

public class MoveTest {

    @Test
    public void testGetters() {
        Move tester = new Move("A", "B", DamageCategories.PHYSICAL, PokemonTypes.BUG);
        assertEquals(tester.getName(), "A");
        assertEquals(tester.getDescription(), "B");
        assertEquals(tester.getDamageCategory(), DamageCategories.PHYSICAL);
        assertEquals(tester.getDamageCategoryAsString(), "PHYSICAL");
        assertEquals(tester.getType(), PokemonTypes.BUG);
        assertEquals(tester.getTypeAsString(), "BUG");
    }

    @Test
    public void testEquality() {
        assertTrue(
                new Move("A", "B", DamageCategories.PHYSICAL, PokemonTypes.BUG).equals(
                        new Move("A", "B", DamageCategories.PHYSICAL, PokemonTypes.BUG)
                )
        );
    }

    @Test
    public void testFalseEquality() {
        Move tester = new Move("A", "B", DamageCategories.PHYSICAL, PokemonTypes.BUG);
        assertFalse(tester.equals(new Move("A", "A", DamageCategories.PHYSICAL, PokemonTypes.BUG)));
        assertFalse(tester.equals(new Move("B", "B", DamageCategories.PHYSICAL, PokemonTypes.BUG)));
        assertFalse(tester.equals(new Move("A", "B", DamageCategories.SPECIAL, PokemonTypes.BUG)));
        assertFalse(tester.equals(new Move("A", "B", DamageCategories.PHYSICAL, PokemonTypes.ICE)));
    }

}
