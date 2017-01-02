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

    public Pokeball() {
        this.ballType = Pokeballs.POKEBALL;
    }

    public Pokeball(Pokeballs ballType) {
        this.ballType = ballType;
    }

    public boolean isInheritable() {
        return !(this.ballType == Pokeballs.MASTERBALL || this.ballType == Pokeballs.CHERISHBALL);
    }

    public boolean isApricornBall() {
        return false; // TODO Check which balls are apricorn balls
    }

    public Pokeballs getBallType() {
        return this.ballType;
    }

    public String getBallTypeAsString() {
        return this.ballType.name();
    }

    public boolean equals(Pokeball otherPokeball) {
        return this.ballType == otherPokeball.getBallType();
    }

}
