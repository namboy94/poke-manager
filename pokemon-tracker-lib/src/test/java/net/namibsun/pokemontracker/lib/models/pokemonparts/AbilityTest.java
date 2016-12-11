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


public class AbilityTest {

    @Test
    public void testAbilityGeneratingSingleWithHidden() throws IOException {
        Ability ability = Ability.fromWebParser(new SerebiiParser("Bulbasaur"));

        assertTrue(ability.hasHiddenAbility());
        assertFalse(ability.hasSecondRegularAbility());
        assertArrayEquals(ability.getAbilityTwo(), null);

        assertArrayEquals(ability.getAbilityOne(),
                new String[] {"Overgrow", "When HP is below 1/3rd its maximum, " +
                        "power of Grass-type moves is increased by 50%."});
        assertArrayEquals(ability.getHiddenAbility(),
                new String[] {"Chlorophyll", "When sunny, the Pokémon’s Speed doubles. " +
                        "However, Speed will not double on the turn weather becomes Strong Sunlight."});
    }

    @Test
    public void testAbilityGeneratingDoubleWithHidden() throws IOException {
        Ability ability = Ability.fromWebParser(new SerebiiParser("Paras"));

        assertTrue(ability.hasHiddenAbility());
        assertTrue(ability.hasSecondRegularAbility());

        assertArrayEquals(ability.getAbilityOne(),
                new String[] {"Effect Spore", "The opponent has a 10% chance of being induced by " +
                        "PARALYZE, POISON, or SLEEP when using an attack, " +
                        "that requires physical contact, against this Pokémon."});
        assertArrayEquals(ability.getAbilityTwo(),
                new String[] {"Dry Skin", "HP is restored when hit by Water-type moves " +
                        "or when it is raining but also makes the Pokémon weak to " +
                        "Fire-type moves and reduces HP during strong sunlight."});
        assertArrayEquals(ability.getHiddenAbility(),
                new String[] {"Damp", "Explosion and Selfdestruct will not work while the Pokémon is on the field."});
    }

    @Test
    public void testAbilityGeneratingWithoutHidden() throws IOException {
        Ability ability = Ability.fromWebParser(new SerebiiParser("Gastly"));

        assertFalse(ability.hasHiddenAbility());
        assertFalse(ability.hasSecondRegularAbility());

        assertArrayEquals(ability.getAbilityTwo(), null);
        assertArrayEquals(ability.getHiddenAbility(), null);

        assertArrayEquals(ability.getAbilityOne(),
                new String[] {"Levitate", "Damage dealing Ground-type moves have no effect " +
                        "on this Pokémon. Cannot be trapped by Arena Trap ability. " +
                        "Takes no damage from Spikes."});
    }

    @Test
    public void testSingleAbility() {
        Ability ability = new Ability("A", "B");

        assertFalse(ability.hasSecondRegularAbility());
        assertFalse(ability.hasHiddenAbility());
        assertTrue(ability.getAbilityTwo() == null);
        assertTrue(ability.getHiddenAbility() == null);

        assertArrayEquals(ability.getAbilityOne(), new String[] {"A", "B"});
    }

    @Test
    public void testTwoAbilities() {
        Ability ability = new Ability("A", "B", "C", "D");

        assertTrue(ability.hasSecondRegularAbility());
        assertFalse(ability.hasHiddenAbility());
        assertTrue(ability.getHiddenAbility() == null);

        assertArrayEquals(ability.getAbilityOne(), new String[] {"A", "B"});
        assertArrayEquals(ability.getAbilityTwo(), new String[] {"C", "D"});
    }

    @Test
    public void testSingleAbilityWithHiddenAbility() {
        Ability ability = new Ability("A", "B", null, null, "C", "D");

        assertFalse(ability.hasSecondRegularAbility());
        assertTrue(ability.hasHiddenAbility());
        assertTrue(ability.getAbilityTwo() == null);

        assertArrayEquals(ability.getAbilityOne(), new String[] {"A", "B"});
        assertArrayEquals(ability.getHiddenAbility(), new String[] {"C", "D"});
    }

    @Test
    public void testTwoAbilitiesWithHiddenAbility() {
        Ability ability = new Ability("A", "B", "C", "D", "E", "F");

        assertTrue(ability.hasSecondRegularAbility());
        assertTrue(ability.hasHiddenAbility());

        assertArrayEquals(ability.getAbilityOne(), new String[] {"A", "B"});
        assertArrayEquals(ability.getAbilityTwo(), new String[] {"C", "D"});
        assertArrayEquals(ability.getHiddenAbility(), new String[] {"E", "F"});
    }

}
