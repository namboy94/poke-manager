package net.namibsun.pokemontracker.lib.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import net.namibsun.pokemontracker.lib.models.pokemonparts.Name;

import java.io.IOException;

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

        Document serebiiPage;
        try {
            serebiiPage = Jsoup.connect("http://www.serebii.net/pokedex-sm/" + pokedexNumber + "722.shtml").get();
            this.getOnlineInfo(serebiiPage);
        } catch (IOException e) {
            serebiiPage = null;
        }
    }

    /**
     * Fetches the online information for the Pokemon
     * @param serebiiPage: The serebii page document
     */
    private void getOnlineInfo(Document serebiiPage) {
        this.name = Name.fromSerebiiPage(serebiiPage);
    }

}
