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

/**
 * Class that models the Original Trainer of a Pokemon
 */
public class OriginalTrainer {

    /**
     * The name of the Trainer
     */
    private String name;

    /**
     * The trainer's ID
     */
    private String id;

    /**
     * Creates a new OriginalTrainer object
     * @param name: The name of the trainer
     * @param id:   The trainer's ID number
     */
    public OriginalTrainer(String name, String id) {
        this.name = name;
        this.id = id;
    }

    /**
     * @return The trainer's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The trainer's ID number
     */
    public String getId() {
        return this.id;
    }

    /**
     * Checks two OriginalTrainer objects for equality
     * @param otherOriginalTrainer: the other Trainer
     * @return                      true if equal, else false
     */
    public boolean equals(OriginalTrainer otherOriginalTrainer) {
        return this.name.equals(otherOriginalTrainer.getName()) && this.id.equals(otherOriginalTrainer.getId());
    }

}
