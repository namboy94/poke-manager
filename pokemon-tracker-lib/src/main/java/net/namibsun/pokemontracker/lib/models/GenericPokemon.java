package net.namibsun.pokemontracker.lib.models;

import java.io.IOException;
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
     * Creates a new Pokemon
     * @param pokedexNumber: The national Pokedex Number to identify the Pokemon
     */
    public GenericPokemon(int pokedexNumber) {

        this.pokedexNumber = pokedexNumber;

        try {
            SerebiiParser parser = new SerebiiParser(pokedexNumber);
            this.getOnlineInfo(parser);
        } catch (IOException e) {
            System.out.println("Null");
        }
    }

    private void getOnlineInfo(SerebiiParser parser) {
        this.name = Name.fromSerebiiPage(parser);
    }

}
