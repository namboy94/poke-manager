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

import net.namibsun.pokemontracker.lib.pokemon.enums.Languages;
import org.jsoup.Jsoup;
import java.util.HashMap;
import java.io.IOException;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import net.namibsun.pokemontracker.lib.webscraping.PokemonScraper;
import net.namibsun.pokemontracker.lib.webscraping.PokemonConstants;
import net.namibsun.pokemontracker.lib.pokemon.enums.PokemonStatTypes;

/**
 * A Pokemon Parser for serebii.net
 */
public class SerebiiParser implements PokemonScraper {

    // A couple of Pokemon are a bit non-standard, which requires hard-coded hacks to make them work
    // These are:
    // Deoxys - EV Yield due to multiple Forms
    // Minior - Catch Rate, since Minior has different catch rates in each form
    // Basculin - Abilities/Hidden Abilities, due to form differences and Hidden Abilities marked as 'Dream World Abilities'
    // Shaymin - Regular Abilities, because of its Sky Forme
    // Kyurem, Zygarde, Hoopa - Weight/Height due to their unique forms
    // Kyurem, Zygarde - Abilities, due to their forms

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
    private Elements dexItems;
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
                this.fetchPage(Integer.parseInt(pokedexNumber));
                return;
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
        this.dexItems = this.serebiiPage.select("table.dexitem");
        this.toolTabs = this.serebiiPage.select("table.tooltab");
    }

    /**
     * @return The serebii.net URL used by this parser
     */
    @Override
    public String getUrl() {
        return this.url;
    }

    /**
     * Fetches all names for the Pokemon and turns it into a Hashmap
     * @return The Hashmap containing the names with the particular language as the keys
     */
    @Override
    public HashMap<String, String> parsePokemonName(){

        HashMap<String, String> names = new HashMap<>();
        String dexEntry = this.dexTables.get(0).text();

        names.put(
                Languages.ENGLISH.name().toLowerCase(),
                dexEntry.split("Type")[1].split("Japan:")[0].trim());
        names.put(
                Languages.JAPANESE.name().toLowerCase(),
                dexEntry.split("Japan: ")[1].split("French:")[0].trim());
        names.put(
                Languages.GERMAN.name().toLowerCase(),
                dexEntry.split("German: ")[1].split("Korean:")[0].trim());
        names.put(
                Languages.FRENCH.name().toLowerCase(),
                dexEntry.split("French: ")[1].split("German:")[0].trim());
        names.put(
                Languages.KOREAN.name().toLowerCase(),
                dexEntry.split("Korean: ")[1].split("National:")[0].trim());

        return names;
    }

    /**
     * Parses the Gender of the Pokemon.
     * @return an array of two double values, representing the male and female ratio respectively.
     *         If the Pokemon is neutrally gendered, null is returned.
     */
    @Override
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
    @Override
    public String[] parseTypes() {

        Element typeTab = this.toolTabs.get(1);
        Elements types = typeTab.select("a");
        if (types.size() == 0) {
            typeTab = this.toolTabs.get(2);
            types = typeTab.select("tr").get(1).select("td").get(0).select("a");
        }

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
    @Override
    public double[] parseWeight() {

        // Pokemon-specific hacks
        if (this.url.endsWith("646.shtml")) { return new double[] { 325.0, 716.5 }; } // Kyurem
        if (this.url.endsWith("718.shtml")) { return new double[] { 284.6, 627.4 }; } // Zygarde
        if (this.url.endsWith("720.shtml")) { return new double[] { 9.0, 19.8 }; }    // Hoopa

        String info;
        String metric;
        String imperial;

        try { // Normal Pokemon
            info = this.toolTabs.get(3).select("tr").get(1).text();
            metric = info.split("kg")[0].split(" ")[3].trim();
            imperial = info.split("lbs")[0].split(" ")[2].trim();

        } catch (ArrayIndexOutOfBoundsException e) { //Pokemon with Multiple Forms

            info = this.toolTabs.get(4).select("tr").get(1).text();
            metric = info.split("kg")[0].split(" ")[3].trim();
            imperial = info.split("lbs")[0].split(" ")[2].trim();

        }

        try {
            return new double[]{
                    Double.parseDouble(metric),
                    Double.parseDouble(imperial)
            };
        } catch (NumberFormatException e) {  // Pokemon with Alolan form

            info = this.toolTabs.get(4).select("tr").get(3).text();
            metric = info.split("kg")[0].split(" ")[1].trim();
            imperial = info.split("lbs")[0].split(" ")[0].trim();

            return new double[]{
                    Double.parseDouble(metric),
                    Double.parseDouble(imperial)
            };
        }
    }

    /**
     * Parses the height of the Pokemon
     * @return an array of doubles, with the first element being in m, the second in feet('), inches(")
     */
    @Override
    public double[] parseHeight() {

        // Pokemon-specific hacks
        if (this.url.endsWith("646.shtml")) { return new double[] { 3.0, 9.10 }; }
        if (this.url.endsWith("718.shtml")) { return new double[] { 5.0, 16.05 }; }
        if (this.url.endsWith("720.shtml")) { return new double[] { 0.5, 1.08 }; }

        String info = this.toolTabs.get(3).select("tr").get(1).text();

        if (!info.contains("m") || !info.contains("\u0094") || !info.contains("\u0092")) {
            info = this.toolTabs.get(4).select("tr").get(1).text();
        }

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
    @Override
    public String parseClassification() {
        return this.toolTabs.get(2).select("tr").get(1).text();
    }

    /**
     * Parses the Pokemon's capture rate
     * @return The Capture Rate
     */
    @Override
    public int parseCaptureRate() {

        if (this.url.endsWith("774.shtml")) { // Special case for Minior, who has two different catch rates
            return 30;
        }

        String[] parts = this.dexTables.get(0).text().split(" ");
        return Integer.parseInt(parts[parts.length - 2]);
    }

    /**
     * Parses the Pokemon's required amount of steps to hatch
     * @return The Pokemon's Base Egg Step amount
     */
    @Override
    public int parseBaseEggSteps() {
        String[] parts = this.dexTables.get(0).text().split(" ");
        return Integer.parseInt(parts[parts.length - 1].replace(",", ""));
    }

    /**
     * Parses the Pokemon's base happiness value
     * @return the Pokemon's base happiness
     */
    @Override
    public int parseBaseHappiness() {
        Elements tableElements = this.dexTables.get(1).select("td");
        return Integer.parseInt(tableElements.get(tableElements.size() - 3).text());
    }

    /**
     * Parses the Pokemon's amount of XP required to reach Level 100
     * @return the Pokemon's Experience Points at level 100
     */
    @Override
    public int parseExperienceGrowthPoints() {
        Elements tableElements = this.dexTables.get(1).select("td");
        String experienceGrowth = tableElements.get(tableElements.size() - 4).text();
        return Integer.parseInt(experienceGrowth.split(" ")[0].replace(",", ""));
    }

    /**
     * Parses the Pokemon's experience growth speed description
     * @return The experience growth speed description
     */
    @Override
    public String parseExperienceGrowthDescription() {
        Elements tableElements = this.dexTables.get(1).select("td");
        String[] experienceParts = tableElements.get(tableElements.size() - 4).text().split(" ");

        String description = "";
        for (int i = 2; i < experienceParts.length; i++) {
            description += experienceParts[i] + " ";
        }
        return description.trim();
    }

    /**
     * Parses the Effort Value yield for a specified Stat type
     * @param statType: The stat type to search for the EV yield
     * @return          The EV yield
     */
    @Override
    public int parseEffortValueYield(PokemonStatTypes statType) {

        if (this.url.endsWith("386.shtml")) {
            // Special case for Deoxys
            if (statType == PokemonStatTypes.HP
                    || statType == PokemonStatTypes.DEF
                    || statType == PokemonStatTypes.SDEF) {
                return 0;
            }
            else {
                return 1;
            }
        }

        String statTypeIdentifier = "";
        //noinspection IfCanBeSwitch  Because of Java7 compatibility
        if (statType == PokemonStatTypes.HP)        { statTypeIdentifier = "HP"; }
        else if (statType == PokemonStatTypes.ATK)  { statTypeIdentifier = "Attack"; }
        else if (statType == PokemonStatTypes.DEF)  { statTypeIdentifier = "Defense"; }
        else if (statType == PokemonStatTypes.SATK) { statTypeIdentifier = "Sp. Attack"; }
        else if (statType == PokemonStatTypes.SDEF) { statTypeIdentifier = "Sp. Defense"; }
        else if (statType == PokemonStatTypes.SPD)  { statTypeIdentifier = "Speed"; }

        Elements tableElements = this.dexTables.get(1).select("td");
        String evYieldText = tableElements.get(tableElements.size() - 2).text();

        if (evYieldText.contains(statTypeIdentifier) && !evYieldText.contains("Sp. " + statTypeIdentifier)) {

            String[] evYieldParts = evYieldText.split(Pattern.quote(statTypeIdentifier))[0].trim().split(" ");
            String evYield = evYieldParts[evYieldParts.length - 1];
            return Integer.parseInt(evYield);

        }
        else {
            return 0;
        }
    }

    /**
     * Parses the regular abilities of a Pokemon
     * @return an array of the form:
     *         [Abilities 1 name, Abilities 1 description, Abilities 2 name, Abilities 2 description]
     *         or, if the Pokemon has no second regular abilty, like this:
     *         [Abilities 1 name, Abilities 2 description]
     */
    public String[] parseRegularAbilities() {

        if (this.url.endsWith("550.shtml")) {  // Basculin-specific Hack
            return new String[] { "Adaptability", "Increases the Same Type Attack Bonus from *1.5 to *2." };
        }
        if (this.url.endsWith("492.shtml")) {  // Shaymin-specific Hack
            return new String[] { "Natural Cure", "The Pokémon’s status (BURN, PARALYZE, SLEEP, POISON, FREEZE)" +
                    " is healed when withdrawn from battle." };
        }
        if (this.url.endsWith("646.shtml")) {  //Kyurem-specific Hack
            return new String[] { "Pressure", "When this Pokémon is hit by a move, the opponent’s PP lowers by 2 " +
                    "rather than 1. Opponents in S.O.S. Battles are more likely to call for help."};
        }
        if (this.url.endsWith("718.shtml")) {
            return new String[] {"Aura Break", "The effects of Aura Abilities are reversed to lower the power of " +
                    "affected moves.",
                    "Power Construct", "Other Cells gather to aid when its HP becomes half or less. " +
                    "Then the Pokémon changes its form to Complete Forme."};
        }

        Elements tableElements = this.dexTables.get(1).select("td");

        String[] abilities = tableElements.get(0).text().split("Abilities: ")[1].split(" - ");
        String abilityDescriptions = tableElements.get(1).text();

        String primaryAbility = abilities[0].trim();
        String primaryAbilityDescription;
        try {
            primaryAbilityDescription = abilityDescriptions.split(primaryAbility + ": ")[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            primaryAbilityDescription = abilityDescriptions.split(primaryAbility + " : ")[1].trim();
        }

        if (abilities.length > 1) {
            String secondaryAbility = abilities[1].trim();

            if (secondaryAbility.contains("(Hidden Ability)") || secondaryAbility.contains("(Hidden)")) {
                primaryAbilityDescription = primaryAbilityDescription.split(" Hidden Ability")[0].trim();
            }
            else {
                primaryAbilityDescription = primaryAbilityDescription.split(" " + secondaryAbility)[0].trim();
                String secondaryAbilityDescription = abilityDescriptions.split(secondaryAbility + ":")[1].trim();
                if (secondaryAbilityDescription.contains("Hidden Ability")) {
                    secondaryAbilityDescription = secondaryAbilityDescription.split(" Hidden Ability")[0].trim();
                }
                return new String[] {
                        primaryAbility, primaryAbilityDescription, secondaryAbility, secondaryAbilityDescription
                };
            }
        }
        return new String[] { primaryAbility, primaryAbilityDescription };
    }

    /**
     * Parses the hidden ability of a Pokemon
     * @return an array of the form [Abilities Name, Description] or null if the Pokemon has no hidden ability
     */
    public String[] parseHiddenAbility() {

        if (this.url.endsWith("550.shtml")) {  // Basculin-specific Hack
            return new String[] {
                    "Mold Breaker", "The Pokémon's moves are not affected by foe’s abilities during battle."};
        }

        Elements tableElements = this.dexTables.get(1).select("td");
        String abilities = tableElements.get(0).text().split("Abilities: ")[1];
        String abilityDescriptions = tableElements.get(1).text();

        if (abilities.contains("(Hidden Ability)") || abilities.contains("(Hidden)")) {
            String[] abilityNames = abilities.split(" \\(")[0].split(" - ");
            String abilityName = abilityNames[abilityNames.length - 1].trim();
            String abilityDescription;
            try {
                abilityDescription = abilityDescriptions.split(abilityName + ": ")[1].trim();
            }catch (ArrayIndexOutOfBoundsException e) {
                abilityDescription = abilityDescriptions.split(abilityName + " : ")[1].trim();
            }

            return new String[]{abilityName, abilityDescription};
        }
        else {
            return null;
        }
    }

    /**
     * Parses the base stats of a Pokemon
     * @return these stats in an ordered Array in the following order:
     *           HP, Attack, Defense, Special Attack, Special Defense, Speed
     */
    public int[] parseBaseStats() {

        Elements stats = null;
        for (Element table: dexTables) {
            if (table.text().startsWith("Stats")) {
                stats = table.select("tr").get(2).select("td");
                break;
            }
        }

        assert(stats != null);

        return new int[]{
                Integer.parseInt(stats.get(1).text()),
                Integer.parseInt(stats.get(2).text()),
                Integer.parseInt(stats.get(3).text()),
                Integer.parseInt(stats.get(4).text()),
                Integer.parseInt(stats.get(5).text()),
                Integer.parseInt(stats.get(6).text())
        };
    }

    /**
     * Parses the egg groups of a Pokemon
     * @return the egg groups as an array of strings of length 3 in the following order:
     *         the primary egg group, the secondary egg group, an indicator if the Pokemon is genderless
     *             (genderless = 'true', has gender = 'false')
     */
    public String[] parseEggGroups() {

        Elements groups = this.dexItems.select("td");
        String[] returnArray = new String[] {null, null, null};
        returnArray[2] = "" + (this.parseGenderRatio() == null);  //Check if genderless -> Can only breed with Ditto

        if (groups.size() < 2) {
            returnArray[0] = "UNDISCOVERED";
            return returnArray;
        }
        else {

            while (groups.size() > 4 || groups.get(0).text().contains(" - ")) {
                groups.remove(0);
            }

            returnArray[0] = this.convertIntoEggGroupName(groups.get(1).text());
            if (groups.size() == 4) {
                returnArray[1] = this.convertIntoEggGroupName(groups.get(3).text());
            }

            return returnArray;
        }
    }

    /**
     * Changes the Egg Group String value into something that can be converted to an EggGroupTypes enum
     * @param name: The name as parsed from serebii.net
     * @return      The enum-ready name
     */
    private String convertIntoEggGroupName(String name) {

        if (name.toLowerCase().equals("human-like")) {
            name = name.replace("-", "");
        }
        else if (name.contains(" ")) {
            name = name.replace(" ", "");
        }

        return name.toUpperCase();
    }
}