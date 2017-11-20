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
import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.individual.*;

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

    /**
     * The Pokemon's Level
     */
    private Level level;

    /**
     * The Pokemon's Moveset
     */
    private MoveSet moveSet;

    /**
     * The Pokemon's nature
     */
    private Nature nature;

    /**
     * Notes describing this Pokemon
     */
    private Notes notes;

    /**
     * The Pokemon's origin
     */
    private Origin origin;

    /**
     * The Pokemon's original trainer
     */
    private OriginalTrainer originalTrainer;

    /**
     * The Pokeball the Pokemon was caught in.
     */
    private Pokeball pokeball;

    /**
     * The Pokerus (Pokemon Virus) state of the Pokemon
     */
    private Pokerus pokerus;

    /**
     * Creates a new Pokemon object
     * @param species:         The Pokemon's Species
     * @param evSpread:        The Pokemon's EV Spread
     * @param ivSpread:        The Pokemon's IV Spread
     * @param ability:         The Pokemon's Ability
     * @param gender:          The Pokemon's Gender
     * @param heldItem:        The Pokemon's Held Item
     * @param level:           The Pokemon's level
     * @param moveSet:         The Pokemon's MoveSet
     * @param nature:          The Pokemon's Nature
     * @param notes:           Notes about the Pokemon
     * @param origin:          The Pokemon's Origin
     * @param originalTrainer: The Pokemon's original trainer
     * @param pokeball:        The Pokeball in which the Pokemon was caught in
     * @param pokerus:         The Pokemon's Pokerus state
     */
    public Pokemon(PokemonSpecies species,
                   EffortValueSpread evSpread,
                   IndividualValueSpread ivSpread,
                   Ability ability,
                   Gender gender,
                   HeldItem heldItem,
                   Level level,
                   MoveSet moveSet,
                   Nature nature,
                   Notes notes,
                   Origin origin,
                   OriginalTrainer originalTrainer,
                   Pokeball pokeball,
                   Pokerus pokerus) {
        this.species = species;
        this.evSpread = evSpread;
        this.ivSpread = ivSpread;
        this.ability = ability;
        this.gender = gender;
        this.heldItem = heldItem;
        this.level = level;
        this.moveSet = moveSet;
        this.nature = nature;
        this.notes = notes;
        this.origin = origin;
        this.originalTrainer = originalTrainer;
        this.pokeball = pokeball;
        this.pokerus = pokerus;
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
     * @return The Pokemon's level
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * @return The Pokemon's MoveSet
     */
    public MoveSet getMoveSet() {
        return this.moveSet;
    }

    /**
     * @return The Pokemon's Nature
     */
    public Nature getNature() {
        return this.nature;
    }

    /**
     * @return The Notes pertaining to this Pokemon
     */
    public Notes getNotes() {
        return this.notes;
    }

    /**
     * @return The Pokemon's Origin
     */
    public Origin getOrigin() {
        return this.origin;
    }

    /**
     * @return The pokemon's original trainer
     */
    public OriginalTrainer getOriginalTrainer() {
        return this.originalTrainer;
    }

    /**
     * @return The Pokeball in which this Pokemon was caught in
     */
    public Pokeball getPokeball() {
        return this.pokeball;
    }

    /**
     * @return The Pokemon's Pokerus State
     */
    public Pokerus getPokerus() {
        return this.pokerus;
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
                this.heldItem.equals(otherPokemon.getHeldItem()) &&
                this.level.equals(otherPokemon.getLevel()) &&
                this.moveSet.equals(otherPokemon.getMoveSet()) &&
                this.nature.equals(otherPokemon.getNature()) &&
                this.notes.equals(otherPokemon.getNotes()) &&
                this.origin.equals(otherPokemon.getOrigin()) &&
                this.originalTrainer.equals(otherPokemon.getOriginalTrainer()) &&
                this.pokeball.equals(otherPokemon.getPokeball()) &&
                this.pokerus.equals(otherPokemon.getPokerus());
    }

}