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

package net.namibsun.pokemontracker.lib.models.pokemonparts;

import net.namibsun.pokemontracker.lib.models.enums.EggGroupTypes;
import net.namibsun.pokemontracker.lib.webscraping.PokemonScraper;

/**
 * Class that models the Egg groups of a Pokemon
 */
public class EggGroups {

    /**
     * The Pokemon's primary egg group
     */
    private EggGroupTypes primaryEggGroup;

    /**
     * The Pokemon's secondary egg group, if one is present
     */
    private EggGroupTypes secondaryEggGroup;

    private boolean onlyWithDitto;

    /**
     * Constructor for a Pokemon with a single egg group
     * @param primaryEggGroup: The egg group
     * @param genderless:      Indicates that the Pokemon is genderless
     */
    public EggGroups(EggGroupTypes primaryEggGroup, boolean genderless) {
        this(primaryEggGroup, null, genderless);
    }

    /**
     * Stores the egg groups in private variables
     * @param primaryEggGroup:   The primary egg group
     * @param secondaryEggGroup: The secondary egg group
     * @param genderless:        Indicates that the Pokemon is genderless
     */
    public EggGroups(EggGroupTypes primaryEggGroup, EggGroupTypes secondaryEggGroup, boolean genderless) {
        this.primaryEggGroup = primaryEggGroup;
        this.secondaryEggGroup = secondaryEggGroup;
        this.onlyWithDitto = genderless && primaryEggGroup != EggGroupTypes.UNDISCOVERED;
    }

    /**
     * Checks if the Pokemon has multiple egg groups
     * @return true, if the Pokemon has multiple egg groups, false otherwise
     */
    public boolean hasTwoEggGroups() {
        return this.secondaryEggGroup != null;
    }

    /**
     * @return the primary egg group
     */
    public EggGroupTypes getPrimaryEggGroup() {
        return this.primaryEggGroup;
    }

    /**
     * @return the secondary egg group, or null if the Pokemon does not have two egg groups
     */
    public EggGroupTypes getSecondaryEggGroup() {
        return this.secondaryEggGroup;
    }

    /**
     * Checks if the Pokemon is able to breed
     * @return true if it is able to breed, false otherwise
     */
    public boolean canBreed() {
        return this.primaryEggGroup != EggGroupTypes.UNDISCOVERED;
    }

    /**
     * Checks if the Pokemon can only breed with Ditto
     * @return true if the Pokemon can only breed with Ditto, false otherwise
     */
    public boolean canOnlyBreedWithDitto() {
        return onlyWithDitto;
    }

    /**
     * Generates a new EggGroup from a Web Parser's information
     * @param parser: the parser to use
     * @return        the generated Egg Group
     */
    public static EggGroups fromWebParser(PokemonScraper parser) {

        String[] eggGroups = parser.parseEggGroups();

        try {
            return new EggGroups(
                    EggGroupTypes.valueOf(eggGroups[0]),
                    EggGroupTypes.valueOf(eggGroups[1]),
                    Boolean.parseBoolean(eggGroups[2])
            );
        } catch (NullPointerException e) {
            return new EggGroups(
                    EggGroupTypes.valueOf(eggGroups[0]),
                    Boolean.parseBoolean(eggGroups[2])
            );
        }
    }

    /**
     * Compares two EggGroups object
     * @param otherEggGroups: The EggGroups object to compare against
     * @return                true if the objects are equal, false otherwise
     */
    public boolean equals(EggGroups otherEggGroups) {

        return  this.primaryEggGroup == otherEggGroups.getPrimaryEggGroup() &&
                this.secondaryEggGroup == otherEggGroups.getSecondaryEggGroup();

    }
}
