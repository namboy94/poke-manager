package net.namibsun.pokemontracker.lib.models;

import java.io.IOException;

import net.namibsun.pokemontracker.lib.models.pokemonparts.GenderRatio;
import net.namibsun.pokemontracker.lib.models.pokemonparts.Type;
import net.namibsun.pokemontracker.lib.serebii.SerebiiParser;
import net.namibsun.pokemontracker.lib.models.pokemonparts.Name;

/**
 * Class that models a Generic Pokemon of a specific species
 */
public class GenericPokemon {

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
     * Creates a new Pokemon
     * @param pokedexNumber: The national Pokedex Number to identify the Pokemon
     */
    public GenericPokemon(int pokedexNumber) {

        this.pokedexNumber = pokedexNumber;

        try {
            SerebiiParser parser = new SerebiiParser(pokedexNumber);
            this.getOnlineInfo(parser);
        } catch (IOException e) {
            System.out.println("Null - TODO");
        }
    }

    private void getOnlineInfo(SerebiiParser parser) {
        this.name = Name.fromSerebiiPage(parser);
        this.genderRatio = GenderRatio.fromSerebiiPage(parser);
        this.type = Type.fromSerebiiPage(parser);
    }

}
