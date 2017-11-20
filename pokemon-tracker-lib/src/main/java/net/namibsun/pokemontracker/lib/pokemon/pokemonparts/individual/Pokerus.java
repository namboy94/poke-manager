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

import net.namibsun.pokemontracker.lib.pokemon.enums.PokerusStates;

/**
 * Class that models the Pokerus state of a Pokemon.
 * Pokerus, or Pokemon Virus enhances the EV gain of a Pokemon
 */
public class Pokerus {

    /**
     * The current Pokerus State
     */
    private PokerusStates state;

    /**
     * Creates a Pokerus object in the HEALTHY state
     */
    public Pokerus() {
        this.state = PokerusStates.HEALTHY;
    }

    /**
     * Creates a Pokerus object in a specific state
     * @param state: The Pokerus State
     */
    public Pokerus(PokerusStates state) {
        this.state = state;
    }

    /**
     * @return The current Pokerus State
     */
    public PokerusStates getState() {
        return this.state;
    }

    /**
     * Checks if the Pokemon currently receives an EV gain boost
     * @return true if the Pokemon is currently infected, false if Healthy ot already cured
     */
    public boolean hasGrowthBenefit() {
        return this.state == PokerusStates.INFECTED;
    }

    /**
     * Checks if the Pokemon can still be infected by Pokerus
     * @return true if the Pokemon is healthy, false otherwise
     */
    public boolean canBeInfected() {
        return this.state == PokerusStates.HEALTHY;
    }

    /**
     * Checks two Pokerus objects for equality
     * @param otherPokerus: The other Pokerus object
     * @return              true if equal, false otherwise
     */
    public boolean equals(Pokerus otherPokerus) {
        return this.state == otherPokerus.getState();
    }

}
