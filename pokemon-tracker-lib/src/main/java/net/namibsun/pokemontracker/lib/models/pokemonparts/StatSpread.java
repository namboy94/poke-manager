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

import net.namibsun.pokemontracker.lib.webscraping.PokemonScraper;

public abstract class StatSpread {

    /**
     * The Hit Point Value of a Pokemon
     */
    protected int hp;

    /**
     * The Physical Attack Value of a Pokemon
     */
    protected int attack;

    /**
     * The Physical Defense Value of a Pokemon
     */
    protected int defense;

    /**
     * The Special Attack Value of a Pokemon
     */
    protected int specialAttack;

    /**
     * The Special Defense Value of a Pokemon
     */
    protected int specialDefense;

    /**
     * The Speed Value of a Pokemon
     */
    protected int speed;

    /**
     * A limiter on the Pokemon's stat. Can be used to create different types of spreads, for example IV or EV spreads.
     */
    protected int upperRangeLimit;

    /**
     * A limiter on what the lowest possible Stat a Pokemon can have is.
     */
    protected int lowerRangeLimit;

    /**
     * Basic Constructor, that sets the internal stat values and checks if they are below the specified threshold.
     * @param hp:             The HP value
     * @param attack:         The Attack Value
     * @param defense:        The Defense Value
     * @param specialAttack:  The Special Attack Value
     * @param specialDefense: The Special Defense Value
     * @param speed:          The Speed Value
     */
    public StatSpread(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {

        this.setValidRanges();

        this.hp = this.checkValue(hp);
        this.attack = this.checkValue(attack);
        this.defense = this.checkValue(defense);
        this.specialAttack = this.checkValue(specialAttack);
        this.specialDefense = this.checkValue(specialDefense);
        this.speed = this.checkValue(speed);
        
    }

    /**
     * Checks if a value is in the given range
     * Throws a NumberFormatException if a discrepancy is detected
     * @param value: The value to check
     * @return       The value, but only if no discrepancy was detected
     */
    private int checkValue(int value) {
        if (value <= this.upperRangeLimit && value >= this.lowerRangeLimit) {
            return value;
        }
        else {
            throw new NumberFormatException("Stat out of Range");
        }
    }

    /**
     * Sets the valid ranges of the Stat Spread
     */
    protected void setValidRanges() {
        this.upperRangeLimit = Integer.MAX_VALUE;
        this.lowerRangeLimit = 0;
    }

    /**
     * @return The HP value
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * @return The Attack value
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * @return The Defense value
     */
    public int getDefense() {
        return this.defense;
    }

    /**
     * @return The Special Attack value
     */
    public int getSpecialAttack() {
        return this.specialAttack;
    }

    /**
     * @return The Special Defense value
     */
    public int getSpecialDefense() {
        return this.specialDefense;
    }

    /**
     * @return The Speed value
     */
    public int getSpeed() {
        return this.speed;
    }

}
