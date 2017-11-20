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

import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.StatSpread;

/**
 * Models an Effort Value (EV) Spread of a Pokemon.
 * Effort Values range from 0 to 252 per stat, with a total maximum of 510 Points per Pokemon
 */
public class EffortValueSpread extends StatSpread {

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
    public EffortValueSpread(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        super(hp, attack, defense, specialAttack, specialDefense, speed);
        if (hp + attack + defense + specialAttack + specialDefense + speed > 510) {
            throw new NumberFormatException("Too many Effort Values!");
        }
    }

    /**
     * Sets the valid ranges of the EV Spread
     */
    @Override
    protected void setValidRanges() {
        this.lowerRangeLimit = 0;
        this.upperRangeLimit = 252;
    }
}
