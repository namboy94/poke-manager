package net.namibsun.pokemontracker.lib.models.pokemonparts;

import net.namibsun.pokemontracker.lib.serebii.SerebiiConstants;
import net.namibsun.pokemontracker.lib.serebii.SerebiiParser;

/**
 * Class that models the type of a Pokemon
 */
public class Type {

    /**
     * The Pokemon's primary type
     */
    private SerebiiConstants.PokemonType primaryType;

    /**
     * The Pokemon's secondary type
     */
    private SerebiiConstants.PokemonType secondaryType;

    /**
     * Single-type Constructor directly from PokemonType enum
     * @param primaryType: The primary Pokemon type, as a PokemonType enum
     */
    public Type(SerebiiConstants.PokemonType primaryType) {
        this(primaryType, null);
    }

    /**
     * Single-type Constructor for a String type value
     * @param primaryType: The primary Pokemon type, as a String
     */
    public Type(String primaryType) {
        this(SerebiiConstants.PokemonType.valueOf(primaryType));
    }

    /**
     * Multi-type Constructor directly from PokemonType enums
     * @param primaryType: The primary Pokemon type, as a PokemonType enum
     * @param secondaryType: The secondary Pokemon Type, as a PokemonType enum
     */
    public Type(SerebiiConstants.PokemonType primaryType, SerebiiConstants.PokemonType secondaryType) {
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
    }

    /**
     * Multi-type Constructor using String values
     * @param primaryType: The primary Pokemon type, as a String
     * @param secondaryType: The secondary Pokemon Type, as a String
     */
    public Type(String primaryType, String secondaryType) {
        this(SerebiiConstants.PokemonType.valueOf(primaryType), SerebiiConstants.PokemonType.valueOf(secondaryType));
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
        return this.primaryType.name();
    }

    /**
     * @return The Secondary Pokemon Type, as a String (null if no second type exists)
     */
    public String getSecondaryTypeAsString() {
        if (this.isDualTyped()) {
            return this.secondaryType.name();
        }
        else {
            return null;
        }
    }

    /**
     * @return The Primary Pokemon Type, as a PokemonTypes enum value
     */
    public SerebiiConstants.PokemonType getPrimaryType() {
        return this.primaryType;
    }

    /**
     * @return The Secondary Pokemon Type, as a PokemonTypes enum value (null if no second type exists)
     */
    public SerebiiConstants.PokemonType getSecondaryType() {
        return this.secondaryType;
    }

    /**
     * Creates a new type for a Pokemon parsed from serebii.net
     * @param parser: The Serebii Parser to use
     * @return        The generated Type object
     */
    public static Type fromSerebiiPage(SerebiiParser parser) {
        String[] types = parser.parseTypes();
        if (types.length == 1) {
            return new Type(types[0]);
        }
        else {
            return new Type(types[0], types[1]);
        }
    }
}
