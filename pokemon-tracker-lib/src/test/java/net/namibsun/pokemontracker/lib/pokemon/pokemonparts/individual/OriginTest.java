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

import net.namibsun.pokemontracker.lib.pokemon.enums.Games;
import net.namibsun.pokemontracker.lib.pokemon.enums.Generations;
import org.junit.Test;
import static org.junit.Assert.*;

public class OriginTest {

    @Test
    public void testInferringGeneration() {
        assertEquals(Generations.I, new Origin(Games.RED).getOriginGeneration());
        assertEquals(Generations.II, new Origin(Games.SILVER).getOriginGeneration());
        assertEquals(Generations.III, new Origin(Games.XD).getOriginGeneration());
        assertEquals(Generations.IV, new Origin(Games.DIAMOND).getOriginGeneration());
        assertEquals(Generations.V, new Origin(Games.BLACK2).getOriginGeneration());
        assertEquals(Generations.VI, new Origin(Games.OMEGARUBY).getOriginGeneration());
        assertEquals(Generations.VII, new Origin(Games.MOON).getOriginGeneration());
        assertEquals(Generations.UNKNOWN, new Origin(Games.UNKNOWN).getOriginGeneration());
    }

    @Test
    public void testEquality() {
        Origin origin = new Origin(Games.RED);
        assertTrue(origin.equals(new Origin(Games.RED, Generations.I)));
        assertFalse(origin.equals(new Origin(Games.BLUE)));
        assertFalse(origin.equals(new Origin(Games.RED, Generations.VI)));
    }

    @Test
    public void testGeneratingUnknownOrigin() {
        Origin origin = new Origin();
        assertEquals(origin.getOriginGame(), Games.UNKNOWN);
        assertEquals(origin.getOriginGeneration(), Generations.UNKNOWN);
    }

}
