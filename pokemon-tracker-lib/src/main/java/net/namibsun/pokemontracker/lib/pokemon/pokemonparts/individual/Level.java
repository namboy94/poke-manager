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
 * Class that models the level of a Pokemon
 */
public class Level {

    /**
     * The level of the Pokemon. It is always a value between 1 and 100
     */
    private int level;

    /**
     * Creates a new Level object. It is checked if the given level is between 1 and 100
     * @param level:                     The current level of the Pokemon. Must be at least 1 and at most 100
     * @throws IllegalArgumentException: If the passed level is not between 1 and 100
     */
    public Level(int level) {
        if (level < 1 || level > 100) {
            throw new IllegalArgumentException("Level must be between 1 and 100");
        }
        this.level = level;
    }

    /**
     * @return The Pokemon's current level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Compares a Level object with another level object
     * @param otherLevel: The other level object
     * @return            true if both objects are equal, false otherwise
     */
    public boolean equals(Level otherLevel) {
        return this.level == otherLevel.getLevel();
    }

}
