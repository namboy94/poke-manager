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
import net.namibsun.pokemontracker.lib.models.enums.EggGroupTypes;
import net.namibsun.pokemontracker.lib.webscraping.serebii.SerebiiParser;

public class EggGroupsTest {

    @Test
    public void testGeneratingFromWebParser() throws IOException {
        EggGroups eggGroup = EggGroups.fromWebParser(new SerebiiParser("Bulbasaur"));
        assertEquals(eggGroup.getPrimaryEggGroup(), EggGroupTypes.MONSTER);
        assertEquals(eggGroup.getSecondaryEggGroup(), EggGroupTypes.GRASS);
        assertTrue(eggGroup.canBreed());
        assertFalse(eggGroup.canOnlyBreedWithDitto());
        assertTrue(eggGroup.hasTwoEggGroups());
    }

    @Test
    public void testGeneratingNonBreedablePokemon() throws IOException {
        EggGroups eggGroup = EggGroups.fromWebParser(new SerebiiParser("Magnemite"));
        assertEquals(eggGroup.getPrimaryEggGroup(), EggGroupTypes.MINERAL);
        assertTrue(eggGroup.canBreed());
        assertTrue(eggGroup.canOnlyBreedWithDitto());
        assertFalse(eggGroup.hasTwoEggGroups());
    }

    @Test
    public void testGeneratingGenderlessPokemon() throws IOException {
        EggGroups eggGroup = EggGroups.fromWebParser(new SerebiiParser("Mewtwo"));
        assertEquals(eggGroup.getPrimaryEggGroup(), EggGroupTypes.UNDISCOVERED);
        assertFalse(eggGroup.canBreed());
        assertFalse(eggGroup.canOnlyBreedWithDitto());
        assertFalse(eggGroup.hasTwoEggGroups());
    }

}
