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

package net.namibsun.pokemontracker.lib.webscraping;

import java.util.HashMap;
import net.namibsun.pokemontracker.lib.models.enums.PokemonStatTypes;

/**
 * Defines an interface for a Pokemon Web Scraper
 */
public interface PokemonScraper {

    /**
     * @return The URL used by this parser/scraper
     */
    String getUrl();

    /**
     * Fetches all names for the Pokemon and turns it into a Hashmap
     * @return The Hashmap containing the names with the languages of the names as keys
     */
    HashMap<String, String> parsePokemonName();

    /**
     * Parses the Gender of the Pokemon.
     * @return an array of two double values, representing the male and female ratio respectively.
     *         If the Pokemon is neutrally gendered, null is returned.
     */
    double[] parseGenderRatio();

    /**
     * Parses the types of a Pokemon
     * @return an array of types for the Pokemon, as capitalized Strings
     */
    String[] parseTypes();

    /**
     * Parse the weight of the Pokemon
     * @return an array of doubles, with the first element being in kilograms, the second in pounds
     */
    double[] parseWeight();

    /**
     * Parses the height of the Pokemon
     * @return an array of doubles, with the first element being in meter, the second in feet(').inches(")
     */
    double[] parseHeight();

    /**
     * Parses the species classification of the Pokemon
     * @return the classification of the pokemon
     */
    String parseClassification();

    /**
     * Parses the Pokemon's capture rate
     * @return The Capture Rate
     */
    int parseCaptureRate();

    /**
     * Parses the Pokemon's required amount of steps to hatch
     * @return The Pokemon's Base Egg Step amount
     */
    int parseBaseEggSteps();

    /**
     * Parses the Pokemon's base happiness value
     * @return the Pokemon's base happiness
     */
    int parseBaseHappiness();

    /**
     * Parses the Pokemon's amount of XP required to reach Level 100
     * @return the Pokemon's Experience Points at level 100
     */
    int parseExperienceGrowthPoints();

    /**
     * Parses the Pokemon's experience growth speed description
     * @return The experience growth speed description
     */
    String parseExperienceGrowthDescription();

    /**
     * Parses the Effort Value yield for a specified Stat type
     * @param statType: The stat type to search for the EV yield
     * @return          The EV yield
     */
    int parseEffortValueYield(PokemonStatTypes statType);

    /**
     * Parses the regular abilities of a Pokemon
     * @return an array of the form:
     *         [Ability 1 name, Ability 1 description, Ability 2 name, Ability 2 description]
     *         or, if the Pokemon has no second regular abilty, like this:
     *         [Ability 1 name, Ability 2 description]
     */
    String[] parseRegularAbilities();

    /**
     * Parses the hidden ability of a Pokemon
     * @return an array of the form [Ability Name, Description] or null if the Pokemon has no hidden ability
     */
    String[] parseHiddenAbility();

    /**
     * Parses the base stats of a Pokemon
     * @return these stats in an ordered Array in the following order:
     *           HP, Attack, Defense, Special Attack, Special Defense, Speed
     */
    int[] parseBaseStats();

    /**
     * Parses the egg groups of a Pokemon
     * @return the egg groups as an array of strings of length 3 in the following order:
     *         the primary egg group, the secondary egg group, an indicator if the Pokemon is genderless
     *             (genderless = 'true', has gender = 'false')
     */
    String[] parseEggGroups();

}
