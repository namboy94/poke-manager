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


public class Pokerus {

    private PokerusStates state;

    public Pokerus() {
        this.state = PokerusStates.HEALTHY;
    }

    public Pokerus(PokerusStates state) {
        this.state = state;
    }

    public PokerusStates getState() {
        return this.state;
    }

    public boolean hasGrowthBenefit() {
        return this.state == PokerusStates.INFECTED;
    }

    public boolean canBeInfected() {
        return this.state == PokerusStates.HEALTHY;
    }

    public boolean equals(Pokerus otherPokerus) {
        return this.state == otherPokerus.getState();
    }

}
