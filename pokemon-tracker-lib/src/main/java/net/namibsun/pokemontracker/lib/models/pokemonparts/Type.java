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

import net.namibsun.pokemontracker.lib.models.enums.PokemonTypes;
import net.namibsun.pokemontracker.lib.webscraping.PokemonScraper;

/**
 * Class that models the type of a Pokemon
 */
public class Type {

    /**
     * The Pokemon's primary type
     */
    private PokemonTypes primaryType;

    /**
     * The Pokemon's secondary type
     */
    private PokemonTypes secondaryType;

    /**
     * Single-type Constructor directly from PokemonType enum
     * @param primaryType: The primary Pokemon type, as a PokemonType enum
     */
    public Type(PokemonTypes primaryType) {
        this(primaryType, null);
    }

    /**
     * Single-type Constructor for a String type value
     * @param primaryType: The primary Pokemon type, as a String
     */
    public Type(String primaryType) {
        this(PokemonTypes.valueOf(primaryType));
    }

    /**
     * Multi-type Constructor directly from PokemonType enums
     * @param primaryType: The primary Pokemon type, as a PokemonType enum
     * @param secondaryType: The secondary Pokemon Type, as a PokemonType enum
     */
    public Type(PokemonTypes primaryType, PokemonTypes secondaryType) {
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
    }

    /**
     * Multi-type Constructor using String values
     * @param primaryType: The primary Pokemon type, as a String
     * @param secondaryType: The secondary Pokemon Type, as a String
     */
    public Type(String primaryType, String secondaryType) {
        this(PokemonTypes.valueOf(primaryType), PokemonTypes.valueOf(secondaryType));
    }

    /**
     * Checks if the Pokemon is dual typed.
     * @return true if the Pokemon has a secondary type, false otherwise
     */
    public boolean isDualTyped() {
        return this.secondaryType != null;
    }

    /**
     * @return The Primary Pokemon Type, as a String
     */
    public String getPrimaryTypeAsString() {
        return this.formatTypeString(this.primaryType.name());
    }

    /**
     * @return The Secondary Pokemon Type, as a String (null if no second type exists)
     */
    public String getSecondaryTypeAsString() {
        if (this.isDualTyped()) {
            return this.formatTypeString(this.secondaryType.name());
        }
        else {
            return null;
        }
    }

    /**
     * Formats the Type String from e.g. GRASS -> Grass
     * @param typeString: The string to format
     * @return            The formatted string
     */
    private String formatTypeString(String typeString) {
        return Character.toUpperCase(typeString.charAt(0)) + typeString.toLowerCase().substring(1);
    }

    /**
     * @return The Primary Pokemon Type, as a PokemonTypes enum value
     */
    public PokemonTypes getPrimaryType() {
        return this.primaryType;
    }

    /**
     * @return The Secondary Pokemon Type, as a PokemonTypes enum value (null if no second type exists)
     */
    public PokemonTypes getSecondaryType() {
        return this.secondaryType;
    }

    /**
     * Creates a new type for a Pokemon parsed from a Web Page
     * @param parser: The Web Parser to use
     * @return        The generated Type object
     */
    public static Type fromWebParser(PokemonScraper parser) {
        String[] types = parser.parseTypes();
        if (types.length == 1) {
            return new Type(types[0]);
        }
        else {
            return new Type(types[0], types[1]);
        }
    }
}
