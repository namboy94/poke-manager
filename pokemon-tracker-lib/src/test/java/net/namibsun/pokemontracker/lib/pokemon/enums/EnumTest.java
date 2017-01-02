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

package net.namibsun.pokemontracker.lib.pokemon.enums;

import org.junit.Test;
import static org.junit.Assert.*;

public class EnumTest {

    @Test
    public void testDamageCategoriesEnum() {
        assertEquals(DamageCategories.valueOf("SPECIAL"), DamageCategories.SPECIAL);
        assertEquals(DamageCategories.values().length, 3);
    }

    @Test
    public void testEggGroupTypesEnum() {
        assertEquals(EggGroupTypes.valueOf("AMORPHOUS"), EggGroupTypes.AMORPHOUS);
        assertEquals(EggGroupTypes.values().length, 15);
    }

    @Test
    public void testGamesEnum() {
        assertEquals(Games.valueOf("RED"), Games.RED);
        assertEquals(Games.values().length, 30);
    }

    @Test
    public void testGenerationsEnum() {
        assertEquals(Generations.valueOf("I"), Generations.I);
        assertEquals(Generations.values().length, 8);
    }

    @Test
    public void testLanguagesEnum() {
        assertEquals(Languages.valueOf("ENGLISH"), Languages.ENGLISH);
        assertEquals(Languages.values().length, 5);
    }

    @Test
    public void testNaturesEnum() {
        assertEquals(Natures.valueOf("ADAMANT"), Natures.ADAMANT);
        assertEquals(Natures.values().length, 25);
    }

    @Test
    public void testPokeballsEnum() {
        assertEquals(Pokeballs.valueOf("POKEBALL"), Pokeballs.POKEBALL);
        assertEquals(Pokeballs.values().length, 23);
    }

    @Test
    public void testPokerusEnum() {
        assertEquals(PokerusStates.valueOf("HEALTHY"), PokerusStates.HEALTHY);
        assertEquals(PokerusStates.values().length, 3);
    }

    @Test
    public void testPokemonStatTypesEnum() {
        assertEquals(PokemonStatTypes.valueOf("HP"), PokemonStatTypes.HP);
        assertEquals(PokemonStatTypes.values().length, 6);
    }

    @Test
    public void testPokemonTypesEnum() {
        assertEquals(PokemonTypes.valueOf("BUG"), PokemonTypes.BUG);
        assertEquals(PokemonTypes.values().length, 18);
    }


}
