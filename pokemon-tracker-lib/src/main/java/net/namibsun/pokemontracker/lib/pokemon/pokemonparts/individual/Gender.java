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

import net.namibsun.pokemontracker.lib.pokemon.enums.GenderTypes;

/**
 * Models the Gender of a Pokemon
 */
public class Gender {

    /**
     * The gender of the Pokemon
     */
    private GenderTypes gender;

    /**
     * Creates a new Gender object
     * @param gender: The gender of the Pokemon
     */
    public Gender(GenderTypes gender) {
        this.gender = gender;
    }

    /**
     * @return The Gender type
     */
    public GenderTypes getGender() {
        return this.gender;
    }

    /**
     * @return The Gender type as a string
     */
    public String getGenderString() {
        return this.gender.name();
    }

    /**
     * @return The gender symbol, i.e. ♂ for males, ♀ for females and - for neutral
     */
    public String getGenderSymbol() {
        switch (this.gender) {
            case MALE:    return "♂";
            case FEMALE:  return "♀";
            case NEUTRAL: return "-";
        }
        return null; // Will never be reached
    }

    /**
     * Checks for equality between two Gender objects
     * @param otherGender: The other Gender object
     * @return             true, if the objects are equal, false otherwise
     */
    public boolean equals(Gender otherGender) {
        return this.gender == otherGender.getGender();
    }

}
