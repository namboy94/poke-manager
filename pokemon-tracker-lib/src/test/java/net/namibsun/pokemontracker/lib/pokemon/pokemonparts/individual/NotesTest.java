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

import java.util.ArrayList;

import static org.junit.Assert.*;

public class NotesTest {

    @SuppressWarnings("RedundantArrayCreation")
    @Test
    public void testEquality() {
        assertTrue(new Notes("A", "B").equals(new Notes(new String[] {"A", "B"})));
        assertFalse(new Notes("A", "B").equals(new Notes(new String[] {"A", "C"})));
    }

    @Test
    public void testGeneratingCsv() {
        Notes notes = new Notes("A", "B", "C", "D", "E");
        assertEquals(notes.getCommaSeparatedNotes(), "A,B,C,D,E");
    }

    @Test
    public void testGettingArrayList() {
        Notes notes = new Notes("A", "B", "C", "D", "E");
        ArrayList<String> list = notes.getNotes();

        assertEquals(list.get(0), "A");
        assertEquals(list.get(1), "B");
        assertEquals(list.get(2), "C");
        assertEquals(list.get(3), "D");
        assertEquals(list.get(4), "E");
    }

}
