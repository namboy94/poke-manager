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

package net.namibsun.pokemontracker.lib.models.pokemonparts;

import net.namibsun.pokemontracker.lib.webscraping.PokemonScraper;

/**
 * Class that keeps track of the abilities a Pokemon can have
 */
public class Ability {

    /**
     * The name of the first regular ability the Pokemon has
     */
    private String abilityOneName;

    /**
     * The description of the first regular ability the Pokemon has
     */
    private String abilityOneDescription;

    /**
     * The name of the second regular ability the Pokemon has
     */
    private String abilityTwoName;

    /**
     * The description of the second regular ability the Pokemon has
     */
    private String abilityTwoDescription;

    /**
     * The name of the Pokemon's hidden Ability
     */
    private String hiddenAbilityName;

    /**
     * The description of the Pokemon's hidden Ability
     */
    private String hiddenAbilityDescription;

    /**
     * Constructor for a Pokemon that only has a single Ability
     * @param abilityOneName:        The Ability Name
     * @param abilityOneDescription: The Ability Description
     */
    public Ability(String abilityOneName, String abilityOneDescription) {
        this(abilityOneName, abilityOneDescription, null, null, null, null);
    }

    /**
     * Constructor for a Pokemon that has no Hidden Ability
     * @param abilityOneName:        The first ability's name
     * @param abilityOneDescription: The first ability's description
     * @param abilityTwoName:        The second ability's name
     * @param abilityTwoDescription: The second ability's description
     */
    public Ability(String abilityOneName, String abilityOneDescription,
                   String abilityTwoName, String abilityTwoDescription) {
        this(abilityOneName, abilityOneDescription, abilityTwoName, abilityTwoDescription, null, null);
    }

    /**
     * Constructor for a Pokemon that has any amount of abilities. If a Pokemon has only one regular
     * ability but also a Hidden Ability, null values should be passed for the second regular Ability
     * @param abilityOneName:           The first ability's name
     * @param abilityOneDescription:    The first ability's description
     * @param abilityTwoName:           The second ability's name
     * @param abilityTwoDescription:    The second ability's description
     * @param hiddenAbilityName:        The hidden ability's name
     * @param hiddenAbilityDescription: The hidden ability's description
     */
    public Ability(String abilityOneName, String abilityOneDescription,
                   String abilityTwoName, String abilityTwoDescription,
                   String hiddenAbilityName, String hiddenAbilityDescription) {
        this.abilityOneName = abilityOneName;
        this.abilityOneDescription = abilityOneDescription;
        this.abilityTwoName = abilityTwoName;
        this.abilityTwoDescription = abilityTwoDescription;
        this.hiddenAbilityName = hiddenAbilityName;
        this.hiddenAbilityDescription = hiddenAbilityDescription;
    }

    /**
     * Checks if the Pokemon has a second regular ability
     * @return true if a second ability was set, false otherwise
     */
    public boolean hasSecondRegularAbility() {
        return this.abilityTwoName != null;
    }

    /**
     * Checks if the Pokemon has a hidden ability
     * @return true if the Pokemon has a hidden ability, false otherwise
     */
    public boolean hasHiddenAbility() {
        return this.hiddenAbilityName != null;
    }

    /**
     * @return the first regular ability of the Pokemon, as an array of the name and the description
     */
    public String[] getAbilityOne() {
        return new String[] { this.abilityOneName, this.abilityOneDescription };
    }

    /**
     * @return the second regular ability of the Pokemon, as an array of the name and the description,
     *         or null if the Pokemon has no second regular ability
     */
    public String[] getAbilityTwo() {
        if (this.abilityTwoName == null || this.abilityTwoDescription == null) {
            return null;
        }
        else {
            return new String[] { this.abilityTwoName, this.abilityTwoDescription };
        }
    }

    /**
     * @return the hidden ability of the Pokemon, as an array of the name and the description,
     *         or null if the Pokemon has no hidden ability
     */
    public String[] getHiddenAbility() {
        if (this.hiddenAbilityName == null || this.hiddenAbilityDescription == null) {
            return null;
        }
        else {
            return new String[] { this.hiddenAbilityName, this.hiddenAbilityDescription};
        }
    }

    /**
     * Generates an Ability object from a Web Scraper's information
     * @param scraper: The web scraper to use
     * @return         The generated Ability object
     */
    public static Ability fromWebParser(PokemonScraper scraper) {
        String[] regularAbilities = scraper.parseRegularAbilities();
        String[] hiddenAbilityParseResult = scraper.parseHiddenAbility();

        String[] abilityOne = new String[]{ regularAbilities[0], regularAbilities[1] };
        String[] abilityTwo;
        String[] hiddenAbility;

        if (regularAbilities.length > 2) {
            abilityTwo = new String[]{ regularAbilities[2], regularAbilities[3] };
        }
        else {
            abilityTwo = new String[]{ null, null };
        }

        if (hiddenAbilityParseResult == null) {
            hiddenAbility = new String[] { null, null };
        }
        else {
            hiddenAbility = new String[] { hiddenAbilityParseResult[0], hiddenAbilityParseResult[1] };
        }

        return new Ability(
                abilityOne[0], abilityOne[1],
                abilityTwo[0], abilityTwo[1],
                hiddenAbility[0], hiddenAbility[1]);
    }

    /**
     * Compares the Ability object with another Ability object
     * @param otherAbility: The Ability object to compare against
     * @return              true if the objects are equal, false otherwise
     */
    public boolean equals(Ability otherAbility) {

        boolean abilityOneSame =
                this.abilityOneName.equals(otherAbility.getAbilityOne()[0]) &&
                this.abilityOneDescription.equals(otherAbility.getAbilityOne()[1]);
        boolean abilityTwoSame;
        boolean hiddenAbilitySame;

        if (this.abilityTwoName == null) {
            abilityTwoSame = null == otherAbility.getAbilityTwo();
        }
        else {
            abilityTwoSame =
                    this.abilityTwoName.equals(otherAbility.getAbilityTwo()[0]) &&
                    this.abilityTwoDescription.equals(otherAbility.getAbilityTwo()[1]);
        }

        if (this.hiddenAbilityName == null) {
            hiddenAbilitySame = null == otherAbility.getHiddenAbility();
        }
        else {
            hiddenAbilitySame =
                    this.hiddenAbilityName.equals(otherAbility.getHiddenAbility()[0]) &&
                    this.hiddenAbilityDescription.equals(otherAbility.getHiddenAbility()[1]);
        }

        return abilityOneSame && abilityTwoSame && hiddenAbilitySame;
    }

}
