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

}
