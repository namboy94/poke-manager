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

import net.namibsun.pokemontracker.lib.pokemon.enums.Pokeballs;

/**
 * Class that models the Pokeball a Pokemon is caught in
 */
public class Pokeball {

    /**
     * The Pokeball Type
     */
    private Pokeballs ballType;

    /**
     * Creates a default Pokeball Object
     */
    public Pokeball() {
        this.ballType = Pokeballs.POKEBALL;
    }

    /**
     * Creates a Pokeball Object
     * @param ballType: The Pokeball type
     */
    public Pokeball(Pokeballs ballType) {
        this.ballType = ballType;
    }

    /**
     * Checks if the Pokeball is inheritable
     * @return true if the ball is inheritable, false otherwise
     */
    public boolean isInheritable() {
        return !(this.ballType == Pokeballs.MASTERBALL || this.ballType == Pokeballs.CHERISHBALL);
    }

    /**
     * Checks if the Pokeball is an Apricorn Ball, famously created by the legendary Pokeball creator Kurt.
     * @return true if the ball is an Apricorn Ball, false otherwise
     */
    public boolean isApricornBall() {

        return this.ballType == Pokeballs.FASTBALL || this.ballType == Pokeballs.FRIENDBALL ||
                this.ballType == Pokeballs.HEAVYBALL || this.ballType == Pokeballs.LEVELBALL ||
                this.ballType == Pokeballs.LOVEBALL || this.ballType == Pokeballs.LUREBALL ||
                this.ballType == Pokeballs.MOONBALL;

    }

    /**
     * @return The Pokeball Type
     */
    public Pokeballs getBallType() {
        return this.ballType;
    }

    /**
     * @return The Pokeball Type as String
     */
    public String getBallTypeAsString() {
        return this.ballType.name();
    }

    /**
     * Compares two Pokeball objects for equality
     * @param otherPokeball: the other Pokeball object
     * @return               true if equal, else false
     */
    public boolean equals(Pokeball otherPokeball) {
        return this.ballType == otherPokeball.getBallType();
    }

}
