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

    /**
     * Constructor for a Pokemon with a single egg group
     * @param primaryEggGroup: The egg group
     */
    public EggGroups(EggGroupTypes primaryEggGroup) {
        this(primaryEggGroup, null);
    }

    /**
     * Stores the egg groups in private variables
     * @param primaryEggGroup:   The primary egg group
     * @param secondaryEggGroup: The secondary egg group
     */
    public EggGroups(EggGroupTypes primaryEggGroup, EggGroupTypes secondaryEggGroup) {
        this.primaryEggGroup = primaryEggGroup;
        this.secondaryEggGroup = secondaryEggGroup;
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
     * Generates a new EggGroup from a Web Parser's information
     * @param parser: the parser to use
     * @return        the generated Egg Group
     */
    public static EggGroups fromWebParser(PokemonScraper parser) {
        String[] eggGroups = parser.parseEggGroups();
        if (eggGroups.length == 1) {
            return new EggGroups(EggGroupTypes.valueOf(eggGroups[0]));
        }
        else {
            return new EggGroups(EggGroupTypes.valueOf(eggGroups[0]), EggGroupTypes.valueOf(eggGroups[1]));
        }
    }

}
