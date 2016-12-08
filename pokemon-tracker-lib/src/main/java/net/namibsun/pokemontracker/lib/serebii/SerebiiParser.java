package net.namibsun.pokemontracker.lib.serebii;

import org.jsoup.Jsoup;
import java.util.HashMap;
import java.io.IOException;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * A Parser for serebii.net
 */
public class SerebiiParser {

    private Document serebiiPage;

    /**
     * Generates a parser from a pokedex number
     * @param pokedexNumber: The pokedex number to use
     * @throws IOException   If there was a connection error
     */
    public SerebiiParser(int pokedexNumber) throws IOException {
        this.fetchPage(pokedexNumber);
    }

    /**
     * Generates a parser from an English Pokemon name
     * @param pokemonName: The Pokemon's name
     * @throws IOException If there was a connection error
     */
    public SerebiiParser(String pokemonName) throws IOException {

        Elements entries = Jsoup.connect(SerebiiConstants.POKEDEX_ROOT_URL).get().select("option");

        for (Element entry: entries) {
            if (entry.text().contains(pokemonName)){
                String pokedexNumber = entry.text().split(" ")[0];
                try {
                    this.fetchPage(Integer.parseInt(pokedexNumber));
                    break;
                } catch (NumberFormatException ignored) {
                }
            }
        }
    }

    /**
     * Fetches the HTML document for a Pokemon with the specified Pokedex number
     * @param pokedexNumber: The Pokemon's Pokedex number
     * @throws IOException   If a connection error occurs
     */
    private void fetchPage(int pokedexNumber) throws IOException {

        if (pokedexNumber < SerebiiConstants.MAX_POKEMON_NUMBER) {
            throw new IOException("Maximum number of Pokemon exceeded");
        }

        String formattedPokedexNumber = "" + pokedexNumber;
        while (formattedPokedexNumber.length() < SerebiiConstants.POKEDEX_NUMBER_LENGTH) {
            formattedPokedexNumber = "0" + formattedPokedexNumber;
        }
        String url = SerebiiConstants.POKEDEX_ROOT_URL + formattedPokedexNumber + ".shtml";
        this.serebiiPage = Jsoup.connect(url).get();
    }

    /**
     * Fetches all names for the Pokemon and turns it into a Hashmap
     * @return The Hashmap containing the names with the particular language as the keys
     */
    public HashMap<String, String> parsePokemonName(){

        HashMap<String, String> names = new HashMap<>();
        String dexEntry = this.serebiiPage.select("table.dextable").get(0).text();

        names.put(SerebiiConstants.ENGLISH_KEY, dexEntry.split("Type")[1].split("Japan:")[0]);
        names.put(SerebiiConstants.JAPANESE_KEY, dexEntry.split("Japan: ")[1].split("French:")[0]);
        names.put(SerebiiConstants.GERMAN_KEY, dexEntry.split("German: ")[1].split("Korean:")[0]);
        names.put(SerebiiConstants.FRENCH_KEY, dexEntry.split("French: ")[1].split("German:")[0]);
        names.put(SerebiiConstants.KOREAN_KEY, dexEntry.split("Korean: ")[1].split("National:")[0]);

        return names;
    }

}
