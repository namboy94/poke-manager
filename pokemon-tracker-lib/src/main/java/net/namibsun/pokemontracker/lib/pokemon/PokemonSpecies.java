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

import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.species.*;
import net.namibsun.pokemontracker.lib.webscraping.PokemonScraper;

/**
 * Class that models a Pokemon of a specific species
 */
public class PokemonSpecies {

    /**
     * The national Pokedex number of this Pokemon
     */
    private int pokedexNumber;

    /**
     * The name of this Pokemon
     */
    private Name name;

    /**
     * The gender ratio of this Pokemon type
     */
    private GenderRatio genderRatio;

    /**
     * The typing of this Pokemon
     */
    private Type type;

    /**
     * The physical description of the Pokemon species
     */
    private SpeciesDescription description;

    /**
     * Various Rates of the Pokemon, like the capture rate
     */
    private Rates rates;

    /**
     * The Effort Value yield when defeating this Pokemon
     */
    private EffortValueYield evYield;

    /**
     * The Pokemon's abilities
     */
    private Abilities abilities;

    /**
     * The Base Stats of the Pokemon
     */
    private BaseStats baseStats;

    /**
     * The egg group information of the Pokemon
     */
    private EggGroups eggGroups;

    /**
     * Creates a new Pokemon Species object
     * @param pokedexNumber: The Pokedex Number of this Pokemon
     * @param name:          The name of the pokemon
     * @param genderRatio:   The gender ratio of the Pokemon
     * @param type:          The Pokemon' type
     * @param description:   The Pokemon's species' description
     * @param rates:         The various rate values of the Pokemon
     * @param evYield:       The EV yield of the Pokemon
     * @param abilities:       The abilities of the Pokemon
     * @param baseStats:     The Base Stats of the Pokemon
     * @param eggGroups:     The Egg Groups of the Pokemon
     */
    public PokemonSpecies(int pokedexNumber, Name name, GenderRatio genderRatio, Type type,
                          SpeciesDescription description, Rates rates, EffortValueYield evYield,
                          Abilities abilities, BaseStats baseStats, EggGroups eggGroups) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.genderRatio = genderRatio;
        this.type = type;
        this.description = description;
        this.rates = rates;
        this.evYield = evYield;
        this.abilities = abilities;
        this.baseStats = baseStats;
        this.eggGroups = eggGroups;
    }

    /**
     * Creates a new PokemonSpecies object using a Web Parser.
     * @param pokedexNumber: The Pokemon's Pokedex Number
     * @param parser:        The parser to be used
     */
    public PokemonSpecies(int pokedexNumber, PokemonScraper parser) {

        this.pokedexNumber = pokedexNumber;
        this.name = Name.fromWebParser(parser);
        this.genderRatio = GenderRatio.fromWebParser(parser);
        this.type = Type.fromWebParser(parser);
        this.description = SpeciesDescription.fromWebParser(parser);
        this.rates = Rates.fromWebParser(parser);
        this.evYield = EffortValueYield.fromWebParser(parser);
        this.abilities = Abilities.fromWebParser(parser);
        this.baseStats = BaseStats.fromWebParser(parser);
        this.eggGroups = EggGroups.fromWebParser(parser);
    }

    /**
     * @return The Pokemon Species' Pokedex number
     */
    public int getPokedexNumber() {
        return this.pokedexNumber;
    }

    /**
     * @return The Pokemon Species' Name
     */
    public Name getName() {
        return this.name;
    }

    /**
     * @return The Pokemon Species' Gender Ratio
     */
    public GenderRatio getGenderRatio() {
        return this.genderRatio;
    }

    /**
     * @return The Pokemon Species' Type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * @return The Pokemon Species' description
     */
    public SpeciesDescription getSpeciesDescription() {
        return this.description;
    }

    /**
     * @return The Pokemon Species' rates
     */
    public Rates getRates() {
        return this.rates;
    }

    /**
     * @return The Pokemon Species' effort value yield
     */
    public EffortValueYield getEffortValueYield() {
        return this.evYield;
    }

    /**
     * @return The Pokemon Species' abilities
     */
    public Abilities getAbilities() {
        return this.abilities;
    }

    /**
     * @return The Pokemon Species' base stats
     */
    public BaseStats getBaseStats() {
        return this.baseStats;
    }

    /**
     * @return The Pokemon Species' egg groups
     */
    public EggGroups getEggGroups() {
        return this.eggGroups;
    }

    /**
     * Compares a Pokemon Species with another Pokemon Species
     * @param otherSpecies: The Species to compare this species to
     * @return              true if both species are equal, false otherwise
     */
    public boolean equals(PokemonSpecies otherSpecies) {

        System.out.println(this.pokedexNumber == otherSpecies.pokedexNumber);
        System.out.println(this.name.equals(otherSpecies.getName()));
        System.out.println(this.genderRatio.equals(otherSpecies.getGenderRatio()));
        System.out.println(this.type.equals(otherSpecies.getType()));
        System.out.println(this.description.equals(otherSpecies.getSpeciesDescription()));
        System.out.println(this.rates.equals(otherSpecies.getRates()));
        System.out.println(this.abilities.equals(otherSpecies.getAbilities()));
        System.out.println(this.evYield.equals(otherSpecies.getEffortValueYield()));
        System.out.println(this.baseStats.equals(otherSpecies.getBaseStats()));
        System.out.println(this.eggGroups.equals(otherSpecies.getEggGroups()));

        return  this.pokedexNumber == otherSpecies.pokedexNumber &&
                this.name.equals(otherSpecies.getName()) &&
                this.genderRatio.equals(otherSpecies.getGenderRatio()) &&
                this.type.equals(otherSpecies.getType()) &&
                this.description.equals(otherSpecies.getSpeciesDescription()) &&
                this.rates.equals(otherSpecies.getRates()) &&
                this.abilities.equals(otherSpecies.getAbilities()) &&
                this.evYield.equals(otherSpecies.getEffortValueYield()) &&
                this.baseStats.equals(otherSpecies.getBaseStats()) &&
                this.eggGroups.equals(otherSpecies.getEggGroups());
    }

}
