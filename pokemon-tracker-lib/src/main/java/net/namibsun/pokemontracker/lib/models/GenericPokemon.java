package net.namibsun.pokemontracker.lib.models;

import org.jsoup.nodes.Document;
import net.namibsun.pokemontracker.lib.models.pokemonparts.Name;

import java.io.IOException;

/**
 * Class that models a Generic Pokemon of a specific species
 */
public class GenericPokemon {

    private int pokedexNumber;
    private Name name;

    public GenericPokemon(int pokedexNumber) {
        this.pokedexNumber = pokedexNumber;

        Document serebiiPage;
        try {
            serebiiPage = Jsoup.connect("http://www.serebii.net/pokedex-sm/" + pokedexNumber + "722.shtml").get();
        } catch (IOException e) {
            serebiiPage = null;
        }

        this.name = Name.fromSerebiiPage()
    }

}
