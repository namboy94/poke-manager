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

import net.namibsun.pokemontracker.lib.models.enums.PokemonStatTypes;
import net.namibsun.pokemontracker.lib.webscraping.PokemonScraper;

/**
 * Class that models the effort value yield of a Pokemon
 */
public class EffortValueYield extends StatSpread {

    /**
     * Creates a new EffortValueYield object
     * @param hpYield:        The HP EV yield
     * @param attackYield:    The Attack EV yield
     * @param defenseYield:   The Defense EV yield
     * @param spAttackYield:  The Special Attack yield
     * @param spDefenseYield: The Special Defense yield
     * @param speedYield:     The Speed yield
     */
    public EffortValueYield(int hpYield,
                            int attackYield,
                            int defenseYield,
                            int spAttackYield,
                            int spDefenseYield,
                            int speedYield) {
        super(hpYield, attackYield, defenseYield, spAttackYield, spDefenseYield, speedYield);
    }

    /**
     * Generates a new EffortValueYield object from the information of a Web parser
     * @param parser: The parser to use
     * @return        The generated EffortValueYield object
     */
    public static EffortValueYield fromWebParser(PokemonScraper parser) {
        return new EffortValueYield(
                parser.parseEffortValueYield(PokemonStatTypes.HP),
                parser.parseEffortValueYield(PokemonStatTypes.ATK),
                parser.parseEffortValueYield(PokemonStatTypes.DEF),
                parser.parseEffortValueYield(PokemonStatTypes.SATK),
                parser.parseEffortValueYield(PokemonStatTypes.SDEF),
                parser.parseEffortValueYield(PokemonStatTypes.SPD)
        );
    }
}
