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

import org.junit.Test;
import static org.junit.Assert.*;

public class AbilityTest {

    @Test
    public void testAbilityCreation() {
        Ability ability = new Ability("A", "B", true);
        assertEquals(ability.getName(), "A");
        assertEquals(ability.getDescription(), "B");
        assertTrue(ability.isHiddenAbility());
    }

    @Test
    public void testEqualityCheckTrue() {
        assertTrue(new Ability("A", "B", true).equals(
                        new Ability("A", "B", true)));
        assertTrue(new Ability("C", "C", false).equals(
                new Ability("C", "C", false)));
    }

    @Test
    public void testEqualityCheckFalse() {
        assertFalse(new Ability("A", "B", true).equals(new Ability("A", "B", false)));
        assertFalse(new Ability("A", "B", true).equals(new Ability("A", "C", true)));
        assertFalse(new Ability("A", "B", true).equals(new Ability("C", "B", true)));
        assertFalse(new Ability("A", "B", true).equals(new Ability("U", "V", true)));
        assertFalse(new Ability("A", "B", true).equals(new Ability("C", "B", false)));
    }


}
