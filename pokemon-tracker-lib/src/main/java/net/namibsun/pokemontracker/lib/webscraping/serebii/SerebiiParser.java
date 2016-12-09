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

package net.namibsun.pokemontracker.lib.webscraping.serebii;

import net.namibsun.pokemontracker.lib.webscraping.PokemonConstants;
import net.namibsun.pokemontracker.lib.webscraping.PokemonScraper;
import org.jsoup.Jsoup;
import java.util.HashMap;
import java.io.IOException;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * A Pokemon Parser for serebii.net
 */
public class SerebiiParser implements PokemonScraper {

    /**
     * The JSoup document of the Serebii Page
     */
    private Document serebiiPage;

    /**
     * The Pokemon's URL on serebii.net
     */
    private String url;

    /**
     * Elements used by multiple parser methods
     */
    private Elements dexTables;
    private Elements toolTabs;

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
                    return;
                } catch (NumberFormatException ignored) {
                }
            }
        }

        throw new IOException("Pokemon not found");

    }

    /**
     * Fetches the HTML document for a Pokemon with the specified Pokedex number
     * @param pokedexNumber: The Pokemon's Pokedex number
     * @throws IOException   If a connection error occurs
     */
    private void fetchPage(int pokedexNumber) throws IOException {

        if (pokedexNumber > PokemonConstants.MAX_POKEMON_NUMBER) {
            throw new IOException("Maximum number of Pokemon exceeded");
        }

        String formattedPokedexNumber = "" + pokedexNumber;
        while (formattedPokedexNumber.length() < SerebiiConstants.POKEDEX_NUMBER_LENGTH) {
            formattedPokedexNumber = "0" + formattedPokedexNumber;
        }
        this.url = SerebiiConstants.POKEDEX_ROOT_URL + formattedPokedexNumber + ".shtml";
        this.serebiiPage = Jsoup.connect(this.url).get();
        this.dexTables = this.serebiiPage.select("table.dextable");
        this.toolTabs = this.serebiiPage.select("table.tooltab");
    }

    /**
     * @return The serebii.net URL used by this parser
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Fetches all names for the Pokemon and turns it into a Hashmap
     * @return The Hashmap containing the names with the particular language as the keys
     */
    public HashMap<String, String> parsePokemonName(){

        HashMap<String, String> names = new HashMap<>();
        String dexEntry = this.dexTables.get(0).text();

        names.put(PokemonConstants.ENGLISH_KEY, dexEntry.split("Type")[1].split("Japan:")[0].trim());
        names.put(PokemonConstants.JAPANESE_KEY, dexEntry.split("Japan: ")[1].split("French:")[0].trim());
        names.put(PokemonConstants.GERMAN_KEY, dexEntry.split("German: ")[1].split("Korean:")[0].trim());
        names.put(PokemonConstants.FRENCH_KEY, dexEntry.split("French: ")[1].split("German:")[0].trim());
        names.put(PokemonConstants.KOREAN_KEY, dexEntry.split("Korean: ")[1].split("National:")[0].trim());

        return names;
    }

    /**
     * Parses the Gender of the Pokemon.
     * @return an array of two double values, representing the male and female ratio respectively.
     *         If the Pokemon is neutrally gendered, null is returned.
     */
    public double[] parseGenderRatio() {
        String dexEntry = this.dexTables.get(0).text();

        if (dexEntry.contains("is Genderless")) {
            return null;
        }

        String maleRatio = dexEntry.split("Male ♂: ")[1].split("%")[0].trim();
        String femaleRatio = dexEntry.split("Female ♀: ")[1].split("%")[0].trim();

        return new double[] {
                Double.parseDouble(maleRatio),
                Double.parseDouble(femaleRatio)
        };
    }

    /**
     * Parses the types of a Pokemon
     * @return an array of types for the Pokemon, as capitalized Strings
     */
    public String[] parseTypes() {
        Element typeTab = this.toolTabs.get(1);
        Elements types = typeTab.select("a");

        if (types.size() == 1) {
            return new String[]{
                    types.get(0).toString().split("/")[2].split(".shtml")[0].toUpperCase()
            };
        }
        else {
            return new String[]{
                    types.get(0).toString().split("/")[2].split(".shtml")[0].toUpperCase(),
                    types.get(1).toString().split("/")[2].split(".shtml")[0].toUpperCase()
            };
        }
    }

    /**
     * Parse the weight of the Pokemon
     * @return an array of doubles, with the first element being in kg, the second in lbs
     */
    public double[] parseWeight() {
        String info = this.toolTabs.get(3).select("tr").get(1).text();
        String metricWeight = info.split("kg")[0].split(" ")[3].trim();
        String imperialWeight = info.split("lbs")[0].split(" ")[2].trim();

        return new double[]{
                Double.parseDouble(metricWeight),
                Double.parseDouble(imperialWeight)
        };
    }

    /**
     * Parses the height of the Pokemon
     * @return an array of doubles, with the first element being in m, the second in feet('), inches(")
     */
    public double[] parseHeight() {

        String info = this.toolTabs.get(3).select("tr").get(1).text();
        String metricHeight = info.split("m")[0].split(" ")[1].trim();
        String imperialFeet = info.split("\u0092")[0];
        String imperialInches = info.split("\u0092")[1].split("\u0094")[0];
        String imperialHeight = imperialFeet + "." + imperialInches;

        return new double[]{
                Double.parseDouble(metricHeight),
                Double.parseDouble(imperialHeight)
        };
    }

    /**
     * Parses the species classification of the Pokemon
     * @return the classification of the pokemon
     */
    public String parseClassification() {
        return this.toolTabs.get(2).select("tr").get(1).text();
    }

}
