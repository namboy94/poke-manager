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

import net.namibsun.pokemontracker.lib.pokemon.enums.Pokeballs;
import org.junit.Test;
import static org.junit.Assert.*;

public class PokeballTest {

    @Test
    public void testDefaultPokeball() {
        assertEquals(new Pokeball().getBallType(), Pokeballs.POKEBALL);
    }

    @Test
    public void testApricornBalls() {
        assertTrue(new Pokeball(Pokeballs.LOVEBALL).isApricornBall());
        assertTrue(new Pokeball(Pokeballs.FRIENDBALL).isApricornBall());
        assertTrue(new Pokeball(Pokeballs.MOONBALL).isApricornBall());
        assertTrue(new Pokeball(Pokeballs.LEVELBALL).isApricornBall());
        assertTrue(new Pokeball(Pokeballs.FASTBALL).isApricornBall());
        assertTrue(new Pokeball(Pokeballs.HEAVYBALL).isApricornBall());
        assertTrue(new Pokeball(Pokeballs.LUREBALL).isApricornBall());
        assertFalse(new Pokeball(Pokeballs.MASTERBALL).isApricornBall());
    }

    @Test
    public void testInheritableBalls() {
        assertTrue(new Pokeball(Pokeballs.GREATBALL).isInheritable());
        assertFalse(new Pokeball(Pokeballs.MASTERBALL).isInheritable());
        assertFalse(new Pokeball(Pokeballs.CHERISHBALL).isInheritable());
    }

    @Test
    public void testEquality() {
        Pokeball pokeball = new Pokeball();
        assertTrue(pokeball.equals(new Pokeball(Pokeballs.POKEBALL)));
        assertFalse(pokeball.equals(new Pokeball(Pokeballs.MASTERBALL)));
    }

}
