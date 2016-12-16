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

import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.species.Abilities;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;
import net.namibsun.pokemontracker.lib.webscraping.serebii.SerebiiParser;


public class AbilitiesTest {

    @Test
    public void testAbilityGeneratingSingleWithHidden() throws IOException {
        Abilities abilities = Abilities.fromWebParser(new SerebiiParser("Bulbasaur"));

        assertTrue(abilities.hasHiddenAbility());
        assertFalse(abilities.hasSecondRegularAbility());
        assertArrayEquals(abilities.getAbilityTwo(), null);

        assertArrayEquals(abilities.getAbilityOne(),
                new String[] {"Overgrow", "When HP is below 1/3rd its maximum, " +
                        "power of Grass-type moves is increased by 50%."});
        assertArrayEquals(abilities.getHiddenAbility(),
                new String[] {"Chlorophyll", "When sunny, the Pokémon’s Speed doubles. " +
                        "However, Speed will not double on the turn weather becomes Strong Sunlight."});
    }

    @Test
    public void testAbilityGeneratingDoubleWithHidden() throws IOException {
        Abilities abilities = Abilities.fromWebParser(new SerebiiParser("Paras"));

        assertTrue(abilities.hasHiddenAbility());
        assertTrue(abilities.hasSecondRegularAbility());

        assertArrayEquals(abilities.getAbilityOne(),
                new String[] {"Effect Spore", "The opponent has a 10% chance of being induced by " +
                        "PARALYZE, POISON, or SLEEP when using an attack, " +
                        "that requires physical contact, against this Pokémon."});
        assertArrayEquals(abilities.getAbilityTwo(),
                new String[] {"Dry Skin", "HP is restored when hit by Water-type moves " +
                        "or when it is raining but also makes the Pokémon weak to " +
                        "Fire-type moves and reduces HP during strong sunlight."});
        assertArrayEquals(abilities.getHiddenAbility(),
                new String[] {"Damp", "Explosion and Selfdestruct will not work while the Pokémon is on the field."});
    }

    @Test
    public void testAbilityGeneratingWithoutHidden() throws IOException {
        Abilities abilities = Abilities.fromWebParser(new SerebiiParser("Gastly"));

        assertFalse(abilities.hasHiddenAbility());
        assertFalse(abilities.hasSecondRegularAbility());

        assertArrayEquals(abilities.getAbilityTwo(), null);
        assertArrayEquals(abilities.getHiddenAbility(), null);

        assertArrayEquals(abilities.getAbilityOne(),
                new String[] {"Levitate", "Damage dealing Ground-type moves have no effect " +
                        "on this Pokémon. Cannot be trapped by Arena Trap abilities. " +
                        "Takes no damage from Spikes."});
    }

    @Test
    public void testSingleAbility() {
        Abilities abilities = new Abilities("A", "B");

        assertFalse(abilities.hasSecondRegularAbility());
        assertFalse(abilities.hasHiddenAbility());
        assertTrue(abilities.getAbilityTwo() == null);
        assertTrue(abilities.getHiddenAbility() == null);

        assertArrayEquals(abilities.getAbilityOne(), new String[] {"A", "B"});
    }

    @Test
    public void testTwoAbilities() {
        Abilities abilities = new Abilities("A", "B", "C", "D");

        assertTrue(abilities.hasSecondRegularAbility());
        assertFalse(abilities.hasHiddenAbility());
        assertTrue(abilities.getHiddenAbility() == null);

        assertArrayEquals(abilities.getAbilityOne(), new String[] {"A", "B"});
        assertArrayEquals(abilities.getAbilityTwo(), new String[] {"C", "D"});
    }

    @Test
    public void testSingleAbilityWithHiddenAbility() {
        Abilities abilities = new Abilities("A", "B", null, null, "C", "D");

        assertFalse(abilities.hasSecondRegularAbility());
        assertTrue(abilities.hasHiddenAbility());
        assertTrue(abilities.getAbilityTwo() == null);

        assertArrayEquals(abilities.getAbilityOne(), new String[] {"A", "B"});
        assertArrayEquals(abilities.getHiddenAbility(), new String[] {"C", "D"});
    }

    @Test
    public void testTwoAbilitiesWithHiddenAbility() {
        Abilities abilities = new Abilities("A", "B", "C", "D", "E", "F");

        assertTrue(abilities.hasSecondRegularAbility());
        assertTrue(abilities.hasHiddenAbility());

        assertArrayEquals(abilities.getAbilityOne(), new String[] {"A", "B"});
        assertArrayEquals(abilities.getAbilityTwo(), new String[] {"C", "D"});
        assertArrayEquals(abilities.getHiddenAbility(), new String[] {"E", "F"});
    }

    @Test
    public void testTrueEquality() {
        assertTrue(new Abilities("A", "B").equals(new Abilities("A", "B")));
        assertTrue(new Abilities("A", "B", "C", "D").equals(new Abilities("A", "B", "C", "D")));
        assertTrue(new Abilities("A", "B", "C", "D", "E", "F").equals(new Abilities("A", "B", "C", "D", "E", "F")));
    }

    @Test
    public void testFalseEquality() {
        assertFalse(new Abilities("A", "B").equals(new Abilities("B", "A")));
        assertFalse(new Abilities("A", "B").equals(new Abilities("A", "A")));
        assertFalse(new Abilities("A", "B").equals(new Abilities("A", "B", "C", "D")));
        assertFalse(new Abilities("A", "B").equals(new Abilities("A", "B", "C", "D", "E", "F")));
        assertFalse(new Abilities("A", "B", "C", "D").equals(new Abilities("A", "B")));
        assertFalse(new Abilities("A", "B", "C", "D").equals(new Abilities("A", "B")));
        assertFalse(new Abilities("A", "B", "C", "D").equals(new Abilities("A", "B", "B", "A")));
        assertFalse(new Abilities("A", "B", "C", "D").equals(new Abilities("A", "B", "C", "C")));
        assertFalse(new Abilities("A", "B", "C", "D").equals(new Abilities("A", "B", "C", "D", "E", "F")));
        assertFalse(new Abilities("A", "B", "C", "D", "E", "F").equals(new Abilities("A", "B")));
        assertFalse(new Abilities("A", "B", "C", "D", "E", "F").equals(new Abilities("A", "B", "C", "D")));
        assertFalse(new Abilities("A", "B", "C", "D", "E", "F").equals(new Abilities("A", "B", "C", "D", "E", "A")));
        assertFalse(new Abilities("A", "B", "C", "D", "E", "F").equals(new Abilities("A", "B", "C", "D", "G", "F")));
    }

}
