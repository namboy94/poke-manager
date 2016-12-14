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

package net.namibsun.pokemontracker.lib.pokemon.species.pokemonparts;

import net.namibsun.pokemontracker.lib.webscraping.PokemonScraper;

/**
 * The base stats of a Pokemon
 */
public class BaseStats extends StatSpread {

    /**
     * Basic Constructor, that sets the internal stat values and checks if they are below the specified threshold.
     *
     * @param hp:             The HP value
     * @param attack:         The Attack Value
     * @param defense:        The Defense Value
     * @param specialAttack:  The Special Attack Value
     * @param specialDefense: The Special Defense Value
     * @param speed:          The Speed Value
     */
    public BaseStats(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        super(hp, attack, defense, specialAttack, specialDefense, speed);
    }

    /**
     * Sets the limits of the ranges to 1 and 255
     */
    @Override
    protected void setValidRanges() {
        this.upperRangeLimit = 255;
        this.lowerRangeLimit = 1;
    }

    /**
     * Generates a new BaseStats object from a Web Parser's information
     * @param parser: The parser to use
     * @return        The newly generated BaseStats object
     */
    public static BaseStats fromWebParser(PokemonScraper parser) {
        int[] stats = parser.parseBaseStats();
        return new BaseStats(stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]);
    }

}
