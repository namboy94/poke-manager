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

public class OriginalTrainerTest {

    @Test
    public void testEquality() {
        OriginalTrainer trainer = new OriginalTrainer("ABC", "123");
        assertTrue(trainer.equals(new OriginalTrainer("ABC", "123")));
        assertFalse(trainer.equals(new OriginalTrainer("ABC", "125")));
        assertFalse(trainer.equals(new OriginalTrainer("ABCABC", "123")));
    }

}
