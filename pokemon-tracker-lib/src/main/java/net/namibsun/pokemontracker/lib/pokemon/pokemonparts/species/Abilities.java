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

package net.namibsun.pokemontracker.lib.pokemon.pokemonparts.species;

import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.Ability;
import net.namibsun.pokemontracker.lib.webscraping.PokemonScraper;

/**
 * Class that keeps track of the abilities a Pokemon can have
 */
public class Abilities {

    /**
     * The first regular ability the Pokemon has
     */
    private Ability abilityOne;

    /**
     * The second regular ability the Pokemon has
     */
    private Ability abilityTwo;

    /**
     * The Pokemon's hidden Ability
     */
    private Ability hiddenAbility;

    /**
     * Constructor for a Pokemon that has any amount of abilities. If a Pokemon has only one regular
     * ability but also a Hidden Abilities, null values should be passed for the second regular Abilities
     * @param abilityOne:    The first ability
     * @param abilityTwo:    The second ability
     * @param hiddenAbility: The hidden ability
     */
    public Abilities(Ability abilityOne, Ability abilityTwo, Ability hiddenAbility) {
        this.abilityOne = abilityOne;
        this.abilityTwo = abilityTwo;
        this.hiddenAbility = hiddenAbility;
    }

    /**
     * Checks if the Pokemon has a second regular ability
     * @return true if a second ability was set, false otherwise
     */
    public boolean hasSecondRegularAbility() {
        return this.abilityTwo != null;
    }

    /**
     * Checks if the Pokemon has a hidden ability
     * @return true if the Pokemon has a hidden ability, false otherwise
     */
    public boolean hasHiddenAbility() {
        return this.hiddenAbility != null;
    }

    /**
     * @return the first regular ability of the Pokemon
     */
    public Ability getAbilityOne() {
        return this.abilityOne;
    }

    /**
     * @return the second regular ability of the Pokemon, or null if the Pokemon does not have a second regular ability
     */
    public Ability getAbilityTwo() {
        return this.abilityTwo;
    }

    /**
     * @return the hidden ability of the Pokemon, or null if the Pokemon has no hidden ability
     */
    public Ability getHiddenAbility() {
        return this.hiddenAbility;
    }

    /**
     * Generates an Abilities object from a Web Scraper's information
     * @param scraper: The web scraper to use
     * @return         The generated Abilities object
     */
    public static Abilities fromWebParser(PokemonScraper scraper) {
        String[] regularAbilities = scraper.parseRegularAbilities();
        String[] hiddenAbilityParseResult = scraper.parseHiddenAbility();

        Ability abilityOne = new Ability(regularAbilities[0], regularAbilities[1], false);
        Ability abilityTwo = null;
        Ability hiddenAbility = null;

        if (regularAbilities.length > 2) {
            abilityTwo = new Ability(regularAbilities[2], regularAbilities[3], false);
        }

        if (hiddenAbilityParseResult != null) {
            hiddenAbility = new Ability(hiddenAbilityParseResult[0], hiddenAbilityParseResult[1], true);
        }

        return new Abilities(abilityOne, abilityTwo, hiddenAbility);
    }

    /**
     * Compares the Abilities object with another Abilities object
     * @param otherAbilities: The Abilities object to compare against
     * @return                true if the objects are equal, false otherwise
     */
    public boolean equals(Abilities otherAbilities) {

        boolean firstEqual = otherAbilities.getAbilityOne().equals(this.abilityOne);
        boolean secondEqual;
        boolean hiddenEqual;

        try {
            secondEqual = otherAbilities.getAbilityTwo().equals(this.abilityTwo);
        } catch (NullPointerException e) {
            secondEqual = otherAbilities.getAbilityTwo() == null && this.abilityTwo == null;
        }

        try {
            hiddenEqual = otherAbilities.getHiddenAbility().equals(this.hiddenAbility);
        } catch (NullPointerException e) {
            hiddenEqual = otherAbilities.getHiddenAbility() == null && this.hiddenAbility == null;
        }

        return firstEqual && secondEqual && hiddenEqual;
    }

}
