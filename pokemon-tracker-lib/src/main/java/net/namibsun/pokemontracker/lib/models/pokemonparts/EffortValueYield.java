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
public class EffortValueYield {

    /**
     * The amount of HP effort values gained when defeating this Pokemon
     */
    private int hpYield;

    /**
     * The amount of Attack effort values gained when defeating this Pokemon
     */
    private int attackYield;

    /**
     * The amount of Defense effort values gained when defeating this Pokemon
     */
    private int defenseYield;

    /**
     * The amount of Special Attack effort values gained when defeating this Pokemon
     */
    private int spAttackYield;

    /**
     * The amount of Special Defense effort values gained when defeating this Pokemon
     */
    private int spDefenseYield;

    /**
     * The amount of Speed effort values gained when defeating this Pokemon
     */
    private int speedYield;

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
        this.hpYield = hpYield;
        this.attackYield = attackYield;
        this.defenseYield = defenseYield;
        this.spAttackYield = spAttackYield;
        this.spDefenseYield = spDefenseYield;
        this.speedYield = speedYield;
    }

    /**
     * @return The HP Effort Value Yield for this Pokemon
     */
    public int getHpYield() {
        return this.hpYield;
    }

    /**
     * @return The Attack Effort Value Yield for this Pokemon
     */
    public int getAttackYield() {
        return this.attackYield;
    }

    /**
     * @return The Defense Effort Value Yield for this Pokemon
     */
    public int getDefenseYield() {
        return this.defenseYield;
    }

    /**
     * @return The Special Attack Effort Value Yield for this Pokemon
     */
    public int getSpAttackYield() {
        return this.spAttackYield;
    }

    /**
     * @return The Special Defense Value Yield for this Pokemon
     */
    public int getSpDefenseYield() {
        return this.spDefenseYield;
    }

    /**
     * @return The Speed Effort Value Yield for this Pokemon
     */
    public int getSpeedYield() {
        return this.speedYield;
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

    /**
     * Compares two Effort Value Yields to one another
     * @param otherEvYield: The Effort Value Yield to compare to
     * @return              true if the objects are equal, false otherwise
     */
    public boolean equals(EffortValueYield otherEvYield) {
        return  this.hpYield == otherEvYield.getHpYield() &&
                this.attackYield == otherEvYield.getAttackYield() &&
                this.defenseYield == otherEvYield.getDefenseYield() &&
                this.spAttackYield == otherEvYield.getSpAttackYield() &&
                this.spDefenseYield == otherEvYield.getSpDefenseYield() &&
                this.speedYield == otherEvYield.getSpeedYield();
    }

}
