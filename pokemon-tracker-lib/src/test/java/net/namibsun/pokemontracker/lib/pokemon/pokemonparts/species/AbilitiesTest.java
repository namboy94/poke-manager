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

package net.namibsun.pokemontracker.lib.pokemon.pokemonparts.species;

import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.Ability;
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
        assertEquals(abilities.getAbilityTwo(), null);

        assertEquals(abilities.getAbilityOne().getName(), "Overgrow");
        assertEquals(abilities.getAbilityOne().getDescription(), "When HP is below 1/3rd its maximum, " +
                "power of Grass-type moves is increased by 50%.");

        assertEquals(abilities.getHiddenAbility().getName(), "Chlorophyll");
        assertEquals(abilities.getHiddenAbility().getDescription(), "When sunny, the Pokémon’s Speed doubles. " +
                "However, Speed will not double on the turn weather becomes Strong Sunlight.");
    }

    @Test
    public void testAbilityGeneratingDoubleWithHidden() throws IOException {
        Abilities abilities = Abilities.fromWebParser(new SerebiiParser("Paras"));

        assertTrue(abilities.hasHiddenAbility());
        assertTrue(abilities.hasSecondRegularAbility());

        assertEquals(abilities.getAbilityOne().getName(), "Effect Spore");
        assertEquals(abilities.getAbilityOne().getDescription(),
                "The opponent has a 10% chance of being induced by " +
                "PARALYZE, POISON, or SLEEP when using an attack, " +
                "that requires physical contact, against this Pokémon.");

        assertEquals(abilities.getAbilityTwo().getName(), "Dry Skin");
        assertEquals(abilities.getAbilityTwo().getDescription(),
                "HP is restored when hit by Water-type moves " +
                "or when it is raining but also makes the Pokémon weak to " +
                "Fire-type moves and reduces HP during strong sunlight.");

        assertEquals(abilities.getHiddenAbility().getName(), "Damp");
        assertEquals(abilities.getHiddenAbility().getDescription(),
                "Explosion and Selfdestruct will not work while the Pokémon is on the field.");

    }

    @Test
    public void testAbilityGeneratingWithoutHidden() throws IOException {
        Abilities abilities = Abilities.fromWebParser(new SerebiiParser("Gastly"));

        assertFalse(abilities.hasHiddenAbility());
        assertFalse(abilities.hasSecondRegularAbility());

        assertEquals(abilities.getAbilityTwo(), null);
        assertEquals(abilities.getHiddenAbility(), null);

        assertEquals(abilities.getAbilityOne().getName(), "Levitate");
        assertEquals(abilities.getAbilityOne().getDescription(),
                "Damage dealing Ground-type moves have no effect on this Pokémon. " +
                        "Cannot be trapped by Arena Trap ability. Takes no damage from Spikes.");
    }

    @Test
    public void testSingleAbility() {
        Abilities abilities = create("A", "B");

        assertFalse(abilities.hasSecondRegularAbility());
        assertFalse(abilities.hasHiddenAbility());
        assertTrue(abilities.getAbilityTwo() == null);
        assertTrue(abilities.getHiddenAbility() == null);

        assertEquals(abilities.getAbilityOne().getName(), "A");
        assertEquals(abilities.getAbilityOne().getDescription(), "B");
    }

    @Test
    public void testTwoAbilities() {
        Abilities abilities = create("A", "B", "C", "D");

        assertTrue(abilities.hasSecondRegularAbility());
        assertFalse(abilities.hasHiddenAbility());
        assertTrue(abilities.getHiddenAbility() == null);

        assertEquals(abilities.getAbilityOne().getName(), "A");
        assertEquals(abilities.getAbilityOne().getDescription(), "B");
        assertEquals(abilities.getAbilityTwo().getName(), "C");
        assertEquals(abilities.getAbilityTwo().getDescription(), "D");

    }

    @Test
    public void testSingleAbilityWithHiddenAbility() {
        Abilities abilities = new Abilities(new Ability("A", "B", false),
                null, new Ability("C", "D", true));

        assertFalse(abilities.hasSecondRegularAbility());
        assertTrue(abilities.hasHiddenAbility());
        assertTrue(abilities.getAbilityTwo() == null);

        assertEquals(abilities.getAbilityOne().getName(), "A");
        assertEquals(abilities.getAbilityOne().getDescription(), "B");
        assertEquals(abilities.getHiddenAbility().getName(), "C");
        assertEquals(abilities.getHiddenAbility().getDescription(), "D");
    }

    @Test
    public void testTwoAbilitiesWithHiddenAbility() {
        Abilities abilities = create("A", "B", "C", "D", "E", "F");

        assertTrue(abilities.hasSecondRegularAbility());
        assertTrue(abilities.hasHiddenAbility());

        assertEquals(abilities.getAbilityOne().getName(), "A");
        assertEquals(abilities.getAbilityOne().getDescription(), "B");
        assertEquals(abilities.getAbilityTwo().getName(), "C");
        assertEquals(abilities.getAbilityTwo().getDescription(), "D");
        assertEquals(abilities.getHiddenAbility().getName(), "E");
        assertEquals(abilities.getHiddenAbility().getDescription(), "F");
    }

    @Test
    public void testTrueEquality() {
        assertTrue(create("A", "B").equals(create("A", "B")));
        assertTrue(create("A", "B", "C", "D").equals(create("A", "B", "C", "D")));
        assertTrue(create("A", "B", "C", "D", "E", "F").equals(create("A", "B", "C", "D", "E", "F")));
    }

    @Test
    public void testFalseEquality() {
        assertFalse(create("A", "B").equals(create("B", "A")));
        assertFalse(create("A", "B").equals(create("A", "A")));
        assertFalse(create("A", "B").equals(create("A", "B", "C", "D")));
        assertFalse(create("A", "B").equals(create("A", "B", "C", "D", "E", "F")));
        assertFalse(create("A", "B", "C", "D").equals(create("A", "B")));
        assertFalse(create("A", "B", "C", "D").equals(create("A", "B")));
        assertFalse(create("A", "B", "C", "D").equals(create("A", "B", "B", "A")));
        assertFalse(create("A", "B", "C", "D").equals(create("A", "B", "C", "C")));
        assertFalse(create("A", "B", "C", "D").equals(create("A", "B", "C", "D", "E", "F")));
        assertFalse(create("A", "B", "C", "D", "E", "F").equals(create("A", "B")));
        assertFalse(create("A", "B", "C", "D", "E", "F").equals(create("A", "B", "C", "D")));
        assertFalse(create("A", "B", "C", "D", "E", "F").equals(create("A", "B", "C", "D", "E", "A")));
        assertFalse(create("A", "B", "C", "D", "E", "F").equals(create("A", "B", "C", "D", "G", "F")));
    }

    public Abilities create(String oneName, String oneDesc) {
        return new Abilities(new Ability(oneName, oneDesc, false), null, null);
    }

    public Abilities create(String oneName, String oneDesc, String twoName, String twoDesc) {
        return new Abilities(
                new Ability(oneName, oneDesc, false),
                new Ability(twoName, twoDesc, false), null);
    }

    public Abilities create(String oneName, String oneDesc, String twoName, String twoDesc, String hiddenName, String hiddenDesc) {
        return new Abilities(
                new Ability(oneName, oneDesc, false),
                new Ability(twoName, twoDesc, false),
                new Ability(hiddenName, hiddenDesc, true));
    }

}
