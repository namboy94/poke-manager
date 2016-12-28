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

package net.namibsun.pokemontracker.lib.pokemon;

import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.Ability;
import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.individual.EffortValueSpread;
import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.individual.Gender;
import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.individual.HeldItem;
import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.individual.IndividualValueSpread;

/**
 * Class that models a concrete Pokemon.
 */
public class Pokemon {

    /**
     * The Species of the Pokemon
     */
    private PokemonSpecies species;

    /**
     * The Pokemon's Effort Value Spread
     */
    private EffortValueSpread evSpread;

    /**
     * The Pokemon's Individual Value Spread
     */
    private IndividualValueSpread ivSpread;

    /**
     * The Pokemon's Gender
     */
    private Gender gender;

    /**
     * The Pokemon's Ability
     */
    private Ability ability;

    /**
     * The Pokemon's Held Item
     */
    private HeldItem heldItem;

    public Pokemon(PokemonSpecies species,
                   EffortValueSpread evSpread,
                   IndividualValueSpread ivSpread,
                   Ability ability,
                   Gender gender,
                   HeldItem heldItem) {
        this.species = species;
        this.evSpread = evSpread;
        this.ivSpread = ivSpread;
        this.ability = ability;
        this.gender = gender;
        this.heldItem = heldItem;
    }

    /**
     * @return The Pokemon's species
     */
    public PokemonSpecies getSpecies() {
        return this.species;
    }

    /**
     * @return The Pokemon's EV spread
     */
    public EffortValueSpread getEffortValueSpread() {
        return this.evSpread;
    }

    /**
     * @return The Pokemon's IV spread
     */
    public IndividualValueSpread getIndividualValueSpread() {
        return this.ivSpread;
    }

    /**
     * @return The Pokemon's ability
     */
    public Ability getAbility() {
        return this.ability;
    }

    /**
     * @return The Pokemon's gender
     */
    public Gender getGender() {
        return this.gender;
    }

    /**
     * @return The Pokemon's held item
     */
    public HeldItem getHeldItem() {
        return this.heldItem;
    }

    /**
     * Compares two Pokemon objects and checks for equality
     * @param otherPokemon: The Pokemon to compare against
     * @return              true if the two objects are equal, false otherwise
     */
    public boolean equals(Pokemon otherPokemon) {
        return  this.species.equals(otherPokemon.getSpecies()) &&
                this.evSpread.equals(otherPokemon.getEffortValueSpread()) &&
                this.ivSpread.equals(otherPokemon.getIndividualValueSpread()) &&
                this.ability.equals(otherPokemon.getAbility()) &&
                this.gender.equals(otherPokemon.getGender()) &&
                this.heldItem.equals(otherPokemon.getHeldItem());
    }

}
