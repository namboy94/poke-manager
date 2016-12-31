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

import org.junit.Test;
import static org.junit.Assert.*;
import net.namibsun.pokemontracker.lib.pokemon.enums.Natures;
import net.namibsun.pokemontracker.lib.pokemon.enums.PokemonStatTypes;

public class NatureTest {

    @Test
    public void testWeakenedStatType() {
        assertEquals(PokemonStatTypes.SATK, new Nature(Natures.ADAMANT).getDroppedStat());
    }

    @Test
    public void testBeneficialStatType() {
        assertEquals(PokemonStatTypes.ATK, new Nature(Natures.ADAMANT).getBoostedStat());
    }

    @Test
    public void testEquality() {
        assertTrue(new Nature(Natures.ADAMANT).equals(new Nature(Natures.ADAMANT)));
        assertFalse(new Nature(Natures.ADAMANT).equals(new Nature(Natures.BASHFUL)));
    }
}
