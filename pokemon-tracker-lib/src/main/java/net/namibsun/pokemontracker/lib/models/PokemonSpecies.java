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

package net.namibsun.pokemontracker.lib.models;

import java.io.IOException;
import net.namibsun.pokemontracker.lib.models.pokemonparts.*;
import net.namibsun.pokemontracker.lib.webscraping.PokemonScraper;
import net.namibsun.pokemontracker.lib.webscraping.serebii.SerebiiParser;

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
    private Ability ability;

    /**
     * Creates a new Pokemon
     * @param pokedexNumber: The national Pokedex Number to identify the Pokemon
     */
    public PokemonSpecies(int pokedexNumber) {

        this.pokedexNumber = pokedexNumber;

        try {
            SerebiiParser parser = new SerebiiParser(pokedexNumber);
            this.getOnlineInfo(parser);
        } catch (IOException e) {
            System.out.println("Null - TODO");
        }
    }

    /**
     * Retrieves the Pokemon's information from the internet
     * @param parser: The Online parser to be used
     */
    private void getOnlineInfo(PokemonScraper parser) {
        this.name = Name.fromWebParser(parser);
        this.genderRatio = GenderRatio.fromWebParser(parser);
        this.type = Type.fromWebParser(parser);
        this.description = SpeciesDescription.fromWebParser(parser);
        this.rates = Rates.fromWebParser(parser);
        this.evYield = EffortValueYield.fromWebParser(parser);
        this.ability = Ability.fromWebParser(parser);
    }

}
