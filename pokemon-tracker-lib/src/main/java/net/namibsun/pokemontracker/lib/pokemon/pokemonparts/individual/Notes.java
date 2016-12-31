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

import java.util.ArrayList;

/**
 * Class that models user-defines notes for a Pokemon
 */
public class Notes {

    /**
     * List of notes for the Pokemon
     */
    private ArrayList<String> notes;

    /**
     * Creates a new Notes object
     * @param noteStrings: Variable amount of String parameters, each representing a new note
     */
    public Notes(String... noteStrings) {
        this.notes = new ArrayList<>();
        for (String note: noteStrings) {
            this.notes.add(note.replace(";", ","));
        }
    }

    /**
     * @return The notes as an array list
     */
    public ArrayList<String> getNotes() {
        return this.notes;
    }

    /**
     * @return The notes as a comma separated string
     */
    public String getCommaSeparatedNotes() {
        String commaNotes = "";
        for (String note: this.notes) {
            commaNotes += note + ",";
        }
        return commaNotes.substring(0, commaNotes.length() - 1); // Remove last Comma
    }

    /**
     * Compares two Notes objects.
     * @param otherNotes: The other Notes objects
     * @return            true if equal, else false
     */
    public boolean equals(Notes otherNotes) {
        return this.getCommaSeparatedNotes().equals(otherNotes.getCommaSeparatedNotes());
    }

}
