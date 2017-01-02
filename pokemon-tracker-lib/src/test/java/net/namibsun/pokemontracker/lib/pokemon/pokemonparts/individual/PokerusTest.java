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

import net.namibsun.pokemontracker.lib.pokemon.enums.PokerusStates;
import org.junit.Test;
import static org.junit.Assert.*;

public class PokerusTest {

    @Test
    public void testInfectivity() {
        assertTrue(new Pokerus(PokerusStates.HEALTHY).canBeInfected());
        assertFalse(new Pokerus(PokerusStates.INFECTED).canBeInfected());
        assertFalse(new Pokerus(PokerusStates.CURED).canBeInfected());
    }

    @Test
    public void testEvBoost() {
        assertFalse(new Pokerus(PokerusStates.HEALTHY).hasGrowthBenefit());
        assertTrue(new Pokerus(PokerusStates.INFECTED).hasGrowthBenefit());
        assertFalse(new Pokerus(PokerusStates.CURED).hasGrowthBenefit());
    }

    @Test
    public void testEquality() {
        Pokerus pokerus = new Pokerus();
        assertTrue(pokerus.equals(new Pokerus(PokerusStates.HEALTHY)));
        assertFalse(pokerus.equals(new Pokerus(PokerusStates.INFECTED)));
        assertFalse(pokerus.equals(new Pokerus(PokerusStates.CURED)));
    }

}
