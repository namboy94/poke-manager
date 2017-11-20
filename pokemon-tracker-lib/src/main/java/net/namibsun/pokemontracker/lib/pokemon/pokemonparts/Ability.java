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

package net.namibsun.pokemontracker.lib.pokemon.pokemonparts;

/**
 * Models a Pokemon's ability
 */
public class Ability {

    /**
     * The ability's name
     */
    private String name;

    /**
     * A description of the ability
     */
    private String description;

    /**
     * Is set to true if the ability is a hidden ability
     */
    private boolean hidden;

    /**
     * Creates a new Ability object
     * @param name:            The name of the ability
     * @param description:     A description of the ability
     * @param isHiddenAbility: Sets if the ability is a hidden ability or not
     */
    public Ability(String name, String description, boolean isHiddenAbility) {
        this.name = name;
        this.description = description;
        this.hidden = isHiddenAbility;
    }

    /**
     * @return The name of the ability
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The description of the ability
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return true, if this is a hidden ability, false otherwise
     */
    public boolean isHiddenAbility() {
        return this.hidden;
    }

    /**
     * Compares two abilities and checks if they are equal
     * @param otherAbility: The ability against which to check against
     * @return              true, if the objects are equal, false otherwise
     */
    public boolean equals(Ability otherAbility) {
        return  this.name.equals(otherAbility.getName()) &&
                this.description.equals(otherAbility.getDescription()) &&
                this.hidden == otherAbility.isHiddenAbility();
    }

}
